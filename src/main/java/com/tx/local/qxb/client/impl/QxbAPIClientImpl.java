/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月11日
 * <修改描述:>
 */
package com.tx.local.qxb.client.impl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.tx.core.exceptions.SILException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.qxb.client.QxbAPIClient;
import com.tx.local.qxb.client.QxbRemoteResult;
import com.tx.local.qxb.model.QxbEnterpriseInfo;

/**
 * 企信宝接口实现<br/>
 * //jackson: https://blog.csdn.net/blwinner/article/details/98532847
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class QxbAPIClientImpl implements QxbAPIClient, InitializingBean {
    
    /** resttemplate构造器 */
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    
    /** resttemplate句柄 */
    private RestTemplate restTemplate;
    
    /** 企信宝appKey */
    private String appKey = "09184446-1efb-4509-a092-43d9a225fe63";
    
    /** 企信宝secretKey */
    private String secretKey = "d4d760ce-c51e-4635-a0c5-1d73dbe3376b";
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.restTemplate = restTemplateBuilder.build();
        AssertUtils.notNull(this.restTemplate, "restTemplate is null.");
        
        for (HttpMessageConverter<?> converter : this.restTemplate
                .getMessageConverters()) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter c = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                mapper.enable(
                        DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                
                mapper.addHandler(new DeserializationProblemHandler() {
                    
                    /** array -> string兼容处理 */
                    @Override
                    public Object handleUnexpectedToken(
                            DeserializationContext ctxt, Class<?> targetType,
                            JsonToken t, JsonParser p, String failureMsg)
                            throws IOException {
                        if (String.class.isAssignableFrom(targetType)
                                && JsonToken.START_ARRAY.equals(t)) {
                            //当档期啊节点为array,需要转换的目标对象为字符串时的处理逻辑
                            //将array节点存放为json字符串
                            StringBuffer s = new StringBuffer();
                            s.append("[");
                            int index = 0;
                            while (!JsonToken.END_ARRAY.equals(p.nextToken())) {
                                if (JsonToken.VALUE_STRING
                                        .equals(p.getCurrentToken())) {
                                    if (index > 0) {
                                        s.append(",");
                                    }
                                    s.append("\"")
                                            .append(p.getValueAsString())
                                            .append("\"");
                                    index++;
                                } else {
                                    //出现了不能处理的节点
                                    return super.handleUnexpectedToken(ctxt,
                                            targetType,
                                            t,
                                            p,
                                            failureMsg);
                                }
                            }
                            s.append("]");
                            String arraystr = s.toString();
                            return arraystr;
                        }
                        return super.handleUnexpectedToken(ctxt,
                                targetType,
                                t,
                                p,
                                failureMsg);
                    }
                    
                    /** 日期特殊字符处理 */
                    @Override
                    public Object handleWeirdStringValue(
                            DeserializationContext ctxt, Class<?> targetType,
                            String valueToConvert, String failureMsg)
                            throws IOException {
                        if (Date.class.isAssignableFrom(targetType)
                                && "-".equals(valueToConvert)) {
                            //将字符串"-"处理为空时间
                            return null;
                        }
                        return super.handleWeirdStringValue(ctxt,
                                targetType,
                                valueToConvert,
                                failureMsg);
                    }
                });
                c.setObjectMapper(mapper);
            }
        }
    }
    
    /**
     * 
     * @param name
     * @return
     */
    @Override
    public QxbEnterpriseInfo getDetailAndContactByName(String name) {
        Map<String, Object> uriVariables = new LinkedHashMap<String, Object>();
        uriVariables.put("appkey", this.appKey);
        uriVariables.put("secret_key", this.secretKey);
        uriVariables.put("keyword", name);
        
        //查询企业商业信息
        QxbRemoteResult<QxbEnterpriseInfo> result = this.restTemplate.exchange(
                "http://api.qixin.com/APITestService/enterprise/getDetailAndContactByName?appkey=ada44bd0070711e6b8a865678b483fde&keyword=小米科技有限责任公司",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<QxbRemoteResult<QxbEnterpriseInfo>>() {
                },
                uriVariables).getBody();
        switch (result.getStatus()) {
            case 200:
                return result.getData();
            default:
                throw new SILException(
                        "通过企信宝查询企业商业信息异常：status:" + result.getStatus()
                                + " message: " + result.getMessage());
        }
    }
    
    //    /**
    //     * @param name
    //     */
    //    @Override
    //    public void getDetailByNameOnline(String name) {
    //        Map<String, Object> uriVariables = new LinkedHashMap<String, Object>();
    //        uriVariables.put("province", "BJ");
    //        uriVariables.put("appkey", appKey);
    //        uriVariables.put("secret_key", secretKey);
    //        uriVariables.put("keyword", "小米科技有限责任公司");
    //        
    //        //        String result = this.restTemplate.getForObject(
    //        //                "http://api.qixin.com/APITestService/v2/enterprise/getDetailByNameOnline",
    //        //                String.class,
    //        //                uriVariables);
    //        //        System.out.println(result);
    //        QxbRemoteResult<QxbEnterpriseInfo> result = this.restTemplate.exchange(
    //                "http://api.qixin.com/APITestService/v2/enterprise/getDetailByNameOnline?province={province}&appkey={appkey}&secret_key={secret_key}&keyword={keyword}",
    //                HttpMethod.GET,
    //                null,
    //                new ParameterizedTypeReference<QxbRemoteResult<QxbEnterpriseInfo>>() {
    //                },
    //                uriVariables).getBody();
    //        System.out.println(result.getStatus() + " ： " + result.getMessage());
    //        switch (result.getStatus()) {
    //            case 200:
    //                System.out.println(result.getData().getAddress());
    //                break;
    //            
    //            default:
    //                break;
    //        }
    //    }
    
}
