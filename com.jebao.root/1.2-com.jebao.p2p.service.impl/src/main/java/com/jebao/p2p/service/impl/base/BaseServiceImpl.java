package com.jebao.p2p.service.impl.base;

import com.jebao.jebaodb.dao.dao.base.BaseInfoDao;
import com.jebao.jebaodb.entity.base.TbBaseBankInfo;
import com.jebao.jebaodb.entity.base.TbBaseRegionInfo;
import com.jebao.p2p.service.inf.base.IBaseServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack on 2016/12/26.
 */
@Service
public class BaseServiceImpl implements IBaseServiceInf {
    @Autowired
    private BaseInfoDao baseInfoDao;

    /**
     * 获取银行列表
     *
     * @return
     */
    @Override
    public List<TbBaseBankInfo> getBankList() {
        return baseInfoDao.selectBankList();
    }

    /**
     * 根据银行编号获取银行信息
     * @param bankCode
     * @return
     */
    @Override
    public TbBaseBankInfo getBankInfoByBankCode(String bankCode){
        return baseInfoDao.selectByBankCode(bankCode);
    }

    /**
     * 获取省市区列表
     *
     * @return
     */
    @Override
    public List<TbBaseRegionInfo> getRegionList() {
        return baseInfoDao.selectRegionList();
    }
}
