/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年2月18日
 * <修改描述:>
 */
package com.tx.fetchhccredit.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.fetchhccredit.model.HCLoanAccountDetailView;

/**
 * 贷款账户详情解析器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年2月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class HCLoanAccountDetailParser {
    
    private static final Map<Integer, Map<String, String>> nameMapping = new HashMap<>();
    
    static {
        for (int i = 0; i < 8; i++) {
            nameMapping.put(i, new HashMap<String, String>());
        }
        //0
        nameMapping.get(0).put("进件编号：", "loanBillNumber");
        nameMapping.get(0).put("业务状态：", "status");
        nameMapping.get(0).put("提交人：", "createOperatorName");
        nameMapping.get(0).put("提交时间：", "createDate");
        
        //借款信息1
        nameMapping.get(1).put("借款类型", "creditProductName");
        nameMapping.get(1).put("申请期限", "totalPeriod");
        nameMapping.get(1).put("借款费率", "interestRate");
        nameMapping.get(1).put("借款用途", "uses");
        nameMapping.get(1).put("审批金额（万元）", "loanAmount");
        nameMapping.get(1).put("可接受最高还款金额", "maxMonthlyRepayAmount");
        
        //业务信息2
        nameMapping.get(2).put("客户姓名", "serviceClientName");
        nameMapping.get(2).put("是否加急", "urgent");
        nameMapping.get(2).put("客服专员", "customerServiceOfficer");
        nameMapping.get(2).put("客户经理", "customerServiceManager");
        nameMapping.get(2).put("客服经理", "customerServiceTeamManager");
        nameMapping.get(2).put("申请日期", "applyDate");
        
        //个人信息3
        nameMapping.get(3).put("客户姓名", "clientName");
        nameMapping.get(3).put("客户性别", "sex");
        nameMapping.get(3).put("证件类型", "idCardType");
        nameMapping.get(3).put("身份证号", "idCardNumber");
        nameMapping.get(3).put("通话详单", "linkInfo");
        nameMapping.get(3).put("移动电话", "telePhoneNumber");
        nameMapping.get(3).put("进件城市", "districtName");
        nameMapping.get(3).put("有无房产", "hasHouse");
        nameMapping.get(3).put("房产有无按揭", "hasMortgage");
        nameMapping.get(3).put("住宅电话", "housePhoneNumber");
        nameMapping.get(3).put("起始居住时间", "startLiveDate");
        
        //4
        
        //工作情况5
        nameMapping.get(5).put("工作状况", "workInfo");
        nameMapping.get(5).put("单位名称", "workUnitInfo");
        nameMapping.get(5).put("单位电话", "workUnitPhoneNumber");
        nameMapping.get(5).put("所属行业", "workUnitIndustry");
        
        //联系人情况6
        nameMapping.get(6).put("姓名", "spouseName");
        nameMapping.get(6).put("单位电话", "spouseWorkUnitPhoneNumber");
        nameMapping.get(6).put("移动电话", "spouseTelePhoneNumber");
        nameMapping.get(6).put("直系亲属电话", "kinPhoneNumber");
        nameMapping.get(6).put("其他联系人电话", "linkManPhoneNumber");
        
        //信审信息7
        nameMapping.get(7).put("补件时间", "patchDate");
        nameMapping.get(7).put("补件原因", "patchReason");
        nameMapping.get(7).put("拒绝原因", "refuseReason");
        nameMapping.get(7).put("子原因", "refuseOtherReason");
        nameMapping.get(7).put("初审人员", "firstTrialOperator");
        nameMapping.get(7).put("初审时间", "firstTrialDate");
        nameMapping.get(7).put("终审人员", "finalTrialOperator");
        nameMapping.get(7).put("终审时间", "finalTrialDate");
        nameMapping.get(7).put("备注", "remark");
        nameMapping.get(7).put("月份", "month");
    }
    
    public static HCLoanAccountDetailView parse(String clientId,String detailHtmlContent) {
        Map<Integer, MultiValueMap<String, String>> valueMapping = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            valueMapping.put(i, new LinkedMultiValueMap<String, String>());
        }
        
        Document doc = Jsoup.parse(detailHtmlContent);
        Elements tableEles = doc.select("table.D_jinjia");
        for (int tableIndex = 0; tableIndex < tableEles.size(); tableIndex++) {
            Elements trEles = tableEles.get(tableIndex).select("tr");
            for (Element trElTemp : trEles) {
                Elements tdEles = trElTemp.select("td");
                int size = tdEles.size();
                if (size <= 1) {
                    continue;
                }
                int comSize = (int) (size / 2);
                for (int i = 0; i < comSize; i++) {
                    String key = tdEles.get(i * 2).text().trim();
                    key = key.replaceAll("\r|\n", "");
                    String value = tdEles.get((i * 2) + 1).text();
                    if (!StringUtils.isEmpty(key)) {
                        valueMapping.get(tableIndex).add(key, value);
                    }
                }
            }
        }
        
        HCLoanAccountDetailView detail = new HCLoanAccountDetailView();
        detail.setId(clientId);
        
        MetaObject mo = MetaObject.forObject(detail);
        for (int i = 0; i < 8; i++) {
            Map<String, String> nameMap = nameMapping.get(i);
            MultiValueMap<String, String> valueMap = valueMapping.get(i);
            
            for (Entry<String, String> entryTemp : nameMap.entrySet()) {
                String nameKey = entryTemp.getKey();
                if (!valueMap.containsKey(nameKey)
                        || valueMap.get(nameKey).size() <= 0) {
                    System.out.println("valueMap not exist. nameKey:" + nameKey);
                    continue;
                }
                
                if (valueMap.get(nameKey).size() == 1) {
                    String fieldName = nameMap.get(nameKey);
                    String value = valueMap.getFirst(nameKey);
                    if(mo.hasSetter(fieldName)){
                        mo.setValue(fieldName, value);
                    }
                } else {
                    for (int j = 1; j <= 4
                            && j < (valueMap.get(nameKey).size() + 1); j++) {
                        String fieldName = nameMap.get(nameKey) + j;
                        String value = valueMap.getFirst(nameKey);
                        if(mo.hasSetter(fieldName)){
                            mo.setValue(fieldName, value);
                        }
                    }
                }
            }
        }
        return detail;
    }
    
    public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String detailHtmlContent = FileUtils.readFileToString(new File(
                "c:/detail.html"));
        
        HCLoanAccountDetailView detail = HCLoanAccountDetailParser.parse("35",detailHtmlContent);
        
        Map resMap = PropertyUtils.describe(detail);
        MapUtils.verbosePrint(System.out, "detailMap", resMap);
    }
}
//map: = 
//{
//    0 = 
//    {
//        进件编号： = [0114051904501004]
//        业务状态： = [拒绝]
//        提交人： = [赵嘉]
//        提交时间： = [2014-05-19]
//    }
//    1 = 
//    {
//        借款类型 = [工薪贷]
//        申请期限 = [0]
//        借款费率 = [.0000]
//        借款用途 = []
//        审批金额（万元） = [0.0]
//        可接受最高还款金额 = []
//    }
//    2 = 
//    {
//        客户姓名 = [赵娟锋]
//        是否加急 = [否]
//        客服专员 = [赵嘉]
//        客户经理 = [童均伟]
//        客服经理 = [段红岗]
//        申请日期 = [2014-05-19]
//    }
//    3 = 
//    {
//        客户姓名 = [赵娟锋]
//        客户性别 = []
//        证件类型 = [身份证]
//        身份证号 = [610328198912280619]
//        通话详单 = [有]
//        移动电话 = [18391709902]
//        进件城市 = [宝鸡]
//        有无房产 = [无]
//        房产有无按揭 = [无]
//        住宅电话 = []
//        起始居住时间 = []
//    }
//    4 = 
//    {
//    }
//    5 = 
//    {
//        工作状况 = []
//        单位名称 = [陕西秦川设备成套服务有限公司]
//        单位电话 = [0917-3670950]
//        所属行业 = []
//    }
//    6 = 
//    {
//        姓名 = [无]
//        单位电话 = []
//        移动电话 = []
//        直系亲属电话 = [13571766359, 13892491871]
//        其他联系人电话 = [15129175960, 18392758577]
//    }
//    7 = 
//    {
//        补件时间 = []
//        补件原因 = []
//        拒绝原因 = [D601偿债能力不足]
//        子原因 = []
//        初审人员 = [胡晓伟]
//        初审时间 = []
//        终审人员 = [崔倩]
//        终审时间 = [2014-05-19]
//        备注 = []
//        月份 = []
//    }
//}
