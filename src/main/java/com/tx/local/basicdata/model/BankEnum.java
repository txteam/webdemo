/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年4月29日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 各银行简码表<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年4月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum BankEnum implements BasicDataEnum {
    
    ICBC("ICBC", "工商银行", "https://mybank.icbc.com.cn/icbc/perbank/index.jsp",
            "https://corporbank-simp.icbc.com.cn/icbc/normalbank/index.jsp",
            new String[] { "中国工商银行" }),
    
    CCB("CCB", "建设银行", "http://www.ccb.com/cn/home/indexv3.html",
            "http://company.ccb.com/cn/home/company_indexv3.html",
            new String[] { "中国建设银行" }),
    
    PSBC("PSBC", "邮储银行", "https://pbank.psbc.com/pweb/prelogin.do?BankId=9999",
            "http://www.psbc.com/portal/zh_CN/corpbank/index.html",
            new String[] { "中国邮政储蓄银行" }),
    
    BOC("BOC", "中国银行", "https://ebsnew.boc.cn/boc15/login.html",
            "https://ebsnew.boc.cn/boccp/login.html?entryType=9"),
    
    ABC("ABC", "农业银行", "http://www.abchina.com/cn/wydl/grwydl/",
            "http://www.abchina.com/cn/wydl/CCustomerLogin/"),
    
    BCM("BCM", "交通银行", "https://pbank.95559.com.cn/personbank/logon.jsp",
            "https://ebank.95559.com.cn/CEBS/cebs/logon.do?bocom_locale_langFlg=zh_CN"),
    
    CMB("CMB", "招商银行", "http://www.cmbchina.com/personal/",
            "http://www.cmbchina.com/corporate/"),
    
    CNCB("CNCB", "中信银行", "https://i.bank.ecitic.com/perbank6/signIn.do",
            "https://enterprise.bank.ecitic.com/corporbank/userLogin.do"),
    
    CIB("CIB", "兴业银行", "https://personalbank.cib.com.cn/pers/main/login.do",
            "http://www.cib.com.cn/cn/index.html"),
    
    CMBC("CMBC", "民生银行", "https://nper.cmbc.com.cn/pweb/static/login.html",
            "https://ent.cmbc.com.cn/eweb/static/login.html?fromOldBank=1"),
    
    PAB("PAB", "平安银行", "http://bank.pingan.com/geren/index.shtml",
            "http://bank.pingan.com/gongsi/index.shtml"),
    
    HXB("HXB", "华夏银行", "http://www.hxb.com.cn/home/cn/",
            "http://www.hxb.com.cn/home/cn/"),
    
    HF("HF", "恒丰银行", "http://www.hfbank.com.cn/", "http://www.hfbank.com.cn/"),
    
    CGB("CGB", "广发银行", new String[] { "广发银行股份有限公司" }),
    
    CQRCB("CQRCB", "重庆农商行", new String[] { "重庆农村商业银行" }),
    
    SPDB("SPDB", "浦东发展银行"),
    
    CEB("CEB", "光大银行"),
    
    BOB("BOB", "北京银行"),
    
    BOS("BOS", "上海银行"),
    
    JSB("JSB", "江苏银行"),
    
    BJRCB("BJRCB", "北京农商银行"),
    
    SRCB("SRCB", "上海农商行"),
    
    NJCB("NJCB", "南京银行"),
    
    GRCB("GRCB", "广州农商银行"),
    
    NBCB("NBCB", "宁波银行"),
    
    HSB("HSB", "徽商银行"),
    
    HZB("HZB", "杭州银行"),
    
    GZCB("GZCB", "广州银行"),
    
    BODL("BODL", "大连银行"),
    
    BSB("BSB", "包商银行"),
    
    BOCD("BOCD", "成都银行"),
    
    BOLJ("BOLJ", "龙江银行"),
    
    HKB("HKB", "汉口银行"),
    
    CQCB("CQCB", "重庆银行"),
    
    CHAB("CHAB", "山东联盟长安银行"),
    
    TAB("TAB", "泰安市商业银行"),
    
    RZB("RZB", "日照银行"),
    
    WFCCB("WFCCB", "潍坊银行"),
    
    LSB("LSB", "临商银行"),
    
    LWB("LWB", "莱商银行"),
    
    DZB("DZB", "德州银行"),
    
    QSB("QSB", "齐商银行"),
    
    DYLSCZ("DYLSCZ", "东营莱商村镇银行"),
    
    CJCCB("CJCCB", "长江银行"),
    
    BOLY("BOLY", "洛阳银行"),
    
    GDNYB("GDNYB", "广东南粤银行"),
    
    JZCB("JZCB", "晋中银行"),
    
    ORDOS("ORDOS", "鄂尔多斯银行"),
    
    BOXM("BOXM", "厦门银行"),
    
    WZCB("WZCB", "温州银行"),
    
    BONC("BONC", "南昌银行"),
    
    JJCCB("JJCCB", "九江银行"),
    
    HBNX("HBNX", "河北省农村信用社"),
    
    HUBNX("HUBNX", "湖北省农村信用社"),
    
    GSNX("GSNX", "甘肃省农村信用社"),
    
    SHXNX("SHXNX", "陕西省农村信用社"),
    
    BHRCB("BHRCB", "天津滨海农村商业银行");
    
    /** 关键字 */
    private final String code;
    
    /** 银行名 */
    private final String name;
    
    /** 银行别名 */
    private String[] aliases;
    
    /** 个人网银登录url */
    private String personalLoginUrl = "";
    
    /** 机构网银登录url */
    private String institutionLoginUrl = "";
    
    /** <默认构造函数> */
    private BankEnum(String code, String name) {
        this.code = code;
        this.name = name;
        
        this.aliases = aliases(name, null);
    }
    
    /** <默认构造函数> */
    private BankEnum(String code, String name, String[] aliases) {
        this.code = code;
        this.name = name;
        
        this.aliases = aliases(name, aliases);
    }
    
    /** <默认构造函数> */
    private BankEnum(String code, String name, String personalLoginUrl,
            String institutionLoginUrl) {
        this.code = code;
        this.name = name;
        this.personalLoginUrl = personalLoginUrl;
        this.institutionLoginUrl = institutionLoginUrl;
        
        this.aliases = aliases(name, null);
    }
    
    /** <默认构造函数> */
    private BankEnum(String code, String name, String personalLoginUrl,
            String institutionLoginUrl, String[] aliases) {
        this.code = code;
        this.name = name;
        this.personalLoginUrl = personalLoginUrl;
        this.institutionLoginUrl = institutionLoginUrl;
        
        this.aliases = aliases(name, aliases);
    }
    
    /**
      * 获取别名
      * <功能详细描述>
      * @param name
      * @param aliases
      * @return [参数说明]
      * 
      * @return String[] [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private String[] aliases(String name, String[] aliases) {
        if (ArrayUtils.isEmpty(aliases)) {
            aliases = new String[] { name };
        } else {
            boolean isInclude = false;
            for (String nameTemp : aliases) {
                if (nameTemp.equals(name)) {
                    isInclude = true;
                    break;
                }
            }
            if (!isInclude) {
                aliases = ArrayUtils.add(aliases, name);
            }
        }
        return aliases;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 aliases
     */
    public String[] getAliases() {
        return aliases;
    }
    
    /**
     * @return 返回 personalLoginUrl
     */
    public String getPersonalLoginUrl() {
        return personalLoginUrl;
    }
    
    /**
     * @return 返回 institutionLoginUrl
     */
    public String getInstitutionLoginUrl() {
        return institutionLoginUrl;
    }
    
    /**
      * 根据银行编码获取对应的银行枚举实例<br/>
      * <功能详细描述>
      * @param key
      * @return [参数说明]
      * 
      * @return BankEnum [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static BankEnum forCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (BankEnum bankTemp : BankEnum.values()) {
            if (code.equalsIgnoreCase(bankTemp.toString())) {
                return bankTemp;
            }
        }
        return null;
    }
    
    /**
      * 根据银行名称获取银行枚举<br/>
      * <功能详细描述>
      * @param name
      * @return [参数说明]
      * 
      * @return BankEnum [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static BankEnum forName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (BankEnum bankTemp : BankEnum.values()) {
            for (String alias : bankTemp.aliases) {
                if (name.equalsIgnoreCase(alias)) {
                    continue;
                }
                return bankTemp;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (BankEnum bank : BankEnum.values()) {
            System.out.println(MessageFormatter.arrayFormat(
                    "update cl_baseClientInfo set bank='{}',bankCardType='{}',bankCardName='{}' where bankCardName='{}' or bankCardName='{}';",
                    new Object[] { bank.toString(), "借记卡", bank.getName(),
                            bank.getName(), bank.getCode() })
                    .getMessage());
            
            sb.append("'").append(bank.getCode()).append("',");
            sb.append("'").append(bank.getName()).append("',");
        }
        String resStr = sb.substring(0, sb.length() - 1);
        System.out
                .println(
                        MessageFormatter
                                .arrayFormat(
                                        "update cl_baseClientInfo set bank=null,bankCardType='{}' where bankCardName not in ({});",
                                        new Object[] { "借记卡", resStr })
                                .getMessage());
    }
}
