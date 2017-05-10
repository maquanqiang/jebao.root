package com.jebao.erp.web.controllerApi.article;

import com.jebao.erp.service.inf.article.IArticleServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.requestModel.article.ArticleIM;
import com.jebao.erp.web.requestModel.article.ArticleSM;
import com.jebao.erp.web.responseModel.article.ArticleListVM;
import com.jebao.erp.web.responseModel.article.ArticleVM;
import com.jebao.erp.web.responseModel.base.*;
import com.jebao.erp.web.utils.session.CurrentUser;
import com.jebao.erp.web.utils.session.LoginSessionUtil;
import com.jebao.erp.web.utils.validation.ValidationResult;
import com.jebao.erp.web.utils.validation.ValidationUtil;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@RestController
@RequestMapping("api/article/")
public class ArticleControllerApi extends _BaseController {
    @Autowired
    private IArticleServiceInf articleService;

    @RequestMapping(value = "post",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody
    JsonResult post(ArticleIM model) {
        ValidationResult resultValidation = ValidationUtil.validateEntity(model);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg());
        }
        CurrentUser user = LoginSessionUtil.User(request, response);
        //todo 编辑借款人逻辑实现
        //todo 实际的业务逻辑
        TbArticle entity = new TbArticle();
        entity.setaId(model.getId());
        entity.setaContent(model.getContent());
        entity.setaEditDate(model.getEditDate());
        entity.setaEditUser(model.getEditUser());
        entity.setaTitle(model.getTitle());
        entity.setaTypeId(model.getTypeId());
        entity.setaCreateUserId(user.getId());
        entity.setaWeight(model.getWeight());
        entity.setaIsDel(EnumModel.IsDel.有效.getValue());
        int result = articleService.saveArticle(entity);
        //
        if (result > 0) {
            return new JsonResultOk("保存成功");
        } else {
            return new JsonResultError("保存失败");
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public @ResponseBody JsonResult delete(Long aId){
        if(aId == null || aId == 0){
            return new JsonResultData<>(null);
        }
        int result = articleService.deleteArticleById(aId);
        if (result > 0) {
            return new JsonResultOk("删除成功");
        } else {
            return new JsonResultError("删除失败");
        }
    }

    @RequestMapping("list")
    public JsonResult list(ArticleSM model) {
        if (model==null){return new JsonResultList<>(null);}

        TbArticle record = new TbArticle();
        record.setaTypeId(model.getTypeId());
        record.setaTitle(model.getTitle());
        PageWhere page = new PageWhere(model.getPageIndex(),model.getPageSize());
        List<ArticleInfo> articleList = articleService.selectArticleByParamsForPage(record, page);
        List<ArticleListVM> viewModelList = new ArrayList<>();
        articleList.forEach(o -> viewModelList.add(new ArticleListVM(o)));

        int count=0;
        if (model.getPageIndex()==0){
            count = articleService.selectArticleByParamsForPageCount(record);
        }

        return new JsonResultList<>(viewModelList,count);
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult details(Long aId){
        if(aId == null || aId == 0){
            return new JsonResultData<>(null);
        }
        TbArticle article = articleService.findArticleById(aId);
        if(article == null){
            return new JsonResultData<>(null);
        }
        ArticleVM viewModel = new ArticleVM(article);
        return new JsonResultData<>(viewModel);
    }
}
