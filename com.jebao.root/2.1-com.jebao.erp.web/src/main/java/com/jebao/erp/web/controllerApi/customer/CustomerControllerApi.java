package com.jebao.erp.web.controllerApi.customer;

import com.jebao.common.utils.sms.SmsSendUtil;
import com.jebao.erp.service.inf.investment.IInvestInfoServiceInf;
import com.jebao.erp.service.inf.loanmanage.ITbBidPlanServiceInf;
import com.jebao.erp.service.inf.user.IUserDetailsServiceInf;
import com.jebao.erp.service.inf.voucher.TbVoucherServiceInf;
import com.jebao.erp.web.controller._BaseController;
import com.jebao.erp.web.responseModel.base.JsonResult;
import com.jebao.erp.web.responseModel.base.JsonResultList;
import com.jebao.erp.web.responseModel.customer.CustomerVM;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.user.TbFundsDetails;
import com.jebao.jebaodb.entity.user.TbUserDetails;
import com.jebao.jebaodb.entity.user.search.UserSM;
import com.jebao.jebaodb.entity.voucher.TbVoucher;
import com.jebao.thirdPay.fuiou.model.base.BasePlain;
import com.jebao.thirdPay.fuiou.model.transferBmu.TransferBmuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/4/17.
 */
@Controller
@RequestMapping("api/customer")
public class CustomerControllerApi extends _BaseController {

    @Autowired
    private IUserDetailsServiceInf userDetailsService;
    @Autowired
    private TbVoucherServiceInf voucherService;
    @Autowired
    private IInvestInfoServiceInf investInfoService;
    @Autowired
    private ITbBidPlanServiceInf bidPlanService;

    @RequestMapping("getCustomerList")
    public JsonResult getCustomerList(UserSM userSM) {

        List<CustomerVM> customerVMs = new ArrayList<>();

        List<TbUserDetails> tbUserDetailses = userDetailsService.selectByConditionForPage(userSM);
        if (tbUserDetailses != null && tbUserDetailses.size() > 0) {
            for (TbUserDetails details : tbUserDetailses) {
                TbVoucher voucher = new TbVoucher();
                voucher.setvLoginId(details.getUdLoginId());
                Integer vCount = voucherService.selectByConditionCount(voucher);
                CustomerVM vm = new CustomerVM(details);
                vm.setVoucherCount(vCount);
                customerVMs.add(vm);
            }
        }

        Integer count = userDetailsService.selectListCount(userSM);
        return new JsonResultList<>(customerVMs, count);
    }

    @RequestMapping("sendVoucher")
    public void sendVoucher(Long investId) {
        if(investId == 234 || investId == 235){
            voucherService.sendVoucher(investId);
        }
    }
}
