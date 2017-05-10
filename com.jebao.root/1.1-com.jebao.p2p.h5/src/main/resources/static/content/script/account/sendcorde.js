/**
 * Created by wenyq on 2017/2/25.
 */

var model = {
    form: {},
    codeDisabled: false,
    error: {
        hasError: false,
        message: "错误消息显示"
    }
};
//$(function(){
//
//    //短信倒计时
//    var  oBtn=$('.userCodeBtn');
//    var  n=90;
//    var  bFlag=false;
//    $.post("/api/account/sendMessage", {
//        mobile: model.form.mobile,
//        imgCode: model.form.imgCode
//    }, function (response) {
//        if (response.success_is_ok) {
//            var leftSeconds = 90;
//            var sendTimerInterval = setInterval(function () {
//                leftSeconds--;
//                if (leftSeconds === 0) {
//                    clearInterval(sendTimerInterval);
//                    $target.removeClass("disabled").val(initVal);
//                } else {
//                    $target.val(leftSeconds + ' s后可重发');
//                }
//            }, 1000);
//        } else {
//            $target.removeClass("disabled").val(initVal);
//            if (response.code == 1001) {
//                $validator.updateStatus("imgCode", "INVALID", "notEmpty");
//            } else {
//                model.error.hasError = true;
//                model.error.message = response.error;
//            }
//        }
//    }, "json");
//
//
//
//    time();
//    oBtn.click(function(){
//        time();
//    });
//    function time(){
//        if(bFlag){
//            return;
//        }
//        bFlag=true;
//        tick();
//        var timer=setInterval(tick,1000);
//        function tick(){
//            n--;
//            oBtn.val(n+'s后可重发');
//            if(n==0){
//                oBtn.val('重新获取验证码');
//                clearInterval(timer);
//                n=90;
//                bFlag=false;
//            }
//        }
//    }
//    $.mvalidateExtend({
//        phone:{
//            required : true,
//            pattern : /^0?1[3|4|5|8][0-9]\d{8}$/,
//            each:function(){
//
//            },
//            descriptions:{
//                required : '<div class="field-invalidmsg">请输入手机号码</div>',
//                pattern : '<div class="field-invalidmsg">您输入的手机号码格式不正确</div>',
//                valid : '<div class="field-validmsg">正确</div>'
//            }
//        }
//    });
//    $("#registerForm").mvalidate({
//        type:1,
//        onKeyup:true,
//        sendForm:true,
//        firstInvalidFocus:false,
//        valid:function(event,options){
//            //点击提交按钮时,表单通过验证触发函数
////                alert("验证通过！接下来可以做你想做的事情啦！");
////                event.preventDefault();
//        },
//        invalid:function(event, status, options){
//            //点击提交按钮时,表单未通过验证触发函数
//        },
//        eachField:function(event,status,options){
//            //点击提交按钮时,表单每个输入域触发这个函数 this 执向当前表单输入域，是jquery对象
//        },
//        eachValidField:function(val){},
//        eachInvalidField:function(event, status, options){},
//        descriptions:{
//            code:{
//                required : '请输入验证码'
//            }
//        }
//    });
//})


var vm = new Vue({
    el: ".wrap",
    data: model,
    beforeCreate: function () {
        //初始化 model.form
        model.form = $("#registerForm").serializeObject();
        model.form.agree = false;
    },
    mounted: function () {
        //前端写的代码
        //注册点击服务协议

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
            //var $validator = $target.closest("form").data('bootstrapValidator');
            //var disableFileds = ["messageCode", "agree"];
            //$.each(disableFileds, function (i, o) {
            //    $validator.enableFieldValidators(o, false);//暂时禁用短信验证码字段的验证
            //});
            //
            //var isValid = false;
            //if (!(isValid = $validator.isValid())) {
            //    $validator.validate();
            //}
            //$.each(disableFileds, function (i, o) {
            //    $validator.enableFieldValidators(o, true);//暂时禁用短信验证码字段的验证
            //});
            //if (!isValid) {
            //    return false;
            //}
            $target.addClass("disabled");
            var initVal = $target.val();
            $target.val("短信发送中...");
            $.post("/api/account/sendMessage", {
                mobile: +$("#tel").val(),
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
                    //if (response.code == 1001) {
                       // $validator.updateStatus("imgCode", "INVALID", "notEmpty");
                        layer.msg(response.error);
                        return;
                    //} else {
                    //    model.error.hasError = true;
                    //    model.error.message = response.error;
                    //}
                }
            }, "json");
        },

        initValidateForm: function () {
    $("#registerForm").mvalidate({
        type:1,
        onKeyup:true,
        sendForm:true,
        firstInvalidFocus:false,
        valid:function(event,options){
            //点击提交按钮时,表单通过验证触发函数
//                alert("验证通过！接下来可以做你想做的事情啦！");
                event.preventDefault();

            var $form = $("#registerForm");
            var loadIndex = layer.load(2);
            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serializeObject(), function (response) {
                if (response.success_is_ok) {
                    window.location.href = "/account/registerSuccess";
                    return;
                } else {
                    layer.close(loadIndex);
                    layer.msg(response.error);
                    return;
                    //if (response.code == 1001) {
                    //    $form.data('bootstrapValidator').updateStatus("imgCode", "INVALID", "notEmpty");
                    //} else if (response.code == 1002) {
                    //    $form.data('bootstrapValidator').updateStatus("messageCode", "INVALID", "notEmpty");
                    //} else {
                    //    model.error.hasError = true;
                    //    model.error.message = response.error;
                    //}
                }
            }, "json");
        },
        invalid:function(event, status, options){
            //点击提交按钮时,表单未通过验证触发函数
        },
        eachField:function(event,status,options){
            //点击提交按钮时,表单每个输入域触发这个函数 this 执向当前表单输入域，是jquery对象
        },
        eachValidField:function(val){},
        eachInvalidField:function(event, status, options){},
        descriptions:{
            code:{
                required : '请输入验证码'
            }
        }
    });

        }

    }
});