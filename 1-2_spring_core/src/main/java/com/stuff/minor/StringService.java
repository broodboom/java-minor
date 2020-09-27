package com.stuff.minor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StringService {

    @Autowired
    StringRepository stringRepository;

    @Autowired
    public StringService() {
    }

    public String FlipString(final String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public Integer CountWords(final String input) {
        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            return 0;
        }
        
        if (stringRepository.Exists(input)) {
            return stringRepository.GetCount(input);
        }

        Integer count = trimmed.split("\\s+").length;

        stringRepository.Add(input, count);

        return count;
    }

    public String CapString(final String input) {
        return input.toUpperCase();
    }
}