package com.jebao.jebaodb.entity.extEntity;

/**
 * Created by Jack on 2016/12/9.
 */
public class EnumModel {
    /**
     * 是否有效状态
     */
    public enum IsDel{
        有效(1),无效(2);
        private int value;
        private IsDel(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

/*    *//**
     * 注册(来源)平台
     *//*
    public enum Platform{
        pc(1),
        android(2),
        ios(3),
        weixin(4),
        mobile(5),//非android APP、ios APP、weixin 的其他移动平台,例如手机网页
        other(6);

        private int value;
        private Platform(int val){
            this.value=val;
        }
        public int getValue(){
            return this.value;
        }
    }*/
    /**
     * 注册(来源)平台
     */
    public enum Platform{
        pc(1),
        mobile(2),
        other(3);
        private int value;
        private Platform(int val){
            this.value=val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 注册(来源)平台分类
     */
    public enum PlatformType{
        pc(1),
        android(2),
        iphone(3),
        mac(4),
        weixin(5),
        other(6);
        private int value;
        private PlatformType(int val){
            this.value=val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 渠道
     */
    public enum Channel{
        me(0),
        other(1);
        private int value;
        private Channel(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 渠道分类
     */
    public enum ChannelType{
        me(0),
        other(1);
        private int value;
        private ChannelType(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    public enum BankCardChangeStatus{
        正常(0),更换审核中(1);
        private int value;
        private BankCardChangeStatus(int val){
            this.value=val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * POS机签约状态
     */
    public enum PosStatus{
        未签约(0),已签约(1);
        private int value;
        private PosStatus(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 资金收支状态
     */
    public enum FdBalanceStatus{
        收入(1),支出(2);
        private int value;
        private FdBalanceStatus(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 资金交易状态
     */
    public enum FdSerialStatus{
        失败(-1),处理中(0),成功(1);
        private int value;
        private FdSerialStatus(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 资金交易类型
     */
    public enum SerialType{
        充值(1),
        提现(2),
        投资冻结(3),
        借款入账(4),
        本金还款(5),
        付息(6),
        投资转账(7);
        private int value;
        private SerialType(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 资金类型
     */
    public enum FundType{
        全部(0),
        本金(1),
        利息(2);
        private int value;
        private FundType(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 收款状态(还款状态)
     */
    public enum IncomeStatus{
        冻结中(0),
        未还(1),
        已还(2);
        private int value;
        private IncomeStatus(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 标的状态
     */
    public enum BidStatus{
        初始待审核(0),
        审核被拒(1),
        招标中(2),
        满标(3),
        过期(4),
        起息中(6),
        还款中(7),
        完成(10);
        private int value;
        private BidStatus(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 投资状态
     */
    public enum FreezeStatus{
        冻结中(1),
        还款中(2),
        已还款(3),
        流标(4);
        private int value;
        private FreezeStatus(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }

    /**
     * 文章类别
     */
    public enum ArticleType{
        公司动态(1),
        平台公告(2),
        媒体报道(3);
        private int value;
        private ArticleType(int val){
            this.value = val;
        }
        public int getValue(){
            return this.value;
        }
    }
}
