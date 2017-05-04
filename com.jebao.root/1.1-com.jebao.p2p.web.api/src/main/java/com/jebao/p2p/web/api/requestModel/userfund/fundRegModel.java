package com.jebao.p2p.web.api.requestModel.userfund;

import com.jebao.thirdPay.fuiou.model.reg.RegRequest;
import com.jebao.thirdPay.fuiou.util.MD5Util;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jack on 2016/12/26.
 */
public class fundRegModel {

    public RegRequest toRequest(){
        RegRequest reqData = new RegRequest();
        reqData.setCust_nm(this.realName); //客户姓名
        reqData.setCertif_id(this.idCard); //身份证号
        reqData.setMobile_no(""); //手机号码，在业务逻辑层填写
        reqData.setEmail("");
        reqData.setCity_id(this.bankCityCode); //开户行城市代码
        reqData.setParent_bank_id(this.bankCode); //开户行代码
        reqData.setBank_nm(""); //开户行支行名称，非必填
        reqData.setCapAcntNm(""); //客户姓名，填写空值。富友取客户姓名 字段值代替
        reqData.setCapAcntNo(this.bankCardNo); //银行卡号
        //密码要 MD5 加密小写
        String m5dPass = MD5Util.encode(this.payPassword,"UTF-8");
        reqData.setPassword(m5dPass); //提现密码，不填默认为手机号后6位
        reqData.setLpassword(""); //登录密码，不填写默认为手机号码后6位
        reqData.setRem("");

        return reqData;
    }

    @NotBlank(message="真实姓名不能为空")
    private String realName;
    @NotBlank(message="身份证号不能为空")
    private String idCard;
    @NotBlank(message="开户银行不能为空")
    private String bankCode;
    @NotBlank(message="开户行省市不能为空")
    private String bankProvinceCode;
    @NotBlank(message="开户行区县不能为空")
    private String bankCityCode;
    @NotBlank(message="银行卡号不能为空")
    private String bankCardNo;
    @NotBlank(message="支付密码不能为空")
    private String payPassword;
    @NotBlank(message="确认支付密码不能为空")
    private String payPasswordAgain;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPayPasswordAgain() {
        return payPasswordAgain;
    }

    public void setPayPasswordAgain(String payPasswordAgain) {
        this.payPasswordAgain = payPasswordAgain;
    }
}
