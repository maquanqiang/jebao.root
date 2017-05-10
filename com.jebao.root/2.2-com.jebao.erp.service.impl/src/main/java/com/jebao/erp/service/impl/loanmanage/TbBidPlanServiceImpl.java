package com.jebao.erp.service.impl.loanmanage;

import com.jebao.common.utils.sms.SmsSendUtil;
import com.jebao.erp.service.inf.loanmanage.ITbBidPlanServiceInf;
import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbThirdInterfaceLogDao;
import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.dao.dao.user.TbFundsDetailsDao;
import com.jebao.jebaodb.dao.dao.user.TbUserDetailsDao;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanExtSM;
import com.jebao.jebaodb.entity.loanmanage.search.BidPlanSM;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.thirdPay.fuiou.impl.TransferBuServiceImpl;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuRequest;
import com.jebao.thirdPay.fuiou.model.transferBu.TransferBuResponse;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2016/11/17.
 */
@Service
public class TbBidPlanServiceImpl implements ITbBidPlanServiceInf {

    @Autowired
    private TbBidPlanDao bidPlanDao;
    @Autowired
    private TbThirdInterfaceLogDao thirdInterfaceLogDao;
    @Autowired
    private TbAccountsFundsDao accountsFundsDao;
    @Autowired
    private TbInvestInfoDao investInfoDao;
    @Autowired
    private TransferBuServiceImpl transferBuService;
    @Autowired
    private TbFundsDetailsDao fundsDetailsDao;
    @Autowired
    private TbIncomeDetailDao incomeDetailDao;
    @Autowired
    private TbUserDetailsDao userDetailsDao;

    private final static Logger LOGGER = LoggerFactory.getLogger(TbBidPlanServiceImpl.class);

    @Override
    public int add(TbBidPlan plan) {

        int insert = bidPlanDao.insert(plan);
        return insert;
    }

    @Override
    public TbBidPlan selectByBpId(Long bpId) {
        TbBidPlan bidPlan = bidPlanDao.selectByPrimaryKey(bpId);
        return bidPlan;
    }

    @Override
    public List<TbBidPlan> selectByConditionForPage(TbBidPlan bidPlan, PageWhere pageWhere) {
        List<TbBidPlan> tbBidPlans = bidPlanDao.selectByConditionForPage(bidPlan, pageWhere);
        return tbBidPlans;
    }

    @Override
    public int selectByConditionCount(TbBidPlan record) {
        int count = bidPlanDao.selectByConditionCount(record);
        return count;
    }

    @Override
    public int updateByBidIdSelective(TbBidPlan record) {
        int intCount = bidPlanDao.updateByPrimaryKeySelective(record);
        return intCount;
    }

    @Override
    public List<TbBidPlan> selectBySelfConditionForPage(BidPlanSM record, PageWhere pageWhere) {
        return bidPlanDao.selectBySelfConditionForPage(record, pageWhere);
    }

    @Override
    public int selectBySelfConditionCount(BidPlanSM record) {
        int count = bidPlanDao.selectBySelfConditionCount(record);
        return count;
    }

