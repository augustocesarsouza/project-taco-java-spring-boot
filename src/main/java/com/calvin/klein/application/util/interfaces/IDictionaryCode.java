package com.calvin.klein.application.util.interfaces;

public interface IDictionaryCode {
    Integer getKeyDictionary(String key);

    void removeKeyDictionary(String key);

    void putKeyValueDictionary(String key, Integer value);
}
