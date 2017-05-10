/**
 * Created by wenyq on 2017/3/2.
 */
//Model
var model = {
    //银行卡信息
    bankInfo: {},
    posStatus: true,
  //  inTime: "",
    hasFundAccount: true
};
var vm = new Vue({
    el: ".wrap",
    data: model,
    beforeCreate: function () {
        var now = new Date();
        var tomorrow = new Date(now.setDate(now.getDate() + 1));
       // model.inTime = tomorrow.toFormatString('yyyy-MM-dd');
    },
    //初始化远程数据
    created: function () {
        this.init();
    },
    mounted: function () {
        //console.log($("#quickPay_form").length)
        this.initForm();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        initForm:function(){
            $("#rechargeForm").mvalidate({
                type:1,
                onKeyup:true,
                sendForm:true,
                firstInvalidFocus:false,
                valid:function(event,options){
                    //点击提交按钮时,表单通过验证触发函数
                    event.preventDefault();
                    var $form = $("#rechargeForm");
                    var loadIndex = layer.load(2);
                    $form.attr("action", common.apiOrigin + $form.attr("action"));
                    $form[0].submit();
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
                    money:{
                        required : '请输入充值金额',
                        pattern : '请输入任意正整数，正小数（小数位不超过2位）'
                    }
                }
            });
        },
        init: function () {
            $.get("/api/user/getuser", function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    if (data != null) {
                        vm.bankInfo = data;
                        vm.posStatus = data.posStatus == 1 ? true : false;
                        vm.hasFundAccount = data.hasFundAccount;
                    }
                }
            });
        }
        //initValidateForm: function (form) {
        //    return $(form).bootstrapValidator({
        //        fields: {
        //            money: {
        //                validators: {
        //                    notEmpty: {
        //                        message: '金额不能为空'
        //                    },
        //                    regexp: {
        //                        regexp: /^[0-9]\d*$/,
        //                        message: '请输入整数，金额不能有小数点'
        //                    }
        //                }
        //            }
        //        }
        //    }).on('success.form.bv', function (e) {
        //        e.preventDefault();//阻止默认事件提交
        //
        //        var $form = $(e.target);
        //        $form.attr("action", common.apiOrigin + $form.attr("action"));
        //        $form[0].submit();
        //    })
        //}
        //
    }
});