    @Override
    public boolean doLoan(TbBidPlan record) {
        //借款人账户
        TbAccountsFunds tbAccountsFunds = accountsFundsDao.selectByLoginId(record.getBpLoginId());

        boolean flag = false;

        //获取该标的投资列表
        TbInvestInfo tbInvestInfo = new TbInvestInfo();
        tbInvestInfo.setIiBpId(record.getBpId());
        PageWhere pageWhere = new PageWhere(0, 10000);
        List<TbInvestInfo> tbInvestInfos = investInfoDao.selectBybpId(tbInvestInfo, pageWhere);

        //调用富友接口
        if(tbInvestInfos!=null && tbInvestInfos.size()>0){
            for (TbInvestInfo investInfo : tbInvestInfos){

                TransferBuRequest reqData = new TransferBuRequest();

                //提交富友参数
                String amt = investInfo.getIiMoney().multiply(new BigDecimal(100)).setScale(0).toString();
                reqData.setOut_cust_no(investInfo.getIiThirdAccount());
                reqData.setIn_cust_no(tbAccountsFunds.getAfThirdAccount());
                reqData.setAmt(amt);
                reqData.setContract_no(investInfo.getIiContractNo());
                reqData.setRem("doLoan");

                TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
                thirdInterfaceLog.setTilCreateTime(new Date());
                thirdInterfaceLog.setTilType(6);
                thirdInterfaceLog.setTilReqText(reqData.requestSignPlain());
                thirdInterfaceLog.setTilSerialNumber(reqData.getMchnt_txn_ssn());

                thirdInterfaceLogDao.insert(thirdInterfaceLog);

                try {
                    TransferBuResponse thirdResp = transferBuService.post(reqData);
                    BasePlain plain = thirdResp.getPlain();

                    //富友日志更新
                    String respStr = XmlUtil.toXML(thirdResp);
                    thirdInterfaceLog.setTilReturnCode(plain.getResp_code());
                    thirdInterfaceLog.setTilRespText(respStr);
                    thirdInterfaceLogDao.updateByPrimaryKeySelective(thirdInterfaceLog);

                    if("0000".equals(plain.getResp_code())){
                        //修改投资列表状态
                        investInfo.setIiUpdateTime(new Date());
                        investInfo.setIiFreezeStatus(TbInvestInfo.STATUS_REPAYING);
                        investInfoDao.updateByPrimaryKeySelective(investInfo);

                        //添加出账流水记录
                        TbAccountsFunds outAccount = accountsFundsDao.selectByLoginId(investInfo.getIiLoginId());

                        TbFundsDetails outFundsDetails = new TbFundsDetails();
                        outFundsDetails.setFdLoginId(investInfo.getIiLoginId());
                        outFundsDetails.setFdThirdAccount(outAccount.getAfThirdAccount());
                        outFundsDetails.setFdSerialNumber(reqData.getMchnt_txn_ssn());
                        outFundsDetails.setFdSerialTypeId(7);            //3投资冻结 4 借款入账  5本金还款  6付息  7投资转账
                        outFundsDetails.setFdSerialTypeName("投资");
                        outFundsDetails.setFdSerialAmount(investInfo.getIiMoney());
                        outFundsDetails.setFdBalanceBefore(outAccount.getAfBalance());
                        outFundsDetails.setFdBalanceAfter(outAccount.getAfBalance());
                        outFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
                        outFundsDetails.setFdBpId(investInfo.getIiBpId());
                        outFundsDetails.setFdBpName(investInfo.getIiBpName());
                        outFundsDetails.setFdCreateTime(new Date());
                        outFundsDetails.setFdSerialTime(new Date());
                        outFundsDetails.setFdBalanceStatus(2);           //支出
                        outFundsDetails.setFdSerialStatus(1);
                        outFundsDetails.setFdIsDel(1);
                        outFundsDetails.setFdPlatform(EnumModel.Platform.pc.getValue());
                        outFundsDetails.setFdPlatformType(EnumModel.PlatformType.pc.getValue());
                        outFundsDetails.setFdChannel(0);
                        outFundsDetails.setFdChannelType(0);
                        fundsDetailsDao.insert(outFundsDetails);


                        //添加入账流水记录

                        TbFundsDetails inFundsDetails = new TbFundsDetails();
                        inFundsDetails.setFdLoginId(record.getBpLoginId());
                        inFundsDetails.setFdThirdAccount(tbAccountsFunds.getAfThirdAccount());
                        inFundsDetails.setFdSerialNumber(reqData.getMchnt_txn_ssn());
                        inFundsDetails.setFdSerialTypeId(4);            //3投资冻结 4 借款入账  5本金还款  6付息
                        inFundsDetails.setFdSerialTypeName("借款入账");
                        inFundsDetails.setFdSerialAmount(investInfo.getIiMoney());
                        inFundsDetails.setFdBalanceBefore(tbAccountsFunds.getAfBalance());
                        inFundsDetails.setFdBalanceAfter(tbAccountsFunds.getAfBalance().add(investInfo.getIiMoney()));
                        inFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
                        inFundsDetails.setFdBpId(investInfo.getIiBpId());
                        inFundsDetails.setFdBpName(investInfo.getIiBpName());
                        inFundsDetails.setFdCreateTime(new Date());
                        inFundsDetails.setFdSerialTime(new Date());
                        inFundsDetails.setFdBalanceStatus(1);           //收入
                        inFundsDetails.setFdSerialStatus(1);
                        inFundsDetails.setFdIsDel(1);
                        inFundsDetails.setFdPlatform(EnumModel.Platform.pc.getValue());
                        inFundsDetails.setFdPlatformType(EnumModel.PlatformType.pc.getValue());
                        inFundsDetails.setFdChannel(0);
                        inFundsDetails.setFdChannelType(0);
                        fundsDetailsDao.insert(inFundsDetails);

                        //更新借款人账户信息
                        tbAccountsFunds.setAfUpdateTime(new Date());
                        tbAccountsFunds.setAfBalance(tbAccountsFunds.getAfBalance().add(investInfo.getIiMoney()));
                        accountsFundsDao.updateByPrimaryKeySelective(tbAccountsFunds);

                        flag = true;
                    }else{
                        if(LOGGER.isDebugEnabled()){
                            LOGGER.debug("投资转账失败-流水号：{}, 富友错误码:{}", reqData.getMchnt_txn_ssn(),plain.getResp_code());
                        }
                    }
                } catch (Exception e) {
                    if(LOGGER.isDebugEnabled()){
                        LOGGER.debug("投资转账报错-流水号：{}，当前投资ID：{}", reqData.getMchnt_txn_ssn(), investInfo.getIiId());
                    }
                    e.printStackTrace();
                }
            }
        }

        return flag;
    }

