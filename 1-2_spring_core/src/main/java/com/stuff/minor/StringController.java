package com.stuff.minor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StringController {

    @Autowired 
    StringService stringService;

    @Autowired
    Environment env;

    @GetMapping("/api/flip")
    @ResponseBody
    public String FlipString(@RequestParam final String input) {

      if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
          return stringService.FlipString(input);
      } else {
          return stringService.CapString(input);
      }
    }

    @GetMapping("/api/count")
    @ResponseBody
    public Integer CountWords(@RequestParam final String input) {
        return stringService.CountWords(input);
    }
}