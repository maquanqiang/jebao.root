package com.jebao.erp.web.controllerApi.article;

import com.jebao.erp.service.inf.article.IArticleTypeServiceInf;
import com.jebao.erp.web.responseModel.article.ArticleTypeVM;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.jebaodb.entity.article.TbArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
@RestController
@RequestMapping("api/articletype")
public class ArticleTypeControllerApi {
    @Autowired
    private IArticleTypeServiceInf articleTypeService;

    @RequestMapping("list")
    public JsonResult list()
    {
        List<TbArticleType> articleTypeList = articleTypeService.selectForList();
        List<ArticleTypeVM> vmList = new ArrayList<>();
        articleTypeList.forEach(o->vmList.add(new ArticleTypeVM(o)));
        return new JsonResultList<>(vmList);
    }
}
