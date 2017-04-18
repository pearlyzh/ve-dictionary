/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.jdesktop.swingx.calendar.DateUtils;

/**
 *
 * @author NghiaTruongNgoc
 */
public class LookedupDateList {
    private ArrayList<WordsFrequencyInDate> dateList;
    
    public LookedupDateList()
    {
        dateList = new ArrayList<>();
    }
    
    public WordsFrequencyInDate getAt(int index)
    {
        return dateList.get(index);
    }
    
    public void add(WordsFrequencyInDate wordsFrequency)
    {
        dateList.add(wordsFrequency);
    }
    
    public int getSize()
    {
        return dateList.size();
    }
    
    public void increaseFreqbyOne(Date currentDate, String word)
    {
        boolean found = false; //found current date in list
        for (int i = 0; i < dateList.size(); i++)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (sdf.format(currentDate).equals(sdf.format(dateList.get(i).getDate())))
            {
                found= true;
                Integer lastFreq = dateList.get(i).getFreq(word);

                if (lastFreq == null) //havenot existed
                {
                    dateList.get(i).setWordFreq(word, 1);
                } else //have existed, now we add its frequencies together
                {
                    Integer currentFreq = lastFreq + 1;
                    //then we put it into hashmap again with currentFreq
                    dateList.get(i).setWordFreq(word, currentFreq);
                }
            }
        }
        
        if (!found)
        {
            WordsFrequencyInDate wfid = new WordsFrequencyInDate();
            wfid.setDate(currentDate);
            wfid.setWordFreq(word, 1);
            dateList.add(wfid);
        }
    }
}
