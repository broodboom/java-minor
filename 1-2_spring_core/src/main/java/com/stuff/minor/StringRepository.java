package com.stuff.minor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StringRepository {
    private final Map<String, Integer> dict = new HashMap<String, Integer>();

    public Integer GetCount(String key) {
        if (!Exists(key)) {
            return -1;
        }

        return dict.get(key);
    }

    public Boolean Exists(String key) {
        return dict.containsKey(key);
    }

    public void Add(String key, Integer value) {
        dict.put(key, value);
    }
}