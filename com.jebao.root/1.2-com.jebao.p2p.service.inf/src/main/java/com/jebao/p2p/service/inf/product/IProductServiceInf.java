package com.jebao.p2p.service.inf.product;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import com.jebao.jebaodb.entity.extEntity.ResultInfo;
import com.jebao.jebaodb.entity.investment.TbIncomeDetail;
import com.jebao.jebaodb.entity.investment.TbInvestInfo;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.loanmanage.TbBidRiskData;
import com.jebao.jebaodb.entity.postLoan.search.RepaymentDetailSM;
import com.jebao.jebaodb.entity.product.ProductSM;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loanmanage.TbBidPlan;
import com.jebao.jebaodb.entity.user.TbAccountsFunds;
import com.jebao.jebaodb.entity.user.TbUserDetails;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lee on 2016/12/7.
 */
public interface IProductServiceInf {

    List<TbBidPlan> selectP2PList(ProductSM record, PageWhere pageWhere);

    int selectP2PListCount(ProductSM record);

    TbBidPlan selectByBpId(Long bpId);

//    String[] investBid(Long bpId, Long loginId, BigDecimal investMoney);

    TbLoaner selectByPrimaryKey(Long lId);

    List<TbBidRiskData> selectRiskByConditionForPage(TbBidRiskData data, PageWhere pageWhere);

    List<TbInvestInfo> selectInvestInfoBybpId(TbInvestInfo tbInvestInfo, PageWhere pageWhere);

    int selectInvestInfoByConditionCount(TbInvestInfo tbInvestInfo);

    List<TbIncomeDetail> selectGroupByConditionForPage(RepaymentDetailSM record, PageWhere pageWhere);

    int selectGroupByConditionCount(RepaymentDetailSM record);

    List<TbInvestInfo> recentInvestment(TbInvestInfo tbInvestInfo, PageWhere pageWhere);

    ResultInfo investBid(TbUserDetails outUser, TbBidPlan tbBidPlan, BigDecimal investMoney, EnumModel.Platform platform, EnumModel.PlatformType platformType);

    List<TbBidPlan> selectCacheData(List ids);
}
