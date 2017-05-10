package com.jebao.jebaodb.dao.mapper.loanmanage;

import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;

public interface TbThirdInterfaceLogMapper {
    int insert(TbThirdInterfaceLog record);

    int insertSelective(TbThirdInterfaceLog record);

    TbThirdInterfaceLog selectByPrimaryKey(Long tilId);

    int updateByPrimaryKeySelective(TbThirdInterfaceLog record);

    int updateByPrimaryKey(TbThirdInterfaceLog record);
}