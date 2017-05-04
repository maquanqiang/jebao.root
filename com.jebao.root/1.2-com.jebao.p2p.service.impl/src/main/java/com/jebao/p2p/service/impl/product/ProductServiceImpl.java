package com.jebao.p2p.service.impl.product;

import com.jebao.common.utils.sms.SmsSendUtil;
import com.jebao.jebaodb.dao.dao.investment.TbIncomeDetailDao;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.dao.dao.loaner.TbLoanerDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidRiskDataDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbThirdInterfaceLogDao;
import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.dao.dao.user.TbLoginInfoDao;
import com.jebao.jebaodb.dao.dao.user.TbUserDetailsDao;
import com.jebao.jebaodb.dao.dao.voucher.TbVoucherDao;
import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;
import com.jebao.jebaodb.entity.product.ProductSM;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.user.TbLoginInfo;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import com.jebao.p2p.service.inf.product.IProductServiceInf;
import com.jebao.p2p.service.inf.user.IFundsDetailsServiceInf;
import com.jebao.thirdPay.fuiou.constants.ProjectSetting;
import com.jebao.thirdPay.fuiou.impl.PreAuthServiceImpl;
import com.jebao.thirdPay.fuiou.impl.TransferBmuServiceImpl;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthRequest;
import com.jebao.thirdPay.fuiou.model.preAuth.PreAuthResponse;
import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuRequest;
import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuResponse;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2016/12/7.
 */
@Service
public class ProductServiceImpl implements IProductServiceInf {

    @Autowired
    private TbBidPlanDao tbBidPlanDao;
    @Autowired
    private TbLoanerDao tbLoanerDao;
    @Autowired
    private TbBidRiskDataDao tbBidRiskDataDao;
    @Autowired
    private TbInvestInfoDao tbInvestInfoDao;
    @Autowired
    private TbIncomeDetailDao tbIncomeDetailDao;
    @Autowired
    private PreAuthServiceImpl preAuthService;
    @Autowired
    private TbUserDetailsDao tbUserDetailsDao;
    @Autowired
    private TbLoginInfoDao tbLoginInfoDao;
    @Autowired
    private TbThirdInterfaceLogDao thirdInterfaceLogDao;
    @Autowired
    private TbAccountsFundsDao accountsFundsDao;
    @Autowired
    private IFundsDetailsServiceInf fundsDetailsService;
    @Autowired
    private TbVoucherDao voucherDao;
    @Autowired
    private TransferBmuServiceImpl transferBmuService;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<TbBidPlan> selectP2PList(ProductSM record, PageWhere pageWhere) {
        return tbBidPlanDao.selectP2PList(record, pageWhere);
    }

    @Override
    public int selectP2PListCount(ProductSM record) {
        return tbBidPlanDao.selectP2PListCount(record);
    }

    @Override
    public TbBidPlan selectByBpId(Long bpId) {
        return tbBidPlanDao.selectByPrimaryKey(bpId);
    }

