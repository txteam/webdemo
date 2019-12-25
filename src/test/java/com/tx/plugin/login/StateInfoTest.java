///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年12月25日
// * <修改描述:>
// */
//package com.tx.plugin.login;
//
//import java.nio.charset.Charset;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.util.Base64Utils;
//
//import com.tx.core.util.JsonUtils;
//import com.tx.plugin.login.model.StateInfo;
//
///**
// * <功能简述>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年12月25日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class StateInfoTest {
//    
//    public static void main(String[] args) {
//        StateInfo s = new StateInfo();
//        s.setId(RandomStringUtils.randomAlphabetic(16));
//        s.setUserId("01234567890123456789012345678901");
//        s.setTargetName("bind");
//        String json = JsonUtils.toJson(s);
//        System.out.println(json);
//        String jsonEncode = Base64Utils.encodeToString(json.getBytes());
//        System.out.println(jsonEncode);
//        String source = new String(Base64Utils.decodeFromString(jsonEncode),
//                Charset.forName("UTF-8"));
//        System.out.println(source);
//        
//        StateInfo s1 = new StateInfo();
//        s1.setId(RandomStringUtils.randomAlphabetic(16));
//        s1.setTargetName("bind");
//        String json1 = JsonUtils.toJson(s1);
//        System.out.println(json1);
//        String jsonEncode1 = Base64Utils.encodeToString(json1.getBytes());
//        System.out.println(jsonEncode1);
//        String source1 = new String(Base64Utils.decodeFromString(jsonEncode1),
//                Charset.forName("UTF-8"));
//        System.out.println(source1);
//    }
//}
