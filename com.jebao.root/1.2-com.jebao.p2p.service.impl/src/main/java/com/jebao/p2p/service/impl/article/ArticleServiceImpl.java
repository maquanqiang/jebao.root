package com.jebao.p2p.service.impl.article;

import com.jebao.jebaodb.dao.dao.article.TbArticleDao;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.p2p.service.inf.article.IArticleServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class ArticleServiceImpl implements IArticleServiceInf {
    @Autowired
    private TbArticleDao tbArticleDao;

    @Override
    public TbArticle findArticleById(Long aId) {
        return tbArticleDao.selectByPrimaryKey(aId);
    }

    @Override
    public List<ArticleInfo> selectArticleByTypeIdForPage(int typeId, PageWhere page) {
        TbArticle record = new TbArticle();
        record.setaTypeId(typeId);
        return tbArticleDao.selectByParamsForPage(record, page);
    }

    @Override
    public int selectArticleByTypeIdForPageCount(int typeId) {
        TbArticle record = new TbArticle();
        record.setaTypeId(typeId);
        return tbArticleDao.selectByParamsForPageCount(record);
    }

    @Override
    public List<TbArticle> selectArticleByTypeIdForIndex(int typeId, PageWhere page) {
        TbArticle record = new TbArticle();
        record.setaTypeId(typeId);
        return tbArticleDao.selectByParamsForPageExt(record, page);
    }
}
