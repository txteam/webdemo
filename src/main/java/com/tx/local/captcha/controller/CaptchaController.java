/*
 * Copyright 2005-2017 cqtianxin.com. All rights reserved.
 * Support: http://www.cqtianxin.com
 * License: http://www.cqtianxin.com/license
 */
package com.tx.local.captcha.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.tx.local.captcha.CaptchaConstants;

/**
 * Controller - 验证码
 * 
 * @author cqtianxin Team
 * @version 5.0
 */
@Controller
@RequestMapping("/kaptcha")
public class CaptchaController {
    
    @Resource(name = "login.kaptchaProducer")
    private Producer kaptchaProducer;
    
    /**
     * 登录验证码<br/>
     * <功能详细描述>
     * @param captchaId
     * @param session
     * @param response
     * @return [参数说明]
     * 
     * @return BufferedImage [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @GetMapping(path = "/login", produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage login(String captchaId, HttpSession session,
            HttpServletResponse response) {
        response.addHeader("poweredBy", "cqtianxin");
        
        // Set to expire far in the past.
        response.setDateHeader(HttpHeaders.EXPIRES, 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader(HttpHeaders.CACHE_CONTROL,
                "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader(HttpHeaders.CACHE_CONTROL,
                "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        
        //生成随机验证码
        String loginCaptchaCode = kaptchaProducer.createText();
        
        //System.out.println(session.getAttribute(CaptchaConstants.SESSION_LOGIN_CAPTCHA_CODE_KEY));
        //将验证码写入会话中
        session.setAttribute(CaptchaConstants.SESSION_KAPTCHA_CODE_KEY,
                loginCaptchaCode);
        //System.out.println(session.getAttribute(CaptchaConstants.SESSION_LOGIN_CAPTCHA_CODE_KEY));
        
        //根据验证码创建图片
        BufferedImage image = kaptchaProducer.createImage(loginCaptchaCode);
        
        return image;
    }
    
}