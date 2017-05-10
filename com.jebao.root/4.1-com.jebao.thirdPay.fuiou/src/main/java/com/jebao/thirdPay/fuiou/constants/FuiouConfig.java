package com.jebao.thirdPay.fuiou.constants;

import java.util.HashMap;

/**
 * Created by Lee on 2016/12/10.
 */
public class FuiouConfig {
    public final static String url= ProjectSetting.getConfigProperty("project.thirdPay.fuiou.url");
    public final static String platLoginName= ProjectSetting.getConfigProperty("project.thirdPay.fuiou.platLoginName");
    public final static String platNumber= ProjectSetting.getConfigProperty("project.thirdPay.fuiou.platNumber");
    public final static String notifyUrl= ProjectSetting.getConfigProperty("project.thirdPay.fuiou.notifyUrl");
    public final static String pageCallUrl= ProjectSetting.getConfigProperty("project.thirdPay.fuiou.pageCallUrl");
    public final static String Jebao_Notify_Origin = ProjectSetting.getConfigProperty("project.webapi.origin");
    public final static String documentVersion = "0.44"; // 0.49会导致不返回正常错误信息
    public final static String Success_Code = "0000"; // 富有接口返回成功代码

    private static HashMap<String,String> respDesc = null;
    private static void initRespDesc() {
        respDesc = new HashMap<>();
        respDesc.put("5019","数据校验失败");
        respDesc.put("5029","调用交易查询接口过于频繁");
        respDesc.put("5343","用户已开户");
        respDesc.put("5836","不允许信用卡注册");
        respDesc.put("5837","卡号和行别不一致");
        respDesc.put("5850","已经存在相同银行卡号注册的用户");
        respDesc.put("5851","已经存在相同证件号注册的用户");
        respDesc.put("5855","该手机号绑定卡号超过2张(在代收付系统解约)");
        respDesc.put("5891","用户已开户");
    }

    public static HashMap<String, String> getRespDesc() {
        if (respDesc == null){
            initRespDesc();
        }
        return respDesc;
    }
}
