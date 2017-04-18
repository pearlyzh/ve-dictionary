/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.bussiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import mydictionary.dto.LookedupDateList;
import mydictionary.dto.WordsFrequencyInDate;

/**
 *
 * @author NghiaTruongNgoc
 */
public class StatitisticHandler {
    public static int exceptionChecker = 0;
    
    public static HashMap<String, String> parseInforDateList(LookedupDateList dateList, 
            String strDateStart, String strDateEnd)
    {
        HashMap<String,String> infor = new HashMap<>();
        Date dateStart;
        Date dateEnd;
        
        try{
            dateStart = DateHandler.stringToDate(strDateStart);
            dateEnd = DateHandler.stringToDate(strDateEnd);
        }
        catch (Exception e)
        {
            exceptionChecker = 1;   //wrong format date
            return null;
        }
        
        HashMap <String, Integer> wordsFreq = new HashMap<>();
        
        for (int i = 0; i < dateList.getSize(); i++)
        {
            Date date = dateList.getAt(i).getDate();
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            if ((date.after(dateStart) || sdf.format(date).equals(sdf.format(dateStart))) &&
                    (date.before(dateEnd) || sdf.format(date).equals(sdf.format(dateEnd))))
            {
                WordsFrequencyInDate wfid = dateList.getAt(i);
                ArrayList<String> words = new ArrayList<>(wfid.getWordSet());
                
                for (int j = 0; j < words.size(); j++)
                {
                    //trick to check if a key (word) existed?
                    Integer lastFreq = wordsFreq.get(words.get(j));
                    
                    if (lastFreq == null) //havenot existed
                    {
                        wordsFreq.put(words.get(j), wfid.getFreq(words.get(j)));
                    }
                    else //have existed, now we add its frequencies together
                    {
                        Integer currentFreq = lastFreq + wfid.getFreq(words.get(j));
                        //then we put it into hashmap again with currentFreq
                        wordsFreq.put(words.get(j), currentFreq);
                    }
                }
            }
        }
        
        if (wordsFreq.isEmpty())
        {
            exceptionChecker = 2; //no word found
            return null;
        }
        
        ArrayList<String> words = new ArrayList(wordsFreq.keySet());
        
        for (int i = 0; i < wordsFreq.size(); i++)
        {
            infor.put(words.get(i), wordsFreq.get(words.get(i)).toString());
        }
        
        return infor;
    }
    
    
    public static DefaultTableModel hashMaptoTable(HashMap<String, String> map)
    {
        DefaultTableModel model = new DefaultTableModel(); 
        model.addColumn("Từ");
        model.addColumn("Số lần tra");

        ArrayList<String> words = new ArrayList<>(map.keySet());
        for (int i = 0; i < words.size(); i++)
        {
            model.addRow(new Object[]{words.get(i), map.get(words.get(i))});
        }
        
      return model;
    }
}
