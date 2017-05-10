package com.jebao.erp.service.impl.employee;

import com.jebao.erp.service.inf.employee.IRankServiceInf;
import com.jebao.jebaodb.dao.dao.employee.TbRankDao;
import com.jebao.jebaodb.entity.employee.TbRank;
import com.jebao.jebaodb.entity.employee.search.RankSM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack on 2016/11/21.
 */
@Service
public class RankServiceImpl implements IRankServiceInf {
    @Autowired
    private TbRankDao rankDao;

    @Override
    public List<TbRank> getRankList(RankSM model) {
        return rankDao.selectList(model);
    }

    @Override
    public int getRankListCount(RankSM model) {
        return rankDao.selectListCount(model);
    }
    @Override
    public ResultInfo save(TbRank model){
        if (model==null || StringUtils.isBlank(model.getRankName())){
            return new ResultInfo(false,"参数异常");
        }
        int reval=0;
        if (model.getRankId()==null || model.getRankId()==0){
            //插入
            reval = rankDao.insert(model);
        }else{
            //修改
            reval = rankDao.updateByPrimaryKeySelective(model);
        }
        if (reval>0){
            return new ResultInfo(true,"保存成功");
        }
        return new ResultInfo(false,"保存失败");
    }
    @Override
    public ResultInfo delete(int id){
        if (id==0){
            return new ResultInfo(false,"参数异常");
        }
        //检测是否有子级
        RankSM searchModel = new RankSM();
        searchModel.setParentId(id);
        List<TbRank> childList = rankDao.selectList(searchModel);
        if (childList!=null && childList.size()>0){
            return new ResultInfo(false,"不允许删除：该职级存在下属职级");
        }
        int reval = rankDao.delete(id);
        if (reval>0){
            return new ResultInfo(true,"删除成功");
        }
        return new ResultInfo(false,"删除失败");
    }

}
