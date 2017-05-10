package com.jebao.p2p.web.api.controllerApi.product;

import com.jebao.common.cache.redis.sharded.ShardedRedisUtil;
import com.jebao.common.cache.utils.wrapper.CachedWrapper;
import com.jebao.common.cache.utils.wrapper.CachedWrapperExecutor;
import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;
import com.jebao.jebaodb.entity.product.ProductSM;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.p2p.service.inf.product.IProductServiceInf;
import com.jebao.p2p.service.inf.user.IInvestServiceInf;
import com.jebao.p2p.service.inf.user.IUserServiceInf;
import com.jebao.p2p.web.api.controllerApi._BaseController;
import com.jebao.p2p.web.api.requestModel.product.ExpectRevenueForm;
import com.jebao.p2p.web.api.requestModel.product.InvestInfoForm;
import com.jebao.p2p.web.api.requestModel.product.ProductForm;
import com.jebao.p2p.web.api.responseModel.base.*;
import com.jebao.p2p.web.api.responseModel.product.*;
import com.jebao.p2p.web.api.utils.cached.CachedSetting;
import com.jebao.p2p.web.api.utils.cached.CachedUtil;
import com.jebao.p2p.web.api.utils.constants.Constants;
import com.jebao.p2p.web.api.utils.http.HttpUtil;
import com.jebao.p2p.web.api.utils.product.BetweenDay;
import com.jebao.p2p.web.api.utils.session.CurrentUser;
import com.jebao.p2p.web.api.utils.session.CurrentUserContextHolder;
import com.jebao.p2p.web.api.utils.validation.ValidationResult;
import com.jebao.p2p.web.api.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Lee on 2016/12/7.
 */
@Controller
@RequestMapping("api/product")
public class ProductControllerApi extends _BaseController {
    @Autowired
    private IProductServiceInf productService;
    @Autowired
    private IInvestServiceInf investService;
    @Autowired
    private IUserServiceInf userService;

    /**
     * 项目列表
     *
     * @param form
     * @param pageWhere
     * @return
     */
//    @RequestMapping("list")
//    @ResponseBody
//    public JsonResult list(ProductForm form, PageWhere pageWhere) {
//
//        ProductSM productSM = ProductForm.toEntity(form);
//        pageWhere.setOrderBy("bp_status ASC, bp_bid_money DESC, bp_rate DESC, bp_start_time DESC");
//        List<TbBidPlan> tbBidPlans = productService.selectP2PList(productSM, pageWhere);
//        List<ProductVm> productVms = new ArrayList<>();
//        tbBidPlans.forEach(o -> productVms.add(new ProductVm(o)));
//        int count = productService.selectP2PListCount(productSM);
//        return new JsonResultList<>(productVms, count);
//    }

    /**
     * 项目详情
     *
     * @param bpId
     * @return
     */
    @RequestMapping("productDetail")
    @ResponseBody
    public JsonResult productDetail(Long bpId) {
        TbBidPlan tbBidPlan = productService.selectByBpId(bpId);
        if (tbBidPlan == null || tbBidPlan.getBpStatus() < 2) {
            return new JsonResultError();
        }
        return new JsonResultData<>(new ProductDetailVM(tbBidPlan));
    }

    /**
     * 借款人信息
     *
     * @param lid
     * @return
     */
    @RequestMapping("loanerInfo")
    @ResponseBody
    public JsonResult loanerInfo(Long lid) {
        TbLoaner tbLoaner = productService.selectByPrimaryKey(lid);
        return new JsonResultData<>(new LoanerInfoVM(tbLoaner));
    }

