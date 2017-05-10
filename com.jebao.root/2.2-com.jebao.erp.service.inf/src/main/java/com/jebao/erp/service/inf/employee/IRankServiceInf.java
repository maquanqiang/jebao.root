package com.jebao.erp.service.inf.employee;

import com.jebao.jebaodb.entity.employee.TbRank;
import com.jebao.jebaodb.entity.employee.search.RankSM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;

import java.util.List;

/**
 * Created by Jack on 2016/11/21.
 */
public interface IRankServiceInf {
    List<TbRank> getRankList(RankSM model);
    int getRankListCount(RankSM model);
    ResultInfo save(TbRank model);
    ResultInfo delete(int id);
}
