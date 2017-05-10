package com.jebao.p2p.web.api.responseModel.mobilebanner;

import com.jebao.p2p.web.api.responseModel.ViewModel;

/**
 * Created by Lee on 2017/2/17.
 */
public class MobileBannerVm extends ViewModel {

    private Integer id;
    private String imgUrl;
    private String detailUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public MobileBannerVm(Integer id, String imgUrl, String detailUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.detailUrl = detailUrl;
    }
}
