package com.jebao.p2p.service.impl.user;

import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.dao.dao.user.TbFundsDetailsDao;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.FundDetailSM;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.p2p.service.inf.user.IFundsDetailsServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/2.
 */
@Service
public class FundsDetailsServiceImpl implements IFundsDetailsServiceInf {
    @Autowired
    private TbFundsDetailsDao tbFundsDetailsDao;
    @Autowired
    private TbIncomeDetailDao incomeDetailDao;

    @Override
    public List<TbFundsDetails> selectFundsDetailsByLoginIdForPage(Long loginId, PageWhere page) {
        TbFundsDetails record = new TbFundsDetails();
        record.setFdLoginId(loginId);
        record.setFdSerialStatus(EnumModel.FdSerialStatus.成功.getValue());
        return tbFundsDetailsDao.selectByParamsForPage(record, page);
    }

    @Override
    public int selectFundsDetailsByLoginIdForPageCount(Long loginId) {
        TbFundsDetails record = new TbFundsDetails();
        record.setFdLoginId(loginId);
        record.setFdSerialStatus(EnumModel.FdSerialStatus.成功.getValue());
        return tbFundsDetailsDao.selectByParamsForPageCount(record);
    }

    @Override
    public List<TbIncomeDetail> selectFundList(FundDetailSM record, PageWhere pageWhere) {
        return incomeDetailDao.selectFundList(record, pageWhere);
    }

    @Override
    public int selectFundCount(FundDetailSM record) {
        return incomeDetailDao.selectFundCount(record);
    }

    @Override
    public Map<String, BigDecimal> loanManageInfo(Long loginId) {

        BigDecimal loanMoneyTotal = incomeDetailDao.loanMoneyTotal(loginId);
        TbIncomeDetail repaymentTotal = incomeDetailDao.overdueMoneyOther(loginId, new Date());
        TbIncomeDetail overdueMoneyOther = incomeDetailDao.overdueMoneyOther(loginId, null);

        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.put("loanMoneyTotal", loanMoneyTotal);
        map.put("overdueMoneyTotal", overdueMoneyOther.getIndOverdueMoney());
        map.put("repaymentMoneyTotal", overdueMoneyOther.getIndMoney());
        map.put("afterTenMoney", repaymentTotal.getIndMoney());

        return map;
    }

    @Override
    public int insert(TbFundsDetails record) {
        return tbFundsDetailsDao.insertSelective(record);
    }

    @Override
    public int update(TbFundsDetails record) {
        return tbFundsDetailsDao.updateBySsn(record);
    }

    @Override
    public int selectBySerialNumberForPageCount(Long loginId, String serialNumber) {
        TbFundsDetails record = new TbFundsDetails();
        record.setFdLoginId(loginId);
        record.setFdSerialNumber(serialNumber);
        return tbFundsDetailsDao.selectByParamsForPageCount(record);
    }

    @Override
    public List<TbFundsDetails> selectByParamsForPage(TbFundsDetails record, PageWhere pageWhere) {

        return tbFundsDetailsDao.selectByParamsForPage(record, pageWhere);
    }


}
