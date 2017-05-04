$(document).ready(function () {
    var lgn = $.cookie("jebao_lgn");
    if (lgn){
        $("#loginForm .userName input").val(lgn);
    }
    $.mvalidateExtend({
        phone:{
            required : true,
            pattern : /^0?1[3|4|5|8][0-9]\d{8}$/,
            each:function(){

            },
            descriptions:{
                required : '<div class="field-invalidmsg">请输入手机号码</div>',
                pattern : '<div class="field-invalidmsg">您输入的手机号码格式不正确</div>',
                valid : '<div class="field-validmsg">正确</div>'
            }
        }
    });
    $("#loginForm").mvalidate({
        type:1,
        onKeyup:true,
        sendForm:true,
        firstInvalidFocus:false,
        valid:function(event,options){
            //点击提交按钮时,表单通过验证触发函数
           // alert("验证通过！接下来可以做你想做的事情啦111！");
            event.preventDefault();
            //var $errorPlace = $("#login_message").addClass("none");
            var loadIndex = layer.load(2);
            var $form = $("#loginForm");
            var submitData = $form.serializeObject();
            $.post($form.attr('action'), submitData, function (response) {
                if (response.success_is_ok) {
                    if(submitData.remember === "1"){

                        $.cookie("jebao_lgn",submitData.jebUsername,{ expires: 3 });
                    }
                    $.get("/api/user/syncThirdPosStatus");
                    $.get("/api/user/syncUserBalance");
                    //ajax中包含同步的ajax请求需要设置延迟setTimeout,
                    //否则firefox与safari浏览器因跳转太快无法执行前面的两个ajax请求
                    setTimeout(function(){
                        var redirectUrl = common.getUrlParam("redirect") || "/";
                        window.location.href=redirectUrl;
                        return;
                    }, 200 );
                } else {
                    layer.close(loadIndex);
                    layer.tips(response.error, '#pwd', {
                        tips: 3 //还可配置颜色
                    });
                    //var index=layer.msg(response.error);
                    //layer.style(index, {
                    //    color:'#fff'
                    //});
                }
            });

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
                required : '请输入密码'
            }
        }
    });
});


