package com.jebao.jebaodb.entity.user.search;

import com.jebao.jebaodb.entity.extEntity.PageWhere;

/**
 * Created by Jack on 2016/12/12.
 */
public class UserSM extends PageWhere {
    private String mobile;
    private String invitationCode;
    private int managerId;
    private String trueName;


    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
