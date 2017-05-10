package com.jebao.p2p.web.api.responseModel.voucher;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import com.jebao.p2p.web.api.responseModel.ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lee on 2017/3/19.
 */
public class VoucherVM extends ViewModel {

    public VoucherVM(TbVoucher voucher) {
        this.setvMinPrice(voucher.getvMinPrice());
        this.setvStatus(voucher.getvStatus());
        this.setvMinCycle(voucher.getvMinCycle());
        this.setvLoginId(voucher.getvLoginId());
        this.setvAmount(voucher.getvAmount().setScale(2, BigDecimal.ROUND_DOWN));
        this.setvBegintime(voucher.getvBegintime());
        this.setvCreatetime(voucher.getvCreatetime());
        this.setvEndtime(voucher.getvEndtime());
        this.setvId(voucher.getvId());
        this.setvIiId(voucher.getvIiId());
        this.setvName(voucher.getvName());
        this.setvType(voucher.getvType());
        if(voucher.getvType()==0){
            this.setChannelType("新用户注册");
        }else{
             this.setChannelType("活动");
        }
        this.setvUpdatetime(voucher.getvUpdatetime());
    }

    private Long vId;

    private String vName;

    private BigDecimal vAmount;

    private Long vLoginId;

    private Long vIiId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vBegintime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date vEndtime;

    private Integer vStatus;

    private BigDecimal vMinPrice;

    private Integer vMinCycle;

    private Integer vType;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vCreatetime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vUpdatetime;

    private String channelType; //红包来源


    public Date getvUpdatetime() {
        return vUpdatetime;
    }

    public void setvUpdatetime(Date vUpdatetime) {
        this.vUpdatetime = vUpdatetime;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Long getvId() {
        return vId;
    }

    public void setvId(Long vId) {
        this.vId = vId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public BigDecimal getvAmount() {
        return vAmount;
    }

    public void setvAmount(BigDecimal vAmount) {
        this.vAmount = vAmount;
    }

    public Long getvLoginId() {
        return vLoginId;
    }

    public void setvLoginId(Long vLoginId) {
        this.vLoginId = vLoginId;
    }

    public Long getvIiId() {
        return vIiId;
    }

    public void setvIiId(Long vIiId) {
        this.vIiId = vIiId;
    }

    public Date getvBegintime() {
        return vBegintime;
    }

    public void setvBegintime(Date vBegintime) {
        this.vBegintime = vBegintime;
    }

    public Date getvEndtime() {
        return vEndtime;
    }

    public void setvEndtime(Date vEndtime) {
        this.vEndtime = vEndtime;
    }

    public Integer getvStatus() {
        return vStatus;
    }

    public void setvStatus(Integer vStatus) {
        this.vStatus = vStatus;
    }

    public BigDecimal getvMinPrice() {
        return vMinPrice;
    }

    public void setvMinPrice(BigDecimal vMinPrice) {
        this.vMinPrice = vMinPrice;
    }

    public Integer getvMinCycle() {
        return vMinCycle;
    }

    public void setvMinCycle(Integer vMinCycle) {
        this.vMinCycle = vMinCycle;
    }

    public Integer getvType() {
        return vType;
    }

    public void setvType(Integer vType) {
        this.vType = vType;
    }

    public Date getvCreatetime() {
        return vCreatetime;
    }

    public void setvCreatetime(Date vCreatetime) {
        this.vCreatetime = vCreatetime;
    }
}