    /**
     * 投标
     *
     * @return
     */
//    @Override
//    public String[] investBid(Long bpId, Long loginId, BigDecimal investMoney) {
//
//        String[] result = new String[2];
//        result[0] = "false";
//
//        //查询标的信息
//        TbBidPlan tbBidPlan = tbBidPlanDao.selectByPrimaryKey(bpId);
//        if(tbBidPlan.getBpLoginId()==loginId){
//            result[1] = "投资人不为能该标的借款人";
//            return result;
//        }
//        //投资人详情
//        TbUserDetails outUser = tbUserDetailsDao.selectByLoginId(loginId);
//        if(outUser!=null){
//            if(outUser.getUdThirdAccount()==null){
//                result[1] = "您尚未开通第三方";
//                return result;
//            }
//        }
//        //投资人账户
//        TbAccountsFunds accountsFunds = accountsFundsDao.selectByLoginId(loginId);
//        if(accountsFunds==null || accountsFunds.getAfBalance().compareTo(investMoney) <0){
//            result[1] = "账户余额不足";
//            return result;
//        }
//
//        //更新标的信息表
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("bpId", bpId);
//        map.put("investMoney", investMoney);
//        map.put("nowTime", new Date());
//        int count = tbBidPlanDao.investBid(map);
//        if(count>0){
//
//                String amt = investMoney.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString();
//                //借款人详情
//                TbUserDetails inUser = tbUserDetailsDao.selectByLoginId(tbBidPlan.getBpLoginId());
//
//                //投资人登录信息
//                TbLoginInfo tbLoginInfo = tbLoginInfoDao.selectByPrimaryKey(loginId);
//
//                PreAuthRequest preAuthRequest = new PreAuthRequest();
//                preAuthRequest.setIn_cust_no(inUser.getUdThirdAccount());
//                preAuthRequest.setOut_cust_no(outUser.getUdThirdAccount());
//                preAuthRequest.setRem("investBid");
//                preAuthRequest.setAmt(amt);
//                //保存日志信息
//                TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
//                thirdInterfaceLog.setTilCreateTime(new Date());
//                thirdInterfaceLog.setTilSerialNumber(preAuthRequest.getMchnt_txn_ssn());
//                thirdInterfaceLog.setTilType(3);
//                thirdInterfaceLog.setTilReqText(preAuthRequest.requestSignPlain());
//                thirdInterfaceLogDao.insert(thirdInterfaceLog);
//
//                //添加流水记录
//                TbFundsDetails tbFundsDetails = new TbFundsDetails();
//                tbFundsDetails.setFdLoginId(loginId);
//                tbFundsDetails.setFdThirdAccount(outUser.getUdThirdAccount());
//                tbFundsDetails.setFdSerialNumber(preAuthRequest.getMchnt_txn_ssn());
//                tbFundsDetails.setFdSerialTypeId(3);            //3投资冻结 4 借款入账
//                tbFundsDetails.setFdSerialTypeName("投资冻结");
//                tbFundsDetails.setFdSerialAmount(investMoney);
//                tbFundsDetails.setFdBalanceBefore(accountsFunds.getAfBalance());
//                tbFundsDetails.setFdBalanceAfter(accountsFunds.getAfBalance().subtract(investMoney));
//                tbFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
//                tbFundsDetails.setFdBpId(bpId);
//                tbFundsDetails.setFdBpName(tbBidPlan.getBpName());
//                tbFundsDetails.setFdCreateTime(new Date());
//                tbFundsDetails.setFdSerialTime(new Date());
//                tbFundsDetails.setFdBalanceStatus(2);           //支出
//                tbFundsDetails.setFdSerialStatus(0);
//                tbFundsDetails.setFdIsDel(1);
//
//                fundsDetailsDao.insert(tbFundsDetails);
//            try {
//                PreAuthResponse response = preAuthService.post(preAuthRequest);
//                //更新日志信息
//                String respStr = XmlUtil.toXML(response);
//                thirdInterfaceLog.setTilReturnCode(response.getPlain().getResp_code());
//                thirdInterfaceLog.setTilRespText(respStr);
//                thirdInterfaceLogDao.updateByPrimaryKeySelective(thirdInterfaceLog);
//
//                if("0000".equals(response.getPlain().getResp_code())){  //冻结成功
//
//                    //添加投资记录
//                    TbInvestInfo tbInvestInfo = new TbInvestInfo();
//                    tbInvestInfo.setIiBpId(tbBidPlan.getBpId());
//                    tbInvestInfo.setIiFreezeStatus(TbInvestInfo.STATUS_FREEZE);          //1:冻结中 2:还款中, 3:已还款,4:流标
//                    tbInvestInfo.setBpBidMoney(tbBidPlan.getBpBidMoney());
//                    tbInvestInfo.setIiBpName(tbBidPlan.getBpName());
//                    tbInvestInfo.setIiBpRepayedPeriods(0);      //默认当前期数0（未还款）
//                    tbInvestInfo.setIiContractNo(response.getPlain().getContract_no());
//                    tbInvestInfo.setIiCreateTime(new Date());
//                    tbInvestInfo.setIiMoney(investMoney);
//                    tbInvestInfo.setIiIsDel(1);             //数据有效性 1：有效  2：无效
//                    tbInvestInfo.setIiLoginId(loginId);
//                    tbInvestInfo.setIiLoginName(tbLoginInfo.getLiLoginName());
//                    tbInvestInfo.setIiTrueName(outUser.getUdTrueName());
//                    tbInvestInfo.setIiSsn(response.getPlain().getMchnt_txn_ssn());
//                    tbInvestInfo.setIiUpdateTime(tbInvestInfo.getIiCreateTime());
//                    tbInvestInfo.setIiThirdAccount(outUser.getUdThirdAccount());
//                    tbInvestInfoDao.insert(tbInvestInfo);
//
//                    //更新流水记录
//                    tbFundsDetails.setFdSerialStatus(1);
//                    fundsDetailsDao.updateByPrimaryKeySelective(tbFundsDetails);
//
//
//                    //修改余额
//                    accountsFunds.setAfBalance(accountsFunds.getAfBalance().subtract(investMoney));
//                    accountsFunds.setAfUpdateTime(new Date());
//                    accountsFundsDao.updateByPrimaryKeySelective(accountsFunds);
//
//                    //查询投资总额 满标 更新 标的信息
//                    BigDecimal investTotal = tbInvestInfoDao.investTotal(bpId);
//                    Map<String, Object> fullMap = new HashMap<>();
//                    fullMap.put("bpId", bpId);
//                    fullMap.put("nowTime", new Date());
//                    if(tbBidPlan.getBpBidMoney().compareTo(investTotal) == 0){
//                        tbBidPlanDao.fullBid(fullMap);
//                    }
//
//                    result[0] = "true";
//                    result[1] = "投资成功";
//                }else{
//                    result[1] = "请核实您的账户信息";
//                    //更新流水记录
//                    tbFundsDetails.setFdSerialStatus(-1);
//                    fundsDetailsDao.updateByPrimaryKeySelective(tbFundsDetails);
//                    tbBidPlanDao.addSurplus(map);
//                }
//            } catch (Exception e) {
//                if(LOGGER.isErrorEnabled()){
//                    LOGGER.error("投资调用失败：流水号-{}, 异常信息：{}",preAuthRequest.getMchnt_txn_ssn(), e);
//                }
//                result[1] = "操作异常";
//                tbBidPlanDao.addSurplus(map);
//            }
//        }else {
//            result[1] = "投资金额大于剩余金额";
//        }
//
//        return result;
//    }
    @Override
    public TbLoaner selectByPrimaryKey(Long lId) {
        return tbLoanerDao.selectByPrimaryKey(lId);
    }

