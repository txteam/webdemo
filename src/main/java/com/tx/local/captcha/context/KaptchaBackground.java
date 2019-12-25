/*
 * Copyright 2005-2017 cqtianxin.com. All rights reserved.
 * Support: http://www.cqtianxin.com
 * License: http://www.cqtianxin.com/license
 */
package com.tx.local.captcha.context;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

/**
 * Captcha - 验证码背景
 * 
 * @author cqtianxin Team
 * @version 5.0
 */
public class KaptchaBackground extends Configurable
        implements BackgroundProducer {
    
    /** 日志记录器 */
    private Logger logger = LoggerFactory.getLogger(KaptchaBackground.class);
    
    /** 正则表达式解析 */
    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
    
    /** 图片路径对应实例的映射 */
    private static final Map<String, KaptchaBackground> path2InstanceMap = new HashMap<String, KaptchaBackground>();
    
    /** 背景图片缓存 */
    private final List<BufferedImage> backgroundImagesCache = new CopyOnWriteArrayList<>();
    
    /**
     * 根据验证码背景图片路径获取对应的实例<br/>
     * <功能详细描述>
     * @param backgroundImagePath
     * @return [参数说明]
     * 
     * @return CaptchaBackground [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static KaptchaBackground getInstance(String backgroundImagePath) {
        if (path2InstanceMap.containsKey(backgroundImagePath)) {
            return path2InstanceMap.get(backgroundImagePath);
        }
        KaptchaBackground cb = new KaptchaBackground(backgroundImagePath);
        path2InstanceMap.put(backgroundImagePath, cb);
        return cb;
    }
    
    /** <默认构造函数> */
    private KaptchaBackground(String backgroundImagePath) {
        super();
        initBackgroundImages(backgroundImagePath);
    }
    
    /**
     * 获取背景图集合<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<BufferedImage> [返回类型说明]
     * @throws IOException 
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void initBackgroundImages(String backgroundImagePath) {
        //获取背景图
        try {
            Resource[] resources = RESOURCE_PATTERN_RESOLVER
                    .getResources(backgroundImagePath);
            for (Resource resTemp : resources) {
                try (InputStream inputStream = resTemp.getInputStream()) {
                    BufferedImage backgroundImage = ImageIO.read(inputStream);
                    if (backgroundImage != null) {
                        this.backgroundImagesCache.add(backgroundImage);
                    }
                }
            }
        } catch (IOException e) {
            logger.warn(
                    "加载验证码背景图片失败: backgroundImagePath:" + backgroundImagePath,
                    e);
        }
    }
    
    /**
     * 添加背景
     * @param baseImage 基本图片
     * @return 目标图片
     */
    @Override
    public BufferedImage addBackground(BufferedImage baseImage) {
        List<BufferedImage> backgroundImages = this.backgroundImagesCache;
        
        Graphics2D graphics2D = null;
        try {
            int width = baseImage.getWidth();
            int height = baseImage.getHeight();
            BufferedImage destImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            graphics2D = destImage.createGraphics();
            
            int random = RandomUtils.nextInt(0, backgroundImages.size());
            BufferedImage backgroundImage = backgroundImages.get(random);
            graphics2D.drawImage(backgroundImage, 0, 0, width, height, null);
            graphics2D.drawImage(baseImage, 0, 0, null);
            return destImage;
        } finally {
            if (graphics2D != null) {
                graphics2D.dispose();
            }
        }
    }
    
    /**
     * 获取背景图片缓存大小<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int getBackgroundImagesCacheSize() {
        return this.backgroundImagesCache.size();
    }
}