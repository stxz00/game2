package com.games.game2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class MailController {

    @PostMapping
    public String mail() {
        return "";
    }
}
