package com.jebao.p2p.web.api.utils.captcha;

import java.time.LocalDateTime;

/**
 * Created by Jack on 2016/12/13.
 */
public class MessageRedisValue {
    /**
     * 验证码
     */
    private String verifyCode;
    /**
     * 该手机号已发送次数
     */
    private int sendNumber;
    /**
     * 上一次短信验证码发送时间
     */
    private LocalDateTime lastSendTime;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    public LocalDateTime getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(LocalDateTime lastSendTime) {
        this.lastSendTime = lastSendTime;
    }
}