    @Override
    public List<TbBidRiskData> selectRiskByConditionForPage(TbBidRiskData data, PageWhere pageWhere) {
        return tbBidRiskDataDao.selectByConditionForPage(data, pageWhere);
    }

    @Override
    public List<TbInvestInfo> selectInvestInfoBybpId(TbInvestInfo tbInvestInfo, PageWhere pageWhere) {
        return tbInvestInfoDao.selectBybpId(tbInvestInfo, pageWhere);
    }

    @Override
    public int selectInvestInfoByConditionCount(TbInvestInfo tbInvestInfo) {
        return tbInvestInfoDao.selectByConditionCount(tbInvestInfo);
    }

    @Override
    public List<TbIncomeDetail> selectGroupByConditionForPage(RepaymentDetailSM record, PageWhere pageWhere) {
        return tbIncomeDetailDao.selectGroupByConditionForPage(record, pageWhere);
    }

    @Override
    public int selectGroupByConditionCount(RepaymentDetailSM record) {
        return tbIncomeDetailDao.selectGroupByConditionCount(record);
    }

    @Override
    public List<TbInvestInfo> recentInvestment(TbInvestInfo tbInvestInfo, PageWhere pageWhere) {

        return tbInvestInfoDao.selectByConditionForPage(tbInvestInfo, pageWhere);
    }

