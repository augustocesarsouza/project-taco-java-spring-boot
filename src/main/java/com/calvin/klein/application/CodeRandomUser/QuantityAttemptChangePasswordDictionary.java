package com.calvin.klein.application.CodeRandomUser;

import com.calvin.klein.application.CodeRandomUser.Interface.IQuantityAttemptChangePasswordDictionary;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class QuantityAttemptChangePasswordDictionary implements IQuantityAttemptChangePasswordDictionary {
    private final Map<String, Integer> dictionaryNumber = new ConcurrentHashMap<>();
    private final Map<String, ScheduledFuture<?>> timers = new ConcurrentHashMap<>();
    private final Map<String, Instant> expirationTimes = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final int timeToExpireSeconds = 10;

    @Override
    public void Add(String key, int valueCode) {
        dictionaryNumber.put(key, valueCode);
    }

    @Override
    public void AddTimer(String key, int valueCode) {
        dictionaryNumber.put(key, valueCode);
        Instant expirationTime = Instant.now().plusSeconds(timeToExpireSeconds);
        expirationTimes.put(key, expirationTime);
        startRemoveTimer(key);
    }

    @Override
    public boolean Container(String key) {
        return dictionaryNumber.containsKey(key);
    }

    @Override
    public KeyInfo getKey(String key) {
        int value = dictionaryNumber.getOrDefault(key, 0);
        Duration timeLeft = null;

        Instant expirationTime = expirationTimes.get(key);
        if (expirationTime != null) {
            Duration duration = Duration.between(Instant.now(), expirationTime);
            if (!duration.isNegative()) {
                timeLeft = duration;
            } else {
                timeLeft = Duration.ZERO;
            }
        }

        return new KeyInfo(value, timeLeft);
    }

    @Override
    public void remove(String key) {
        dictionaryNumber.remove(key);
        expirationTimes.remove(key);

        ScheduledFuture<?> timer = timers.remove(key);
        if (timer != null) {
            timer.cancel(false);
        }
    }

    @Override
    public String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        long nanos = duration.getNano(); // 0 a 999_999_999

        // Convertemos nanos (9 dígitos) para 7 dígitos como no C#
        long fractional = nanos / 100; // reduz para 7 dígitos

        return String.format("%02d:%02d:%02d.%07d", hours, minutes, seconds, fractional);
    }

    private void startRemoveTimer(String key) {
        ScheduledFuture<?> scheduled = scheduler.schedule(() -> remove(key), timeToExpireSeconds, TimeUnit.SECONDS);
        timers.put(key, scheduled);
    }
}
