/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.data;

import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import mydictionary.dto.Dictionary;
import mydictionary.dto.Dictionary.DictionaryType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author NghiaTruongNgoc
 */
public class XmlDataHelper {

    public static final String PATH_EV = "./data/Anh_Viet.xml";
    public static final String PATH_VE = "./data/Viet_Anh.xml";
    public static final String PATH_FAV_EV = "./data/Favorites_EV.xml";
    public static final String PATH_FAV_VE = "./data/Favorites_VE.xml";

    public static Dictionary loadResources(String pathXMLFile, DefaultListModel words) {
        try {
            Dictionary dic = new Dictionary();
            File fXmlFile = new File(pathXMLFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("record");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    dic.addRecord(
                            element.getElementsByTagName("word").item(0).getTextContent(),
                            element.getElementsByTagName("meaning").item(0).getTextContent());
                    
                    if (words != null)
                        words.addElement(element.getElementsByTagName("word").item(0).getTextContent());
                }
            }

            return dic;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static Dictionary loadFavorites(String pathXmlFile) 
    {
        Dictionary dic = loadResources(pathXmlFile, null);
        if (dic == null)
            dic = new Dictionary();
        return dic;
    }

    public static void saveFavorites(Dictionary favWords, DictionaryType type) {
        if (favWords.isEmpty())
            return;
        
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            Element rootElement = doc.createElement("dictionary");
            doc.appendChild(rootElement);
            
            ArrayList words = new ArrayList(favWords.getKeySet());

            for (int i = 0; i < favWords.getNumWords(); i++) {
                Element record = doc.createElement("record");
                Element word = doc.createElement("word");
                word.appendChild(doc.createTextNode(words.get(i).toString()));
                Element meaning = doc.createElement("meaning");
                meaning.appendChild(
                        doc.createTextNode(
                                favWords.getValue(words.get(i).toString())));
                
                record.appendChild(word);
                record.appendChild(meaning);
                
                rootElement.appendChild(record);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result;
            
            if (type == DictionaryType.EV)
            {
                result = new StreamResult(new File(PATH_FAV_EV));
            }
            else
                result = new StreamResult(new File(PATH_FAV_VE));
            
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
