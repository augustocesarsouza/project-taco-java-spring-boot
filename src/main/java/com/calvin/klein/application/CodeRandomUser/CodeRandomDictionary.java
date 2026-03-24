package com.calvin.klein.application.CodeRandomUser;

import com.calvin.klein.application.CodeRandomUser.Interface.ICodeRandomDictionary;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CodeRandomDictionary implements ICodeRandomDictionary {
    private static final ConcurrentHashMap<String, Integer> dictionaryCode = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Timer> timers = new ConcurrentHashMap<>();

    @Override
    public void Add(String key, int valueCode) {
        dictionaryCode.put(key, valueCode);
        startRemoveTimer(key);
    }

    @Override
    public boolean Container(String key, int valueCode) {
        return dictionaryCode.containsKey(key) && dictionaryCode.get(key) == valueCode;
    }

    @Override
    public boolean Container(String key) {
        return dictionaryCode.containsKey(key);
    }

    public void Remove(String key) {
        if (dictionaryCode.remove(key) != null) {
            Timer timer = timers.remove(key);
            if (timer != null) {
                timer.cancel(); // equivalente a Dispose
            }
        }
    }

    private void startRemoveTimer(String key){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Remove(key); // executa após 60 minutos
            }
        }, 60 * 60 * 1000); // 60 minutos em milissegundos
        timers.put(key, timer);
    }
}
