/**
 * Created by Jack on 2016/12/16.
 */
var model = {
    form1:{},
    form2:{},
    form3:{show:false}
};
var vm = new Vue({
    el:".forget-content",
    data:model,
    beforeCreate:function(){
        model.form1 = $("#form1").serializeObject();
        model.form1.show = true;
        model.form1.error = {hasError:false,message:""};
        model.form2 = $("#form2").serializeObject();
        model.form2.show = false;
        model.form2.error = {hasError:false,message:""};
        model.form2.message = {hasError:false,message:""};
    },
    mounted:function(){
        this.initForm();
    },
    methods:{
        initForm:function(){
            $('#form1').bootstrapValidator({
                trigger: 'keyup blur',
                fields: {
                    mobile: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^1(3|4|5|7|8)\d{9}$/,
                                message: '手机号错误'
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
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();

                var $form = $(e.target);

                $.post($form.attr('action'), $form.serializeObject(), function (response) {
                    if (response.success_is_ok) {
                        model.form1.show = false;
                        model.form2.show = true;
                        $("#form2 .get_code_btn").click();
                    } else {
                        model.form1.error = {hasError:true,message:response.error};
                    }
                }, "json");
            });

            $('#form2').bootstrapValidator({
                trigger: 'keyup blur',
                fields: {
                    messageCode: {
                        validators: {
                            notEmpty: {
                                message: '短信验证码错误'
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
                            identical: {
                                field: 'password',
                                message: '两次密码不一致'
                            }
                        }
                    },
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();

                var $form = $(e.target);
                var submitData =  $.extend({}, model.form1, model.form2);
                $.post($form.attr('action'), submitData, function (response) {
                    if (response.success_is_ok) {
                        model.form2.show=false;
                        model.form3.show=true;

                        var $timer = $("#complete .time");
                        var leftSeconds = parseInt($timer.html());
                        var timerInterval = setInterval(function () {
                            leftSeconds--;
                            if (leftSeconds === 0) {
                                clearInterval(timerInterval);
                                window.location.href= $("#complete .go-btn").attr("href");
                            }else{
                                $timer.html(leftSeconds);
                            }
                        }, 1000);
                    } else {
                        model.form2.error = {hasError:true,message:response.error};
                    }
                }, "json");
            });
        },
        submitStep1:function(){
            $("#form1").submit();
            return false;
        },
        submitStep2:function(){
            $("#form2").submit();
            return false;
        },
        backToStep1:function(){
            model.form1.show = true;
            model.form2.show = false;
        },
        refreshCaptcha: function (event) {
            event.target.src = "/captcha/getCode?" + Math.random();
        },
        sendMessage: function (event) {
            var $target = $(event.target);
            if ($target.hasClass("disabled")) {
                return false;
            }

            var $form1Validator = $("#form1").data('bootstrapValidator');

            if (!$form1Validator.isValid()) {
                $form1Validator.validate();
                return false;
            }
            $target.addClass("disabled");
            var initVal = $target.val();
            $target.val("短信发送中...");
            $.post("/api/account/sendMessage", {
                mobile: model.form1.mobile,
                imgCode: model.form1.imgCode
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
                    model.form2.message.hasError = true;
                    model.form2.message.message = response.error;
                }
            }, "json");
        },
    }
});