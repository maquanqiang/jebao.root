/**
 * Created by lihui on 2016/10/21.
 */
$(document).ready(function() {
    initValidateForm();
    $("#submitBtn").click(function(){
        var bootstrapValidator = $("#defaultForm").data('bootstrapValidator').validate();
        if(!bootstrapValidator.isValid()){return;}
        //TODO 后台逻辑
        $.axForForm($('#defaultForm'),function(response){

            if(response.success_is_ok)
            {
                window.location.href="/home/index";
                return;
            }else{
                $("#error_place_id").html(response.msg);
                $(".verification").click();
            }
        });
    });
    $(document).keyup(function(event) {
        if(event.which==13){
            $("#submitBtn").click();
        }
    });
    //点击切换验证码图片
    $(".verification").click(function(){
        $(this).attr('src','/captcha/getCode?'+Math.random());
    });
});
//表单登录验证封装
function initValidateForm(){
    $('#defaultForm').bootstrapValidator({
        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '用户名长度必须在6到18位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含大写、小写、数字和下划线'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        regexp:/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/,
                        message: '密码格式有误'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能和用户名相同'
                    }
                }
            },
            verifyCode: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 4,
                        message: '验证码长度必须是4位'
                    }
                }
            }
        }
    });
}
