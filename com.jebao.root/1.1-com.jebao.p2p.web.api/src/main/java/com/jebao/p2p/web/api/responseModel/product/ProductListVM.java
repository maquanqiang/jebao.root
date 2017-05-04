package com.jebao.p2p.web.api.responseModel.product;

import com.jebao.p2p.web.api.responseModel.ViewModel;

import javax.swing.text.View;
import java.util.List;

/**
 * Created by Lee on 2017/2/27.
 */
public class ProductListVM {
    public ProductListVM(){

    }

    private List<ProductVm> productVmList;
    private Integer count;

    public ProductListVM(List<ProductVm> productVmList, Integer count) {
        this.productVmList = productVmList;
        this.count = count;
    }

    public List<ProductVm> getProductVmList() {
        return productVmList;
    }

    public void setProductVmList(List<ProductVm> productVmList) {
        this.productVmList = productVmList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
