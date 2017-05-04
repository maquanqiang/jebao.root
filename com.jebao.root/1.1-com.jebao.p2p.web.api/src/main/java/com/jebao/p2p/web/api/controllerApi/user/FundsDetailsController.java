package com.jebao.p2p.web.api.controllerApi.user;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.p2p.service.inf.user.IFundsDetailsServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.user.FundsDetailsSM;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.responseModel.user.FundsDetailsVM;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
@RestController
@RequestMapping("/api/funds/")
public class FundsDetailsController extends _BaseController {
    @Autowired
    private IFundsDetailsServiceInf fundsDetailsService;

    /**
     * 账户总览-收支明细
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list() {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if(currentUser == null){
            return new JsonResultList<>(null);
        }

        PageWhere page = new PageWhere(0,2);
        List<TbFundsDetails> fdList = fundsDetailsService.selectFundsDetailsByLoginIdForPage(currentUser.getId(), page);
        if(fdList == null || fdList.size() == 0){
            return new JsonResultList<>(null);
        }
        List<FundsDetailsVM> viewModelList = new ArrayList<>();
        fdList.forEach(o -> viewModelList.add(new FundsDetailsVM(o)));
        return new JsonResultList<>(viewModelList);
    }

    /**
     * 收支明细
     * @param model
     * @return
     */
    @RequestMapping(value = "details", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult details(FundsDetailsSM model) {
        if (model == null) {
            return new JsonResultList<>(null);
        }

        CurrentUser currentUser = CurrentUserContextHolder.get();
        if(currentUser == null){
            return new JsonResultList<>(null);
        }

        PageWhere page = new PageWhere(model.getPageIndex(), model.getPageSize());
        List<TbFundsDetails> fdList = fundsDetailsService.selectFundsDetailsByLoginIdForPage(currentUser.getId(), page);
        if(fdList == null || fdList.size() == 0){
            return new JsonResultList<>(null);
        }

        List<FundsDetailsVM> viewModelList = new ArrayList<>();
        fdList.forEach(o -> viewModelList.add(new FundsDetailsVM(o)));

        int count = 0;
        if (model.getPageIndex() == 0) {
            count = fundsDetailsService.selectFundsDetailsByLoginIdForPageCount(currentUser.getId());
        }
        return new JsonResultList<>(viewModelList, count);
    }
}