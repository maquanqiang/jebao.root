package com.jebao.jebaodb.dao.dao.loanmanage;

import com.jebao.jebaodb.dao.mapper.loanmanage.TbThirdInterfaceLogMapper;
import com.jebao.jebaodb.dao.mapper.loanmanage.TbThirdInterfaceLogMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.product.ProductSM;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20.
 */
@Repository
public class TbThirdInterfaceLogDao {
    @Autowired
    private TbThirdInterfaceLogMapper mapper;

    public int insert(TbThirdInterfaceLog record) {
        return mapper.insert(record);
    }
    public int insertSelective(TbThirdInterfaceLog record) {
        return mapper.insertSelective(record);
    }
    public TbThirdInterfaceLog selectByPrimaryKey(Long bpId)
    {
        return mapper.selectByPrimaryKey(bpId);
    }
    public int updateByPrimaryKeySelective(TbThirdInterfaceLog record)
    {
        return mapper.updateByPrimaryKeySelective(record);
    }
    public int updateByPrimaryKey(TbThirdInterfaceLog record)
    {
        return mapper.updateByPrimaryKey(record);
    }

}