    /**
     * 风控信息
     *
     * @param bpId
     * @return
     */
    @RequestMapping("riskListByBpId")
    @ResponseBody
    public JsonResult riskListByBpId(Long bpId) {
        TbBidRiskData tbBidRiskData = new TbBidRiskData();
        tbBidRiskData.setBrdBpId(bpId);
        PageWhere pageWhere = new PageWhere(0, 100);
        pageWhere.setOrderBy(" brd_name asc");
        List<TbBidRiskData> tbBidRiskDatas = productService.selectRiskByConditionForPage(tbBidRiskData, pageWhere);
        List<BidRiskDataVM> bidRiskDataVMs = new ArrayList<>();
        tbBidRiskDatas.forEach(o -> bidRiskDataVMs.add(new BidRiskDataVM(o)));
        return new JsonResultList<>(bidRiskDataVMs);
    }

    /**
     * 该标的投资人列表
     *
     * @param bpId
     * @param pageWhere
     * @return
     */
    @RequestMapping("investInfoByBpId")
    @ResponseBody
    public JsonResult investInfoByBpId(Long bpId, PageWhere pageWhere) {
        TbInvestInfo tbInvestInfo = new TbInvestInfo();
        tbInvestInfo.setIiBpId(bpId);
        List<TbInvestInfo> tbInvestInfos = productService.selectInvestInfoBybpId(tbInvestInfo, pageWhere);
        List<InvestInfoVM> investInfoVMs = new ArrayList<>();
        tbInvestInfos.forEach(o -> investInfoVMs.add(new InvestInfoVM(o)));
        int count = productService.selectInvestInfoByConditionCount(tbInvestInfo);
        return new JsonResultList<>(investInfoVMs, count);
    }

    /**
     * 借款人还款计划
     *
     * @param bpId
     * @param pageWhere
     * @return
     */
    @RequestMapping("incomeDetailByBpId")
    @ResponseBody
    public JsonResult incomeDetailByBpId(Long bpId, PageWhere pageWhere) {
        RepaymentDetailSM repaymentDetailSM = new RepaymentDetailSM();
        repaymentDetailSM.setIndBpId(bpId);

        List<TbIncomeDetail> incomeDetails = productService.selectGroupByConditionForPage(repaymentDetailSM, pageWhere);
        List<IncomeDetailsVM> incomeDetailsVMs = new ArrayList<>();

        int count = productService.selectGroupByConditionCount(repaymentDetailSM);
        incomeDetails.forEach(o -> incomeDetailsVMs.add(new IncomeDetailsVM(o)));
        return new JsonResultList<>(incomeDetailsVMs, count);
    }

    /**
     * 投资
     * @param form
     * @return
     */
//    @RequestMapping("investBid")
//    @ResponseBody
//    public JsonResult investBid(InvestInfoForm form){
//        CurrentUser currentUser = CurrentUserContextHolder.get();
//        if(currentUser == null){            //未登录 重定向登录页
//            return new JsonResultError("尚未登录");
//        }
//
//        //校验
//        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
//        if (resultValidation.isHasErrors()) {
//            return new JsonResultError(resultValidation.getErrorMsg().toString());
//        }
//
//        //判断是否为新手标
//        TbBidPlan tbBidPlan = productService.selectByBpId(form.getBpId());
//        if(tbBidPlan.getBpType()==2){
//            TbInvestInfo tbInvestInfo = new TbInvestInfo();
//            tbInvestInfo.setIiLoginId(currentUser.getId());
//
//            int count = investService.selectInvestBaseByLoginIdForPageCount(tbInvestInfo);
//            if(count>0){
//                return new JsonResultError("新手专享");
//            }
//        }
//
//        String[] result = productService.investBid(form.getBpId(), currentUser.getId(), form.getInvestMoney());
//
//        ProductResultVM productResultVM = new ProductResultVM();
//        productResultVM.setFlag(result[0]);
//        productResultVM.setMsg(result[1]);
//        return new JsonResultData<>(productResultVM);
//    }

