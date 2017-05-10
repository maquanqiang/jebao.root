package com.jebao.erp.service.impl;

import com.jebao.erp.service.inf.ITempTestServiceInf;
import com.jebao.jebaodb.dao.dao.TbTempTestDao;
import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class TempTestServiceImpl implements ITempTestServiceInf {

    @Autowired
    private TbTempTestDao tbTempTestDao;
    @Override
    public int add(TbTempTest data) {
        return tbTempTestDao.insert(data);
    }

    @Override
    public List<TbTempTest> getList(int pageIndex, int pageSize) {
        PageWhere pageWhere=new PageWhere(pageIndex,pageSize);
        return tbTempTestDao.selectForPage(pageWhere);
    }
}
