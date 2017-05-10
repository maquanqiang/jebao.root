/**
 * Created by wenyq on 2017/2/25.
 */
/**
 * Created by Jack on 2016/12/8.
 */
$(function(){

    $.mvalidateExtend({
        phone: {
            required: true,
            pattern: /^0?1[3|4|5|8][0-9]\d{8}$/,
            each: function () {

            },
            //remote: {
            //    type: 'post',
            //    url: '/api/account/validateMobile',
            //    message: '该手机号码已注册'
            //},
            descriptions: {
                required: '<div class="field-invalidmsg">请输入手机号码</div>',
                pattern: '<div class="field-invalidmsg">您输入的手机号码格式不正确</div>',
                valid: '<div class="field-validmsg">正确</div>'
            }
        },
        invitationCode: {
            required: false,
            pattern : /^0?1[3|4|5|8][0-9]\d{8}$/,
            each: function () {

            },
            remote: {                                          //验证是否存在  未起作用
                url: "/api/account/validateMobile",
                type: "POST"
            },
            descriptions: {
                pattern: '<div class="field-invalidmsg">您输入的邀请码格式不正确</div>',
                valid: '<div class="field-validmsg">正确</div>',
                remote:'<div class="field-validmsg">邀请码不存在</div>'
            }
        }
    })
    $("#registerForm").mvalidate({
        type:1,
        onKeyup:true,
        sendForm:true,
        firstInvalidFocus:false,
        valid:function(event,options){
            //点击提交按钮时,表单通过验证触发函数
//                alert("验证通过！接下来可以做你想做的事情啦！");
//                event.preventDefault();
            $("#registerForm").submit();
        },
        invalid:function(event, status, options){
            //点击提交按钮时,表单未通过验证触发函数
        },
        eachField:function(event,status,options){
            //点击提交按钮时,表单每个输入域触发这个函数 this 执向当前表单输入域，是jquery对象
        },
        eachValidField:function(val){},
        eachInvalidField:function(event, status, options){},
        conditional:{
            confirmpwd:function(){
                return $("#pwd").val()==$("#confirmpwd").val();
            }
        },
        descriptions:{
            password:{
                required : '请输入密码',
                pattern : '请设置6-16位密码（由字母、数字组成）'
            }
        }
    });
})