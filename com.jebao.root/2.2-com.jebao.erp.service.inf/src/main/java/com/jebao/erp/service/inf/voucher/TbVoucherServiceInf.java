package com.jebao.erp.service.inf.voucher;

import com.jebao.jebaodb.entity.voucher.TbVoucher;

/**
 * Created by Lee on 2017/4/17.
 */
public interface TbVoucherServiceInf {

    Integer selectByConditionCount(TbVoucher voucher);

    public void sendVoucher(Long investId);
}
