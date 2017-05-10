package com.jebao.erp.web.utils.session;

/**
 * Created by Administrator on 2016/9/22.
 */
public class CurrentUser {
    private int Id;
    private String Name;
    private String UUID;
    private String FundAccount;
    private long LoanerId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
