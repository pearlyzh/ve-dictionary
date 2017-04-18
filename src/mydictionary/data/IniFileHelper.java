/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import mydictionary.bussiness.DateHandler;
import mydictionary.dto.LookedupDateList;
import mydictionary.dto.WordsFrequencyInDate;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

/**
 *
 * @author NghiaTruongNgoc
 */
public class IniFileHelper {
    public static final String INI_PATH_EV = "./data/statitisticEV.ini";
    public static final String INI_PATH_VE = "./data/statitisticVE.ini";
    
    public static LookedupDateList getLookedupDateList(String pathIni)
    {
        try
        {  
            LookedupDateList lookedupDateList = new LookedupDateList();
            Ini iniFile = new Ini(new FileReader(pathIni));
            
            for (String sectionName: iniFile.keySet()) 
            {
                
                WordsFrequencyInDate temp = new WordsFrequencyInDate();
                temp.setDate(DateHandler.stringToDate(sectionName));
                
                Section section = iniFile.get(sectionName);
                
                for (String optionKey : section.keySet()) 
                {
                    temp.setWordFreq(optionKey, Integer.parseInt(section.get(optionKey)));
                }
                
                lookedupDateList.add(temp);
        }
            
            return lookedupDateList;
        }
        catch (IOException e)
        {
            return new LookedupDateList();
        }
    }
    
    public static void saveLookedupDateList(LookedupDateList lookedupDateList,
                                            String pathIni)
    {
        try
        {  
            if (lookedupDateList == null)
                return;
            
            File f = new File(pathIni);
            f.getParentFile().mkdirs();
            f.createNewFile();
            
            Ini iniFile = new Ini(new File(pathIni));
            for (int i = 0; i < lookedupDateList.getSize(); i++)
            {
                ArrayList<String> words = new ArrayList(lookedupDateList.getAt(i).getWordSet());
                String strDate = DateHandler.dateToString(lookedupDateList.getAt(i).getDate());
                
                for (int j = 0; j < words.size(); j++)
                {
                    iniFile.put(strDate, words.get(j), 
                            lookedupDateList.getAt(i).getFreq(words.get(j)));
                }
            }
            
            iniFile.store();
        }
        catch (IOException e)
        {
            return;
        }
    }
}
