/*
 * 描          述:  <描述>
 * 修  改   人:  txteam_administrator
 * 修改时间:  2016年12月1日
 * <修改描述:>
 */
package com.tx.basicdata.fetchhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tx.basicdata.fetchhelper.HttpClientUtils.HttpClient;
import com.tx.core.datasource.DataSourceFinder;
import com.tx.core.datasource.finder.SimpleDataSourceFinder;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.mybatis.support.MyBatisDaoSupportHelper;
import com.tx.core.support.jsoup.JsoupUtils;
import com.tx.core.util.PinyinUtils;
import com.tx.core.util.dialect.DataSourceTypeEnum;
import com.tx.local.basicdata.dao.DistrictDao;
import com.tx.local.basicdata.dao.impl.DistrictDaoImpl;
import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.DistrictTypeEnum;

/**
 * http://www.stats.gov.cn/tjsj/tjbz/
 * 区域数据爬取帮助类<br/>
 *     //读取url: http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/index.html
 *     //迭代解析区域数据:
 *     
 *     //读取身份证号码: http://www.aa963.com/iden/search.asp?id=名称模糊查询
 * <功能详细描述>
 * 
 * @author  txteam_administrator
 * @version  [版本号, 2016年12月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DistrictFetchHelper {
    
    private static HttpClient httpClient = HttpClientUtils.buildHttpClient(HttpClientUtils.class,
            500L,
            30000,
            (5 * 60 * 1000),
            30,
            30,
            true,
            true,
            50,
            3000);
    
    private static DataSource ds;
    
    private static MyBatisDaoSupport myBatisDaoSupport;
    
    private static DistrictDao districtDao;
    
    static {
        DataSourceFinder finder = new SimpleDataSourceFinder(
                "com.mysql.jdbc.Driver",
                //"jdbc:mysql://120.24.75.25:3306/jhms_db?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
                "jdbc:mysql://localhost:3306/fetch_data?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
                //"pqy", "pqy"
                "root", "root");
        ds = finder.getDataSource();
        
        try {
            myBatisDaoSupport = MyBatisDaoSupportHelper.buildMyBatisDaoSupport("classpath:context/mybatis-config.xml",
                    new String[] { "classpath:com/tx/component/basicdata/dao/impl/*SqlMap.xml" },
                    DataSourceTypeEnum.MYSQL,
                    ds);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        DistrictDaoImpl dd = new DistrictDaoImpl();
        dd.setMyBatisDaoSupport(myBatisDaoSupport);
        districtDao = dd;
    }
    
    public static void main(String[] args) throws IOException {
        fetchDistrict("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/index.html");//
    }
    
    /** 
     *<功能简述>
     *<功能详细描述>
     * @throws IOException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static void fetchDistrict(String url) throws IOException {
        String htmlContent = httpClient.get(url, "GBK", "GBK");//VFSUtils.readFileToString(url, "GBK");
        
        //System.out.println(htmlContent);
        //解析这个方法（HTML内容）
        Document doc = JsoupUtils.parse(htmlContent);
        //找到body这个标签下面的table 下面的tr 里面的 provincetr
        Elements trEls = doc.select("body > table tr.provincetr");
        
        //解析有哪些省
        Map<String, String> province2urlMap = new HashMap<>();
        for (int j = 0; j < trEls.size(); j++) {
            //trEls 意思(?) tdEls 意思(?) 
            Elements tdEls = trEls.get(j).getElementsByTag("td");
            for (int i = 0; i < tdEls.size(); i++) {
                Element tdEl = tdEls.get(i);
                //                System.out.println(tdEl.text() + " | "
                //                        + tdEl.getElementsByTag("a").get(0).attr("href"));
                province2urlMap.put(tdEl.text(), tdEl.getElementsByTag("a")
                        .get(0)
                        .attr("href"));
            }
        }
        
        //单个解析省
        List<District> districtList = new ArrayList<>();
        //set 和get 的区别 
        Set<String> paredUrlSet = new HashSet<>();
        for (Entry<String, String> entryTemp : province2urlMap.entrySet()) {
            String name = entryTemp.getKey();
            //name = new String(name.getBytes("GBK"),"UTF-8");
            String urlTemp = entryTemp.getValue();
            String code = urlTemp.substring(0, urlTemp.indexOf("."));
            code = StringUtils.rightPad(code, 12, "0");
            District district = doBuildDistrict(null,
                    code,
                    name,
                    DistrictTypeEnum.PROVINCE,
                    name,
                    districtList);
            
            parseChildDistrict(district, url.substring(0, url.lastIndexOf("/"))
                    + "/" + urlTemp, districtList, paredUrlSet);
            //break;
        }
    }
    
    /** 
     * <功能简述>
     * <功能详细描述>
     * @param baseUrl
     * @param resDistrictList
     * @param entryTemp
     * @throws IOException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static void parseChildDistrict(District parent, String url,
            List<District> districtList, Set<String> paredUrlSet)
            throws IOException {
        if (paredUrlSet.contains(url)) {
            return;
        }
        long getStartTime = (new Date()).getTime();
        String htmlContentTemp = httpClient.get(url, "GBK", "GBK");//VFSUtils.readFileToString(url, "GBK");
        paredUrlSet.add(url);
        long getEndTime = (new Date()).getTime();
        
        //System.out.println(htmlContentTemp);
        long parseStartTime = (new Date()).getTime();
        Document docTemp = JsoupUtils.parse(htmlContentTemp);
        long parseEndTime = (new Date()).getTime();
        
        DistrictTypeEnum currentType = null;
        switch (parent.getType()) {
            case PROVINCE:
                currentType = DistrictTypeEnum.CITY;
                break;
            case CITY:
                currentType = DistrictTypeEnum.COUNTY;
                break;
            case COUNTY:
                currentType = DistrictTypeEnum.TOWN;
                break;
            case TOWN:
                currentType = DistrictTypeEnum.VILLAGE;
                break;
            case VILLAGE:
                currentType = DistrictTypeEnum.VILLAGE;
                break;
            default:
                break;
        }
        Elements trElsTemp = docTemp.select("body > table tr."
                + currentType.toString().toLowerCase() + "tr");
        //解析有哪些省
        for (int j = 0; j < trElsTemp.size(); j++) {
            Elements tdElsTemp = trElsTemp.get(j).getElementsByTag("td");
            
            String codeTemp = tdElsTemp.get(0).text();
            String nameTemp = tdElsTemp.get(1).text();
            String remarkTemp = tdElsTemp.get(1).text();
            if (DistrictTypeEnum.VILLAGE.equals(currentType)) {
                nameTemp = tdElsTemp.get(2).text();
                remarkTemp = tdElsTemp.get(1).text() + "_"
                        + tdElsTemp.get(2).text();
            }
            //nameTemp = new String(name.getBytes("GBK"),"UTF-8");
            boolean existChild = tdElsTemp.get(0).getElementsByTag("a").size() > 0;
            District district = doBuildDistrict(parent,
                    codeTemp,
                    nameTemp,
                    currentType,
                    remarkTemp,
                    districtList);
            
            if (existChild) {
                String urlTemp = tdElsTemp.get(0)
                        .getElementsByTag("a")
                        .get(0)
                        .attr("href");
                
                parseChildDistrict(district,
                        url.substring(0, url.lastIndexOf("/")) + "/" + urlTemp,
                        districtList,
                        paredUrlSet);
            }
        }
        
        System.out.println("currentListSize:" + districtList.size()
                + "getTime:" + (getStartTime - getEndTime) + "parseTime:"
                + (parseStartTime - parseEndTime));
    }
    
    private static District doBuildDistrict(District parent, String code,
            String name, DistrictTypeEnum type, String remark,
            List<District> districtList) {
        if (name.indexOf("街道办事处") > 0) {
            name = StringUtils.substringBefore(name, "街道办事处");
        } else if (name.indexOf("村村民委员会") > 0) {
            name = StringUtils.substringBefore(name, "村民委员会");
        } else if (name.indexOf("村民委员会") > 0) {
            name = StringUtils.substringBefore(name, "民委员会");
        } else if (name.indexOf("村村委会") > 0) {
            name = StringUtils.substringBefore(name, "村委会");
        } else if (name.indexOf("村委会") > 0) {
            name = StringUtils.substringBefore(name, "委会");
        } else if (name.indexOf("社区居民委员会") > 0) {
            name = StringUtils.substringBefore(name, "社区居民委员会");
        } else if (name.indexOf("社区居委会") > 0) {
            name = StringUtils.substringBefore(name, "社区居委会");
        } else if (name.indexOf("社区居委会") > 0) {
            name = StringUtils.substringBefore(name, "社区居委会");
        } else if (name.indexOf("社区委会") > 0) {
            name = StringUtils.substringBefore(name, "社区委会");
        } else if (name.indexOf("社区居民委会") > 0) {
            name = StringUtils.substringBefore(name, "社区居民委会");
        } else if (name.indexOf("村民委委会") > 0) {
            name = StringUtils.substringBefore(name, "村民委委会");
        } else if (name.indexOf("社区居民委会") > 0) {
            name = StringUtils.substringBefore(name, "社区居民委会");
        } else if (name.indexOf("社区委会") > 0) {
            name = StringUtils.substringBefore(name, "社区委会");
        } else if (name.indexOf("居委会") > 0) {
            name = StringUtils.substringBefore(name, "居委会");
        } else if (name.indexOf("渔委会") > 0) {
            name = StringUtils.substringBefore(name, "渔委会");
        }
        
        District district = new District();
        district.setId(code);
        
        district.setCode(code);
        district.setZipCode(code);
        district.setName(name);
        district.setRemark(remark);
        district.setType(type);
        district.setLevel(type.getLevel());
        district.setValid(true);
        district.setModifyAble(false);
        
        Date now = new Date();
        district.setCreateDate(now);
        district.setLastUpdateDate(now);
        String pinyin = PinyinUtils.parseToPinyin(name);
        district.setPinyin(pinyin);
        String py = PinyinUtils.parseToPY(name);
        district.setPy(py);
        if (parent != null) {
            district.setFullName(parent.getFullName() + "," + name);
            district.setParent(parent);
        } else {
            district.setFullName(name);
        }
        switch (type) {
            case PROVINCE:
                district.setProvince(district);
                break;
            case CITY:
                district.setProvince(parent.getProvince());
                district.setCity(district);
                break;
            case COUNTY:
                district.setProvince(parent.getProvince());
                district.setCity(parent.getCity());
                district.setCounty(district);
                break;
            case TOWN:
                district.setProvince(parent.getProvince());
                district.setCity(parent.getCity());
                district.setCounty(parent.getCounty());
                break;
            case VILLAGE:
                district.setProvince(parent.getProvince());
                district.setCity(parent.getCity());
                district.setCounty(parent.getCounty());
                break;
            default:
                break;
        }
        
        if (district.getPinyin().length() > 128) {
            district.setPinyin(district.getPinyin().substring(0, 128));
        }
        districtDao.insert(district);
        districtList.add(district);
        return district;
    }
}