    @Override
    public ResultInfo repayFreeze(TbIncomeDetail tbIncomeDetail) {

        ResultInfo resultInfo = new ResultInfo(true, "还款成功");
        StringBuffer sb = new StringBuffer("还款失败的台账Id:");
        PageWhere pageWhere = new PageWhere(0, 10000);
        List<TbIncomeDetail> incomeDetails = incomeDetailDao.selectByConditionForPage(tbIncomeDetail, pageWhere);
        //资金是否正常冻结
        for(TbIncomeDetail incomeDetail : incomeDetails){
            if(incomeDetail.getIndStatus() != 0 || incomeDetail.getIndThirdContractNo() == null){
                resultInfo.setSuccess_is_ok(false);
                resultInfo.setMsg("还款资金冻结异常");
                return resultInfo;
            }
        }


        TbBidPlan tbBidPlan = bidPlanDao.selectByPrimaryKey(tbIncomeDetail.getIndBpId());
        TbAccountsFunds outUser = accountsFundsDao.selectByLoginId(tbBidPlan.getBpLoginId());
        for (TbIncomeDetail detail : incomeDetails) {

            TransferBuRequest reqData = new TransferBuRequest();

            //提交富友参数
            String amt = detail.getIndMoney().multiply(new BigDecimal(100)).setScale(0).toString();
            reqData.setOut_cust_no(outUser.getAfThirdAccount());
            reqData.setIn_cust_no(detail.getIndThirdAccount());
            reqData.setAmt(amt);
            reqData.setContract_no(detail.getIndThirdContractNo());
            reqData.setRem("repayFreeze");

            TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
            thirdInterfaceLog.setTilCreateTime(new Date());
            thirdInterfaceLog.setTilType(6);
            thirdInterfaceLog.setTilReqText(reqData.requestSignPlain());
            thirdInterfaceLog.setTilSerialNumber(reqData.getMchnt_txn_ssn());

            thirdInterfaceLogDao.insert(thirdInterfaceLog);

            try {
                TransferBuResponse thirdResp = transferBuService.post(reqData);
                BasePlain plain = thirdResp.getPlain();

                //富友日志更新
                String respStr = XmlUtil.toXML(thirdResp);
                thirdInterfaceLog.setTilReturnCode(plain.getResp_code());
                thirdInterfaceLog.setTilRespText(respStr);
                thirdInterfaceLogDao.updateByPrimaryKeySelective(thirdInterfaceLog);

                if ("0000".equals(plain.getResp_code())) {
                    //更新台账信息
                    detail.setIndStatus(2);
                    detail.setIndFactDateTime(new Date());
                    detail.setIndUpdateTime(detail.getIndFactDateTime());
                    detail.setIndFactMoeny(detail.getIndMoney());
                    incomeDetailDao.updateByPrimaryKeySelective(detail);

                    TbAccountsFunds inUserAccount = accountsFundsDao.selectByLoginId(detail.getIndLoginId());
                    //添加入账流水记录
                    TbFundsDetails inFundsDetails = new TbFundsDetails();
                    inFundsDetails.setFdLoginId(detail.getIndLoginId());
                    inFundsDetails.setFdThirdAccount(detail.getIndThirdAccount());
                    inFundsDetails.setFdSerialNumber(reqData.getMchnt_txn_ssn());
                    inFundsDetails.setFdSerialTypeId(detail.getIndFundType() == 1 ? 5 : 6);            //3投资冻结 4 借款入账  5本金还款  6付息
                    inFundsDetails.setFdSerialTypeName(detail.getIndFundType() == 1 ? "本金收回" : "收益利息");
                    inFundsDetails.setFdSerialAmount(detail.getIndMoney());
                    inFundsDetails.setFdBalanceBefore(inUserAccount.getAfBalance());
                    inFundsDetails.setFdBalanceAfter(inUserAccount.getAfBalance().add(detail.getIndMoney()));
                    inFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
                    inFundsDetails.setFdBpId(detail.getIndBpId());
                    inFundsDetails.setFdBpName(detail.getIndBpName());
                    inFundsDetails.setFdCreateTime(new Date());
                    inFundsDetails.setFdSerialTime(new Date());
                    inFundsDetails.setFdBalanceStatus(1);           //收入
                    inFundsDetails.setFdSerialStatus(1);
                    inFundsDetails.setFdIsDel(1);
                    inFundsDetails.setFdPlatform(EnumModel.Platform.pc.getValue());
                    inFundsDetails.setFdPlatformType(EnumModel.PlatformType.pc.getValue());
                    inFundsDetails.setFdChannel(0);
                    inFundsDetails.setFdChannelType(0);
                    fundsDetailsDao.insert(inFundsDetails);

                    //投资人入账
                    inUserAccount.setAfUpdateTime(new Date());
                    inUserAccount.setAfBalance(inUserAccount.getAfBalance().add(detail.getIndMoney()));
                    accountsFundsDao.updateByPrimaryKeySelective(inUserAccount);

                    //添加出账流水记录
                    TbFundsDetails outFundsDetails = new TbFundsDetails();
                    outFundsDetails.setFdLoginId(outUser.getAfLoginId());
                    outFundsDetails.setFdThirdAccount(outUser.getAfThirdAccount());
                    outFundsDetails.setFdSerialNumber(reqData.getMchnt_txn_ssn());
                    outFundsDetails.setFdSerialTypeId(detail.getIndFundType()==1?5:6);            //3投资冻结 4 借款入账  5本金还款  6付息
                    outFundsDetails.setFdSerialTypeName(detail.getIndFundType()==1?"本金还款":"付息");
                    outFundsDetails.setFdSerialAmount(detail.getIndMoney());
                    outFundsDetails.setFdBalanceBefore(outUser.getAfBalance());
                    outFundsDetails.setFdBalanceAfter(outUser.getAfBalance().subtract(detail.getIndMoney()));
                    outFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
                    outFundsDetails.setFdBpId(tbBidPlan.getBpId());
                    outFundsDetails.setFdBpName(tbBidPlan.getBpName());
                    outFundsDetails.setFdCreateTime(new Date());
                    outFundsDetails.setFdSerialTime(new Date());
                    outFundsDetails.setFdBalanceStatus(2);           //支出
                    outFundsDetails.setFdSerialStatus(1);
                    outFundsDetails.setFdIsDel(1);
                    outFundsDetails.setFdPlatform(EnumModel.Platform.pc.getValue());
                    outFundsDetails.setFdPlatformType(EnumModel.PlatformType.pc.getValue());
                    outFundsDetails.setFdChannel(0);
                    outFundsDetails.setFdChannelType(0);
                    fundsDetailsDao.insert(outFundsDetails);


                    TbUserDetails userDetail = userDetailsDao.selectByLoginId(detail.getIndLoginId());
                    //给投资发送还款短信
                    SmsSendUtil.sendRepaySms(userDetail.getUdPhone(),new Date(),detail.getIndMoney(),detail.getIndFundType(), detail.getIndBpName()+"("+detail.getIndBpNumber()+")");
                } else {
                    resultInfo.setSuccess_is_ok(false);
                    sb.append("-"+detail.getIndId());
                    resultInfo.setMsg(sb.toString());
                }
            } catch (Exception e) {
                resultInfo.setSuccess_is_ok(false);
                sb.append("-"+detail.getIndId());
                resultInfo.setMsg(sb.toString());
            }
        }

        //成功返回true 修改标的信息
        if(resultInfo.getSuccess_is_ok()){

            tbBidPlan.setBpUpdateTime(new Date());
            if(tbBidPlan.getBpInterestPayType()==1){
                tbBidPlan.setBpStatus(10);
            }else if(tbBidPlan.getBpInterestPayType()==2){      //说明已到最后一期  修改标的为完成
                if(tbIncomeDetail.getIndPeriods().equals(tbBidPlan.getBpPeriods())){
                    tbBidPlan.setBpStatus(10);
                }
            }
            tbBidPlan.setBpRepayedPeriods(tbIncomeDetail.getIndPeriods());

            bidPlanDao.updateByPrimaryKeySelective(tbBidPlan);
            //修改投资记录中已还款期数
            TbInvestInfo tbInvestInfo = new TbInvestInfo();
            tbInvestInfo.setIiBpId(tbBidPlan.getBpId());
            tbInvestInfo.setIiIsDel(1);
            List<TbInvestInfo> tbInvestInfos = investInfoDao.selectByConditionForPage(tbInvestInfo, pageWhere);
            //更新投资列表状态
            if(tbInvestInfos!=null && tbInvestInfos.size()>0){
                for(TbInvestInfo info : tbInvestInfos){
                    info.setIiBpRepayedPeriods(tbIncomeDetail.getIndPeriods());
                    info.setIiUpdateTime(new Date());
                    if(tbBidPlan.getBpInterestPayType()==1){
                        info.setIiFreezeStatus(TbInvestInfo.STATUS_COMPLETE);
                    }else if(tbBidPlan.getBpInterestPayType()==2){      //说明已到最后一期  修改标的为完成
                        if(tbIncomeDetail.getIndPeriods().equals(tbBidPlan.getBpPeriods())){
                            info.setIiFreezeStatus(TbInvestInfo.STATUS_COMPLETE);
                        }
                    }
                    investInfoDao.updateByPrimaryKeySelective(info);
                }
            }
            //TODO 借款人还款明细 作废
//            TbLoanerRepaymentDetail tbLoanerRepaymentDetail = new TbLoanerRepaymentDetail();
//            tbLoanerRepaymentDetail.setLrdFactDateTime(new Date());
//            tbLoanerRepaymentDetail.setLrdFactMoney(repayMoney);
        }
        return resultInfo;
    }

