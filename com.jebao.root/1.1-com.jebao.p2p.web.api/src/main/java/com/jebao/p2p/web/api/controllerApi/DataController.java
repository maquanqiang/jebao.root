package com.jebao.p2p.web.api.controllerApi;

import com.jebao.jebaodb.entity.base.TbBaseBankInfo;
import com.jebao.jebaodb.entity.base.TbBaseRegionInfo;
import com.jebao.p2p.service.inf.base.IBaseServiceInf;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.responseModel.datavm.BankVM;
import com.jebao.p2p.web.api.responseModel.datavm.RegionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/12/26.
 */
@RestController
@RequestMapping("/api/data/")
public class DataController {
    @Autowired
    private IBaseServiceInf baseService;
    @RequestMapping("bankList")
    public JsonResult bankList(){
        List<TbBaseBankInfo> bankEntityList = baseService.getBankList();
        List<BankVM> modelList = new ArrayList<>();
        bankEntityList.forEach(o->modelList.add(new BankVM(o)));

        return new JsonResultList<>(modelList);
    }
    @RequestMapping("regionList")
    public JsonResult regionList(){
        List<TbBaseRegionInfo> regionEntityList = baseService.getRegionList();
        //List<RegionVM> modelList = new RegionVM("0",regionEntityList).getChildren();
        List<RegionVM> modelList = new RegionVM("0",regionEntityList).smartGroup();

        return new JsonResultList<>(modelList,modelList.size());
    }


}
