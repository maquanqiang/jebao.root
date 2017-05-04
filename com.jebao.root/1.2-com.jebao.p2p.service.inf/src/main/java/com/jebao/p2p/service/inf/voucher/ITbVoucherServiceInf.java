package com.jebao.p2p.service.inf.voucher;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.voucher.TbVoucher;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wenyq on 2017/3/13.
 */
public interface ITbVoucherServiceInf {
    TbVoucher selectByVId(Long vId);
    List<TbVoucher> getByMinWhere( BigDecimal vMinPrice,Integer vMinCycle,Long loginId);

    long insertByBatch(Long loginId);
    int updateStatus(Long vId,Long vIiId);

    List<TbVoucher> selectVoucherListForPage(TbVoucher voucher, PageWhere pageWhere);

    int selectByConditionCount(TbVoucher voucher);
}
