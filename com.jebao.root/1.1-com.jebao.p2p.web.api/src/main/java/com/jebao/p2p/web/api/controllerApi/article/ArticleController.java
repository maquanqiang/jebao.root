package com.jebao.p2p.web.api.controllerApi.article;

import com.jebao.common.cache.redis.sharded.ShardedRedisUtil;
import com.jebao.common.cache.utils.wrapper.CachedWrapper;
import com.jebao.common.cache.utils.wrapper.CachedWrapperExecutor;
import com.jebao.jebaodb.entity.article.ArticleInfo;
import com.jebao.jebaodb.entity.article.TbArticle;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.p2p.service.inf.article.IArticleServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.article.ArticleSM;
import com.jebao.p2p.web.api.responseModel.article.ArticleIndexVM;
import com.jebao.p2p.web.api.responseModel.article.ArticleListVM;
import com.jebao.p2p.web.api.responseModel.article.ArticleVM;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultData;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.utils.cached.CachedSetting;
import com.jebao.p2p.web.api.utils.cached.CachedUtil;
import com.jebao.p2p.web.api.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
@RestController
@RequestMapping("/api/article/")
public class ArticleController extends _BaseController {
    @Autowired
    private IArticleServiceInf articleService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult index(ArticleSM model) throws Exception {
        if (model == null) {
            return new JsonResultList<>(null);
        }
        //首页的三个媒体报道缓存10分钟
        String keyMd5 = CachedUtil.KeyMd5(model);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        CachedSetting cachedSetting = Constants.CACHED_API_MEDIANEWS_INDEX;
        CachedWrapper<List<ArticleIndexVM>> articleListCached = redisUtil.getCachedWrapperByMutexKey(
                cachedSetting.getKey() + keyMd5,
                cachedSetting.getKeyExpireSec(),
                cachedSetting.getNullValueExpireSec(),
                cachedSetting.getKeyMutexExpireSec(),
                new CachedWrapperExecutor<List<ArticleIndexVM>>() {
                    @Override
                    public List<ArticleIndexVM> execute() {
                        PageWhere page = new PageWhere(model.getPageIndex(), model.getPageSize());
                        List<TbArticle> articleList = articleService.selectArticleByTypeIdForIndex(model.getTypeId(), page);
                        if (articleList == null || articleList.size() == 0) {
                            return null;
                        }
                        List<ArticleIndexVM> viewModelList = new ArrayList<>();
                        articleList.forEach(o -> viewModelList.add(new ArticleIndexVM(o)));
                        return viewModelList;
                    }
                });
        //
        return new JsonResultList<>(articleListCached.getData());
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(ArticleSM model) throws Exception {
        if (model == null) {
            return new JsonResultList<>(null);
        }

        //文章列表缓存10分钟
        String keyMd5 = CachedUtil.KeyMd5(model);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        CachedSetting cachedSetting = Constants.CACHED_API_ARTICLE_LIST;
        CachedWrapper<List<ArticleListVM>> articleListCached = redisUtil.getCachedWrapperByMutexKey(
                cachedSetting.getKey() + keyMd5,
                cachedSetting.getKeyExpireSec(),
                cachedSetting.getNullValueExpireSec(),
                cachedSetting.getKeyMutexExpireSec(),
                new CachedWrapperExecutor<List<ArticleListVM>>() {
                    @Override
                    public List<ArticleListVM> execute() {
                        PageWhere page = new PageWhere(model.getPageIndex(), model.getPageSize());

                        List<ArticleInfo> articleList = articleService.selectArticleByTypeIdForPage(model.getTypeId(), page);
                        if (articleList == null || articleList.size() == 0) {
                            return null;
                        }
                        List<ArticleListVM> viewModelList = new ArrayList<>();
                        articleList.forEach(o -> viewModelList.add(new ArticleListVM(o)));

                        return viewModelList;
                    }
                });

        CachedSetting cachedSettingCount = Constants.CACHED_API_ARTICLE_LIST_COUNT;
        CachedWrapper<Integer> articleListCountCached = redisUtil.getCachedWrapperByMutexKey(
                cachedSettingCount.getKey() + keyMd5,
                cachedSettingCount.getKeyExpireSec(),
                cachedSettingCount.getNullValueExpireSec(),
                cachedSettingCount.getKeyMutexExpireSec(),
                new CachedWrapperExecutor<Integer>() {
                    @Override
                    public Integer execute() {
                        int count = 0;
                        if (model.getPageIndex() == 0) {
                            count = articleService.selectArticleByTypeIdForPageCount(model.getTypeId());
                        }
                        return count;
                    }
                });

        return new JsonResultList<>(articleListCached.getData(), articleListCountCached.getData());
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult details(Long aId) {
        if (aId == null || aId == 0) {
            return new JsonResultData<>(null);
        }
        TbArticle article = articleService.findArticleById(aId);
        if (article == null) {
            return new JsonResultData<>(null);
        }
        ArticleVM viewModel = new ArticleVM(article);
        return new JsonResultData<>(viewModel);
    }
}
