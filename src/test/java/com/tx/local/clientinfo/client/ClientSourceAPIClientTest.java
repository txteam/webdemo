/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年6月12日
 * <修改描述:>
 */
package com.tx.local.clientinfo.client;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.clientinfo.model.ClientSource;

/**
 * 客户来源APIClient测试<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RunWith(SpringRunner.class)
@Import({ HttpClientConfiguration.class, FeignAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class })
@SpringBootTest(classes = ClientSourceAPIClient.class)
@EnableFeignClients(clients = ClientSourceAPIClient.class)
public class ClientSourceAPIClientTest {
    
    @Autowired
    private ClientSourceAPIClient client;
    
    /** 主键 "yyyyMMddHHmmssSSS" */
    protected String id = DateFormatUtils.format(new Date(), "yyyy");
    
    /** 实体列表 */
    protected List<ClientSource> entityList = new ArrayList<>();
    
    /** 构建实体 */
    protected ClientSource buildEntity(String suffix) {
        ClientSource entity = new ClientSource();
        entity.setCode("test_code_" + id + "_" + suffix);
        entity.setName("test_name_" + id + "_" + suffix);
        
        return entity;
    }
    
    /** 前置方法 */
    @Before
    public void before() {
        List<ClientSource> resList = client.queryList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.like("name", "test_name_%"))
                        .querier());
        Map<String, ClientSource> entityMap = new HashMap<>();
        resList.stream().forEach(entity -> {
            entityMap.put(entity.getName(), entity);
        });
        
        for (int i = 0; i < 5; i++) {
            ClientSource entity = buildEntity(String.valueOf(i));
            if (!entityMap.containsKey(entity.getName())) {
                entityList.add(client.insert(entity));
            } else {
                entityList.add(entityMap.get(entity.getName()));
            }
        }
    }
    
    /** 后置方法 */
    @After
    public void after() {
        List<ClientSource> resList = client.queryList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.like("name", "test_name_%"))
                        .querier());
        
        for (ClientSource temp : resList) {
            client.deleteById(temp.getId());
        }
    }
    
    //测试插入
    @Test
    public void testInsert() {
        ClientSource cs = buildEntity("4insert");
        cs = client.insert(cs);
        
        assertTrue(cs != null && !StringUtils.isEmpty(cs.getId()));
        entityList.add(cs);
    }
    
    //测试删除
    @Test
    public void testDeleteById() {
        ClientSource cs = entityList.get(0);
        
        boolean flag = client.deleteById(cs.getId());
        assertTrue(flag);
        
        entityList.remove(0);
    }
    
    //测试删除
    @Test
    public void testDeleteByCode() {
        ClientSource cs = entityList.get(0);
        
        boolean flag = client.deleteByCode(cs.getCode());
        assertTrue(flag);
        
        entityList.remove(0);
    }
    
    //测试更新
    @Test
    public void testUpdateById() {
        ClientSource cs = entityList.get(0);
        if (cs.isValid()) {
            client.disableById(cs.getId());
        }
        
        //验证更新字段
        cs.setRemark("testremark");
        boolean flag = client.updateById(cs.getId(), cs);
        
        ClientSource res = client.findById(cs.getId());
        assertTrue(flag && "testremark".equals(res.getRemark()));
    }
    
    //测试禁用
    @Test
    public void testDisableById() {
        ClientSource cs = entityList.get(0);
        if (!cs.isValid()) {
            client.enableById(cs.getId());
        }
        
        boolean flag = client.disableById(cs.getId());
        cs = client.findById(cs.getId());
        assertTrue(flag && !cs.isValid());
    }
    
    //测试启用
    @Test
    public void testEnableById() {
        ClientSource cs = entityList.get(0);
        if (cs.isValid()) {
            client.disableById(cs.getId());
        }
        
        boolean flag = client.enableById(cs.getId());
        
        cs = client.findById(cs.getId());
        assertTrue(flag && cs.isValid());
    }
    
    //测试查询单例
    @Test
    public void testFindById() {
        ClientSource cs = entityList.get(0);
        
        ClientSource res = client.findById(cs.getId());
        assertTrue(res != null && res.getId().equals(cs.getId()));
    }
    
    //测试查询单例
    @Test
    public void testFindByCode() {
        ClientSource cs = entityList.get(0);
        
        ClientSource res = client.findByCode(cs.getCode());
        assertTrue(res != null && res.getCode().equals(cs.getCode()));
    }
    
    //测试查询列表
    @Test
    public void testQueryList() {
        List<ClientSource> resList = client.queryList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.isNotNull("name"))
                        .addFilter(Filter.ne("id", "xxxxasdfbb"))
                        .addOrder("createDate")
                        .addOrder("code")
                        .querier());
        assertTrue(resList.size() > 0);
    }
    
    //测试查询分页列表
    @Test
    public void testQueryPagedList() {
        PagedList<ClientSource> resPagedList = client.queryPagedList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.isNotNull("name"))
                        .addFilter(Filter.ne("id", "xxxxasdfbb"))
                        .addOrder("createDate")
                        .addOrder("code")
                        .querier(),
                1,
                100);
        assertTrue(resPagedList.getCount() > 0);
    }
    
    //查询数量
    @Test
    public void testCount() {
        int count = client.count(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.isNotNull("name"))
                        .addFilter(Filter.ne("id", "xxxxasdfbb"))
                        .addOrder("createDate")
                        .addOrder("code")
                        .querier());
        assertTrue(count > 0);
    }
    
    //查询数量
    @Test
    public void testExists() {
        boolean flag = client.exists(
                QuerierBuilder.newInstance()
                        .addFilter(Filter.like("name", "test_name%"))
                        .querier(),
                null);
        assertTrue(flag);
    }
}
