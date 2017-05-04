package com.jebao.jebaodb.dao.mapper.article;

import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbArticleMapper {
    int insert(TbArticle record);

    int insertSelective(TbArticle record);

    TbArticle selectByPrimaryKey(Long aId);

    int updateByPrimaryKeySelective(TbArticle record);

    int updateByPrimaryKeyWithBLOBs(TbArticle record);

    int updateByPrimaryKey(TbArticle record);

    /* ==================================================华丽分割线==================================================*/
    int deleteByPrimaryKey(Long aId);

    List<ArticleInfo> selectByParamsForPage(@Param("record") TbArticle record, @Param("pageWhere") PageWhere pageWhere);

    List<TbArticle> selectByParamsForPageExt(@Param("record") TbArticle record, @Param("pageWhere") PageWhere pageWhere);

    int selectByParamsForPageCount(@Param("record") TbArticle record);
}