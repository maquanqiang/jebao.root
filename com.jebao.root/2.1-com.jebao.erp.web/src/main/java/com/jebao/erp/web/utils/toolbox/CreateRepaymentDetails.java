package com.jebao.erp.web.utils.toolbox;

import com.jebao.erp.web.responseModel.bidplan.LoanIntentVM;
import com.jebao.erp.web.responseModel.investment.RepaymentDetail;
import com.jebao.jebaodb.entity.investment.TbLoanerRepaymentDetail;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Lee on 2016/12/3.
 */
public class CreateRepaymentDetails {

    public static List<RepaymentDetail> create(BigDecimal money,BigDecimal bpRate, Integer periods, Date interestSt, Integer bpCycleType, Integer payType){

        Date nextRepayDate = null;
        List<RepaymentDetail> loanFundIntents = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(interestSt);

        Calendar now = Calendar.getInstance();
        if(payType == 1){     //一次性还本付息
            nextRepayDate = repayDate(bpCycleType, interestSt, periods);
            RepaymentDetail interest = new RepaymentDetail();       //利息
            now.setTime(nextRepayDate);
            if(bpCycleType!=1){
                if(now.get(GregorianCalendar.DAY_OF_MONTH)!=calendar.get(GregorianCalendar.DAY_OF_MONTH)){
                    now.add(Calendar.DATE,1);
                }
            }

            interest.setRepayDate(now.getTime());

            interest.setIntentPeriod(1);
            interest.setFundType(2);
            interest.setInterestSt(interestSt);
            int days = BetweenDays.differentDays(interestSt, now.getTime());
            //计算结束日比还款日晚一天
            now.add(Calendar.DATE, -1);
            interest.setInterestEt(now.getTime());
            BigDecimal interestMoney = money.multiply(bpRate).multiply(new BigDecimal(days))
                    .divide(new BigDecimal(100 * 365), 2, BigDecimal.ROUND_HALF_UP);
            interest.setMoney(interestMoney);

            loanFundIntents.add(interest);
        }else if(payType == 2){ //按期付息

            nextRepayDate = interestSt;
            Date interestEt;
            for(int i=1; i<=periods; i++){
                RepaymentDetail loanIntent = new RepaymentDetail();
                loanIntent.setInterestSt(nextRepayDate);
                Date repayDate = repayDate(bpCycleType, interestSt, i);
                now.setTime(repayDate);
                if(bpCycleType!=1){
                    if(now.get(GregorianCalendar.DAY_OF_MONTH)!=calendar.get(GregorianCalendar.DAY_OF_MONTH)){
                        now.add(Calendar.DATE, 1);
                    }
                }
                int days = BetweenDays.differentDays(nextRepayDate, now.getTime());
                nextRepayDate = now.getTime();
                //计算结束日比还款日晚一天
                now.add(Calendar.DATE, -1);
                interestEt = now.getTime();
                BigDecimal interest = money.multiply(bpRate).multiply(new BigDecimal(days))
                        .divide(new BigDecimal(100 * 365), 2, BigDecimal.ROUND_HALF_UP);

                loanIntent.setIntentPeriod(i);
                loanIntent.setInterestEt(interestEt);
                loanIntent.setFundType(2);
                loanIntent.setRepayDate(nextRepayDate);
                loanIntent.setMoney(interest);

                loanFundIntents.add(loanIntent);

                System.out.println("每个周期时间"+days);

            }
        }



        RepaymentDetail pLoanIntent = new RepaymentDetail();
        pLoanIntent.setFundType(1);
        pLoanIntent.setMoney(money);
        if(payType == 1){
            pLoanIntent.setIntentPeriod(1);
        }else {
            pLoanIntent.setIntentPeriod(periods);
        }
        pLoanIntent.setRepayDate(nextRepayDate);

        loanFundIntents.add(pLoanIntent);

        return loanFundIntents;
    }


    public static Date repayDate(Integer bpCycleType, Date interestSt, Integer periods){
        Calendar now = Calendar.getInstance();
        now.setTime(interestSt);
        if(bpCycleType==1){         //日
            now.add(Calendar.DATE, periods);
        }else if(bpCycleType==2){   //月
            now.add(Calendar.MONTH, periods);
        }else if(bpCycleType==3){   //季
            now.add(Calendar.MONTH, periods*3);
        }else if(bpCycleType==4){   //年
            now.add(Calendar.YEAR, periods);
        }
        return now.getTime();
    }
}
