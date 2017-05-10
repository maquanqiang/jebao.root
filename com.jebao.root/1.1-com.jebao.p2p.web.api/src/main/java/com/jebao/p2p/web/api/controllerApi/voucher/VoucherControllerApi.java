package com.jebao.p2p.web.api.controllerApi.voucher;

import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import com.jebao.p2p.service.inf.voucher.ITbVoucherServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.voucher.VoucherForm;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultData;
import com.jebao.p2p.web.api.responseModel.base.JsonResultError;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.responseModel.voucher.VoucherVM;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.web.api.utils.validation.ValidationResult;
import com.jebao.p2p.web.api.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenyq on 2017/3/13.
 */
@Controller
@RequestMapping("api/voucher")
public class VoucherControllerApi extends _BaseController {
    @Autowired
    private ITbVoucherServiceInf voucherService;
    @RequestMapping("getByVId")
    @ResponseBody
    public JsonResult getByVId(Long vid)
    {
        TbVoucher tbVoucher = voucherService.selectByVId(vid);

        return new JsonResultData<>( tbVoucher);
    }
/*
* 根据条件获取可使用的优惠券（一条）
* */

    @RequestMapping("getByMinWhere")
    @ResponseBody
    public JsonResult getByMinWhere(VoucherForm form)
    {
        //校验
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {            //未登录 重定向登录页
            return new JsonResultError("尚未登录");
        }
        List<TbVoucher> tbVoucherList =voucherService.getByMinWhere(  form.getvMinPrice(),form.getvMinCycle(),currentUser.getId());
        TbVoucher tbVoucher=null;
        if(tbVoucherList !=null && tbVoucherList.size()>0)
        {
            tbVoucher=tbVoucherList.get(0);
        }
        return new JsonResultData<>( tbVoucher);

    }
    /*
* 根据条件获取可使用的优惠券（所有多条）
* */

    @RequestMapping("getByMinWhereAll")
    @ResponseBody
    public JsonResult getByMinWhereAll(int vMinCycle)
    {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {            //未登录 重定向登录页
            return new JsonResultError("尚未登录");
        }
        List<TbVoucher>  tbVoucherList =voucherService.getByMinWhere(null,vMinCycle,currentUser.getId());
        return new JsonResultData<>( tbVoucherList);

    }


    /**
     * 查询红包列表
     * @param form
     * @param pageWhere
     * @return
     */
    @RequestMapping("getVoucherForPage")
    @ResponseBody
    public JsonResult getVoucherForPage(VoucherForm form, PageWhere pageWhere){

        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {            //未登录 重定向登录页
            return new JsonResultError("尚未登录");
        }

        form.setvLoginId(currentUser.getId());

        TbVoucher voucher = form.toEntity(form);
        pageWhere.setOrderBy(" v_endtime ASC, v_Amount DESC");
        List<TbVoucher> tbVouchers = voucherService.selectVoucherListForPage(voucher, pageWhere);
        List<VoucherVM> voucherVMs = new ArrayList<>();
        tbVouchers.forEach(o -> voucherVMs.add(new VoucherVM(o)));

        int count = voucherService.selectByConditionCount(voucher);

        return new JsonResultList<>(voucherVMs,count);
    }


    /**
     * 获取可用红包数
     * @param voucher
     * @return
     */
    @RequestMapping("getEnableCount")
    @ResponseBody
    public JsonResult getEnableCount(TbVoucher voucher){

        int count = 0;
        count = voucherService.selectByConditionCount(voucher);
        return new JsonResultData<>(count);
    }
 }
