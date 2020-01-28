/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月19日
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.model.LABankAccountTypeEnum;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;

/**
  * 银行账户业务层<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月19日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("laBankAccountService")
public class LABankAccountService {
    
    public LABankAccount findById(String bankAccountId) {
        AssertUtils.notNull(bankAccountId, "bankAccountId is empty.");
        
        String[] baids = StringUtils.splitByWholeSeparator(bankAccountId, "-");
        LABankAccountTypeEnum bankAccountType = LABankAccountTypeEnum
                .valueOf(baids[0]);
        LoanAccountTypeEnum loanAccountType = LoanAccountTypeEnum
                .valueOf(baids[1]);
        
        AssertUtils.notNull(bankAccountType, "bankAccountType is null.");
        AssertUtils.notNull(loanAccountType, "loanAccountType is null.");
        
        LABankAccount bankAccount = new LABankAccount();
        bankAccount.setBankAccountType(bankAccountType);
        bankAccount.setLoanAccountType(loanAccountType);
        bankAccount.setId(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        bankAccount.setCode(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        bankAccount.setName(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        
        return bankAccount;
    }
    
    public LABankAccount findMaxPriorityByType(
            LABankAccountTypeEnum bankAccountType,
            LoanAccountTypeEnum loanAccountType) {
        AssertUtils.notNull(bankAccountType, "bankAccountType is null.");
        AssertUtils.notNull(loanAccountType, "loanAccountType is null.");
        
        LABankAccount bankAccount = new LABankAccount();
        bankAccount.setBankAccountType(bankAccountType);
        bankAccount.setLoanAccountType(loanAccountType);
        bankAccount.setId(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        bankAccount.setCode(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        bankAccount.setName(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        
        return bankAccount;
    }
    
    public List<LABankAccount> queryListByType(
            LABankAccountTypeEnum bankAccountType,
            LoanAccountTypeEnum loanAccountType) {
        AssertUtils.notNull(bankAccountType, "bankAccountType is null.");
        AssertUtils.notNull(loanAccountType, "loanAccountType is null.");
        
        List<LABankAccount> resList = new ArrayList<>();
        LABankAccount bankAccount = new LABankAccount();
        bankAccount.setBankAccountType(bankAccountType);
        bankAccount.setLoanAccountType(loanAccountType);
        bankAccount.setId(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        bankAccount.setCode(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        bankAccount.setName(
                bankAccountType.getCode() + "-" + loanAccountType.getKey());
        resList.add(bankAccount);
        
        return resList;
    }
    
    public List<LABankAccount> queryListByTypes(
            LABankAccountTypeEnum[] bankAccountTypes,
            LoanAccountTypeEnum loanAccountType) {
        AssertUtils.notEmpty(bankAccountTypes, "bankAccountTypes is empty.");
        AssertUtils.notNull(loanAccountType, "loanAccountType is null.");
        
        List<LABankAccount> resList = new ArrayList<>();
        for (LABankAccountTypeEnum baTypeTemp : bankAccountTypes) {
            resList.addAll(queryListByType(baTypeTemp, loanAccountType));
        }
        
        return resList;
    }
    
}
