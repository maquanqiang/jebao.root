package com.jebao.p2p.service.impl.user;

import com.jebao.common.utils.incomeDetail.IncomeDetailUtil;
import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbThirdInterfaceLogDao;
import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.dao.dao.user.TbFundsDetailsDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.p2p.service.inf.user.ILoanManageServiceInf;
import com.jebao.thirdPay.fuiou.impl.PreAuthServiceImpl;
import com.jebao.thirdPay.fuiou.impl.TransferBuServiceImpl;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthRequest;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthResponse;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuRequest;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuResponse;
import com.jebao.thirdPay.fuiou.util.RequestData;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2016/12/16.
 */
@Service
public class LoanManageServiceImpl implements ILoanManageServiceInf {

    @Autowired
    private TbIncomeDetailDao tbIncomeDetailDao;
    @Autowired
    private TransferBuServiceImpl transferBuService;
    @Autowired
    private TbAccountsFundsDao accountsFundsDao;
    @Autowired
    private TbBidPlanDao tbBidPlanDao;
    @Autowired
    private TbThirdInterfaceLogDao thirdInterfaceLogDao;
    @Autowired
    private TbFundsDetailsDao fundsDetailsDao;
    @Autowired
    private TbInvestInfoDao investInfoDao;
    @Autowired
    private PreAuthServiceImpl preAuthService;

