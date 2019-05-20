/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月18日
 * <修改描述:>
 */
package com.tx.local.kaptcha.context;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码配置<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
@PropertySource(value = { "classpath:context/kaptcha.properties" })
public class KaptchaConfiguration {
    
    
    
    /**
     * 注册期间使用的验证码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CaptchaConfigProperties [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("register.kaptchaConfig")
    @ConfigurationProperties(prefix = "tx.kaptcha.register")
    public KaptchaConfigProperties registKaptchaConfigProperties() {
        KaptchaConfigProperties props = new KaptchaConfigProperties();
        
        return props;
    }
    
    /**
     * 登录验证码属性<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CaptchaConfigProperties [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("login.kaptchaConfig")
    @Primary
    @ConfigurationProperties(prefix = "tx.kaptcha.login")
    public KaptchaConfigProperties loginKaptchaConfigProperties() {
        KaptchaConfigProperties props = new KaptchaConfigProperties();
        return props;
    }
    
    /**
     * 默认的验证码生产者配置<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Producer [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Primary
    @Bean("login.kaptchaProducer")
    public Producer kaptchaProducer() {
        KaptchaConfigProperties configs = loginKaptchaConfigProperties();
        
        DefaultKaptcha captchaProducer = buildProducer(configs);
        
        return captchaProducer;
    }

    
    /** 
     * 构建验证码生成器<br/>
     * <功能详细描述>
     * @param configs
     * @return [参数说明]
     * 
     * @return DefaultKaptcha [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private DefaultKaptcha buildProducer(KaptchaConfigProperties configs) {
        DefaultKaptcha kaptchaProducer = new DefaultKaptcha();
        
        Properties props = new Properties();
        //图片边框，合法值：yes , no
        props.put("kaptcha.border", "no");
        //kaptcha.border.color 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.
        //kaptcha.border.thickness 边框厚度，合法值：>0
        props.put(Constants.KAPTCHA_IMAGE_WIDTH, configs.getImageWidth());
        props.put(Constants.KAPTCHA_IMAGE_HEIGHT, configs.getImageHeight());
        //文本集合，验证码值从此集合中获取
        props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,
                configs.getCharString());
        //验证码长度
        props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,
                String.valueOf(configs.getCharLength()));
        //文字间隔
        props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE,
                String.valueOf(configs.getCharSpace()));
        props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR,
                configs.getFontColor());
        props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,
                String.valueOf(configs.getFontSize()));
        if(!StringUtils.isEmpty(configs.getBackgroundImpl())){
            props.put(Constants.KAPTCHA_BACKGROUND_IMPL,
                    configs.getBackgroundImpl());
        }
        
        
        //干扰实现类
        props.put(Constants.KAPTCHA_NOISE_IMPL,
                "com.google.code.kaptcha.impl.NoNoise");
        props.put(Constants.KAPTCHA_OBSCURIFICATOR_IMPL,
                "com.google.code.kaptcha.impl.WaterRipple");
        
        Config config = new Config(props) {
            /**
             * @return
             */
            @Override
            public BackgroundProducer getBackgroundImpl() {
                String paramName = Constants.KAPTCHA_BACKGROUND_IMPL;
                String paramValue = getProperties().getProperty(paramName);
                if ("com.tx.local.captcha.config.CaptchaBackground"
                        .equals(paramValue)
                        && !StringUtils
                                .isEmpty(configs.getBackgroundImagePath())) {
                    KaptchaBackground cb = KaptchaBackground
                            .getInstance(configs.getBackgroundImagePath());
                    if(cb.getBackgroundImagesCacheSize() > 0){
                        //如果背景图片数量>0
                        return cb;
                    }else{
                        //覆盖配置
                        getProperties().put(paramName, "");
                    }
                }
                //否则使用默认实现
                BackgroundProducer backgroundProducer = super.getBackgroundImpl();
                return backgroundProducer;
            }
            
        };
        kaptchaProducer.setConfig(config);
        return kaptchaProducer;
    }
}
