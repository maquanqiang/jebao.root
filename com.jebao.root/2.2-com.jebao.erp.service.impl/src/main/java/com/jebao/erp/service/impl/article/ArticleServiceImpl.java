package com.jebao.erp.service.impl.article;

import com.jebao.erp.service.inf.article.IArticleServiceInf;
import com.jebao.jebaodb.dao.dao.article.TbArticleDao;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Service
public class ArticleServiceImpl implements IArticleServiceInf {
    @Autowired
    private TbArticleDao tbArticleDao;

    @Override
    public int saveArticle(TbArticle entity) {
        if(entity == null){
            return 0;
        }
        long aId;
        if(entity.getaId() == null){
            aId = 0;
        }else {
            aId = entity.getaId();
        }
        if(aId == 0){
            return addArticle(entity);
        }else{
            return updateArticle(entity);
        }
    }

    @Override
    public int addArticle(TbArticle entity) {
        entity.setaCreateTime(new Date());
        entity.setaUpdateTime(new Date());
        return tbArticleDao.insertSelective(entity);
    }

    @Override
    public int updateArticle(TbArticle entity) {
        entity.setaUpdateTime(new Date());
        return tbArticleDao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public TbArticle findArticleById(Long aId) {
        return tbArticleDao.selectByPrimaryKey(aId);
    }

    @Override
    public int deleteArticleById(Long aId) {
        return tbArticleDao.deleteByPrimaryKey(aId);
    }

    @Override
    public List<ArticleInfo> selectArticleByParamsForPage(TbArticle record, PageWhere page) {
        return tbArticleDao.selectByParamsForPage(record,page);
    }

    @Override
    public int selectArticleByParamsForPageCount(TbArticle record) {
        return tbArticleDao.selectByParamsForPageCount(record);
    }
}
