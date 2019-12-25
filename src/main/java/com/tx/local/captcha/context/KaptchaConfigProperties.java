/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月18日
 * <修改描述:>
 */
package com.tx.local.captcha.context;

/**
 * 验证码配置属性<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class KaptchaConfigProperties {
    
    /** 生成图片宽度 */
    private int imageWidth = 80;
    
    /** 生成图片高度 */
    private int imageHeight = 34;
    
    /** 验证码字符集 */
    private String charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /** 验证码长度 */
    private int charLength = 4;
    
    /** 验证码留白 */
    private int charSpace = 2;
    
    /** 验证码颜色 */
    private String fontColor = "white";
    
    /** 验证码字母大小 */
    private int fontSize = 22;
    
    /** 背景图片实现 */
    private String backgroundImpl;
    
    /** 背景图片路径 */
    private String backgroundImagePath = "classpath:static/kaptcha";
    
    /**
     * @return 返回 imageWidth
     */
    public int getImageWidth() {
        return imageWidth;
    }
    
    /**
     * @param 对imageWidth进行赋值
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }
    
    /**
     * @return 返回 imageHeight
     */
    public int getImageHeight() {
        return imageHeight;
    }
    
    /**
     * @param 对imageHeight进行赋值
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }
    
    /**
     * @return 返回 charString
     */
    public String getCharString() {
        return charString;
    }
    
    /**
     * @param 对charString进行赋值
     */
    public void setCharString(String charString) {
        this.charString = charString;
    }
    
    /**
     * @return 返回 charLength
     */
    public int getCharLength() {
        return charLength;
    }
    
    /**
     * @param 对charLength进行赋值
     */
    public void setCharLength(int charLength) {
        this.charLength = charLength;
    }
    
    /**
     * @return 返回 charSpace
     */
    public int getCharSpace() {
        return charSpace;
    }
    
    /**
     * @param 对charSpace进行赋值
     */
    public void setCharSpace(int charSpace) {
        this.charSpace = charSpace;
    }
    
    /**
     * @return 返回 fontColor
     */
    public String getFontColor() {
        return fontColor;
    }
    
    /**
     * @param 对fontColor进行赋值
     */
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
    
    /**
     * @return 返回 fontSize
     */
    public int getFontSize() {
        return fontSize;
    }
    
    /**
     * @param 对fontSize进行赋值
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    
    /**
     * @return 返回 backgroundImagePath
     */
    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }
    
    /**
     * @param 对backgroundImagePath进行赋值
     */
    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    /**
     * @return 返回 backgroundImpl
     */
    public String getBackgroundImpl() {
        return backgroundImpl;
    }

    /**
     * @param 对backgroundImpl进行赋值
     */
    public void setBackgroundImpl(String backgroundImpl) {
        this.backgroundImpl = backgroundImpl;
    }
}