    /**
     * 投标2
     *
     * @param outUser     投资人信息
     * @param tbBidPlan
     * @param investMoney
     * @return
     */
    @Override
    public ResultInfo investBid(TbUserDetails outUser, TbBidPlan tbBidPlan, BigDecimal investMoney, EnumModel.Platform platform, EnumModel.PlatformType platformType){

        ResultInfo resultInfo = new ResultInfo(false, "投资失败");

        TbAccountsFunds accountsFunds = accountsFundsDao.selectByLoginId(outUser.getUdLoginId());
        //更新标的信息表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bpId", tbBidPlan.getBpId());
        map.put("investMoney", investMoney);
        map.put("nowTime", new Date());
        int count = tbBidPlanDao.investBid(map);
        if (count > 0) {

            String amt = investMoney.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString();
            //借款人详情
            TbUserDetails inUser = tbUserDetailsDao.selectByLoginId(tbBidPlan.getBpLoginId());

            //投资人登录信息
            TbLoginInfo tbLoginInfo = tbLoginInfoDao.selectByPrimaryKey(outUser.getUdLoginId());

            PreAuthRequest preAuthRequest = new PreAuthRequest();
            preAuthRequest.setIn_cust_no(inUser.getUdThirdAccount());
            preAuthRequest.setOut_cust_no(outUser.getUdThirdAccount());
            preAuthRequest.setRem("investBid");
            preAuthRequest.setAmt(amt);
            //保存日志信息
            TbThirdInterfaceLog thirdInterfaceLog = new TbThirdInterfaceLog();
            thirdInterfaceLog.setTilCreateTime(new Date());
            thirdInterfaceLog.setTilSerialNumber(preAuthRequest.getMchnt_txn_ssn());
            thirdInterfaceLog.setTilType(3);
            thirdInterfaceLog.setTilReqText(preAuthRequest.requestSignPlain());
            thirdInterfaceLogDao.insert(thirdInterfaceLog);

            //添加流水记录
            TbFundsDetails tbFundsDetails = new TbFundsDetails();
            tbFundsDetails.setFdLoginId(outUser.getUdLoginId());
            tbFundsDetails.setFdThirdAccount(outUser.getUdThirdAccount());
            tbFundsDetails.setFdSerialNumber(preAuthRequest.getMchnt_txn_ssn());
            tbFundsDetails.setFdSerialTypeId(3);            //3投资冻结 4 借款入账
            tbFundsDetails.setFdSerialTypeName("投资冻结");
            tbFundsDetails.setFdSerialAmount(investMoney);
            tbFundsDetails.setFdBalanceBefore(accountsFunds.getAfBalance());
            tbFundsDetails.setFdBalanceAfter(accountsFunds.getAfBalance().subtract(investMoney));
            tbFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
            tbFundsDetails.setFdBpId(tbBidPlan.getBpId());
            tbFundsDetails.setFdBpName(tbBidPlan.getBpName());
            tbFundsDetails.setFdCreateTime(new Date());
            tbFundsDetails.setFdSerialTime(new Date());
            tbFundsDetails.setFdBalanceStatus(2);           //支出
            tbFundsDetails.setFdSerialStatus(0);
            tbFundsDetails.setFdIsDel(1);
            tbFundsDetails.setFdPlatform(platform.getValue());
            tbFundsDetails.setFdPlatformType(platformType.getValue());
            tbFundsDetails.setFdChannel(0);
            tbFundsDetails.setFdChannelType(0);
            fundsDetailsService.insert(tbFundsDetails);
            PreAuthResponse response = preAuthService.post(preAuthRequest);

            if(response!=null && response.getPlain()!=null){
                //更新日志信息
                String respStr = XmlUtil.toXML(response);
                thirdInterfaceLog.setTilReturnCode(response.getPlain().getResp_code());
                thirdInterfaceLog.setTilRespText(respStr);
                thirdInterfaceLogDao.updateByPrimaryKeySelective(thirdInterfaceLog);

                if ("0000".equals(response.getPlain().getResp_code())) {  //冻结成功

                    //添加投资记录
                    TbInvestInfo tbInvestInfo = new TbInvestInfo();
                    tbInvestInfo.setIiBpId(tbBidPlan.getBpId());
                    tbInvestInfo.setIiFreezeStatus(TbInvestInfo.STATUS_FREEZE);          //1:冻结中 2:还款中, 3:已还款,4:流标
                    tbInvestInfo.setBpBidMoney(tbBidPlan.getBpBidMoney());
                    tbInvestInfo.setIiBpName(tbBidPlan.getBpName());
                    tbInvestInfo.setIiBpRepayedPeriods(0);      //默认当前期数0（未还款）
                    tbInvestInfo.setIiContractNo(response.getPlain().getContract_no());
                    tbInvestInfo.setIiCreateTime(new Date());
                    tbInvestInfo.setIiMoney(investMoney);
                    tbInvestInfo.setIiIsDel(1);             //数据有效性 1：有效  2：无效
                    tbInvestInfo.setIiLoginId(outUser.getUdLoginId());
                    tbInvestInfo.setIiLoginName(tbLoginInfo.getLiLoginName());
                    tbInvestInfo.setIiTrueName(outUser.getUdTrueName());
                    tbInvestInfo.setIiSsn(response.getPlain().getMchnt_txn_ssn());
                    tbInvestInfo.setIiUpdateTime(tbInvestInfo.getIiCreateTime());
                    tbInvestInfo.setIiThirdAccount(outUser.getUdThirdAccount());
                    tbInvestInfo.setIiPlatform(platform.getValue());
                    tbInvestInfo.setIiPlatformType(platformType.getValue());
                    tbInvestInfo.setIiChannel(0);
                    tbInvestInfo.setIiChannelType(0);
                    tbInvestInfoDao.insert(tbInvestInfo);

                    //更新流水记录
                    tbFundsDetails.setFdSerialStatus(1);
                    fundsDetailsService.update(tbFundsDetails);


                    //修改余额
                    accountsFunds.setAfBalance(accountsFunds.getAfBalance().subtract(investMoney));
                    accountsFunds.setAfUpdateTime(new Date());
                    accountsFundsDao.updateByPrimaryKeySelective(accountsFunds);

                    //查询投资总额 满标 更新 标的信息
                    BigDecimal investTotal = tbInvestInfoDao.investTotal(tbBidPlan.getBpId());
                    Map<String, Object> fullMap = new HashMap<>();
                    fullMap.put("bpId", tbBidPlan.getBpId());
                    fullMap.put("nowTime", new Date());
                    if (tbBidPlan.getBpBidMoney().compareTo(investTotal) == 0) {
                        tbBidPlanDao.fullBid(fullMap);
                    }

                    resultInfo.setSuccess_is_ok(true);
                    resultInfo.setMsg(investMoney.toString());


                    boolean voucherFlag = false;
                    //查询红包记录
                    List<TbVoucher> voucherList = voucherDao.getByMinWhere(investMoney, tbBidPlan.getBpMonthTerm(), outUser.getUdLoginId());
                    if(voucherList!=null && voucherList.size()>0){

                        TbVoucher tbVoucher = voucherList.get(0);
                        //修改红包状态
                        int updateCount = voucherDao.updateStatus(tbVoucher.getvId(), tbInvestInfo.getIiId());
                        if(updateCount>0){

                            //调用发送红包接口
                            TransferBmuResponse respData = payVoucher(tbVoucher.getvAmount(), outUser);
                            if(respData!=null){
                                BasePlain plain = respData.getPlain();
                                if("0000".equals(plain.getResp_code())){

                                    //添加流水记录
                                    TbFundsDetails voucherFundsDetails = new TbFundsDetails();
                                    voucherFundsDetails.setFdLoginId(outUser.getUdLoginId());
                                    voucherFundsDetails.setFdThirdAccount(outUser.getUdThirdAccount());
                                    voucherFundsDetails.setFdSerialNumber(plain.getMchnt_txn_ssn());
                                    voucherFundsDetails.setFdSerialTypeId(7);            //7红包入账
                                    voucherFundsDetails.setFdSerialTypeName("红包入账");
                                    voucherFundsDetails.setFdSerialAmount(tbVoucher.getvAmount());
                                    voucherFundsDetails.setFdBalanceBefore(accountsFunds.getAfBalance());
                                    voucherFundsDetails.setFdBalanceAfter(accountsFunds.getAfBalance().add(tbVoucher.getvAmount()));
                                    voucherFundsDetails.setFdCommissionCharge(BigDecimal.ZERO);
                                    voucherFundsDetails.setFdBpId(tbBidPlan.getBpId());
                                    voucherFundsDetails.setFdBpName(tbBidPlan.getBpName());
                                    voucherFundsDetails.setFdCreateTime(new Date());
                                    voucherFundsDetails.setFdSerialTime(new Date());
                                    voucherFundsDetails.setFdBalanceStatus(1);           //收入
                                    voucherFundsDetails.setFdSerialStatus(1);           //-1 失败 0 处理中  1成功
                                    voucherFundsDetails.setFdIsDel(1);
                                    voucherFundsDetails.setFdPlatform(platform.getValue());
                                    voucherFundsDetails.setFdPlatformType(platformType.getValue());
                                    voucherFundsDetails.setFdChannel(0);
                                    voucherFundsDetails.setFdChannelType(0);
                                    fundsDetailsService.insert(voucherFundsDetails);
                                    //记录入账金额
                                    accountsFunds.setAfBalance(accountsFunds.getAfBalance().add(tbVoucher.getvAmount()));
                                    accountsFunds.setAfUpdateTime(new Date());
                                    accountsFundsDao.updateByPrimaryKeySelective(accountsFunds);
                                }
                            }
                            voucherFlag = true;
                            //发送有红包短信
                            SmsSendUtil.sendInvestMsg(outUser.getUdPhone(),new Date(),tbVoucher.getvAmount(),investMoney);
                        }
                    }

                    //发送无红包短信
                    if(!voucherFlag){
                        SmsSendUtil.sendInvestMsg(outUser.getUdPhone(),new Date(),null,investMoney);
                    }


                } else {
                    resultInfo.setMsg("请核实您的账户信息");
                    //更新流水记录
                    tbFundsDetails.setFdSerialStatus(-1);
                    fundsDetailsService.update(tbFundsDetails);
                    tbBidPlanDao.addSurplus(map);
                }
            }else{
                resultInfo.setMsg("系统忙");
                //更新流水记录
                tbFundsDetails.setFdSerialStatus(-1);
                fundsDetailsService.update(tbFundsDetails);
                tbBidPlanDao.addSurplus(map);
            }
        } else {
            resultInfo.setMsg("投资金额大于剩余金额");
        }

        return resultInfo;
    }

