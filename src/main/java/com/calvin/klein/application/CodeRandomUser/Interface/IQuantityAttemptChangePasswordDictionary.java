package com.calvin.klein.application.CodeRandomUser.Interface;

import java.time.Duration;

public interface IQuantityAttemptChangePasswordDictionary {
    void Add(String key, int valueCode);
    void AddTimer(String key, int valueCode);
    boolean Container(String key);
    KeyInfo getKey(String key);
    void remove(String key);
    String formatDuration(Duration duration);

    class KeyInfo {
        private final int value;
        private final Duration timeLeft;

        public KeyInfo(int value, Duration timeLeft) {
            this.value = value;
            this.timeLeft = timeLeft;
        }

        public int getValue() {
            return value;
        }

        public Duration getTimeLeft() {
            return timeLeft;
        }
    }
}
