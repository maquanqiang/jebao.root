package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.user.TbLoginInfoDao;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TbLoginInfoDao_UnitTest extends _BaseUnitTest {
    @Autowired
    private TbLoginInfoDao tbLoginInfoDao;

    @Test
    public void insertExample(){
        TbLoginInfo record = new TbLoginInfo();
        record.setLiCreateTime(new Date());
        record.setLiIsDel(1);
        record.setLiLastLoginTime(new Date());
        record.setLiLoginName("18600575242");
        record.setLiPassword("575242");
        long result= tbLoginInfoDao.insert(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getLiId());
    }
    @Test
    public void insertSelectiveExample() {
        TbLoginInfo record = new TbLoginInfo();
        record.setLiCreateTime(new Date());
        record.setLiIsDel(1);
        record.setLiLastLoginTime(new Date());
        record.setLiLoginName("15522699687");
        record.setLiPassword("699687");
        int result= tbLoginInfoDao.insertSelective(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getLiId());
    }
    @Test
    public void selectByPrimaryKeyExample() {
        TbLoginInfo result= tbLoginInfoDao.selectByPrimaryKey((long)1);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void updateByPrimaryKeySelectiveExample() {
        TbLoginInfo record= tbLoginInfoDao.selectByPrimaryKey((long)1);
        record.setLiPassword("123456");
        int result= tbLoginInfoDao.updateByPrimaryKeySelective(record);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void updateByPrimaryKeyExample() {
        TbLoginInfo record= tbLoginInfoDao.selectByPrimaryKey((long)2);
        record.setLiPassword("654321");
        int result= tbLoginInfoDao.updateByPrimaryKey(record);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void deleteByPrimaryKeyExample()
    {
        int result= tbLoginInfoDao.deleteByPrimaryKey((long)2);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void selectForPageExample()
    {
        PageWhere pageWhere=new PageWhere(1,10);
        List<TbLoginInfo> result = tbLoginInfoDao.selectForPage(pageWhere);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void selectByLoginNameExample()
    {
        TbLoginInfo result = tbLoginInfoDao.selectByLoginName("15901048116");
        assertThat(result).isNotEqualTo(null);
        if(result == null){
            System.out.println("wu");
        }else{
            System.out.println(result.getLiLoginName());
        }
    }
    /**
     * Spring Boot中的事务管理
     * 事务使用
     * */
    @Test
    public void insertForTransactionalExample()
    {
        TbLoginInfo record = new TbLoginInfo();
        record.setLiCreateTime(new Date());
        record.setLiIsDel(1);
        record.setLiLastLoginTime(new Date());
        record.setLiLoginName("15901048114");
        record.setLiPassword("111111");
        long result= tbLoginInfoDao.insertForTransactional(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getLiId());
    }
}