    @Override
    public List<TbBidPlan> selectCacheData(List ids) {
        return tbBidPlanDao.selectCacheData(ids);
    }

    //投资红包返现
    public TransferBmuResponse payVoucher(BigDecimal vMinPrice, TbUserDetails inUser){

        String voucherMoney = vMinPrice.multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_DOWN).toString();
        String platLoginName = ProjectSetting.getConfigProperty("project.thirdPay.fuiou.platLoginName");
        //调用转账接口
        TransferBmuRequest reqData = new TransferBmuRequest();
        reqData.setOut_cust_no(platLoginName);
        reqData.setIn_cust_no(inUser.getUdThirdAccount());
        reqData.setAmt(voucherMoney);
        reqData.setContract_no("");
        reqData.setRem("voucher");


        //保存日志信息
        TbThirdInterfaceLog voucherLog = new TbThirdInterfaceLog();
        voucherLog.setTilCreateTime(new Date());
        voucherLog.setTilSerialNumber(reqData.getMchnt_txn_ssn());
        voucherLog.setTilType(5);
        voucherLog.setTilReqText(reqData.requestSignPlain());
        thirdInterfaceLogDao.insert(voucherLog);


        TransferBmuResponse respData = transferBmuService.post(reqData);

        if(respData!=null){
            //更新日志信息
            String respStr = XmlUtil.toXML(respData);
            voucherLog.setTilReturnCode(respData.getPlain().getResp_code());
            voucherLog.setTilRespText(respStr);
            thirdInterfaceLogDao.updateByPrimaryKeySelective(voucherLog);
        }

        return respData;
    }


}