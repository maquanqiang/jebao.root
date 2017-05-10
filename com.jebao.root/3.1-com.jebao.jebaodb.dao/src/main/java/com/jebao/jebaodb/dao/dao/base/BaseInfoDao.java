package com.jebao.jebaodb.dao.dao.base;

import com.jebao.jebaodb.dao.mapper.base.TbBaseBankInfoMapper;
import com.jebao.jebaodb.dao.mapper.base.TbBaseRegionInfoMapper;
import com.jebao.jebaodb.entity.base.TbBaseBankInfo;
import com.jebao.jebaodb.entity.base.TbBaseRegionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jack on 2016/12/26.
 */
@Repository
public class BaseInfoDao {
    @Autowired
    private TbBaseRegionInfoMapper regionInfoMapper;
    @Autowired
    private TbBaseBankInfoMapper bankInfoMapper;

    /**
     * 获取银行列表
     * @return
     */
    public List<TbBaseBankInfo> selectBankList(){
        return bankInfoMapper.selectList();
    }

    /**
     * 根据银行编号获取银行信息
     * @param blBankCode
     * @return
     */
    public TbBaseBankInfo selectByBankCode(String blBankCode){
        return bankInfoMapper.selectByBankCode(blBankCode);
    }

    /**
     * 获取省市区列表
     * @return
     */
    public List<TbBaseRegionInfo> selectRegionList(){
        return regionInfoMapper.selectList();
    }

}
