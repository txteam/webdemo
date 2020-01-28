///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年3月5日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.charge;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.context.receiver.trading.AbstractTradingReceiver;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//
///**
// * 计费处理器工厂类<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2015年3月5日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("chargeHandleFactory")
//public class ChargeHandleFactory implements ApplicationContextAware,
//        InitializingBean {
//    
//    private static Map<LoanAccountTypeEnum, Map<ChargeHandleTypeEnum, String>> chargeHandleNameMap = new HashMap<LoanAccountTypeEnum, Map<ChargeHandleTypeEnum, String>>();
//    
//    private static ApplicationContext applicationContext;
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    @SuppressWarnings("rawtypes")
//    public void afterPropertiesSet() throws Exception {
//        Collection<ChargeHandle> handles = applicationContext.getBeansOfType(ChargeHandle.class)
//                .values();
//        for (ChargeHandle chargeHandleTemp : handles) {
//            @SuppressWarnings("unchecked")
//            List<LoanAccountTypeEnum> loanAccountTypes = chargeHandleTemp.getSupportLoanAccountTypes();
//            AssertUtils.notEmpty(loanAccountTypes,
//                    "loanAccountTypes is empty.caculatorTemp:{}",
//                    new Object[] { chargeHandleTemp.getClass() });
//            
//            for (LoanAccountTypeEnum loanAccountTypeTemp : loanAccountTypes) {
//                if (!chargeHandleNameMap.containsKey(loanAccountTypeTemp)) {
//                    chargeHandleNameMap.put(loanAccountTypeTemp,
//                            new HashMap<ChargeHandleTypeEnum, String>());
//                }
//                
//                ChargeHandleTypeEnum chargeHandleType = chargeHandleTemp.getChargeHandleType();
//                AssertUtils.notNull(chargeHandleType,
//                        "chargeHandleType is null.caculatorTemp:{}",
//                        new Object[] { chargeHandleTemp.getClass() });
//                AssertUtils.isTrue(!chargeHandleNameMap.get(loanAccountTypeTemp)
//                        .containsKey(chargeHandleType),
//                        "重复的贷款账户金额计算器.{}",
//                        new Object[] { chargeHandleTemp.getClass() });
//                
//                String beanName = chargeHandleTemp.getClass()
//                        .getAnnotation(Component.class)
//                        .value();
//                String scopeValue = chargeHandleTemp.getClass()
//                        .getAnnotation(Scope.class)
//                        .value();
//                AssertUtils.isTrue("prototype".equals(scopeValue),
//                        "scopeValue should prototype{}",
//                        new Object[] { chargeHandleTemp.getClass() });
//                AssertUtils.isTrue(!chargeHandleNameMap.get(loanAccountTypeTemp)
//                        .containsKey(chargeHandleType),
//                        "duplicat caculator loanAccounttype:{} amountCaculatorType:{} class:{}",
//                        new Object[] { loanAccountTypeTemp, chargeHandleType,
//                                chargeHandleTemp.getClass() });
//                chargeHandleNameMap.get(loanAccountTypeTemp)
//                        .put(chargeHandleType, beanName);
//            }
//        }
//    }
//    
//    /**
//     * @param applicationContext
//     * @throws BeansException
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        ChargeHandleFactory.applicationContext = applicationContext;
//    }
//    
//    /**
//     * 根据账户类型，以及金额计算器类型获取对应的金额计算器<br/>
//     * <功能详细描述>
//     * @param loanAccountType
//     * @param amountCaculatorType
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    private static String getChargeHandleBeanName(
//            LoanAccountTypeEnum loanAccountType,
//            ChargeHandleTypeEnum chargeHandleType) {
//        AssertUtils.isTrue(chargeHandleNameMap.containsKey(loanAccountType),
//                "loanAccountType:{} not exist.chargeHandleType:{}",
//                new Object[] { loanAccountType });
//        AssertUtils.isTrue(chargeHandleNameMap.get(loanAccountType)
//                .containsKey(chargeHandleType),
//                "loanAccountType:{} not exist.chargeHandleType:{} amountCaculatorType:{}",
//                new Object[] { loanAccountType, chargeHandleType });
//        
//        String beanName = chargeHandleNameMap.get(loanAccountType)
//                .get(chargeHandleType);
//        return beanName;
//    }
//    
//    /**
//      * 获取对应的计费处理器<br/>
//      * <功能详细描述>
//      * @param chargeHandleType
//      * @param loanAccount
//      * @param receiver
//      * @param request
//      * @return [参数说明]
//      * 
//      * @return CH [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @SuppressWarnings({ "unchecked" })
//    public static <CH extends ChargeHandle<?>> CH buildChargeHandle(
//            ChargeHandleTypeEnum chargeHandleType, LoanAccount loanAccount,
//            AbstractTradingReceiver<?> receiver) {
//        String beanName = getChargeHandleBeanName(loanAccount.getLoanAccountType(),
//                chargeHandleType);
//        CH resChargeHandle = (CH) ChargeHandleFactory.applicationContext.getBean(beanName,
//                loanAccount,
//                receiver);
//        return resChargeHandle;
//    }
//    
//    /**
//     * 获取对应的计费处理器<br/>
//     * <功能详细描述>
//     * @param chargeHandleType
//     * @param loanAccount
//     * @param receiver
//     * @param request
//     * @return [参数说明]
//     * 
//     * @return CH [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @SuppressWarnings({ "unchecked" })
//    public static <CH extends ChargeHandle<?>> CH buildChargeHandle(
//            ChargeHandleTypeEnum chargeHandleType, PaymentScheduleHandler handle) {
//        String beanName = getChargeHandleBeanName(handle.getLoanAccount()
//                .getLoanAccountType(), chargeHandleType);
//        CH resChargeHandle = (CH) ChargeHandleFactory.applicationContext.getBean(beanName,
//                handle);
//        return resChargeHandle;
//    }
//}
