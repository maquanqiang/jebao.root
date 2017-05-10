package com.jebao.jebaodb.dao.mapper.voucher;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TbVoucherMapper {
    int insert(TbVoucher record);

    int insertSelective(TbVoucher record);
    int insertByBatch(List<TbVoucher> record);
    TbVoucher selectByPrimaryKey(Long vId);

    int updateByPrimaryKeySelective(TbVoucher record);

    int updateByPrimaryKey(TbVoucher record);
    int updateStatus(@Param("vId") Long vId,  @Param("vIiId") Long vIiId    );
    List<TbVoucher> getByMinWhere( @Param("vMinPrice") BigDecimal vMinPrice,  @Param("vMinCycle") Integer vMinCycle,  @Param("loginId") Long loginId );

    List<TbVoucher> selectByConditionForPage(@Param("record")TbVoucher record, @Param("pageWhere")PageWhere pageWhere);

    int selectByConditionCount(@Param("record")TbVoucher record);

    int updateStatusTimeout();

    List<TbVoucher> selectRemindList(String paramDate);
}