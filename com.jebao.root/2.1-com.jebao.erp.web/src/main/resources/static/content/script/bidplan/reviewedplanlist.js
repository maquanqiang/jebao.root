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
    //列表
    planlist: [],
    bpTypeArr:[],
    bpCycleTypeArr : [],
    bpInterestPayTypeArr : [],
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
        model.displayArr = ["","显示","不显示"];
        model.searchObj.page=0;
        model.searchObj.rows=10;
        model.searchObj.bpStatus=0;
    },
    //初始化远程数据
    created:function(){
        this.search();

    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
            $.get("/api/bidPlan/getPlanListForPage",model.searchObj,function(response){
                if (response.success_is_ok){
                    vm.planlist=response.data;
                    console.log(response.count);
                    if (response.count>0){
                        var pageCount = Math.ceil(response.count / model.searchObj.rows);
                            //调用分页
                            laypage({
                                cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                                pages: pageCount, //总页数
                                curr:model.searchObj.page+1,
                                groups: 7, //连续显示分页数
                                jump: function(obj, first){ //触发分页后的回调
                                    if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                        vm.searchObj.page=obj.curr -1;
                                        vm.search();
                                    }
                                },
                                skin: '#3c8dbc'
                            });
                        }
                    }
            })
        },
        bidPlanViewBtn:function(bpId){
            window.location.href = "/bidplan/reviewedPlanDetail/"+bpId;
        },
        modifyRiskDataBtn:function(bpId){
            window.location.href = "/bidplan/bidRiskDataList/"+bpId;
        },
        modifyPlanBtn:function(bpId){
            window.location.href = "/bidplan/updateplandetail/"+bpId;
        },
        removeBtn:function(bpId){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/bidPlan/removeBidPlan",{bpId:bpId},function(response){
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                        vm.search();
                    }else{
                        layer.alert(response.msg);
                    }
                });
                layer.closeAll();
            });
        }

    }
});
