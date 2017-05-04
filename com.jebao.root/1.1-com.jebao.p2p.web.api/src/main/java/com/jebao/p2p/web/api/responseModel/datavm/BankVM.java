package com.jebao.p2p.web.api.responseModel.datavm;

import com.jebao.jebaodb.entity.base.TbBaseBankInfo;
import com.jebao.p2p.web.api.responseModel.ViewModel;

/**
 * Created by Jack on 2016/12/26.
 */
public class BankVM extends ViewModel {

    public BankVM(TbBaseBankInfo entity){
        this.code=entity.getBlBankCode();
        this.name=entity.getBlBankName();
        this.bin=entity.getBlBankBin();


    }

    private String code;
    private String name;
    private String bin;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }
}
