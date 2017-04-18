/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.dto;

import java.util.HashMap;
import java.util.Set;
/**
 *
 * @author NghiaTruongNgoc
 */
public class Dictionary 
{

    public void removeWord(String wordtoDelete) {
        hashMap.remove(wordtoDelete);
    }
    public enum DictionaryType
    {
        EV,
        VE,
    }
    
    HashMap<String, String> hashMap;
    
    public Dictionary()
    {
        hashMap = new HashMap<>();
    }
    
    public void addRecord(String key, String value)
    {
        hashMap.put(key, value);
    }
    
    public String getValue(String key)
    {
        return hashMap.get(key);
    }
    
    public int getNumWords()
    {
        return hashMap.size();
    }
    
    public Set getKeySet()
    {
        return hashMap.keySet();
    }
    
    public boolean isEmpty()
    {
        return hashMap.isEmpty();
    }
}