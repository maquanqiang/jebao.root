package com.jebao.p2p.web.api.utils.cached;

/**
 * Created by Administrator on 2017/1/17.
 */
public class CachedSetting {
    public CachedSetting(String key,int keyExpireSec,String desc){
        this.setKey(key);
        this.setKeyExpireSec(keyExpireSec);
        this.setDesc(desc);
    }
    public CachedSetting(String key,int keyExpireSec,int nullValueExpireSec,int keyMutexExpireSec,String desc){
        this.setKey(key);
        this.setKeyExpireSec(keyExpireSec);
        this.setNullValueExpireSec(nullValueExpireSec);
        this.setKeyMutexExpireSec(keyMutexExpireSec);
        this.setDesc(desc);
    }
    public CachedSetting(String key,int keyExpireSec,int nullValueExpireSec,int keyMutexExpireSec,int sleepMilliseconds,String desc){
        this.setKey(key);
        this.setKeyExpireSec(keyExpireSec);
        this.setNullValueExpireSec(nullValueExpireSec);
        this.setKeyMutexExpireSec(keyMutexExpireSec);
        this.setDesc(desc);
        this.setSleepMilliseconds(sleepMilliseconds);
    }
    private String key;
    private int keyExpireSec;
    private int nullValueExpireSec;
    private int keyMutexExpireSec;
    private int sleepMilliseconds;
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getKeyExpireSec() {
        return keyExpireSec;
    }

    public void setKeyExpireSec(int keyExpireSec) {
        this.keyExpireSec = keyExpireSec;
    }

    public int getNullValueExpireSec() {
        return nullValueExpireSec;
    }

    public void setNullValueExpireSec(int nullValueExpireSec) {
        this.nullValueExpireSec = nullValueExpireSec;
    }

    public int getKeyMutexExpireSec() {
        return keyMutexExpireSec;
    }

    public void setKeyMutexExpireSec(int keyMutexExpireSec) {
        this.keyMutexExpireSec = keyMutexExpireSec;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSleepMilliseconds() {
        return sleepMilliseconds;
    }

    public void setSleepMilliseconds(int sleepMilliseconds) {
        this.sleepMilliseconds = sleepMilliseconds;
    }
}
