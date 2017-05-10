/**
 * Created by wenyq on 2017/2/26.
 */
$(function(){
    $("#accountForm").mvalidate({
        type:1,
        onKeyup:true,
        sendForm:true,
        firstInvalidFocus:false,
        valid:function(event,options){
            //点击提交按钮时,表单通过验证触发函数
//                alert("验证通过！接下来可以做你想做的事情啦！");
            event.preventDefault();
            window.location.href = "/userfund/registers/"+$("#userPeople").val()+"/"+$("#userId").val() ;
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
            userPeople:{
                required : '请输入真实姓名',
                pattern : '真实姓名只能是中文'
            },
            userId:{
                required : '请输入身份证号',
                pattern : '请输入正确的身份证号'
            }
        }
    });
})