    private static Logger LOGGER = LoggerFactory.getLogger(LoanManageServiceImpl.class);

//    @Override
//    public ResultInfo repay(Long bpId, Long loginId, Integer period, BigDecimal repayMoney) {
//
//        ResultInfo resultInfo = new ResultInfo(true, "还款成功");
//        //查看用户本地余额
//        TbAccountsFunds accountsFunds = accountsFundsDao.selectByLoginId(loginId);
//        if(accountsFunds.getAfBalance().compareTo(repayMoney)==-1){
//            resultInfo.setSuccess_is_ok(false);
//            resultInfo.setMsg("账户余额不足，请及时充值");
//            return resultInfo;
//        }
//
//        //查询还款列表
//        TbIncomeDetail tbIncomeDetail = new TbIncomeDetail();
//        tbIncomeDetail.setIndBpId(bpId);
//        tbIncomeDetail.setIndPeriods(period);
//        tbIncomeDetail.setIndStatus(1);
//        BigDecimal total = tbIncomeDetailDao.repaymoneyTotal(tbIncomeDetail);
//
//        //并判断还款金额是否跟页面一致
//        if(total.compareTo(repayMoney)!=0){
//            resultInfo.setSuccess_is_ok(false);
//            resultInfo.setMsg("还款金额有误，稍后重试，有疑问请联系平台");
//            return resultInfo;
//        }
//
//        TbBidPlan plan = tbBidPlanDao.selectByPrimaryKey(bpId);
//
//        //循环调用划拨
//        List<TbIncomeDetail> incomeDetails = tbIncomeDetailDao.repaymentList(tbIncomeDetail);
//        if(incomeDetails!=null && incomeDetails.size()>0){
//            for (TbIncomeDetail detail : incomeDetails){
//                if(!detail.getIncomeDetailMD5().equals(IncomeDetailUtil.getIncomeDetailMD5(detail.toMD5()))){
//                    resultInfo.setSuccess_is_ok(false);
//                    resultInfo.setMsg("还款金额有误，-台账MD5验证失败，有疑问请联系平台");
//                    return resultInfo;
//                }
//                if(new Date().before(detail.getIndDateTime())){
//                    resultInfo.setSuccess_is_ok(false);
//                    resultInfo.setMsg("还款日未到期，有疑问请联系平台");
//                    return resultInfo;
//                }
//            }
//
//
//            for (TbIncomeDetail detail : incomeDetails){
//                //校验数据有效性
//
//                    String amt = detail.getIndMoney().multiply(new BigDecimal(100)).setScale(0).toString();
//                    TransferBuRequest request = new TransferBuRequest();
//
//                    request.setAmt(amt);
//                    request.setRem("repay");
//                    request.setIn_cust_no(detail.getIndThirdAccount());
//                    request.setOut_cust_no(accountsFunds.getAfThirdAccount());
//                    request.setContract_no("");
//                    try {
//                        //保存富友日志
//                        TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
//                        thirdInterfaceLog.setTilType(3);
//                        thirdInterfaceLog.setTilCreateTime(new Date());
//                        thirdInterfaceLog.setTilSerialNumber(request.getMchnt_txn_ssn());
//                        thirdInterfaceLog.setTilReqText(request.requestSignPlain());
//                        thirdInterfaceLogDao.insert(thirdInterfaceLog);
//
//                        TransferBuResponse response = transferBuService.post(request);
//                        //更新富友日志
//                        String resqStr = XmlUtil.toXML(response);
//                        thirdInterfaceLog.setTilReturnCode(response.getPlain().getResp_code());
//                        thirdInterfaceLog.setTilRespText(resqStr);
//                        thirdInterfaceLogDao.updateByPrimaryKeySelective(thirdInterfaceLog);
//
//                        if("0000".equals(response.getPlain().getResp_code())){
//                            //修改还款明细信息
//                            TbIncomeDetail updateDetail = new TbIncomeDetail();
//                            updateDetail.setIndStatus(2);
//                            updateDetail.setIndFactDateTime(new Date());
//                            updateDetail.setIndFactMoeny(detail.getIndMoney());
//                            updateDetail.setIndUpdateTime(updateDetail.getIndFactDateTime());
//                            updateDetail.setIndId(detail.getIndId());
//                            tbIncomeDetailDao.updateByPrimaryKeySelective(updateDetail);
//
//                            //添加出账流水记录
//                            TbFundsDetails outFundsDetails = new TbFundsDetails();
//                            outFundsDetails.setFdLoginId(loginId);
//                            outFundsDetails.setFdThirdAccount(accountsFunds.getAfThirdAccount());
//                            outFundsDetails.setFdSerialNumber(request.getMchnt_txn_ssn());
//                            outFundsDetails.setFdSerialTypeId(detail.getIndFundType()==1?5:6);            //3投资冻结 4 借款入账  5本金还款  6付息
//                            outFundsDetails.setFdSerialTypeName(detail.getIndFundType()==1?"本金还款":"付息");
//                            outFundsDetails.setFdSerialAmount(detail.getIndMoney());
//                            outFundsDetails.setFdBalanceBefore(accountsFunds.getAfBalance());
//                            outFundsDetails.setFdBalanceAfter(accountsFunds.getAfBalance().subtract(detail.getIndMoney()));
//                            outFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
//                            outFundsDetails.setFdBpId(bpId);
//                            outFundsDetails.setFdBpName(plan.getBpName());
//                            outFundsDetails.setFdCreateTime(new Date());
//                            outFundsDetails.setFdSerialTime(new Date());
//                            outFundsDetails.setFdBalanceStatus(2);           //支出
//                            outFundsDetails.setFdSerialStatus(1);
//                            outFundsDetails.setFdIsDel(1);
//                            fundsDetailsDao.insert(outFundsDetails);
//
//                            accountsFunds.setAfUpdateTime(new Date());
//                            accountsFunds.setAfBalance(accountsFunds.getAfBalance().subtract(detail.getIndMoney()));
//                            accountsFundsDao.updateByPrimaryKeySelective(accountsFunds);
//
//
//                            //添加入账流水记录
//                            TbAccountsFunds inAccount = accountsFundsDao.selectByLoginId(detail.getIndLoginId());
//                            TbFundsDetails inFundsDetails = new TbFundsDetails();
//                            inFundsDetails.setFdLoginId(detail.getIndLoginId());
//                            inFundsDetails.setFdThirdAccount(detail.getIndThirdAccount());
//                            inFundsDetails.setFdSerialNumber(request.getMchnt_txn_ssn());
//                            inFundsDetails.setFdSerialTypeId(detail.getIndFundType()==1?5:6);            //3投资冻结 4 借款入账  5本金还款  6付息
//                            inFundsDetails.setFdSerialTypeName(detail.getIndFundType()==1?"回收本金":"回收收益");
//                            inFundsDetails.setFdSerialAmount(detail.getIndMoney());
//                            inFundsDetails.setFdBalanceBefore(inAccount.getAfBalance());
//                            inFundsDetails.setFdBalanceAfter(inAccount.getAfBalance().add(detail.getIndMoney()));
//                            inFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
//                            inFundsDetails.setFdBpId(bpId);
//                            inFundsDetails.setFdBpName(plan.getBpName());
//                            inFundsDetails.setFdCreateTime(new Date());
//                            inFundsDetails.setFdSerialTime(new Date());
//                            inFundsDetails.setFdBalanceStatus(1);           //收入
//                            inFundsDetails.setFdSerialStatus(1);
//                            inFundsDetails.setFdIsDel(1);
//                            fundsDetailsDao.insert(inFundsDetails);
//
//
//                            inAccount.setAfUpdateTime(new Date());
//                            inAccount.setAfBalance(inAccount.getAfBalance().add(detail.getIndMoney()));
//                            accountsFundsDao.updateByPrimaryKeySelective(inAccount);
//                        }else{
//                            resultInfo.setSuccess_is_ok(false);
//                            if(LOGGER.isDebugEnabled()){
//                                LOGGER.debug("还款失败，当前还款记录ID：{}, 第三方返回码：{}",detail.getIndId(), response.getPlain().getResp_code());
//                            }
//
//                        }
//                    } catch (Exception e) {
//                        resultInfo.setSuccess_is_ok(false);
//                        if(LOGGER.isErrorEnabled()){
//                            LOGGER.error("还款失败，当前还款记录ID：{}",detail.getIndId());
//                        }
//                        e.printStackTrace();
//                    }
//                }
//            }
//        //成功返回true 修改标的信息
//        if(resultInfo.getSuccess_is_ok()){
//
//            plan.setBpUpdateTime(new Date());
//            if(plan.getBpInterestPayType()==1){
//                plan.setBpStatus(10);
//            }else if(plan.getBpInterestPayType()==2){      //说明已到最后一期  修改标的为完成
//                if(period == plan.getBpPeriods()){
//                    plan.setBpStatus(10);
//                }
//            }
//            plan.setBpRepayedPeriods(period);
//
//            tbBidPlanDao.updateByPrimaryKeySelective(plan);
//            //修改投资记录中已还款期数
//            TbInvestInfo tbInvestInfo = new TbInvestInfo();
//            tbInvestInfo.setIiBpId(bpId);
//            tbInvestInfo.setIiIsDel(1);
//            PageWhere pageWhere = new PageWhere(0, 10000);
//            List<TbInvestInfo> tbInvestInfos = investInfoDao.selectByConditionForPage(tbInvestInfo, pageWhere);
//            //更新投资列表状态
//            if(tbInvestInfos!=null && tbInvestInfos.size()>0){
//                for(TbInvestInfo info : tbInvestInfos){
//                    info.setIiBpRepayedPeriods(period);
//                    info.setIiUpdateTime(new Date());
//                    if(plan.getBpInterestPayType()==1){
//                        info.setIiFreezeStatus(TbInvestInfo.STATUS_COMPLETE);
//                    }else if(plan.getBpInterestPayType()==2){      //说明已到最后一期  修改标的为完成
//                        if(period == plan.getBpPeriods()){
//                            info.setIiFreezeStatus(TbInvestInfo.STATUS_COMPLETE);
//                        }
//                    }
//                    investInfoDao.updateByPrimaryKeySelective(info);
//                }
//            }
//            //TODO 借款人还款明细 作废
////            TbLoanerRepaymentDetail tbLoanerRepaymentDetail = new TbLoanerRepaymentDetail();
////            tbLoanerRepaymentDetail.setLrdFactDateTime(new Date());
////            tbLoanerRepaymentDetail.setLrdFactMoney(repayMoney);
//        }
//        return resultInfo;
//    }



