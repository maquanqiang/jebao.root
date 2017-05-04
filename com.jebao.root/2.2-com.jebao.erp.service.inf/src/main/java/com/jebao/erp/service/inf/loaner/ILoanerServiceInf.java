package com.jebao.erp.service.inf.loaner;

import com.jebao.jebaodb.entity.account.AccountCount;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import com.jebao.jebaodb.entity.loaner.TbRcpMaterialsTemp;
import com.jebao.jebaodb.entity.loaner.TbRiskCtlPrjTemp;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */
public interface ILoanerServiceInf {
    //region 借款人接口
    /**
     * 保存借款人信息
     * @param entity
     * @return
     */
    int saveLoaner(TbLoaner entity);

    /**
     * 添加借款人基本资料
     * @param entity
     * @return
     */
    int addLoaner(TbLoaner entity);

    /**
     * 根据手机号查询借款人基本信息
     * @param phone
     * @return
     */
    TbLoaner getLoanerByPhone(String phone);

    /**
     * 修改借款人详细资料
     * @param entity
     * @return
     */
    int updateLoaner(TbLoaner entity);

    /**
     * 删除借款人
     * @param lId 主键Id
     * @return
     */
    int deleteLoanerById(Long lId);

    /**
     * 根据主键Id获取借款人信息
     * @param lId
     * @return
     */
    TbLoaner findLoanerById(Long lId);

    /**
     * 根据用户Id获取借款人信息
     * @param loginId
     * @return
     */
    TbLoaner findLoanerByLoginId(Long loginId);

    /**
     * 根据查询条件统计借款人信息总数
     * @param record
     * @return
     */
    int selectLoanerByParamsForPageCount(TbLoaner record);

    /**
     * 根据查询条件获取借款人列表(分页)
     * @param record
     * @param page
     * @return
     */
    List<TbLoaner> selectLoanerByParamsForPage(TbLoaner record,PageWhere page);
    //endregion

    //region 风控项目模版接口
    /**
     * 添加风控项目模版
     * @param record
     * @return
     */
    int addRiskCtlPrjTemp(TbRiskCtlPrjTemp record);

    /**
     * 修改风控项目模版
     * @param record
     * @return
     */
    int updateRiskCtlPrjTemp(TbRiskCtlPrjTemp record);

    /**
     * 删除风控项目模版
     * @param rcptId 主键Id
     * @return
     */
    int deleteRiskCtlPrjTempById(Long rcptId);

    /**
     * 根据主键Id获取风控项目模版信息
     * @param rcptId
     * @return
     */
    TbRiskCtlPrjTemp findRiskCtlPrjTempById(Long rcptId);

    /**
     * 根据借款人ID统计风控项目模版信息总数
     * @param loanerId 借款人ID
     * @return
     */
    int selectRiskCtlPrjTempByLoanerIdForPageCount(Long loanerId);

    /**
     * 根据借款人ID获取风控项目模版信息列表（分页）
     * @param loanerId 借款人ID
     * @param page
     * @return
     */
    List<TbRiskCtlPrjTemp> selectRiskCtlPrjTempByLoanerIdForPage(Long loanerId,PageWhere page);
    //endregion

    //region 风控项目认证材料模版接口
    /**
     * 添加风控项目认证材料模版
     * @param record
     * @return
     */
    int addRcpMaterialsTemp(TbRcpMaterialsTemp record);

    /**
     * 修改风控项目认证材料模版
     * @param record
     * @return
     */
    int updateRcpMaterialsTemp(TbRcpMaterialsTemp record);

    /**
     * 根据主键I删除风控项目认证材料模版
     * @param rcpmtId 主键Id
     * @return
     */
    int deleteRcpMaterialsTempById(Long rcpmtId);

    /**
     * 根据主键Id获取风控项目认证材料模版信息
     * @param rcpmtId
     * @return
     */
    TbRcpMaterialsTemp findRcpMaterialsTempById(Long rcpmtId);

    /**
     * 根据风控项目Id统计风控项目认证材料模版信息总数
     * @param projectId 风控项目Id
     * @return
     */
    int selectRcpMaterialsTempByPrjIdForPageCount(Long projectId);

    /**
     * 根据风控项目Id获取风控项目认证材料模版信息列表（分页）
     * @param projectId 风控项目Id
     * @param page
     * @return
     */
    List<TbRcpMaterialsTemp> selectRcpMaterialsTempByPrjIdForPage(Long projectId,PageWhere page);
    //endregion


    AccountCount accountCount();
}
