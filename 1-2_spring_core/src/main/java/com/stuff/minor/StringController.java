package com.stuff.minor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StringController {

    @Autowired 
    StringService stringService;

    @GetMapping("/api/flip")
    @ResponseBody
    public String FlipString(@RequestParam String input) {
        return stringService.FlipString(input);
    }

    @GetMapping("/api/count")
    @ResponseBody
    public Integer CountWords(@RequestParam String input) {
        return stringService.CountWords(input);
    }
}