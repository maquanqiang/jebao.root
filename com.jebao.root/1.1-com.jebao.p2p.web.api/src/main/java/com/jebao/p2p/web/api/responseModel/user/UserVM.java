package com.jebao.p2p.web.api.responseModel.user;

import com.jebao.common.utils.regex.RegexUtil;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.p2p.web.api.responseModel.ViewModel;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * Created by Jack on 2016/12/16.
 */
public class UserVM extends ViewModel {

    public UserVM(TbUserDetails entity) {
        this.mobile = entity.getUdPhone();
        this.nickName = entity.getUdNickName();
        if (entity.getUdBankParentBankName() != null) {
            //正则提取，从字符串开头一直到 “银行”
            this.bankName = RegexUtil.getFirstMatch(entity.getUdBankParentBankName(), "^[\\u4e00-\\u9fa5]+银行");
        }
        String theBankCardNo = entity.getUdBankCardNo();
        if (theBankCardNo != null) {
            this.bankCardNo = theBankCardNo.replaceAll("(?<=\\d{4})\\d+(?=\\d{4})", " **** **** "); //银行卡号 中间替换 *
        }
        this.hasFundAccount = !StringUtils.isBlank(entity.getUdThirdAccount());
        this.balance = new BigDecimal(0);
        this.posStatus = entity.getUdPosStatus() == null ? 0 : entity.getUdPosStatus();
    }

    public UserVM(TbUserDetails entity, String inChangeBankName, String inChangeBankCardNo) {
        this(entity);
        if (inChangeBankName != null) {
            this.newBankName = RegexUtil.getFirstMatch(inChangeBankName, "^[\\u4e00-\\u9fa5]+银行");
        }
        if (inChangeBankCardNo != null) {
            this.newBankCardNo = inChangeBankCardNo.replaceAll("(?<=\\d{4})\\d+(?=\\d{4})", " **** **** ");
        }
    }

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 开户银行
     */
    private String bankName;
    /**
     * 银行卡号
     */
    private String bankCardNo;
    private String newBankName;
    /**
     * 新银行卡号，有在更换申请中的卡号。处于审核中状态
     */
    private String newBankCardNo;
    /**
     * 是否开通第三方账户
     */
    private boolean hasFundAccount;

    /**
     * POS机签约状态 0未签约  1签约
     */
    private int posStatus;

    public boolean getHasFundAccount() {
        return hasFundAccount;
    }

    public void setHasFundAccount(boolean hasFundAccount) {
        this.hasFundAccount = hasFundAccount;
    }

    /**
     * 账户余额
     */
    private BigDecimal balance;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNewBankCardNo() {
        return newBankCardNo;
    }

    public void setNewBankCardNo(String newBankCardNo) {
        this.newBankCardNo = newBankCardNo;
    }

    public String getNewBankName() {
        return newBankName;
    }

    public void setNewBankName(String newBankName) {
        this.newBankName = newBankName;
    }

    public int getPosStatus() {
        return posStatus;
    }

    public void setPosStatus(int posStatus) {
        this.posStatus = posStatus;
    }
}