    /**
     * 首页最近投资10条
     *
     * @return
     */
    @RequestMapping("recentInvestment")
    @ResponseBody
    public JsonResult recentInvestment() throws Exception {
        TbInvestInfo model = new TbInvestInfo();
        model.setIiIsDel(1);

        //首页最近投资列表缓存10分钟
        String keyMd5 = CachedUtil.KeyMd5(model);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        CachedSetting cachedSetting = Constants.CACHED_API_RECENTINVEST_INDEX;
        CachedWrapper<List<RecentInvestVM>> recentInvestListCached = redisUtil.getCachedWrapperByMutexKey(
                cachedSetting.getKey() + keyMd5,
                cachedSetting.getKeyExpireSec(),
                cachedSetting.getNullValueExpireSec(),
                cachedSetting.getKeyMutexExpireSec(),
                new CachedWrapperExecutor<List<RecentInvestVM>>() {
                    @Override
                    public List<RecentInvestVM> execute() {
                        PageWhere page = new PageWhere(0, 10);
                        page.setOrderBy("ii_id DESC");
                        List<TbInvestInfo> investInfoList = productService.recentInvestment(model, page);
                        if (investInfoList == null || investInfoList.size() == 0) {
                            return null;
                        }
                        List<RecentInvestVM> viewModelList = new ArrayList<>();
                        investInfoList.forEach(o -> viewModelList.add(new RecentInvestVM(o)));
                        return viewModelList;
                    }
                });
        return new JsonResultList<>(recentInvestListCached.getData());
    }

    /**
     * 首页投资排行榜
     *
     * @return
     */
    @RequestMapping("investmentTop")
    @ResponseBody
    public JsonResult investmentTop() {

        List<TbInvestInfo> tbInvestInfos = investService.selectInvestmentTop();

        List<InvestmentTopVM> vms = new ArrayList<>();

        tbInvestInfos.forEach(o-> vms.add(new InvestmentTopVM(o)));
//        vms.add(new InvestmentTopVM("司**", "4510000"));
//        vms.add(new InvestmentTopVM("王**", "3000000"));
//        vms.add(new InvestmentTopVM("高**", "670000"));
//        vms.add(new InvestmentTopVM("吴**", "670000"));
//        vms.add(new InvestmentTopVM("王**", "625000"));
//        vms.add(new InvestmentTopVM("杨**", "600000"));
//        vms.add(new InvestmentTopVM("谢**", "580000"));

        return new JsonResultList<>(vms);
    }

    /**
     * 投资
     *
     * @param form
     * @return
     */
    @RequestMapping("investBid")
    @ResponseBody
    public JsonResult investBid(InvestInfoForm form) {
        CurrentUser currentUser = CurrentUserContextHolder.get();
        if (currentUser == null) {            //未登录 重定向登录页
            return new JsonResultError("尚未登录");
        }
        //查询标的信息
        TbBidPlan bidPlan = productService.selectByBpId(form.getBpId());
        if (bidPlan.getBpLoginId() == currentUser.getId()) {
            return new JsonResultError("投资人不能是该标的借款人");
        }
        //投资人详情
        TbUserDetails outUser = userService.getUserDetailsInfo(currentUser.getId());
        if (outUser != null) {
            if (outUser.getUdThirdAccount() == null) {
                return new JsonResultError("您尚未开通第三方");
            }
        }
        //投资人账户
        TbAccountsFunds accountsFunds = userService.getAccountsFundsInfo(currentUser.getId());
        if (accountsFunds == null || accountsFunds.getAfBalance().compareTo(form.getInvestMoney()) < 0) {
            return new JsonResultError("账户余额不足");
        }

        //判断是否为新手标
        if (bidPlan.getBpType() == 2) {
            TbInvestInfo tbInvestInfo = new TbInvestInfo();
            tbInvestInfo.setIiLoginId(currentUser.getId());

            int count = investService.selectInvestBaseByLoginIdForPageCount(tbInvestInfo);
            if (count > 0) {
                return new JsonResultError("新手专享");
            }
        }
        //校验
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg().toString());
        }

        //判断投资金额是否符合规则
        if (bidPlan.getBpSurplusMoney().compareTo(bidPlan.getBpStartMoney()) >= 0) {
            if (form.getInvestMoney().compareTo(bidPlan.getBpStartMoney()) >= 0 && form.getInvestMoney().compareTo(bidPlan.getBpTopMoney()) <= 0) {
                BigDecimal remainder = (form.getInvestMoney().subtract(bidPlan.getBpStartMoney())).remainder(bidPlan.getBpRiseMoney());
                if (remainder.compareTo(BigDecimal.ZERO) != 0) {
                    return new JsonResultError("投资金额不符合递增规则");
                }
            } else {
                return new JsonResultError("投资金额小于起投金额或超出上限金额");
            }
        } else {
            if (form.getInvestMoney().compareTo(bidPlan.getBpSurplusMoney()) != 0) {
                return new JsonResultError("投资金额必须为剩余金额");
            }
        }
        HttpUtil httpUtil = new HttpUtil();
        ResultInfo resultInfo = productService.investBid(outUser, bidPlan, form.getInvestMoney(), httpUtil.getPlatform(request), httpUtil.getPlatformType(request));
        ProductResultVM productResultVM = new ProductResultVM();
        productResultVM.setFlag(resultInfo.getSuccess_is_ok());
        productResultVM.setMsg(resultInfo.getMsg());
