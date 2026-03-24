package com.calvin.klein.application.util;

import com.calvin.klein.application.util.interfaces.IDictionaryCode;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.Hashtable;

@Component
public class DictionaryCode implements IDictionaryCode {
    private static Dictionary<String, Integer> dictionaryCode;

    public DictionaryCode() {
        dictionaryCode = new Hashtable<>();
    }

    public Integer getKeyDictionary(String key){
        return dictionaryCode.get(key);
    }

    public void removeKeyDictionary(String key){
        dictionaryCode.remove(key);
    }

    public void putKeyValueDictionary(String key, Integer value){
        dictionaryCode.put(key, value);
    }
}
