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
    search: {},
    //标的
    plan: {},
    //台账列表
    intentList : [],
    //
    riskDataList:[],
    principalTotal:0,
    interestTotal : 0,
    total : 0,
    bpExpectExpireDate:''
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
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
                var date = new Date(vm.plan.bpExpectRepayDate);
                date = date.valueOf();
                date = date - 24 * 60 * 60 * 1000;
                var dateStr = new Date(date).toFormatString("yyyy-MM-dd");
                vm.bpExpectExpireDate = dateStr;
                KindEditor.html("#kindEditorContent", data.bpDesc);
            }else{
                layer.msg(response.error);
            }
        });
        $.get("/api/bidRiskData/getRiskDataListForPage", dataVal, function (response) {
            if (response.success_is_ok) {
                vm.riskDataList = response.data;
            }
        })
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
        search:function(event){
        },
        createIntentBtn:function(){
            vm.intentList = [];
            vm.principalTotal = 0;
            vm.interestTotal = 0;
            vm.total = 0;
            $.post("/api/bidPlan/getLoanFundIntents",vm.plan,function(response){
                if (response.success_is_ok){
                    vm.intentList = response.data;
                    for(var i=0; i<vm.intentList.length; i++){
                        vm.principalTotal +=vm.intentList[i].principal;
                        vm.interestTotal += vm.intentList[i].interest;
                    }
                    vm.total = vm.principalTotal +vm.interestTotal;
                    vm.principalTotal=toDecimal2(vm.principalTotal);
                    vm.interestTotal=toDecimal2(vm.interestTotal);
                    vm.total=toDecimal2(vm.total);
                }
            });
        },
        cancelBtn : function(){
            window.location.href = "/bidplan/reviewedPlanList";
        },
        submitBtn:function() {
            var formValue = $("#defaultForm").serializeObject();
            $.post("/api/bidPlan/updatePlan",formValue,function(response){
                if (response.success_is_ok){
                    window.location.href = "/bidplan/reviewedPlanList";
                }else{
                    errorHandlerFun("#error_place_id");
                }
            });
        }
    }
});
//
//$("#bpExpectLoanDateId").change(function(){
//    vm.plan.bpExpectLoanDate=$(this).val();
//});
////
//$("#bpExpectRepayDateId").change(function(){
//    vm.plan.bpExpectRepayDate=$(this).val();
//});


function endTime(startTime, d){
    startTime = startTime.replace(/-/g,"/");
    var date = new Date(startTime);
    date = date.valueOf();
    date = date + d * 60 * 60 * 1000;
    var nd = new Date(date);
    var time1 = nd.toFormatString("yyyy-MM-dd HH:mm:ss");
    return time1;
}

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

/*开始时间选择*/
laydate({
    elem:'#bpStartTime',
    istime: true,
    istoday : false,
    format: 'YYYY-MM-DD hh:mm:ss',
    choose : function(datas) {
        var bpOpenTime = $("#bpOpenTime").val();
        if(bpOpenTime!=null && bpOpenTime != ""){
            var time = endTime(datas,bpOpenTime);
            $("#bpEndTime").val(time)
        }
    }
});

laydate({
    elem:'#bpExpectLoanDate',
    istime: false,
    format: 'YYYY-MM-DD',
    istoday : false,
    choose : function(datas){
        var d = $("#bpPeriodsDisplay").val();
        var cycle = $("#bpCycleType").val();
        var date = repayDate(datas, d, cycle);
        $("#bpExpectRepayDate").val(date);
        var date = new Date(date);
        date = date.valueOf()
        date = date - 24 * 60 * 60 * 1000;
        date = new Date(date).toFormatString("yyyy-MM-dd")
        $("#bpExpectExpireDate").val(date);
    }
});


$("#bpOpenTime").change(function(){
    var bpStartTime = $("#bpStartTime").val();
    if(bpStartTime!=null && bpStartTime != ""){
        var time = endTime(bpStartTime,$(this).val());
        $("#bpEndTime").val(time);
    }
});


$("#bpPeriodsDisplay").change(function(){

    var period = $(this).val();
    vm.plan.bpPeriods = period;
    var datas = $("#bpExpectLoanDate").val();
    var cycle = $("#bpCycleType").val();
    var date = repayDate(datas, $(this).val(), cycle);
    $("#bpExpectRepayDate").val(date);
    var date = new Date(date);
    date = date.valueOf()
    date = date - 24 * 60 * 60 * 1000;
    date = new Date(date).toFormatString("yyyy-MM-dd")
    $("#bpExpectExpireDate").val(date);
});

$("#bpCycleType").change(function(){
    var datas = $("#bpExpectLoanDate").val();
    var d = $("#bpPeriodsDisplay").val();
    var date = repayDate(datas, d, $(this).val());
    $("#bpExpectRepayDate").val(date);
    var date = new Date(date);
    date = date.valueOf()
    date = date - 24 * 60 * 60 * 1000;
    date = new Date(date).toFormatString("yyyy-MM-dd")
    $("#bpExpectExpireDate").val(date);
});