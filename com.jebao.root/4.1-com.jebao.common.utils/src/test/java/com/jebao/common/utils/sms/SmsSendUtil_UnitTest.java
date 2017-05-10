package com.jebao.common.utils.sms;

import org.junit.Test;

/**
 * Created by Administrator on 2016/11/4.
 */
public class SmsSendUtil_UnitTest {
    @Test
    public void example()
    {
        System.out.println(SmsSendUtil.sendVerifyCode("159010xxxxx","111000"));
    }
}
