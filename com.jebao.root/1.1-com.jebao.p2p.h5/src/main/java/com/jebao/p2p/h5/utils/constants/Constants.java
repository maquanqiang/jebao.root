package com.jebao.p2p.h5.utils.constants;

/**
 * Created by wenyq on 2017/2/17.
 */
public class Constants {
    private static String DOMAIN = "JEBAO_P2P_";
    //---------------CACHE_NAME---------------

    //---------------COOKIE_NAME---------------

    //---------------CAPTCHA---------------
    public static int CAPTCHA_TOKEN_EXPIRE_TIME = 60 * 10;//10-绝对时间
    public static final String CAPTCHA_TOKEN_CACHE_NAME = DOMAIN + "captcha_";
    public static final String CAPTCHA_TOKEN_COOKIE_NAME = DOMAIN + "CT";
    //---------------LOGIN_SESSION---------------
    public static int LOGIN_SESSION_EXPIRE_TIME = 60 * 60 * 12;//12小时-相对时间
    public static final String LOGIN_SESSION_CACHE_NAME = DOMAIN + "login_session_";
    public static final String LOGIN_SESSION_COOKIE_NAME = DOMAIN + "LS";
    //配置信息
    public static final String WebApiOrgin = ProjectSetting.getConfigProperty("project.webapi.origin");
    //手续费
    public static String COMMISSION_CHARGE = ProjectSetting.getConfigProperty("project.commission_charge") == null ? "0" : ProjectSetting.getConfigProperty("project.commission_charge");
    //静态资源文件的路径
    public static final String STATIC_FILE_PATH=ProjectSetting.getConfigProperty("project.static.filePath");
}
