package com.jebao.p2p.service.impl.voucher;

import com.jebao.jebaodb.dao.dao.voucher.TbVoucherDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import com.jebao.p2p.service.inf.voucher.ITbVoucherServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wenyq on 2017/3/13.
 */
@Service
public class TbVoucherServiceImpl implements ITbVoucherServiceInf {

    @Autowired
    private TbVoucherDao voucherDao;

    @Override
    public TbVoucher selectByVId(Long vId) {
        return voucherDao.selectByPrimaryKey(vId);
    }

    @Override
    public List<TbVoucher> getByMinWhere(BigDecimal vMinPrice, Integer vMinCycle,Long loginId) {
        return voucherDao.getByMinWhere(vMinPrice, vMinCycle,loginId);
    }

    /*
    * 注册成功后赠送优惠券
    * */
    @Override
    public long insertByBatch(Long loginId) {
        List<TbVoucher> tbVoucherList = new ArrayList<>();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 45);
        List<Integer> lamount = new ArrayList<>();
        lamount.add(6);
        lamount.add(10);
        lamount.add(20);
        lamount.add(40);
        lamount.add(58);
        lamount.add(118);
        lamount.add(318);
        lamount.add(518);
        List<Integer> listMinPrice = new ArrayList<>();
        listMinPrice.add(200);
        listMinPrice.add(2000);
        listMinPrice.add(6000);
        listMinPrice.add(10000);
        listMinPrice.add(10000);
        listMinPrice.add(50000);
        listMinPrice.add(100000);
        listMinPrice.add(200000);
        List<Integer> listMinCycle = new ArrayList<>();
        listMinCycle.add(3);
        listMinCycle.add(3);
        listMinCycle.add(3);
        listMinCycle.add(3);
        listMinCycle.add(12);
        listMinCycle.add(12);
        listMinCycle.add(12);
        listMinCycle.add(12);
        for (int i = 0; i < 8; i++) {
            TbVoucher tbVoucher = new TbVoucher();

            tbVoucher.setvName("优惠券" + lamount.get(i) + "元");
            tbVoucher.setvAmount(new BigDecimal(lamount.get(i)));
            tbVoucher.setvLoginId(loginId);
            tbVoucher.setvIiId(new Long(0));
            tbVoucher.setvBegintime(date);
            tbVoucher.setvEndtime(calendar.getTime());
            tbVoucher.setvStatus(0);
            tbVoucher.setvMinPrice(new BigDecimal(listMinPrice.get(i)));
            tbVoucher.setvMinCycle(listMinCycle.get(i));
            tbVoucher.setvCreatetime(date);
            tbVoucher.setvUpdatetime(date);
            tbVoucherList.add(tbVoucher);
        }

        return voucherDao.insertByBatch(tbVoucherList);
    }
    @Override
    public  int updateStatus(Long vId,Long vIiId)
    {
        return voucherDao.updateStatus(vId,vIiId);
    }

    @Override
    public List<TbVoucher> selectVoucherListForPage(TbVoucher voucher, PageWhere pageWhere) {
        return voucherDao.selectByConditionForPage(voucher, pageWhere);
    }

    @Override
    public int selectByConditionCount(TbVoucher voucher) {
        return voucherDao.selectByConditionCount(voucher);
    }
}