//        this.investBid(outUser, bidPlan, form.getInvestMoney(),response);

        return new JsonResultData<>(productResultVM);
    }


    @RequestMapping("expectRevenue")
    @ResponseBody
    public JsonResult expectRevenue(ExpectRevenueForm form) {

        //校验
        ValidationResult resultValidation = ValidationUtil.validateEntity(form);
        if (resultValidation.isHasErrors()) {
            return new JsonResultError(resultValidation.getErrorMsg().toString());
        }

        int days = BetweenDay.differentDays(form.getBpExpectLoanDate(), form.getBpExpectRepayDate());

        BigDecimal investMoney = form.getInvestMoney();
        BigDecimal revenue = BigDecimal.ZERO;

        if (form.getInvestMoney().compareTo(BigDecimal.ZERO) > 0) {
            revenue = investMoney.multiply(form.getBpRate()).multiply(new BigDecimal(days)).divide(new BigDecimal(100)).
                    divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
        }

        ExpectRevenueVM vm = new ExpectRevenueVM();
        vm.setInvestMoney(investMoney);
        vm.setExpectRevenue(revenue);

        return new JsonResultData<>(vm);
    }

//    public void investBid(TbUserDetails outUser,TbBidPlan tbBidPlan, BigDecimal investMoney, HttpServletResponse response){
//        ResultInfo resultInfo = productService.investBid(outUser, tbBidPlan, investMoney);
//        if(resultInfo.getSuccess_is_ok()){
//            response.sendRedirect();
//        }else{
//            response.sendRedirect();
//        }
//    }


    //缓存中删除标的详情数据
