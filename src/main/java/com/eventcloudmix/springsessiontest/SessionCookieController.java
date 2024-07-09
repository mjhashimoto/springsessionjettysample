package com.eventcloudmix.springsessiontest;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
public class SessionCookieController {

    public static final String HELLO_IN_JA = "こんにちは";//15byte
    public static final String HELLO_IN_EN = "hellohellohello";//15byte

    @GetMapping("/")
    public String index(HttpSession session, Model model){
        log.info("Session id:{}", session.getId());
        model.addAttribute("html", HELLO_IN_JA);
        return "index";
    }

    @GetMapping("/cookie")
    public String cookie(HttpSession session, Model model){
        log.info("Session id:{}", session.getId());
        // < 32768byte doc
        String html = IntStream.range(0, 2179)
                .mapToObj(i -> HELLO_IN_JA)
                .collect(Collectors.joining());

        model.addAttribute("html", html);
        return "index";
    }

    @GetMapping("/nocookie")
    public String nocookie(HttpSession session, Model model){
        log.info("Session id:{}", session.getId());
        // > 32768byte doc
        String html = IntStream.range(0, 2180)
                .mapToObj(i -> HELLO_IN_JA)
                .collect(Collectors.joining());

        model.addAttribute("html", html);
        return "index";
    }
}
