package com.jebao.erp.web.responseModel.loaner;

import com.jebao.erp.web.responseModel.ViewModel;
import com.jebao.jebaodb.entity.loaner.TbLoaner;

/**
 * Created by wangwei on 2016/11/26.
 */
public class UserInfoVM extends ViewModel {
    public UserInfoVM(TbLoaner entity){
        this.nickName = entity.getlNickName();
        this.trueName = entity.getlTrueName();
        this.idNumber = entity.getlIdNumber();
        this.email = entity.getlEmail();
        this.sex = entity.getlSex();
        this.age = entity.getlAge();
    }

    private String nickName;

    private String trueName;

    private String idNumber;

    private String email;

    private Integer sex;

    private Integer age;

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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
