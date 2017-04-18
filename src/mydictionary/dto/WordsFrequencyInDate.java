/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author NghiaTruongNgoc
 */
public class WordsFrequencyInDate {
    private Date date;
    private HashMap<String, Integer> wordFreq;
    
    public WordsFrequencyInDate()
    {
        date = new Date();
        wordFreq = new HashMap<>();
    }
    
    
    public Integer getFreq(String word)
    {
        return wordFreq.get(word);
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public void setDate(Date _date)
    {
        date = _date;
    }
    
    public void setWordFreq(String word, Integer freq)
    {
        wordFreq.put(word, freq);
    }
    
    public Set getWordSet()
    {
        return wordFreq.keySet();
    }
    
    public boolean isEmpty()
    {
        return wordFreq.isEmpty();
    }
}
