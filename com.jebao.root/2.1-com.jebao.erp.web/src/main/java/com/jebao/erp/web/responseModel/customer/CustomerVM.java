package com.jebao.erp.web.responseModel.customer;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.user.TbUserDetails;

import java.math.BigDecimal;

/**
 * Created by Lee on 2017/4/17.
 */

public class CustomerVM extends ViewModel {

    public CustomerVM() {
    }

    public CustomerVM(TbUserDetails details) {
        this.loginId = details.getUdLoginId();
        this.name = details.getUdTrueName();
        this.mobile = details.getUdPhone();
        this.loginName = details.getUdPhone();
        this.plantform = details.getUdPlatform();
    }

    private Long loginId;
    private String name;
    private String mobile;
    private String loginName;
    private Integer voucherCount;
    private Integer plantform;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public Integer getPlantform() {
        return plantform;
    }

    public void setPlantform(Integer plantform) {
        this.plantform = plantform;
    }

    public Integer getVoucherCount() {
        return voucherCount;
    }

    public void setVoucherCount(Integer voucherCount) {
        this.voucherCount = voucherCount;
    }
}
