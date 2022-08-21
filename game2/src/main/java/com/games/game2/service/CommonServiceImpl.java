package com.games.game2.service;

import com.games.game2.common.utils.MailUtil;
import com.games.game2.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService{
    @Value("${spring.mail.username}")
    String FROM_ADDRESS;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void mailSend() throws Exception {
        log.info("mailSend : " + "메일");
//        if(StringUtils.isSpecial(param.getCponId(), 8,8) ||
//                StringUtils.isNotNumeric(param.getValidStrDt(), 8, 8) ||
//                StringUtils.isNotNumeric(param.getValidEndDt(), 8, 8))
//            return;

//            log.info(param.getEmail().toString());
//            log.info(param.getCponId());

        //테스트
        List<String> list = new ArrayList();
        list.add("stxz@naver.com");

        MailUtil mailUtil = new MailUtil(javaMailSender);

        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");

        mailUtil.setFrom(FROM_ADDRESS); // 보내는이
        mailUtil.setSubject("테스트 쿠폰 전송"); //제목

        //String htmlContent = "<p>" + contents + "</p> <img src='cid:sample-img'>"; // HTML Layout
        //mailUtil.setText(htmlContent, true);
        //mailUtil.setInline("sample-img", "static/t/img/thanq-logo.jpg");// 이미지 삽입
        //mailUtil.send();
        File file = ResourceUtils.getFile("classpath:static/t/mail/mail.html");
        Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
        String html = doc.html();
        html = html.replaceAll("coupon", "쿠폰");
        html = html.replaceAll("dateStart", newDtFormat.format(new Date()));
        html = html.replaceAll("dateEnd", newDtFormat.format(new Date()));
        //String htmlContent2 = "<p>" + contents + "</p>";
        mailUtil.setText(html, true);
        for(String email : list) {
            if(StringUtils.isEmail(email)){
                mailUtil.setTo(email);
                try {
                    mailUtil.send();
                }catch (Exception e){
                    log.info("쿠폰 이메일 전송 문제 발생");
                    e.printStackTrace();
                }
            }else{
                log.info("쿠폰 이메일 전송 불가 이메일 : " + email);
            }
        }
        log.info("쿠폰 전송 완료");
    }
}
