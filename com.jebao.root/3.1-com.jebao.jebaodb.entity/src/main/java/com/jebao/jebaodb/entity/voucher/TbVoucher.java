package com.jebao.jebaodb.entity.voucher;

import java.math.BigDecimal;
import java.util.Date;

public class TbVoucher {
    private Long vId;

    private String vName;

    private BigDecimal vAmount;

    private Long vLoginId;

    private Long vIiId;

    private Date vBegintime;

    private Date vEndtime;

    private Integer vStatus;

    private BigDecimal vMinPrice;

    private Integer vMinCycle;

    private Integer vType;

    private Date vCreatetime;

    private Date vUpdatetime;

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
        this.vName = vName == null ? null : vName.trim();
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

    public Date getvUpdatetime() {
        return vUpdatetime;
    }

    public void setvUpdatetime(Date vUpdatetime) {
        this.vUpdatetime = vUpdatetime;
    }
}