package com.eventcloudmix.springsessiontest;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SessionCookieController {

    public static final String HELLO_IN_JA = "こんにちは"; //15byte

    @GetMapping("/cookie")
    public String cookie(HttpSession session, Model model){
        // < 32768byte doc
        String html = IntStream.range(0, 2184)
                .mapToObj(i -> HELLO_IN_JA)
                .collect(Collectors.joining());
        model.addAttribute("html", html);
        return "index";
    }

    @GetMapping("/nocookie")
    public String nocookie(HttpSession session, Model model){
        // > 32768byte doc
        String html = IntStream.range(0, 2185)
                .mapToObj(i -> HELLO_IN_JA)
                .collect(Collectors.joining());
        model.addAttribute("html", html);
        return "index";
    }
}
