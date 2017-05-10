package com.jebao.jebaodb.dao.dao.voucher;

import com.jebao.jebaodb.dao.mapper.voucher.TbVoucherMapper;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wenyq on 2017/3/13.
 */
@Repository
public class TbVoucherDao {
    @Autowired
    private TbVoucherMapper mapper;
    public long insert(TbVoucher record){
        return mapper.insert(record);
    }
    public long insertByBatch(List<TbVoucher> record){
        return mapper.insertByBatch(record);
    }
    public TbVoucher selectByPrimaryKey(Long vId) {
        return mapper.selectByPrimaryKey(vId);
    }
    public List<TbVoucher> getByMinWhere( BigDecimal vMinPrice,Integer vMinCycle ,Long loginId) {
        return mapper.getByMinWhere(vMinPrice,vMinCycle,loginId);
    }
    public   int updateStatus(Long vId,Long vIiId)
    {
        return mapper.updateStatus(vId,vIiId);
    }

    public List<TbVoucher> selectByConditionForPage(TbVoucher voucher, PageWhere pageWhere){return mapper.selectByConditionForPage(voucher, pageWhere);}

    public int selectByConditionCount(TbVoucher voucher){
        return mapper.selectByConditionCount(voucher);
    }

    public int updateStatusTimeout(){return mapper.updateStatusTimeout();}

    public List<TbVoucher> selectRemindList(String paramDate){return mapper.selectRemindList(paramDate);}
}
