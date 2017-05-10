package com.jebao.jebaodb.dao.dao.article;

import com.jebao.jebaodb.dao.mapper.article.TbArticleTypeMapper;
import com.jebao.jebaodb.entity.article.TbArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
@Repository
public class TbArticleTypeDao {
    @Autowired
    private TbArticleTypeMapper tbArticleTypeMapper;

   public List<TbArticleType> selectForList(){
       return tbArticleTypeMapper.selectForList();
   }
}
