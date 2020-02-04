/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.notepad.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.notepad.model.Notepad;
import com.tx.local.notepad.model.NotepadCatalog;
import com.tx.local.notepad.model.NotepadTopicTypeEnum;
import com.tx.local.notepad.model.NotepadTypeEnum;
import com.tx.local.notepad.service.NotepadCatalogService;
import com.tx.local.notepad.service.NotepadService;
import com.tx.local.security.util.WebContextUtils;

/**
 * 记事本控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@PreAuthorize("hasRole('ROLE_OPERATOR')")
@Controller
@RequestMapping("/operator/notepad")
public class OperatorNotepadController {
    
    private static final String NEW_TITLE = "新建记事本";
    
    /** 记事本分类业务层 */
    @Resource
    private NotepadCatalogService notepadCatalogService;
    
    /** 记事本业务层 */
    @Resource
    private NotepadService notepadService;
    
    /**
     * 操作人员行事历行事历<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String index(@RequestParam Map<String, String> request,
            ModelMap response) {
        return "/notepad/operatorNotepadMain";
    }
    
    /**
     * 查询当前用户所拥有的记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<NotepadCatalog> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/catalogs", method = RequestMethod.GET)
    public List<NotepadCatalog> queryCatalogList() {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("type", NotepadTypeEnum.OPERATOR_NOTEPAD);
        params.put("topicType", NotepadTopicTypeEnum.OPERATOR);
        params.put("topicId", operatorId);
        
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        List<NotepadCatalog> resList = this.notepadCatalogService
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 新增记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/catalog", method = RequestMethod.POST)
    public boolean addCatalog(NotepadCatalog catalog) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        catalog.setVcid(vcid);
        catalog.setTopicId(operatorId);
        catalog.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
        catalog.setTopicType(NotepadTopicTypeEnum.OPERATOR);
        this.notepadCatalogService.insert(catalog);
        return true;
    }
    
    /**
     * 跳转到记事本详情页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/detail")
    public String toDetail(
            @RequestParam(name = "id", required = false) String id,
            ModelMap response) {
        Notepad notepad = null;
        if (!StringUtils.isEmpty(id)) {
            String operatorId = WebContextUtils.getOperatorId();
            String vcid = WebContextUtils.getVcid();
            Notepad condition = new Notepad();
            condition.setVcid(vcid);
            condition.setTopicId(operatorId);
            condition.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
            condition.setTopicType(NotepadTopicTypeEnum.OPERATOR);
            condition.setId(id);
            notepad = this.notepadService.find(condition);
        }
        if (notepad == null) {
            notepad = new Notepad();
            notepad.setTitle(generateNotepadTitle());
            notepad.setContent("");
        }
        response.put("notepad", notepad);
        return "/notepad/operatorNotepadDetail";
    }
    
    /**
     * 查询当前操作人员自己所有的记事本<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/notepads", method = RequestMethod.GET)
    public List<Notepad> queryNotepadList(String title) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("type", NotepadTypeEnum.OPERATOR_NOTEPAD);
        params.put("topicType", NotepadTopicTypeEnum.OPERATOR);
        params.put("topicId", operatorId);
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        List<Notepad> resList = this.notepadService.queryList(querier);
        return resList;
    }
    
    /**
     * 查询当前操作人员自己所有的记事本<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/notepad/{id}", method = RequestMethod.GET)
    public Notepad findNotepadById(
            @PathVariable(name = "id", required = true) String id) {
        //利用query代替find主要考虑到底层为通用实现逻辑，担心恶意查询，被撞库攻击的场景，或在查询中添加额外参数进行锁定也行
        String operatorId = WebContextUtils.getOperatorId();
        String vcid = WebContextUtils.getVcid();
        Notepad condition = new Notepad();
        condition.setVcid(vcid);
        condition.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
        condition.setTopicType(NotepadTopicTypeEnum.OPERATOR);
        condition.setTopicId(operatorId);
        condition.setId(id);
        Notepad res = this.notepadService.find(condition);
        return res;
    }
    
    /**
     * 新增记事本分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/notepad", method = RequestMethod.POST)
    public boolean saveNotepad(Notepad notepad) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        notepad.setVcid(vcid);
        notepad.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
        notepad.setTopicType(NotepadTopicTypeEnum.OPERATOR);
        notepad.setTopicId(operatorId);
        if (StringUtils.isEmpty(notepad.getTitle())) {
            notepad.setTitle(generateNotepadTitle());
        }
        notepad.setLastUpdateUserId(operatorId);
        notepad.setCreateUserId(operatorId);
        this.notepadService.save(notepad);
        return true;
    }
    
    /**
     * 根据id删除记事本<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/notepad/{id}", method = RequestMethod.DELETE)
    public boolean deleteNotepadById(@PathVariable(name = "id", required = true) String id) {
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        
        Notepad condition = new Notepad();
        condition.setId(id);
        condition.setVcid(vcid);
        condition.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
        condition.setTopicType(NotepadTopicTypeEnum.OPERATOR);
        condition.setTopicId(operatorId);
        this.notepadService.delete(condition);
        return true;
    }
    
    ///**
    // * 新增记事本分类<br/>
    // * <功能详细描述>
    // * @return [参数说明]
    // * 
    // * @return boolean [返回类型说明]
    // * @exception throws [异常类型] [异常说明]
    // * @see [类、类#方法、类#成员]
    // */
    //@ResponseBody
    //@RequestMapping(value = "/notepad", method = RequestMethod.POST)
    //public boolean addNotepad(Notepad notepad) {
    //    String vcid = WebContextUtils.getVcid();
    //    String operatorId = WebContextUtils.getOperatorId();
    //    
    //    notepad.setVcid(vcid);
    //    notepad.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
    //    notepad.setTopicType(NotepadTopicTypeEnum.OPERATOR);
    //    notepad.setTopicId(operatorId);
    //    if (StringUtils.isEmpty(notepad.getTitle())) {
    //        notepad.setTitle("记事本名称");
    //    }
    //    notepad.setLastUpdateUserId(operatorId);
    //    notepad.setCreateUserId(operatorId);
    //    this.notepadService.insert(notepad);
    //    return true;
    //}
    //
    ///**
    // * 更新记事本<br/>
    // * <功能详细描述>
    // * @return [参数说明]
    // * 
    // * @return boolean [返回类型说明]
    // * @exception throws [异常类型] [异常说明]
    // * @see [类、类#方法、类#成员]
    // */
    //@ResponseBody
    //@RequestMapping(value = "/notepad/{id}", method = RequestMethod.PUT)
    //public boolean updateNotepad(
    //        @PathVariable(name = "id", required = true) String id,
    //        Notepad notepad) {
    //    String vcid = WebContextUtils.getVcid();
    //    String operatorId = WebContextUtils.getOperatorId();
    //    notepad.setType(NotepadTypeEnum.OPERATOR_NOTEPAD);
    //    notepad.setTopicType(NotepadTopicTypeEnum.OPERATOR);
    //    notepad.setTopicId(operatorId);
    //    notepad.setVcid(vcid);
    //    if (StringUtils.isEmpty(notepad.getTitle())) {
    //        notepad.setTitle("记事本名称");
    //    }
    //    notepad.setLastUpdateUserId(operatorId);
    //    this.notepadService.updateById(id, notepad);
    //    return true;
    //}
    
    /**
     * 为记事本自动生成标题<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String generateNotepadTitle() {
        //String vcid = WebContextUtils.getVcid();
        //String operatorId = WebContextUtils.getOperatorId();
        String prefix = DateFormatUtils.format(new Date(), "yyyyMMddHH");
        String title = prefix + NEW_TITLE;
        
        String vcid = WebContextUtils.getVcid();
        String operatorId = WebContextUtils.getOperatorId();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("type", NotepadTypeEnum.OPERATOR_NOTEPAD);
        params.put("topicType", NotepadTopicTypeEnum.OPERATOR);
        params.put("topicId", operatorId);
        params.put("title", title);
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getParams().putAll(params);
        List<Notepad> ns = this.notepadService.queryList(querier);
        
        Pattern p = Pattern.compile(title + "\\((\\d+?)\\)$");
        if (CollectionUtils.isEmpty(ns)) {
            return title;
        } else {
            int[] index = new int[ns.size()];
            for (int i = 0; i < ns.size(); i++) {
                Matcher m = p.matcher(ns.get(i).getTitle());
                if (!m.matches()) {
                    index[i] = 0;
                } else {
                    index[i] = NumberUtils.toInt(m.group(1), 0);
                }
            }
            int maxIndex = NumberUtils.max(index);
            title = title + "(" + (maxIndex+1) + ")";
            return title;
        }
    }
    
    //public static void main(String[] args) {
    //    String t = "2020020422" + NEW_TITLE;
    //    String t0 = "2020020422" + NEW_TITLE;
    //    String t1 = "2020020422" + NEW_TITLE + "(1)";
    //    String t2 = "2020020422" + NEW_TITLE + "(2)";
    //    
    //    Pattern p = Pattern.compile(title + "\\((\\d+?)\\)$");
    //    Matcher m0 = p.matcher(t0);
    //    System.out.println(m0.matches());
    //    Matcher m1 = p.matcher(t1);
    //    System.out.println(m1.matches());
    //    Matcher m2 = p.matcher(t2);
    //    System.out.println(m2.matches());
    //    
    //    System.out.println(m1.group(1));
    //    System.out.println(m2.group(1));
    //}
}
