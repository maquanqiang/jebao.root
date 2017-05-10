package com.jebao.jebaodb.entity.employee.input;

import com.jebao.jebaodb.entity.extEntity.InputModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jack on 2016/11/25.
 */
public class PasswordIM  extends InputModel{
    @NotBlank(message = "请输入当前密码")
    private String currentPassword;
    @NotBlank(message = "请设置新密码")
    @Length(min = 6,max = 20)
    private String newPassword;
    @NotBlank(message = "请确认新密码")
    private String newPassword2;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
