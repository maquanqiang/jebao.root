
/**
 * Created by lee on 2016/11/18.
 */
$(function () {

    $(".select2").select2();
    /*时间选择*/
    $('.chooseDate').datepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked',
        language: 'cn'
    });
});
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //列表
    planlist: [],
    bpTypeArr:[],
    bpCycleTypeArr : [],
    bpInterestPayTypeArr : [],
    bpStatusArr : [],
    displayArr : []
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.bpTypeArr = ["","普通理财","新手专享","加息标"];
        model.bpCycleTypeArr = ["","日","月","季","年"];
        model.bpInterestPayTypeArr = ["","一次性还本付息","先息后本，按期付息"];
        model.bpStatusArr = ["待审核",'审核未通过',"招标中","已满标",'已过期','','起息中','还款中','','','已结清'];
        model.displayArr = ["","显示","不显示"];
        //初始化本地数据
        model.searchObj = $("#defaultForm").serializeObject(); //初始化 model.search 对象
        model.searchObj.bpStatusSear = '(7,10)';
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=15;
    },
    //初始化远程数据
    created:function(){
       this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
            if((model.searchObj.searchDateSt!=null && model.searchObj.searchDateSt!= "")
                ||(model.searchObj.searchDateEnd!=null && model.searchObj.searchDateEnd!="")){
                if(model.searchObj.searchDateType==null || model.searchObj.searchDateType ==''){
                    layer.alert("请选择时间查询类型");
                    return;
                }
            }

            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#searchBtn").addClass("disabled");//禁用按钮
            $.get("/api/bidPlan/getPlanListBySearchCondition",model.searchObj,function(response){
                $("#searchBtn").removeClass("disabled");//解除禁用
                //调用分页
                if (response.success_is_ok){
                    vm.planlist=response.data;
                    if (response.count>0){
                        var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages : pageCount, //总页数
                            curr : model.searchObj.pageIndex+1,
                            groups: 7, //连续显示分页数
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    console.log(obj.curr);
                                    vm.searchObj.pageIndex=obj.curr -1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }else{
                        $("#pageNum").html("");
                    }
                }
            });
        },
        modifyPlanBtn:function(bpId){
            window.location.href = "/bidplan/alreadyLoanDetail/"+bpId;
        }
    }
});

$("#searchDateSt").change(function(){
    vm.searchObj.searchDateSt=$(this).val();
});
//
$("#searchDateEnd").change(function(){
    vm.searchObj.searchDateEnd=$(this).val();
});
