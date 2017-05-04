package com.jebao.jebaodb.dao.mapper.article;

import com.jebao.jebaodb.entity.article.TbArticleType;

import java.util.List;

public interface TbArticleTypeMapper {
    int insert(TbArticleType record);

    int insertSelective(TbArticleType record);

    TbArticleType selectByPrimaryKey(Integer atId);

    int updateByPrimaryKeySelective(TbArticleType record);

    int updateByPrimaryKey(TbArticleType record);

    /* ==================================================华丽分割线==================================================*/
    List<TbArticleType> selectForList();
}