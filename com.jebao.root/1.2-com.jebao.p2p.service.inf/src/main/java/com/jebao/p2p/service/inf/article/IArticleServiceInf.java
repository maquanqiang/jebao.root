package com.jebao.p2p.service.inf.article;

import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
public interface IArticleServiceInf {
    /**
     * 根据主键Id获取文章信息
     *
     * @param aId
     * @return
     */
    TbArticle findArticleById(Long aId);

    /**
     * 根据查询条件获取文章列表(分页)
     *
     * @param typeId
     * @param page
     * @return
     */
    List<ArticleInfo> selectArticleByTypeIdForPage(int typeId, PageWhere page);

    /**
     * 根据查询条件统计文章信息总数
     *
     * @param typeId
     * @return
     */
    int selectArticleByTypeIdForPageCount(int typeId);

    /**
     * 带内容的文章列表
     * @param typeId
     * @param page
     * @return
     */
    List<TbArticle> selectArticleByTypeIdForIndex(int typeId, PageWhere page);
}
