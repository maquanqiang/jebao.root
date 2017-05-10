package com.jebao.p2p.service.impl;

import com.jebao.jebaodb.dao.dao.TbTempTestDao;
import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.p2p.service.inf.ITempTestServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
@Service
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
