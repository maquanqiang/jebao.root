package com.jebao.erp.service.inf;

import com.jebao.jebaodb.entity.TbTempTest;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public interface ITempTestServiceInf {
    int add(TbTempTest data);
    List<TbTempTest> getList(int pageIndex,int pageSize);
}