    @Override
    public ResultInfo repay(TbBidPlan bidPlan, TbAccountsFunds accountsFunds, Integer period, BigDecimal repayMoney) {

        ResultInfo resultInfo = new ResultInfo(true, "还款预冻结成功");

        //查询还款列表
        TbIncomeDetail tbIncomeDetail = new TbIncomeDetail();
        tbIncomeDetail.setIndBpId(bidPlan.getBpId());
        tbIncomeDetail.setIndPeriods(period);
        tbIncomeDetail.setIndStatus(1);

        BigDecimal total = tbIncomeDetailDao.repaymoneyTotal(tbIncomeDetail);
        //并判断还款金额是否跟页面一致
        if(total.compareTo(repayMoney)!=0){
            resultInfo.setSuccess_is_ok(false);
            resultInfo.setMsg("还款金额有误，稍后重试，有疑问请联系平台");
            return resultInfo;
        }

        //循环调用划拨
        List<TbIncomeDetail> incomeDetails = tbIncomeDetailDao.repaymentList(tbIncomeDetail);
        if(incomeDetails!=null && incomeDetails.size()>0){
            //校验数据有效性
            for (TbIncomeDetail detail : incomeDetails){
                if(!detail.getIncomeDetailMD5().equals(IncomeDetailUtil.getIncomeDetailMD5(detail.toMD5()))){
                    resultInfo.setSuccess_is_ok(false);
                    resultInfo.setMsg("还款金额有误，-台账MD5验证失败，有疑问请联系平台");
                    return resultInfo;
                }
                if(new Date().before(detail.getIndDateTime())){
                    resultInfo.setSuccess_is_ok(false);
                    resultInfo.setMsg("未到还款日期，有疑问请联系平台");
                    return resultInfo;
                }
            }


            for (TbIncomeDetail detail : incomeDetails){

                String amt = detail.getIndMoney().multiply(new BigDecimal(100)).setScale(0).toString();
                PreAuthRequest reqData = new PreAuthRequest();

                reqData.setAmt(amt);
                reqData.setRem("repay");
                reqData.setIn_cust_no(detail.getIndThirdAccount());
                reqData.setOut_cust_no(accountsFunds.getAfThirdAccount());

                try {
                    //保存富友日志
                    TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
                    thirdInterfaceLog.setTilType(3);
                    thirdInterfaceLog.setTilCreateTime(new Date());
                    thirdInterfaceLog.setTilSerialNumber(reqData.getMchnt_txn_ssn());
                    thirdInterfaceLog.setTilReqText(reqData.requestSignPlain());
                    thirdInterfaceLogDao.insert(thirdInterfaceLog);

                    PreAuthResponse response = preAuthService.post(reqData);
                    //更新富友日志
                    String resqStr = XmlUtil.toXML(response);
                    thirdInterfaceLog.setTilReturnCode(response.getPlain().getResp_code());
                    thirdInterfaceLog.setTilRespText(resqStr);
                    thirdInterfaceLogDao.updateByPrimaryKeySelective(thirdInterfaceLog);

                    if("0000".equals(response.getPlain().getResp_code())){
                        //修改还款明细信息
                        TbIncomeDetail updateDetail = new TbIncomeDetail();
                        updateDetail.setIndStatus(0);               //0还款冻结 1 未还  2 已还
                        updateDetail.setIndUpdateTime(new Date());
                        updateDetail.setIndId(detail.getIndId());
                        updateDetail.setIndThirdContractNo(response.getPlain().getContract_no());
                        tbIncomeDetailDao.updateByPrimaryKeySelective(updateDetail);

                        //添加出账流水记录
                        TbFundsDetails outFundsDetails = new TbFundsDetails();
                        outFundsDetails.setFdLoginId(accountsFunds.getAfLoginId());
                        outFundsDetails.setFdThirdAccount(accountsFunds.getAfThirdAccount());
                        outFundsDetails.setFdSerialNumber(reqData.getMchnt_txn_ssn());
                        outFundsDetails.setFdSerialTypeId(detail.getIndFundType()==1?5:6);            //3投资冻结 4 借款入账  5本金还款  6付息  8还款冻结
                        outFundsDetails.setFdSerialTypeName("还款预冻结");
                        outFundsDetails.setFdSerialAmount(detail.getIndMoney());
                        outFundsDetails.setFdBalanceBefore(accountsFunds.getAfBalance());
                        outFundsDetails.setFdBalanceAfter(accountsFunds.getAfBalance().subtract(detail.getIndMoney()));
                        outFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
                        outFundsDetails.setFdBpId(bidPlan.getBpId());
                        outFundsDetails.setFdBpName(bidPlan.getBpName());
                        outFundsDetails.setFdCreateTime(new Date());
                        outFundsDetails.setFdSerialTime(new Date());
                        outFundsDetails.setFdBalanceStatus(2);           //支出
                        outFundsDetails.setFdSerialStatus(1);
                        outFundsDetails.setFdIsDel(1);
                        fundsDetailsDao.insert(outFundsDetails);

                        accountsFunds.setAfUpdateTime(new Date());
                        accountsFunds.setAfBalance(accountsFunds.getAfBalance().subtract(detail.getIndMoney()));
                        accountsFundsDao.updateByPrimaryKeySelective(accountsFunds);

                    }else{
                        resultInfo.setSuccess_is_ok(false);
                        if(LOGGER.isDebugEnabled()){
                            LOGGER.debug("还款冻结失败，当前还款记录ID：{}, 第三方返回码：{}",detail.getIndId(), response.getPlain().getResp_code());
                        }

                    }
                } catch (Exception e) {
                    resultInfo.setSuccess_is_ok(false);
                    if(LOGGER.isErrorEnabled()){
                        LOGGER.error("还款冻结失败，当前还款记录ID：{}",detail.getIndId());
                    }
                    e.printStackTrace();
                }
            }
        }
//        //成功返回true 修改标的信息
//        if(resultInfo.getSuccess_is_ok()){
//
//            bidPlan.setBpUpdateTime(new Date());
//            bidPlan.setBpRepayedPeriods(period);
//
//            tbBidPlanDao.updateByPrimaryKeySelective(bidPlan);
//            //修改投资记录中已还款期数
//            TbInvestInfo tbInvestInfo = new TbInvestInfo();
//            tbInvestInfo.setIiBpId(bidPlan.getBpId());
//            tbInvestInfo.setIiIsDel(1);
//            PageWhere pageWhere = new PageWhere(0, 10000);
//            List<TbInvestInfo> tbInvestInfos = investInfoDao.selectByConditionForPage(tbInvestInfo, pageWhere);
//            //更新投资列表状态
//            if(tbInvestInfos!=null && tbInvestInfos.size()>0){
//                for(TbInvestInfo info : tbInvestInfos){
//                    info.setIiBpRepayedPeriods(period);
//                    info.setIiUpdateTime(new Date());
//                    investInfoDao.updateByPrimaryKeySelective(info);
//                }
//            }
//            TbLoanerRepaymentDetail tbLoanerRepaymentDetail = new TbLoanerRepaymentDetail();
//            tbLoanerRepaymentDetail.setLrdFactDateTime(new Date());
//            tbLoanerRepaymentDetail.setLrdFactMoney(repayMoney);
//        }
        return resultInfo;
    }

}
