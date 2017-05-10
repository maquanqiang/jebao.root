package com.jebao.p2p.web.api.responseModel.base;

/**
 * Created by Jack on 2016/12/18.
 * 错误代码
 */
public enum ErrorEnum {
    imageVerifyCode(1001), // 图形验证码错误
    messageVefiryCode(1002), // 短信验证码
    noRegisterFundAccount(1100),//没有注册第三方资金账户
    userNotExists(9999); // 用户不存在

    private int value;
    private ErrorEnum(int val){
        this.value=val;
    }
    public int getValue(){
        return this.value;
    }
}
