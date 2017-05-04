package com.jebao.p2p.web.api.requestModel.tempTest;

import com.jebao.jebaodb.entity.TbTempTest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * DTO概念
 * Created by Administrator on 2016/10/21.
 */
public class TempTestForm {
    //转为数据库实体数据
    public static TbTempTest toEntity(TempTestForm form)
    {
        TbTempTest result=new TbTempTest();
        result.setUsername(form.getName());
        result.setPassword(form.getPassword());
        return result;
    }
    //===================================================
    @NotBlank(message="name参数的值不能为空")
    private String name;
    @NotBlank(message="password参数的值不能为空")
    private String password;
    private String redirectUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
