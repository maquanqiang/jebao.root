package com.jebao.p2p.web.api.mobileControllerApi;

import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultList;
import com.jebao.p2p.web.api.responseModel.mobilebanner.MobileBannerVm;
import com.jebao.p2p.web.api.utils.constants.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/2/17.
 * 移动端 banner 列表
 */
@RestController
@RequestMapping("mobileApi/banner")
public class MobileBannerController {

    @RequestMapping("bannerList")
    public JsonResult bannerList(){


        List<MobileBannerVm> bannerList = new ArrayList<>();
        String path = "http://192.168.1.7/";
        if(!Constants.IsTest){
            path = "http://static1.jebao.net/";
        }
        bannerList.add(new MobileBannerVm(4, path+"html/phone/images/banner08.png", path+"html/phone/voucher.html"));
        bannerList.add(new MobileBannerVm(1, path+"html/phone/images/banner05.png", path+"html/phone/security.html"));
        bannerList.add(new MobileBannerVm(2, path+"html/phone/images/banner07.png", path+"html/phone/rose.html"));
//        bannerList.add(new MobileBannerVm(3, path+"html/phone/images/banner06.png", path+"html/phone/NewWebsite.html"));

        return new JsonResultList<>(bannerList);
    }
}
