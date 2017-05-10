package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.util.Date;

/**
 * Created by Lee on 2016/12/12.
 */
public class LoanerInfoVM extends ViewModel {


    public LoanerInfoVM(TbLoaner entity){
        this.lId = entity.getlId();
        this.lLoginId = entity.getlLoginId();
        this.lNickName = entity.getlNickName();
        this.lPhone = entity.getlPhone();
        String trueName = entity.getlTrueName().substring(0,1);
        String lIdNumber = entity.getlIdNumber().substring(0,6);
        this.lTrueName = trueName +"**";
        this.lIdNumber = lIdNumber + "********";
        this.lEmail = entity.getlEmail();
        this.lSex = entity.getlSex();
        this.lAge = entity.getlAge();
        this.lHomeAdd = entity.getlHomeAdd();
        this.lHkadr = entity.getlHkadr();
        this.lMaritalStatus = entity.getlMaritalStatus();
        this.lIshaveHouse = entity.getlIshaveHouse();
        this.lIshaveCar = entity.getlIshaveCar();
        this.lPoliticsStatus = entity.getlPoliticsStatus();
        this.lCreditStatus = entity.getlCreditStatus();
        this.lMonthlySalary = entity.getlMonthlySalary();
        this.lEducation = entity.getlEducation();
        this.lWorkCity = entity.getlWorkCity();
    }

    private Long lId;

    private Long lLoginId;

    private String lNickName;

    private String lPhone;

    private String lTrueName;

    private String lIdNumber;

    private String lEmail;

    private Integer lSex;

    private Integer lAge;

    private String lHomeAdd;

    private String lHkadr;

    private Integer lMaritalStatus;

    private Integer lIshaveHouse;

    private Integer lIshaveCar;

    private Integer lPoliticsStatus;

    private String lCreditStatus;

    private Integer lMonthlySalary;

    private Integer lEducation;

    private String lWorkCity;

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public Long getlLoginId() {
        return lLoginId;
    }

    public void setlLoginId(Long lLoginId) {
        this.lLoginId = lLoginId;
    }

    public String getlNickName() {
        return lNickName;
    }

    public void setlNickName(String lNickName) {
        this.lNickName = lNickName;
    }

    public String getlPhone() {
        return lPhone;
    }

    public void setlPhone(String lPhone) {
        this.lPhone = lPhone;
    }

    public String getlTrueName() {
        return lTrueName;
    }

    public void setlTrueName(String lTrueName) {
        this.lTrueName = lTrueName;
    }

    public String getlIdNumber() {
        return lIdNumber;
    }

    public void setlIdNumber(String lIdNumber) {
        this.lIdNumber = lIdNumber;
    }

    public String getlEmail() {
        return lEmail;
    }

    public void setlEmail(String lEmail) {
        this.lEmail = lEmail;
    }

    public Integer getlSex() {
        return lSex;
    }

    public void setlSex(Integer lSex) {
        this.lSex = lSex;
    }

    public Integer getlAge() {
        return lAge;
    }

    public void setlAge(Integer lAge) {
        this.lAge = lAge;
    }

    public String getlHomeAdd() {
        return lHomeAdd;
    }

    public void setlHomeAdd(String lHomeAdd) {
        this.lHomeAdd = lHomeAdd;
    }

    public String getlHkadr() {
        return lHkadr;
    }

    public void setlHkadr(String lHkadr) {
        this.lHkadr = lHkadr;
    }

    public Integer getlMaritalStatus() {
        return lMaritalStatus;
    }

    public void setlMaritalStatus(Integer lMaritalStatus) {
        this.lMaritalStatus = lMaritalStatus;
    }

    public Integer getlIshaveHouse() {
        return lIshaveHouse;
    }

    public void setlIshaveHouse(Integer lIshaveHouse) {
        this.lIshaveHouse = lIshaveHouse;
    }

    public Integer getlIshaveCar() {
        return lIshaveCar;
    }

    public void setlIshaveCar(Integer lIshaveCar) {
        this.lIshaveCar = lIshaveCar;
    }

    public Integer getlPoliticsStatus() {
        return lPoliticsStatus;
    }

    public void setlPoliticsStatus(Integer lPoliticsStatus) {
        this.lPoliticsStatus = lPoliticsStatus;
    }

    public String getlCreditStatus() {
        return lCreditStatus;
    }

    public void setlCreditStatus(String lCreditStatus) {
        this.lCreditStatus = lCreditStatus;
    }

    public Integer getlMonthlySalary() {
        return lMonthlySalary;
    }

    public void setlMonthlySalary(Integer lMonthlySalary) {
        this.lMonthlySalary = lMonthlySalary;
    }

    public Integer getlEducation() {
        return lEducation;
    }

    public void setlEducation(Integer lEducation) {
        this.lEducation = lEducation;
    }

    public String getlWorkCity() {
        return lWorkCity;
    }

    public void setlWorkCity(String lWorkCity) {
        this.lWorkCity = lWorkCity;
    }

}