//    @RequestMapping("cleanProductDetail")
//    @ResponseBody
//    public JsonResult cleanProductDetail(Long bpId){
//        String keyMd5 = CachedUtil.KeyMd5(bpId);
//        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
//        CachedSetting cachedSetting = Constants.CACHED_API_PRODUCT_PRODUCTDETAIL;
//        Long result = redisUtil.del(cachedSetting.getKey() + keyMd5);
//        if(result==1){
//            return new JsonResultOk();
//        }else{
//            return new JsonResultError();
//        }
//    }


    /**
     * 缓存标的详情页数据  不起作用
     * @param bpId
     * @return
     * @throws Exception
     */
    public JsonResult productDetail1(Long bpId){

        //标的详情页缓存5
        String keyMd5 = CachedUtil.KeyMd5(bpId);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        CachedSetting cachedSetting = Constants.CACHED_API_PRODUCT_PRODUCTDETAIL;
        CachedWrapper<String> productDetailCached = null;
        try {
            productDetailCached = redisUtil.getCachedWrapperByMutexKey(
                    cachedSetting.getKey() + keyMd5,
                    cachedSetting.getKeyExpireSec(),
                    cachedSetting.getNullValueExpireSec(),
                    cachedSetting.getKeyMutexExpireSec(),
                    new CachedWrapperExecutor<String>() {

                        @Override
                        public String execute() throws Exception {
                            TbBidPlan tbBidPlan = productService.selectByBpId(bpId);
                            if (tbBidPlan == null) {
                                return null;
                            } else {
                                return FastJsonUtil.serialize(new ProductDetailVM(tbBidPlan));
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResultData<>(null);
        }
        ProductDetailVM productDetailVM = FastJsonUtil.deserialize(productDetailCached.getData(), ProductDetailVM.class);
        if(productDetailVM ==null || productDetailVM.getBpStatus()!=2) return new JsonResultData<>(productDetailVM);

        TbBidPlan tbBidPlan = productService.selectByBpId(bpId);
        return new JsonResultData<>(new ProductDetailVM(tbBidPlan));

    }

    /**
     * 缓存标的列表数据
     * @param form
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public JsonResult list(ProductForm form){

        PageWhere pageWhere = new PageWhere();
        if(form.getPageIndex()!=null){
            pageWhere.setPageIndex(form.getPageIndex());
        }
        if(form.getPageSize()!=null){
            pageWhere.setPageSize(form.getPageSize());
        }
        ProductSM productSM = ProductForm.toEntity(form);
        pageWhere.setOrderBy("bp_status ASC, bp_start_time DESC,bp_bid_money DESC, bp_rate DESC");
        //产品列表缓存5分钟
        String keyMd5 = CachedUtil.KeyMd5(form);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        CachedSetting cachedSetting = Constants.CACHED_API_PRODUCT_LIST;
        CachedWrapper<String>  productListCached = null;
        try {
            productListCached = redisUtil.getCachedWrapperByMutexKey(
                    cachedSetting.getKey() + keyMd5,
                    cachedSetting.getKeyExpireSec(),
                    cachedSetting.getNullValueExpireSec(),
                    cachedSetting.getKeyMutexExpireSec(),
                    new CachedWrapperExecutor<String>() {
                        @Override
                        public String execute() {
                            List<TbBidPlan> tbBidPlans = productService.selectP2PList(productSM, pageWhere);
                            List<ProductVm> productVms = new ArrayList<>();
                            tbBidPlans.forEach(o -> productVms.add(new ProductVm(o)));
                            int count = productService.selectP2PListCount(productSM);

                            String productListStr = FastJsonUtil.serialize(new ProductListVM(productVms,count));
                            return productListStr;
                        }
                    });


        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResultList<>(new ArrayList<ProductVm>(),0);
        }
        //todo 查找更新
        String productListStr2 = productListCached.getData();
        ProductListVM productListVM = FastJsonUtil.deserialize(productListStr2, ProductListVM.class);
        List<ProductVm> productVmList = productListVM.getProductVmList();
        //缓存集合为null直接返回
        if(productVmList==null || productVmList.size()==0) return new JsonResultList<>(new ArrayList<ProductVm>(),0);

        //非null查询更新
        List prodIds = new ArrayList();
        for(int i=0; i< productVmList.size(); i++){
            ProductVm productVm = productVmList.get(i);
            if(productVm.getBpStatus()==2){
                prodIds.add(productVm.getBpId());
            }
        }
        //无更新数据时直接返回
        if(prodIds.size()==0){
            return new JsonResultList<>(productVmList, productListVM.getCount());
        }
        //有更新数据时查询更新缓存数据
        List<TbBidPlan> tbBidPlans = productService.selectCacheData(prodIds);

        for (ProductVm bill : productVmList) {
            for (TbBidPlan bills : tbBidPlans) {
                if(bill.getBpId().equals(bills.getBpId())){
                    bill.setBpSurplusMoney(bills.getBpSurplusMoney());
                    bill.setBpStatus(bills.getBpStatus());
                    bill.setProgress((bill.getBpBidMoney().subtract(bill.getBpSurplusMoney()).multiply(new BigDecimal(100)).divide(bill.getBpBidMoney(),0, BigDecimal.ROUND_DOWN).toString()));
                }
            }
        }

        return new JsonResultList<>(productVmList, productListVM.getCount());
    }
}
