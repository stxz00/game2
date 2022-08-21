package com.games.game2.controller;

import com.games.game2.service.CommonService;
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

    @Autowired
    CommonService commonService;

    @GetMapping("/")
    public String home() throws Exception {
        log.info(testService.selectOne().toString());
        return "index.html";
    }

    @GetMapping("/mailsend")
    public String send() throws Exception {
        //구글 보안 업데이트 이후 SSL 없이 사용 불가
        commonService.mailSend();
        return "index.html";
    }
}
