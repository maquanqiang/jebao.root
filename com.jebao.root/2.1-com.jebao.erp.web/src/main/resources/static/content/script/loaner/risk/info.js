/**
 * Created by Administrator on 2016/11/28.
 */
var model = {
    risk: {},
    searchObj: {},
    loanerId: ""
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj = $("#search_form").serializeObject();
        model.loanerId = $("#order_search_form").find("[name=loanerId]").val();
    },
    //初始化远程数据
    created: function () {
        this.init();
    },
    mounted:function(){
        this.bindFormValidate();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        init: function(){
            $.get("/api/risk/info", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    if(data != null) {
                        //设置kindEditorContent编辑器
                        KindEditor.html("#kindEditorContent", data.desc);
                        vm.risk = data;
                    }
                }
            });
        },
        //绑定表单验证
        bindFormValidate:function(){
            $("#order_search_form").bootstrapValidator({
                fields: {
                    name: {
                        message: '项目名称验证失败',
                        validators: {
                            notEmpty: {
                                message: '项目名称不能为空'
                            }
                        }
                    },
                    borrowDesc: {
                        message: '借款描述验证失败',
                        validators: {
                            notEmpty: {
                                message: '借款描述不能为空'
                            }
                        }
                    },
                    fundsPurpose: {
                        message: '资金用途验证失败',
                        validators: {
                            notEmpty: {
                                message: '资金用途不能为空'
                            }
                        }
                    },
                    repayingSource: {
                        message: '还款来源验证失败',
                        validators: {
                            notEmpty: {
                                message: '还款来源不能为空'
                            }
                        }
                    },
                    mortgageInfo: {
                        message: '抵押信息验证失败',
                        validators: {
                            notEmpty: {
                                message: '抵押信息不能为空'
                            }
                        }
                    },
                    personalCredit: {
                        message: '个人征信验证失败',
                        validators: {
                            notEmpty: {
                                message: '个人征信不能为空'
                            }
                        }
                    },
                    opinion: {
                        message: '风控意见验证失败',
                        validators: {
                            notEmpty: {
                                message: '风控意见不能为空'
                            }
                        }
                    }
                }
            });
        },
        save: function(){
            var bootstrapValidator = $("#order_search_form").data('bootstrapValidator').validate();
            if(!bootstrapValidator.isValid()){
                return false;
            }
            $("#btn-save").addClass("btn disabled");
            layer.load(2);
            var submitModel = $("#order_search_form").serializeObject();
            $.post("/api/risk/post",submitModel,function(response){
                if (response.success_is_ok){
                    layer.msg(response.msg);
                    var targetUrl="/loaner/risk/index/" + vm.loanerId;
                    redirectUrl(targetUrl);
                    return;
                }else{
                    layer.msg(response.msg);
                }
                $("#btn-save").removeClass("disabled");
                layer.closeAll();
            });
        },
        cancel: function(){
            var targetUrl="/loaner/risk/index/" + vm.loanerId;
            redirectUrl(targetUrl);
            return;
        }
    }
});