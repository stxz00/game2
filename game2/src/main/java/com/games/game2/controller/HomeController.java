package com.games.game2.controller;

import com.games.game2.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    TestService testService;

    @GetMapping("/")
    public String home() {
        log.info(testService.selectOne().toString());
        return "index.html";
    }
}
