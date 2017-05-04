package com.jebao.erp.service.impl.article;

import com.jebao.erp.service.inf.article.IArticleTypeServiceInf;
import com.jebao.jebaodb.dao.dao.article.TbArticleTypeDao;
import com.jebao.jebaodb.entity.article.TbArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
@Service
public class ArticleTypeServiceImpl implements IArticleTypeServiceInf {
    @Autowired
    private TbArticleTypeDao tbArticleTypeDao;

    @Override
    public List<TbArticleType> selectForList() {
        return tbArticleTypeDao.selectForList();
    }
}