    @Override
    public List<TbBidPlan> selectBpNumberList(String bpNumberStr) {
        return bidPlanDao.selectBpNumberList(bpNumberStr);
    }



    /* ==================================================借款人相关借款统计查询==================================================*/
    /**
     * 借款资金统计
     * @param loanerId
     * @return
     */
    @Override
    public LoanTotal totalLoanByLoanerId(Long loanerId){
        return bidPlanDao.statisticsByLoanerId(loanerId);
    }

    /**
     * 批量查询统计借款人借款金额，数量
     * @param loanerIds
     * @return
     */
    @Override
    public List<LoanTotal> selectLoanTotalByLoanerIds(List<Long> loanerIds) {
        return bidPlanDao.selectLoanTotalByLoanerIds(loanerIds);
    }

    /**
     * 借款人相关借款记录列表
     * @param model
     * @return
     */
    @Override
    public List<TbBidPlan> selectLoanRecordByLoanerIdForPage(BidPlanExtSM model) {
        return bidPlanDao.selectByLoanerIdForPage(model);
    }

    @Override
    public int selectLoanRecordByLoanerIdForPageCount(BidPlanExtSM model) {
        return bidPlanDao.selectByLoanerIdForPageCount(model);
    }

    @Override
    public BigDecimal selectIncomeCount(int dateSearType) {
        return bidPlanDao.selectIncomeCount(dateSearType);
    }
}
