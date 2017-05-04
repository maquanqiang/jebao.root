package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.user.TbFundsDetailsDao;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2016/11/17.
 */
public class TbFundsDetailsDao_UnitTest extends _BaseUnitTest {
    @Autowired
    private TbFundsDetailsDao tbFundsDetailsDao;

    @Test
    public void insertExample(){
        TbFundsDetails record = new TbFundsDetails();
        record.setFdLoginId(1l);
        record.setFdCommissionCharge(new BigDecimal(30));
        record.setFdSerialAmount(new BigDecimal(300));
        record.setFdSerialNumber("100000002");
        record.setFdSerialTime(new Date());
        record.setFdThirdAccount("15901048116");
        int result= tbFundsDetailsDao.insert(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getFdId());
    }

    @Test
    public void selectByParamsForPageCountExample() {
        TbFundsDetails record = new TbFundsDetails();
        record.setFdLoginId(1l);
        //record.setFdSerialStatus(1);
        int result = tbFundsDetailsDao.selectByParamsForPageCount(record);
        assertThat(result).isNotEqualTo(null);
        System.out.println("result:"+result);
    }
}
