package com.jebao.jebaodb.dao.dao.article;

import com.jebao.jebaodb.dao.mapper.article.TbArticleMapper;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Repository
public class TbArticleDao {
    @Autowired
    private TbArticleMapper tbArticleMapper;

    public int insert(TbArticle record) {
        return tbArticleMapper.insert(record);
    }

    public int insertSelective(TbArticle record) {
        return tbArticleMapper.insertSelective(record);
    }

    public TbArticle selectByPrimaryKey(Long aId) {
        return tbArticleMapper.selectByPrimaryKey(aId);
    }

    public int updateByPrimaryKeySelective(TbArticle record) {
        return tbArticleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TbArticle record) {
        return tbArticleMapper.updateByPrimaryKey(record);
    }

    public int deleteByPrimaryKey(Long aId) {
        return tbArticleMapper.deleteByPrimaryKey(aId);
    }

    public List<TbArticle> selectByParamsForPageExt(TbArticle record, PageWhere pageWhere) {
        if (record == null) {
            record = new TbArticle();
        }
        if (pageWhere == null) {
            pageWhere = new PageWhere(0, 10);
        }
        return tbArticleMapper.selectByParamsForPageExt(record, pageWhere);
    }

    public List<ArticleInfo> selectByParamsForPage(TbArticle record, PageWhere pageWhere) {
        if (record == null) {
            record = new TbArticle();
        }
        if (pageWhere == null) {
            pageWhere = new PageWhere(0, 10);
        }
        return tbArticleMapper.selectByParamsForPage(record, pageWhere);
    }

    public int selectByParamsForPageCount(TbArticle record) {
        if (record == null) {
            record = new TbArticle();
        }
        return tbArticleMapper.selectByParamsForPageCount(record);
    }
}
