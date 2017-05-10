package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.TbTempTestDao;
import com.jebao.jebaodb.entity.TbTempTest;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DAO 使用的DEMO
 * Created by Administrator on 2016/10/20.
 */

public class TbTempTestDao_UnitTest extends _BaseUnitTest {
    @Autowired
    private TbTempTestDao tbTempTestDao;

    @Test
    public void testExample(){
    /*    int type = EnumModel.SerialType.提现.getValue();
        System.out.println("type:"+type);
        String typeName = EnumModel.SerialType.提现.name();
        System.out.println("typeName:"+typeName);*/
        /*BigDecimal a = new BigDecimal(500);
        BigDecimal b = new BigDecimal(100);
        int result = a.compareTo(b);
        if(result!=1){
            System.out.println(result);
        }
        System.out.println(result);*/
        //String content = "<img src=\"images/media-1.png\" alt=\"\"><h4><i></i>聊聊中国P2P公司的估值体系</h4><p>中国的互联网金融公司的价值创造更多的还是资金但在真正获得收益的资产开发端，更多的还是基于线下和人工，和传统机构并无大异。</p><p>但无论市场怎么变化，我始终相信一点，短期波动永远会回归长期价值，是否创造价值最终决定一个公司的估值。</p>";
        String content = "<img src=\"images/media-1.png\" alt=\"\"><h4><i></i>聊聊中国P2P公司的估值体系</h4>";
        int firstIndex = content.indexOf("</p>");
        System.out.println(content.length());
        String content_new;
        if(firstIndex > -1 && firstIndex < content.length() - 4 ){
            content_new = content.substring(0,firstIndex+4);
            System.out.println(content_new);
        }else{
            content_new = content;
            System.out.println(content_new);
        }

    }

    @Test
    public void insertExample()
    {
        TbTempTest record = new TbTempTest();
        record.setUsername("us2016102001");
        record.setPassword("pwd2016102001");
        int result= tbTempTestDao.insert(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getId());
    }
    @Test
    public void insertSelectiveExample()
    {
        TbTempTest record = new TbTempTest();
        record.setUsername("Selective2016102001");
        int result= tbTempTestDao.insertSelective(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getId());
    }

    @Test
    public void selectByPrimaryKeyExample() {
        TbTempTest result= tbTempTestDao.selectByPrimaryKey(105);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void updateByPrimaryKeySelectiveExample() {
        TbTempTest record= tbTempTestDao.selectByPrimaryKey(105);
        record.setPassword("updateByPrimaryKeySelective20161020");
        int result= tbTempTestDao.updateByPrimaryKeySelective(record);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void updateByPrimaryKeyExample() {
        TbTempTest record= tbTempTestDao.selectByPrimaryKey(105);
        record.setPassword("primaryKey2016102001");
        int result= tbTempTestDao.updateByPrimaryKey(record);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void deleteByPrimaryKeyExample() {
        int result= tbTempTestDao.deleteByPrimaryKey(113);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void selectForPageExample() {
        PageWhere pageWhere=new PageWhere(1,10);
        List<TbTempTest> result = tbTempTestDao.selectForPage(pageWhere);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void selectByUserNameForPageExample() {
        PageWhere pageWhere=new PageWhere(0,10);
        TbTempTest record = new TbTempTest();
        record.setUsername("us2016102001");
        List<TbTempTest> result = tbTempTestDao.selectByUserNameForPage(record, pageWhere);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void selectByUserNameForPageCountExample() {
        TbTempTest record = new TbTempTest();
        record.setUsername("us2016102001");
        int result = tbTempTestDao.selectByUserNameForPageCount(record);
        assertThat(result).isNotEqualTo(null);
        System.out.println(result);
    }
    /**
     * Spring Boot中的事务管理
     * 事务使用
     * */
    @Test
    public void insertForTransactionalExample()
    {
        TbTempTest record = new TbTempTest();
        record.setUsername("us2016102001");
        record.setPassword("pwd2016102001");
        int result= tbTempTestDao.insertForTransactional(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getId());
    }
}
