package com.jebao.erp.service.impl.voucher;

import com.jebao.common.utils.sms.SmsSendUtil;
import com.jebao.erp.service.inf.voucher.TbVoucherServiceInf;
import com.jebao.jebaodb.dao.dao.investment.TbInvestInfoDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbThirdInterfaceLogDao;
import com.jebao.jebaodb.dao.dao.user.TbAccountsFundsDao;
import com.jebao.jebaodb.dao.dao.user.TbFundsDetailsDao;
import com.jebao.jebaodb.dao.dao.user.TbUserDetailsDao;
import com.jebao.jebaodb.dao.dao.voucher.TbVoucherDao;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.loanmanage.TbThirdInterfaceLog;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import com.jebao.thirdPay.fuiou.constants.ProjectSetting;
import com.jebao.thirdPay.fuiou.impl.TransferBmuServiceImpl;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuRequest;
import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuResponse;
import com.jebao.thirdPay.fuiou.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/4/17.
 */
@Service
public class TbVoucherServiceImpl implements TbVoucherServiceInf {
    @Autowired
    private TbVoucherDao voucherDao;
    @Autowired
    private TbInvestInfoDao investInfoDao;
    @Autowired
    private TbBidPlanDao bidPlanDao;
    @Autowired
    private TbThirdInterfaceLogDao thirdInterfaceLogDao;
    @Autowired
    private TbUserDetailsDao userDetailsDao;
    @Autowired
    private TbAccountsFundsDao accountsFundsDao;
    @Autowired
    private TbFundsDetailsDao fundsDetailsDao;
    @Autowired
    private TransferBmuServiceImpl transferBmuService;

    @Override
    public Integer selectByConditionCount(TbVoucher voucher) {
        return voucherDao.selectByConditionCount(voucher);
    }

    @Override
    public void sendVoucher(Long investId){
        TbInvestInfo tbInvestInfo = investInfoDao.selectByPrimaryKey(investId);
        TbBidPlan tbBidPlan = bidPlanDao.selectByPrimaryKey(tbInvestInfo.getIiBpId());
        TbUserDetails outUser = userDetailsDao.selectByLoginId(tbInvestInfo.getIiLoginId());
        TbAccountsFunds accountsFunds = accountsFundsDao.selectByLoginId(tbInvestInfo.getIiLoginId());
        //查询红包记录
        List<TbVoucher> voucherList = voucherDao.getByMinWhere(tbInvestInfo.getIiMoney(), tbBidPlan.getBpMonthTerm(), tbInvestInfo.getIiLoginId());
        if (voucherList != null && voucherList.size() > 0) {

            TbVoucher tbVoucher = voucherList.get(0);
            //修改红包状态
            int updateCount = voucherDao.updateStatus(tbVoucher.getvId(), tbInvestInfo.getIiId());
            if (updateCount > 0) {

                //调用发送红包接口
                TransferBmuResponse respData = payVoucher(tbVoucher.getvAmount(), outUser);
                if (respData != null) {
                    BasePlain plain = respData.getPlain();
                    if ("0000".equals(plain.getResp_code())) {

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
                        voucherFundsDetails.setFdPlatform(1);
                        voucherFundsDetails.setFdPlatformType(1);
                        voucherFundsDetails.setFdChannel(0);
                        voucherFundsDetails.setFdChannelType(0);
                        fundsDetailsDao.insert(voucherFundsDetails);
                        //记录入账金额
                        accountsFunds.setAfBalance(accountsFunds.getAfBalance().add(tbVoucher.getvAmount()));
                        accountsFunds.setAfUpdateTime(new Date());
                        accountsFundsDao.updateByPrimaryKeySelective(accountsFunds);
                    }
                }
                //发送有红包短信
                SmsSendUtil.sendInvestMsg(outUser.getUdPhone(), tbInvestInfo.getIiCreateTime(), tbVoucher.getvAmount(), tbInvestInfo.getIiMoney());
            }
        }
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
