package com.jebao.jebaodb.dao.mapper.base;

import com.jebao.jebaodb.entity.base.TbBaseBankInfo;

import java.util.List;

public interface TbBaseBankInfoMapper {

    List<TbBaseBankInfo> selectList();
    TbBaseBankInfo selectByBankCode(String blBankCode);
}