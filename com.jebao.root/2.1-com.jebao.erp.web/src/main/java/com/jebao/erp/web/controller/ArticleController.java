package com.jebao.erp.web.controller;

import com.jebao.erp.service.inf.article.IArticleServiceInf;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Controller
@RequestMapping("/article/")
public class ArticleController extends _BaseController{
    @Autowired
    private IArticleServiceInf articleService;

    @RequestMapping("index")
    public String index(Model model) {
        List<ArticleInfo> list = articleService.selectArticleByParamsForPage(null, null);
        model.addAttribute("list", list);
        return "article/index";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("aId", id);
        return "article/details";
    }
}
