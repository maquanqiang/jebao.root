package com.jebao.erp.web.requestModel.loaner;

import com.jebao.erp.web.requestModel.base._BaseForm;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by Administrator on 2016/11/26.
 */
public class LoanerIM extends _BaseForm {
    private Long id;

    @NotBlank(message="注册手机号不能为空")
    @Pattern(regexp = "(^(13|14|15|17|18)\\d{9}$)",message = "注册手机号格式不正确！")
    private String phone;

    private String homeAdd;

    private String hkadr;

    private Integer maritalStatus;

    private Integer ishaveHouse;

    private Integer ishaveCar;

    private Integer politicsStatus;

    private String creditStatus;

    private Integer monthlySalary;

    private Integer education;

    private String workCity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getHkadr() {
        return hkadr;
    }

    public void setHkadr(String hkadr) {
        this.hkadr = hkadr;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getIshaveHouse() {
        return ishaveHouse;
    }

    public void setIshaveHouse(Integer ishaveHouse) {
        this.ishaveHouse = ishaveHouse;
    }

    public Integer getIshaveCar() {
        return ishaveCar;
    }

    public void setIshaveCar(Integer ishaveCar) {
        this.ishaveCar = ishaveCar;
    }

    public Integer getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(Integer politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Integer monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }
}
