package com.jebao.p2p.web.api.requestModel.voucher;

import com.jebao.jebaodb.entity.voucher.TbVoucher;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by wenyq on 2017/3/14.
 */
public class VoucherForm {

    public TbVoucher toEntity(VoucherForm form){
        TbVoucher voucher = new TbVoucher();
        voucher.setvLoginId(form.getvLoginId());
        voucher.setvStatus(form.getvStatus());
        voucher.setvMinCycle(form.getvMinCycle());
        voucher.setvMinPrice(form.getvMinPrice());
        return voucher;
    }



    private Long vLoginId;
    private Integer vMinCycle;
    @DecimalMin(value="1",message="投资金额有误")
    @NotNull(message = "投资金额不能为空")
    private BigDecimal vMinPrice;

    private Integer vStatus;


    public Integer getvStatus() {
        return vStatus;
    }

    public void setvStatus(Integer vStatus) {
        this.vStatus = vStatus;
    }

    public Long getvLoginId() {
        return vLoginId;
    }

    public void setvLoginId(Long vLoginId) {
        this.vLoginId = vLoginId;
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

    public void setvMinCycle(int vMinCycle) {
        this.vMinCycle = vMinCycle;
    }


}
