/**
 * Created by Jack on 2016/12/8.
 */
var model = {
    form: {},
    codeDisabled: false,
    error: {
        hasError: false,
        message: "错误消息显示"
    }
};
var vm = new Vue({
    el: ".register-content",
    data: model,
    beforeCreate: function () {
        //初始化 model.form
        model.form = $("#registerForm").serializeObject();
        model.form.agree = false;
        //处理邀请码
        var invitationCode = common.getUrlParam("code");
        if (invitationCode) {
            $.cookie("invitation_code",invitationCode);
        }else{
            invitationCode =  $.cookie("invitation_code");
        }
        if (invitationCode) {
            model.form.invitationCode = invitationCode;
            model.codeDisabled = true;
            $.cookie("invitation_code",invitationCode);
        }
    },
    mounted: function () {
        //前端写的代码
        //注册点击服务协议
        $('.remember a').click(function () {
            $('.shadow-bg').show();
            $('.agreement-wrap').show();
        });
        $('.close-btn').click(function () {
            $('.shadow-bg').hide();
            $('.agreement-wrap').hide();
        });

        this.initValidateForm();
    },
    methods: {
        refreshCaptcha: function (event) {
            event.target.src = "/captcha/getCode?" + Math.random();
        },
        //短信验证码
        sendMessage: function (event) {
            var $target = $(event.target);
            if ($target.hasClass("disabled")) {
                return false;
            }
            var $validator = $target.closest("form").data('bootstrapValidator');
            var disableFileds = ["messageCode", "agree"];
            $.each(disableFileds, function (i, o) {
                $validator.enableFieldValidators(o, false);//暂时禁用短信验证码字段的验证
            });

            var isValid = false;
            if (!(isValid = $validator.isValid())) {
                $validator.validate();
            }
            $.each(disableFileds, function (i, o) {
                $validator.enableFieldValidators(o, true);//暂时禁用短信验证码字段的验证
            });
            if (!isValid) {
                return false;
            }
            $target.addClass("disabled");
            var initVal = $target.val();
            $target.val("短信发送中...");
            $.post("/api/account/sendMessage", {
                mobile: model.form.mobile,
                imgCode: model.form.imgCode
            }, function (response) {
                if (response.success_is_ok) {
                    var leftSeconds = 90;
                    var sendTimerInterval = setInterval(function () {
                        leftSeconds--;
                        if (leftSeconds === 0) {
                            clearInterval(sendTimerInterval);
                            $target.removeClass("disabled").val(initVal);
                        } else {
                            $target.val(leftSeconds + ' s后可重发');
                        }
                    }, 1000);
                } else {
                    $target.removeClass("disabled").val(initVal);
                    if (response.code == 1001) {
                        $validator.updateStatus("imgCode", "INVALID", "notEmpty");
                    } else {
                        model.error.hasError = true;
                        model.error.message = response.error;
                    }
                }
            }, "json");
        },
        initValidateForm: function () {
            $('#registerForm').bootstrapValidator({
                trigger: 'keyup blur',
                fields: {
                    mobile: {
                        threshold: 11,
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^1(3|4|5|7|8)\d{9}$/,
                                message: '手机号错误'
                            },
                            remote: {
                                type: 'post',
                                url: '/api/account/validateMobile',
                                message: '该手机号码已注册'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            regexp: {
                                regexp: /^(?![0-9]+$)(?![a-zA-Z]+$)[A-Za-z0-9_-]{6,16}$/,
                                message: '请设置6-16密码（由字母、数字组成）'
                            }
                        }
                    },
                    passwordAgain: {
                        validators: {
                            notEmpty: {
                                message: '请输入确认密码'
                            },
                            identical: {//相同
                                field: 'password', //需要进行比较的input name值
                                message: '两次密码不一致'
                            }
                        }
                    },
                    imgCode: {
                        validators: {
                            notEmpty: {
                                message: '图形验证码错误'
                            }
                        }
                    },
                    messageCode: {
                        validators: {
                            notEmpty: {
                                message: '短信验证码错误'
                            }
                        }
                    },
                    agree: {
                        validators: {
                            notEmpty: {
                                message: '您必须同意服务协议'
                            }
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                var $errorPlace = $(".error-place").addClass("hidden");
                // Get the form instance
                var $form = $(e.target);
                var loadIndex = layer.load(2);
                // Use Ajax to submit form data
                $.post($form.attr('action'), $form.serializeObject(), function (response) {
                    if (response.success_is_ok) {
                        window.location.href = "/account/registerSuccess";
                        return;
                    } else {
                        layer.close(loadIndex);
                        if (response.code == 1001) {
                            $form.data('bootstrapValidator').updateStatus("imgCode", "INVALID", "notEmpty");
                        } else if (response.code == 1002) {
                            $form.data('bootstrapValidator').updateStatus("messageCode", "INVALID", "notEmpty");
                        } else {
                            model.error.hasError = true;
                            model.error.message = response.error;
                        }
                    }
                }, "json");
            });
        },
        register: function () {
            $('#registerForm').submit();
        }
    }
});
