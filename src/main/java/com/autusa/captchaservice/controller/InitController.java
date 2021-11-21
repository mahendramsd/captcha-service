package com.autusa.captchaservice.controller;

import cn.apiclub.captcha.Captcha;
import com.autusa.captchaservice.captcha.CaptchaUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseStatus(HttpStatus.OK)
public class InitController {

    private final CaptchaUtil captchaUtil;

    public InitController(CaptchaUtil captchaUtil) {
        this.captchaUtil = captchaUtil;
    }

    @GetMapping("/build")
    public ResponseEntity<Map<String,String>> createCaptcha() {
        Captcha captcha = captchaUtil.createCaptcha(400,50);
        System.out.println(captcha.getAnswer());
        System.out.println(captchaUtil.encodeCaptcha(captcha).toString());
        Map<String,String> map = new HashMap<>();
        map.put(captcha.getAnswer(),captchaUtil.encodeCaptcha(captcha).toString());
        return ResponseEntity.ok(map);
    }

}
