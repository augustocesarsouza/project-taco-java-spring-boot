package com.calvin.klein.application.CodeRandomUser.Interface;

public interface ICodeRandomDictionary {
    void Add(String key, int valueCode);
    boolean Container(String key, int valueCode);
    boolean Container(String key);
    void Remove(String key);
}
