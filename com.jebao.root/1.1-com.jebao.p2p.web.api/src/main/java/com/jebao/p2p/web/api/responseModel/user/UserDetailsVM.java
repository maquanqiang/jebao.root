package com.jebao.p2p.web.api.responseModel.user;

import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/14.
 */
public class UserDetailsVM extends ViewModel {
    //昵称（用户名）
    private String nickName;

    //真实姓名
    private String trueName;

    //邀请码
    private String invitationCode;

    //资金托管账户
    private String thirdAccount;

    //开户银行类别
    private String bankParentBankName;

    //银行卡号
    private String bankCardNo;

    //账户余额
    private BigDecimal balance;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getThirdAccount() {
        return thirdAccount;
    }

    public void setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
    }

    public String getBankParentBankName() {
        return bankParentBankName;
    }

    public void setBankParentBankName(String bankParentBankName) {
        this.bankParentBankName = bankParentBankName;
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
}
