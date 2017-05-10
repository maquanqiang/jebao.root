package com.jebao.jebaodb.dao.dao.employee;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.entity.employee.EmpPerformanceInfo;
import com.jebao.jebaodb.entity.employee.search.EmployeeSM;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Jack on 2017/1/4.
 */
public class TbEmployeeDaoTest extends _BaseUnitTest {

    @Autowired
    private TbEmployeeDao employeeDao;

    @Test
    public void testSelectEmpPerformance() throws Exception {
        EmployeeSM model = new EmployeeSM();

        List<EmpPerformanceInfo> list = employeeDao.selectEmpPerformance(model);

        System.out.println(list.size());

        Assert.assertTrue(list.size()>0);
    }
}