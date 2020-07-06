/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年6月12日
 * <修改描述:>
 */
package com.tx.apitest.clientinfo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
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
import com.tx.local.clientinfo.client.ClientPromotionChannelAPIClient;
import com.tx.local.clientinfo.model.ClientPromotionChannel;

/**
 * 客户来源APIClient测试<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SpringBootTest(classes = ClientPromotionChannelAPIClient.class)
@RunWith(SpringRunner.class)
@Import({ HttpClientConfiguration.class, FeignAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class })
@EnableFeignClients(clients = ClientPromotionChannelAPIClient.class)
public class ClientPromotionChannelAPIClientTest {
    
    @Autowired
    private ClientPromotionChannelAPIClient client;
    
    /** 测试运行时间 */
    private String id = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
    
    private List<ClientPromotionChannel> testList = new ArrayList<>();
    
    /**
     * 为实体设值<br/>
     * <功能详细描述>
     * @param entity
     * @param i [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private ClientPromotionChannel buildEntity(String suffix) {
        ClientPromotionChannel entity = new ClientPromotionChannel();
        entity.setCode("test_code_" + id + "_" + suffix);
        entity.setName("test_name_" + id + "_" + suffix);
        return entity;
    }
    
    /**
     * 如果不存在则插入
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Before
    public void before() {
        List<ClientPromotionChannel> resList = client.queryList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.like("name", "test_name_%"))
                        .querier());
        Map<String, ClientPromotionChannel> entityMap = new HashMap<>();
        resList.stream().forEach(entity -> {
            entityMap.put(entity.getName(), entity);
        });
        
        for (int i = 0; i < 5; i++) {
            ClientPromotionChannel entity = buildEntity(String.valueOf(i));
            if (!entityMap.containsKey(entity.getName())) {
                testList.add(client.insert(entity));
            } else {
                testList.add(entityMap.get(entity.getName()));
            }
        }
    }
    
    @After
    public void after() {
        List<ClientPromotionChannel> resList = client.queryList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.like("name", "test_name_%"))
                        .querier());
        for (ClientPromotionChannel temp : resList) {
            client.deleteById(temp.getId());
        }
    }
    
    @Test
    public void testInsert() {
        ClientPromotionChannel cs = buildEntity("4Insert");
        cs = client.insert(cs);
        assertTrue(cs != null && !StringUtils.isEmpty(cs.getId()));
        
        testList.add(cs);
    }
    
    @Test
    public void testDeleteById() {
        ClientPromotionChannel cs = testList.get(0);
        
        boolean flag = client.deleteById(cs.getId());
        assertTrue(flag);
        
        testList.remove(0);
    }
    
    @Test
    public void testUpdateById() {
        ClientPromotionChannel cs = testList.get(0);
        if (cs.isValid()) {
            client.disableById(cs.getId());
        }
        
        //验证更新字段
        cs.setRemark("testremark");
        boolean flag = client.updateById(cs.getId(), cs);
        
        ClientPromotionChannel res = client.findById(cs.getId());
        assertTrue(flag && "testremark".equals(res.getRemark()));
    }
    
    @Test
    public void testDisableById() {
        ClientPromotionChannel cs = testList.get(0);
        if (!cs.isValid()) {
            client.enableById(cs.getId());
        }
        
        boolean flag = client.disableById(cs.getId());
        cs = client.findById(cs.getId());
        assertTrue(flag && !cs.isValid());
    }
    
    @Test
    public void testEnableById() {
        ClientPromotionChannel cs = testList.get(0);
        if (cs.isValid()) {
            client.disableById(cs.getId());
        }
        
        boolean flag = client.enableById(cs.getId());
        
        cs = client.findById(cs.getId());
        assertTrue(flag && cs.isValid());
    }
    
    @Test
    public void testFindById() {
        ClientPromotionChannel cs = testList.get(0);
        if (cs.isValid()) {
            client.disableById(cs.getId());
        }
        
        ClientPromotionChannel res = client.findById(cs.getId());
        assertTrue(res != null && res.getId().equals(cs.getId()));
    }
    
    @Test
    public void testQueryList() {
        List<ClientPromotionChannel> resList = client.queryList(null,
                QuerierBuilder.newInstance()
                        .addFilter(Filter.isNotNull("name"))
                        .addFilter(Filter.ne("id", "xxxxasdfbb"))
                        .addOrder("createDate")
                        .addOrder("code")
                        .querier());
        assertTrue(resList.size() > 0);
    }
    
    @Test
    public void testQueryPagedList() {
        PagedList<ClientPromotionChannel> resPagedList = client.queryPagedList(
                null,
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
    
    //    @Test
    //    public void testCount() {
    //        int count = client.count(null,
    //                QuerierBuilder.newInstance()
    //                        .addFilter(Filter.isNotNull("name"))
    //                        .addFilter(Filter.ne("id", "xxxxasdfbb"))
    //                        .addOrder("createDate")
    //                        .addOrder("code")
    //                        .querier());
    //        assertTrue(count > 0);
    //    }
    //    
}
