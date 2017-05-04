package com.jebao.erp.service.impl.loanmanage;

import com.jebao.common.utils.sms.SmsSendUtil;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.dao.dao.user.TbUserDetailsDao;
import com.jebao.jebaodb.dao.dao.voucher.TbVoucherDao;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Lee on 2016/12/15.
 */
@Component
public class TimeoutService {

    @Autowired
    private TbBidPlanDao tbBidPlanDao;
    @Autowired
    private TbVoucherDao tbVoucherDao;

    @Autowired
    private TbUserDetailsDao userDetailsDao;


    /**
     * 标的过期
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void timeoutBid() {
        Date now = new Date();
        tbBidPlanDao.timeoutBid(now);
    }

    /**
     * 红包过期
     */
    @Scheduled(cron="0 0 0 * * ?")
    public void timeoutVoucher(){
        tbVoucherDao.updateStatusTimeout();
    }

    /**
     * 红包过期前提醒
     */
//    @Scheduled(cron="0 0 9 * * ?")
    public void sendVoucherRemind(){
            Integer days = 15
                    ;
            Calendar cl = Calendar.getInstance();
            cl.setTime(new Date());
            cl.add(Calendar.DATE, days);
            Date time = cl.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<TbVoucher> tbVouchers = tbVoucherDao.selectRemindList(sdf.format(time));
            System.out.println(tbVouchers.size());
            if(tbVouchers!=null && tbVouchers.size()>0){
                for(TbVoucher voucher : tbVouchers){
                    TbUserDetails tbUserDetails = userDetailsDao.selectByLoginId(voucher.getvLoginId());
                    SmsSendUtil.sendVoucherRemindSms(tbUserDetails.getUdPhone(), days.toString(), voucher.getvAmount());
                }
            }
        }
}
