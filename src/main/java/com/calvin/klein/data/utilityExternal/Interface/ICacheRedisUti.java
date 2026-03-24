package com.calvin.klein.data.utilityExternal.Interface;

import java.util.concurrent.TimeUnit;

public interface ICacheRedisUti {
    String getString(String key);
    void setString(String key, String value, long timeout, TimeUnit unit);
    void remove(String key);
}
