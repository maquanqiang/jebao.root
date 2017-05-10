package com.jebao.common.utils.encrypt;

/**
 * Created by Administrator on 2016/10/13.
 */
public class EncryptDESUtil {
    private static String DES_KEY="BAA31820";
    public static String toEncrypt(String source)
    {
        EncryptUtil EncryptUtil = new EncryptUtil();
        try
        {
           return EncryptUtil.encryptToDES(DES_KEY, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String toDecrypt(String source)
    {
        EncryptUtil EncryptUtil = new EncryptUtil();
        try
        {
            return EncryptUtil.decryptByDES(DES_KEY, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
    public static void main(String[] args) {
        String source="www.com";
        String t1= EncryptDESUtil.toEncrypt(source);
        String t2= EncryptDESUtil.toDecrypt(t1);
    }*/
}
