package com.jebao.common.utils.encrypt;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/19.
 */
public class EncryptDESUtil_UnitTest {
    @Test
    public void example()
    {
        String source="www.com";
        String t1= EncryptDESUtil.toEncrypt(source);
        String t2= EncryptDESUtil.toDecrypt(t1);
        System.out.println(t1);
        System.out.println(t2);
    }
}
