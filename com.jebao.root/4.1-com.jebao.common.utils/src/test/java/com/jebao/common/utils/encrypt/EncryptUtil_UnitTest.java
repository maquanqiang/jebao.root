package com.jebao.common.utils.encrypt;

import org.junit.Test;

import javax.crypto.SecretKey;

/**
 * Created by Administrator on 2016/10/19.
 */
public class EncryptUtil_UnitTest {
    @Test
    public void example()
    {
        EncryptUtil EncryptUtil = new EncryptUtil();
        String source = "www.jebao.p2p";
        System.out.println("Hello经过MD5:" + EncryptUtil.encryptToMD5(source));
        System.out.println("Hello经过SHA:" + EncryptUtil.encryptToSHA(source));
        System.out.println("========随机生成Key进行加解密==============");
        // 生成一个DES算法的密匙
        SecretKey key = EncryptUtil.createSecretDESKey();
        String str1 = EncryptUtil.encryptToDES(key, source);
        System.out.println("DES加密后为:" + str1);
        // 使用这个密匙解密
        String str2 = EncryptUtil.decryptByDES(key, str1);
        System.out.println("DES解密后为：" + str2);

        // 生成一个AES算法的密匙
        SecretKey key1 = EncryptUtil.createSecretAESKey();
        String stra = EncryptUtil.encryptToAES(key1, source);
        System.out.println("AES加密后为:" + stra);
        // 使用这个密匙解密
        String strb = EncryptUtil.decryptByAES(key1, stra);
        System.out.println("AES解密后为：" + strb);
        System.out.println("========指定Key进行加解密==============");
        try {
            String AESKey = EncryptUtil.getAESKey(EncryptUtil.encryptToSHA(source));
            String DESKey = EncryptUtil.getDESKey(EncryptUtil.encryptToSHA(source));
            System.out.println(AESKey);
            System.out.println(DESKey);
            String str11 = EncryptUtil.encryptToDES(DESKey, source);
            System.out.println("DES加密后为:" + str11);
            // 使用这个密匙解密
            String str12 = EncryptUtil.decryptByDES(DESKey, str11);
            System.out.println("DES解密后为：" + str12);

            // 生成一个AES算法的密匙
            String strc = EncryptUtil.encryptToAES(AESKey, source);
            System.out.println("AES加密后为:" + strc);
            // 使用这个密匙解密
            String strd = EncryptUtil.decryptByAES(AESKey, strc);
            System.out.println("AES解密后为：" + strd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
