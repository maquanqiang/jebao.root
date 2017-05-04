package com.jebao.erp.service.inf.article;

import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface IArticleServiceInf {

    /**
     * 保存文章信息
     * @param entity
     * @return
     */
    int saveArticle(TbArticle entity);

    /**
     * 添加文章信息
     * @param entity
     * @return
     */
    int addArticle(TbArticle entity);

    /**
     * 添加文章信息
     * @param entity
     * @return
     */
    int updateArticle(TbArticle entity);

    /**
     * 根据主键Id获取文章信息
     * @param aId
     * @return
     */
    TbArticle findArticleById(Long aId);

    /**
     * 删除文章
     * @param aId 主键Id
     * @return
     */
    int deleteArticleById(Long aId);

    /**
     * 根据查询条件获取文章列表(分页)
     * @param record
     * @param page
     * @return
     */
    List<ArticleInfo> selectArticleByParamsForPage(TbArticle record,PageWhere page);

    /**
     * 根据查询条件统计文章信息总数
     * @param record
     * @return
     */
    int selectArticleByParamsForPageCount(TbArticle record);
}
