package com.jebao.erp.service.impl.employee;

import com.jebao.erp.service.inf.employee.IDepartmentServiceInf;
import com.jebao.jebaodb.dao.dao.employee.TbDepartmentDao;
import com.jebao.jebaodb.entity.employee.TbDepartment;
import com.jebao.jebaodb.entity.employee.search.DepartmentSM;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack on 2016/11/21.
 */
@Service
public class DepartmentServiceImpl implements IDepartmentServiceInf {
@Autowired
    private TbDepartmentDao departmentDao;
    @Override
    public List<TbDepartment> getDepartmentList(DepartmentSM model) {
        return departmentDao.selectList(model);
    }
    @Override
    public int getDepartmentListCount(DepartmentSM model){return departmentDao.selectListCount(model);}
    @Override
    public ResultInfo save(TbDepartment model){
        if (model==null || StringUtils.isBlank(model.getDepName())){
            return new ResultInfo(false,"参数异常");
        }
        int reval=0;
        if (model.getDepId()==null || model.getDepId()==0){
            //插入
            reval = departmentDao.insert(model);
        }else{
            //修改
            reval = departmentDao.updateByPrimaryKeySelective(model);
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
        DepartmentSM searchModel = new DepartmentSM();
        searchModel.setParentId(id);
        List<TbDepartment> childList = departmentDao.selectList(searchModel);
        if (childList!=null && childList.size()>0){
            return new ResultInfo(false,"不允许删除：该部门存在下属组织");
        }
        int reval = departmentDao.delete(id);
        if (reval>0){
            return new ResultInfo(true,"删除成功");
        }
        return new ResultInfo(false,"删除失败");
    }
}
