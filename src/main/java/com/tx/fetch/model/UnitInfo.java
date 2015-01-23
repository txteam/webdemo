/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月10日
 * <修改描述:>
 */
package com.tx.fetch.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月10日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "FETCH_UNIT_INFO")
public class UnitInfo {
    //{"id":833957,"iname":"广昌青湖钢厂","caseCode":"(2014)广法执字第00232号","
    //focusNumber":95,"cardNum":"680926658","businessEntity":"万长荣",
    //"courtName":"江西省广昌县人民法院","areaName":"江西","partyTypeName":"581","gistId":"（2014）广民初字第501号民事判决书",
    //"regDate":"2014年11月28日","gistUnit":"江西省广昌县人民法院",
    //"duty":"1.被告广昌青湖钢厂在本判决生效之日起七日内支付原告揭孝勇水泥款53300元；2.本案诉讼费1133元，财产保全费620元，由被告承担。",
    //"performance":"全部未履行","disruptTypeName":"其它规避执行","publishDate":"2014年12月11日"}
    /*
     create table FETCH_UNIT_INFO(
        id varchar(64),
        iname varchar(64),
        caseCode varchar(255),
        focusNumber varchar(64),
        cardNum varchar(64),
        businessEntity varchar(64),
        courtName varchar(255),
        areaName varchar(255),
        partyTypeName varchar(64),
        gistId varchar(255),
        regDate varchar(64),
        gistUnit varchar(255),
        duty varchar(4000),
        performance varchar(255),
        disruptTypeName varchar(255),
        publishDate varchar(64),
        int pageIndex,
        int currentPageIndex,
        primary key id
     );
     */
/*
SELECT
    t.iname,
    t.businessEntity,
    t.cardNum,
    t.areaName,
    t.caseCode,
    t.courtName,
    t.disruptTypeName,
    t.duty,
    t.focusNumber,
    t.gistId,
    t.gistUnit,
    t.id,
    t.partyTypeName,
    t.performance,
    t.publishDate,
    t.regDate
FROM
    fetch_unit_info t
WHERE
    t.exported = 0;
 */
    private String id;
    private String iname;
    private String caseCode;
    private String focusNumber;
    private String cardNum;
    private String businessEntity;
    private String courtName;
    private String areaName;
    private String partyTypeName;
    private String gistId;
    private String regDate;
    private String gistUnit;
    private String duty;
    private String performance;
    private String disruptTypeName;
    private String publishDate;
    private int pageIndex;
    private int currentPageIndex;
    private boolean exported;
    private Date exportedDate;
    private Date fetchDate;
    
    /**
     * @return 返回 pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }
    /**
     * @param 对pageIndex进行赋值
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    /**
     * @return 返回 currentPageIndex
     */
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }
    /**
     * @param 对currentPageIndex进行赋值
     */
    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }
    /**
     * @return 返回 exported
     */
    public boolean isExported() {
        return exported;
    }
    /**
     * @param 对exported进行赋值
     */
    public void setExported(boolean exported) {
        this.exported = exported;
    }
    /**
     * @return 返回 exportedDate
     */
    public Date getExportedDate() {
        return exportedDate;
    }
    /**
     * @param 对exportedDate进行赋值
     */
    public void setExportedDate(Date exportedDate) {
        this.exportedDate = exportedDate;
    }
    /**
     * @return 返回 fetchDate
     */
    public Date getFetchDate() {
        return fetchDate;
    }
    /**
     * @param 对fetchDate进行赋值
     */
    public void setFetchDate(Date fetchDate) {
        this.fetchDate = fetchDate;
    }
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return 返回 iname
     */
    public String getIname() {
        return iname;
    }
    /**
     * @param 对iname进行赋值
     */
    public void setIname(String iname) {
        this.iname = iname;
    }
    /**
     * @return 返回 caseCode
     */
    public String getCaseCode() {
        return caseCode;
    }
    /**
     * @param 对caseCode进行赋值
     */
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }
    /**
     * @return 返回 focusNumber
     */
    public String getFocusNumber() {
        return focusNumber;
    }
    /**
     * @param 对focusNumber进行赋值
     */
    public void setFocusNumber(String focusNumber) {
        this.focusNumber = focusNumber;
    }
    /**
     * @return 返回 cardNum
     */
    public String getCardNum() {
        return cardNum;
    }
    /**
     * @param 对cardNum进行赋值
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    /**
     * @return 返回 businessEntity
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * @param 对businessEntity进行赋值
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * @return 返回 courtName
     */
    public String getCourtName() {
        return courtName;
    }
    /**
     * @param 对courtName进行赋值
     */
    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }
    /**
     * @return 返回 areaName
     */
    public String getAreaName() {
        return areaName;
    }
    /**
     * @param 对areaName进行赋值
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    /**
     * @return 返回 partyTypeName
     */
    public String getPartyTypeName() {
        return partyTypeName;
    }
    /**
     * @param 对partyTypeName进行赋值
     */
    public void setPartyTypeName(String partyTypeName) {
        this.partyTypeName = partyTypeName;
    }
    /**
     * @return 返回 gistId
     */
    public String getGistId() {
        return gistId;
    }
    /**
     * @param 对gistId进行赋值
     */
    public void setGistId(String gistId) {
        this.gistId = gistId;
    }
    /**
     * @return 返回 regDate
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * @param 对regDate进行赋值
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    /**
     * @return 返回 gistUnit
     */
    public String getGistUnit() {
        return gistUnit;
    }
    /**
     * @param 对gistUnit进行赋值
     */
    public void setGistUnit(String gistUnit) {
        this.gistUnit = gistUnit;
    }
    /**
     * @return 返回 duty
     */
    public String getDuty() {
        return duty;
    }
    /**
     * @param 对duty进行赋值
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }
    /**
     * @return 返回 performance
     */
    public String getPerformance() {
        return performance;
    }
    /**
     * @param 对performance进行赋值
     */
    public void setPerformance(String performance) {
        this.performance = performance;
    }
    /**
     * @return 返回 disruptTypeName
     */
    public String getDisruptTypeName() {
        return disruptTypeName;
    }
    /**
     * @param 对disruptTypeName进行赋值
     */
    public void setDisruptTypeName(String disruptTypeName) {
        this.disruptTypeName = disruptTypeName;
    }
    /**
     * @return 返回 publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }
    /**
     * @param 对publishDate进行赋值
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
