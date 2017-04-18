/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.bussiness;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import mydictionary.dto.Dictionary;

/**
 *
 * @author NghiaTruongNgoc
 */
public class FavoriteWordHandler {
    public static DefaultListModel getModel(Dictionary dic)
    {
        DefaultListModel dlModel = new DefaultListModel();
        ArrayList words = new ArrayList(dic.getKeySet());
        for (int i = 0; i < words.size(); i++)
        {
            dlModel.addElement(words.get(i));
        }
        
        return dlModel;
    }

    public static void addWordtoList(String word, JList jList) {
        if (!((DefaultListModel)jList.getModel()).contains(word))
            ((DefaultListModel)jList.getModel()).addElement(word);
    }
    
    public static void clearWords(JList jList)
    {
        DefaultListModel dlModel = new DefaultListModel();
        jList.setModel(dlModel);
    }

    public static void loadWordstoList(Dictionary words, JList<String> jFavList) {
       jFavList.setModel(FavoriteWordHandler.getModel(words));
    }
    
    public static void loadWordstoList(DefaultListModel words, JList<String> jFavList) {
       jFavList.setModel(words);
    }
}
