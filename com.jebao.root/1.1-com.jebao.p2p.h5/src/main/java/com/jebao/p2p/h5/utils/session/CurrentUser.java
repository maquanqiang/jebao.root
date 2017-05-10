package com.jebao.p2p.h5.utils.session;

/**
 * Created by wenyq on 2017/2/17.
 */
public class CurrentUser {
    private long Id;
    private String Name;
    private String UUID;
    private String FundAccount;
    private long LoanerId;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getFundAccount() {
        return FundAccount;
    }

    public void setFundAccount(String fundAccount) {
        FundAccount = fundAccount;
    }

    public long getLoanerId() {
        return LoanerId;
    }

    public void setLoanerId(long loanerId) {
        LoanerId = loanerId;
    }
}
