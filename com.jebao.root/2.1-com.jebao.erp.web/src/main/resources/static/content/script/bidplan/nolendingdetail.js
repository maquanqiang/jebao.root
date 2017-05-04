/**
 * Created by Lee on 2016/11/17.
 */

/**
 * Created by Jack on 2016/11/18.
 */
$(function () {

    $(".select2").select2();

});
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //标的
    plan: {},
    //台账列表
    intentList : [],
    //
    riskDataList:[],
    total : 0,
    loanMoney : 0,
    investInfoList : [],
    fundType : ['','本金','利息']
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        //初始化本地数据
        model.searchObj.indBpId = $("#bpId").val();
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=1000;
    },
    //初始化远程数据
    created:function(){
        var val = $("#bpId").val();
        var dataVal = {
            bpId : val
        }
        $.get("/api/bidPlan/getBidPlanById",dataVal,function(response){
            if (response.success_is_ok){
                var data=response.data;
                vm.plan=data;
                vm.loanMoney = data.bpBidMoney-data.bpSurplusMoney;
                var bpFullTime = new Date(data.bpFullTime).toFormatString("yyyy-MM-dd");
                $("#bpInterestSt").val(bpFullTime);
                var date = new Date(vm.plan.bpExpectRepayDate);
                date = date.valueOf();
                date = date - 24 * 60 * 60 * 1000;
                var dateStr = new Date(date).toFormatString("yyyy-MM-dd");
                $("#bpExpectExpireDate").val(dateStr)

                var d = data.bpPeriodsDisplay;
                var cycle = data.bpCycleType;
                var date = repayDate(bpFullTime, d, cycle);
                $("#bpRepayTime").val(date);

                var dateEt = new Date(date)
                dateEt = dateEt.valueOf();
                dateEt = dateEt - 24 * 60 * 60 * 1000;
                var dateEtStr = new Date(dateEt).toFormatString("yyyy-MM-dd");
                $("#bpInterestEt").val(dateEtStr)
            }
        });
        //riskDataList
        $.get("/api/bidRiskData/getRiskDataListForPage", dataVal, function (response) {
            if (response.success_is_ok) {
                vm.riskDataList = response.data;
            }
        })
        //investInfoList
        $.get("/api/investInfo/list", dataVal, function (response) {
            if (response.success_is_ok) {
                vm.investInfoList = response.data;
            }
        })
        //intentList
        //$.get("/api/incomeDetail/repaymentList",model.searchObj,function(response) {
        //    if (response.success_is_ok) {
        //        vm.intentList = response.data;
        //        for(var i=0; i<vm.intentList.length; i++){
        //            vm.total += (vm.intentList[i].indMoney)*1;
        //        }
        //        vm.total=toDecimal2(vm.total);
        //    }
        //})
    },
    //watch可以监视数据变动，针对相应的数据设置监视函数即可
    watch: {
        //
        "plan.bpType" : function (newVal,oldVal) {
            if(newVal == 3){
                $("#bpUpRateDiv").show()
            }else{
                $("#bpUpRateDiv").hide()
                $("#bpUpRate").val(0)
            }
        }
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        //表单登录验证封装
        myInitValidateForm: function (obj) {
            obj.bootstrapValidator({
                fields: {
                    bpInterestSt: {
                        validators: {
                            notEmpty: {
                                message: '起息时间不能为空'
                            }
                        }
                    },
                    bpLoanMoney: {
                        validators: {
                            notEmpty: {
                                message: '放款金额不能为空'
                            }
                        }
                    },
                    bpRepayTime: {
                        validators: {
                            notEmpty: {
                                message: '还款时间不能为空'
                            }
                        }
                    }
                }
            })
        },
        search:function(event){
        },
        createIntentBtn:function(){
            vm.myInitValidateForm($('#defaultForm'));
            var bootstrapValidator = $("#defaultForm").data('bootstrapValidator').validate();
            if (!bootstrapValidator.isValid()) {
                return false;
            }
            vm.intentList = [];
            vm.total = 0;
            var form = $("#defaultForm").serializeObject();

            $.post("/api/investInfo/viewDetails",form,function(response){
                if (response.success_is_ok){
                    vm.intentList = response.data;
                    for(var i=0; i<vm.intentList.length; i++){
                        vm.total += (vm.intentList[i].indMoney)*1;
                    }
                    vm.total=toDecimal2(vm.total);
                }
            });
        },
        closeBtn:function(){
            var money = $("#defaultForm input[name=bpLoanMoney]").val();
            if(money*1>0){
                layer.alert("放款金额不为空，不能关闭");
                return false;
            }
            $.post("/api/bidPlan/close",{bpId : $("#bpId").val()}, function(response){
                if(response.success_is_ok){
                    layer.msg(response.msg);
                    window.location.href = "/bidplan/noLendingList";
                }else{
                    layer.msg(response.error);
                }
            })
        },
        doLoanBtn:function(){
                vm.myInitValidateForm($('#defaultForm'));
                var bootstrapValidator = $("#defaultForm").data('bootstrapValidator').validate();
                if (!bootstrapValidator.isValid()) {
                    return false;
                }
                var loanMoney = $("#defaultForm input[name=bpLoanMoney]").val();
                if(loanMoney*1<=0){
                    layer.alert("放款金额为0");
                    return false;
                }
                //加载层-风格2
                var index = layer.load(1);
                var form = $("#defaultForm").serializeObject();

                $.post("/api/bidPlan/doLoan",form,function(response){
                    layer.close(index);
                    if(response.success_is_ok){
                        layer.msg(response.msg);
                        $.post("/api/investInfo/createIncomeDetails",form,function(response){
                            if (response.success_is_ok){
                                layer.msg(response.msg);
                                //借款人还款
                                $.post("/api/investInfo/createRepaymentDetails",form,function(response){
                                    if (response.success_is_ok){
                                        layer.msg(response.msg);
                                    }
                                });
                                form.bidNumber = vm.plan.bpNumber;
                                $.post("/contract/createDemo", form, function (response) {
                                    if(response.success_is_ok){
                                        //加载层-默认风格
                                        layer.msg("合同制作成功");
                                        setTimeout(function(){window.location.href = "/bidplan/noLendingList"},3000);
                                    }else{
                                        layer.alert(response.error);
                                    }
                                })
                            }else{
                                layer.alert(response.error);
                            }
                        })
                    }else{
                        layer.alert(response.error);
                    }
                })
            }
        },
        createContract:function() {
            if (vm.investInfoList.length > 0) {
                var form = $("#defaultForm").serializeObject();
                form.bidNumber = vm.plan.bpNumber;
                $.post("/contract/createDemo", form, function (response) {
                    if(response.success_is_ok){
                        //加载层-默认风格
                        layer.load(1);
                        setTimeout(function(){
                            layer.closeAll('loading');
                            layer.msg(response.msg);
                            //investInfoList
                            $.get("/api/investInfo/list", {bpId : form.bpId}, function (response) {
                                if (response.success_is_ok) {
                                    vm.investInfoList = response.data;
                                }
                            })
                        }, 10000);

                    }else{
                        layer.msg(response.error);
                    }
                })
            }
        }
});

laydate({
    elem:'#bpInterestSt',
    istime: true,
    format: 'YYYY-MM-DD',
    istoday : true,
    choose : function(datas){
        var d = vm.plan.bpPeriodsDisplay;
        var cycle = vm.plan.bpCycleType;
        var dateStr = repayDate(datas, d, cycle);
        $("#bpRepayTime").val(dateStr);
        var date = new Date(dateStr)
        date = date.valueOf();
        date = date - 24 * 60 * 60 * 1000;
        var date = new Date(date).toFormatString("yyyy-MM-dd");
        $("#bpInterestEt").val(date)

    }
});


function repayDate(loanDate, d, cycle){
    if(cycle!="" && loanDate != "" && d != ""){
        loanDate = loanDate.replace(/-/g,"/");
        var date = new Date(loanDate);
        if(cycle==1){   //天
            date.setDate(date.getDate() + d*1);
        }else if(cycle==2){ //月
            date.setMonth(date.getMonth() + d*1);
        }else if(cycle == 3){   //季
            date.setMonth(date.getMonth() + (d*3));
        }else if(cycle ==4 ){   //年
            date.setFullYear(date.getFullYear() + d*1);
        }
        return date.toFormatString("yyyy-MM-dd");
    }else{
        return null;
    }
}

