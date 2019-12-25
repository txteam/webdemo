/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月19日
 * <修改描述:>
 */
package com.tx.local.captcha;

/**
 * 验证码常量<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CaptchaConstants {
    
    /** 登录请求中验证码_key */
    String REQUEST_KAPTCHA_CODE_KEY = "kaptchaCode";
    
    /** 登录会话中验证码_key */
    String SESSION_KAPTCHA_CODE_KEY = "session_kaptcha_code";
    
    /** "背景图片路径"属性名称 */
    String KAPTCHA_BACKGROUND_IMAGE_PATH = "kaptcha.background.imagePath";
}
