/**
 * Created by Lee on 2016/11/17.
 */

/**
 * Created by Jack on 2016/11/18.
 */
$(function () {
    /*时间选择*/
    $('.chooseDate').datepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked',
        language: 'cn'
    });
});
//Vue实
//Model
var model = {
    //查询条件,代表对象
    searchObj: {},
    //投资列表
    reportDetailList : [],

    //投资期限
    CycleType:["","日","月","季","年"],
    //付息方式
    InterestPayType:["","一次性还本付息","先息后本，按期付息"]
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj.searchDateSt = $("#searchDateSt").val();
         model.searchObj.searchDateEnd = $("#searchDateEnd").val();
         model.searchObj.indBpNumber=$("#indBpNumber").val();
        model.searchObj = $("#defaultForm").serializeObject(); //初始化 model.search 对象
        model.searchObj.bpStatusSear = '(7,10)';
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=10;

    },
    //初始化远程数据
    created:function(){

          this.search();
    },
    methods: {
        search:function(event,searchDateSt,searchDateEnd,indBpNumber){
                var searchDateSt= $("#searchDateSt").val();
               var searchDateEnd=$("#searchDateEnd").val();
               var indBpNumber=$("#indBpNumber").val();
             if((model.searchObj.searchDateSt!=null && model.searchObj.searchDateSt!= "")
              ||(model.searchObj.searchDateEnd!=null && model.searchObj.searchDateEnd!="")){
                          $.get("/api/report/getDate",{searchDateSt:searchDateSt,searchDateEnd:searchDateEnd},function(response){
                            if(response.success_is_ok){
                                vm.reportDetailList=response.data;
                            }
                          })
                     }else if(model.searchObj.indBpNumber!=null && model.searchObj.indBpNumber!=""){
                        $.get("/api/report/getIdAll",{indBpNumber:indBpNumber},function(response){
                            if(response.success_is_ok){
                                vm.reportDetailList=response.data;
                            }
                        });
                     }else{
                       var form = $("#defaultForm").serializeObject();
                                    //查看当前的数量
                                    $.get("/api/report/pagingSelectDetails",model.searchObj,function(response) {
                                        if (response.success_is_ok) {
                                            vm.reportDetailList = response.data;
                                            if (response.count>0){

                                                var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                                                //调用分页
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
                                                    skin: '#3c8dbc' //皮肤的颜色
                                                });
                                            }else{
                                                $("#pageNum").html("");
                                            }
                                        }
                                    })
                                    $("#searchBtn").removeClass("disabled");//解除禁用
             }
        },
//        event：事件
        excel:function(event){
//        获取数据
              var searchDateSt= $("#searchDateSt").val();
              var searchDateEnd=$("#searchDateEnd").val();
//              利用ajaxsubmit来事件异步加载并且提交当前的数据
//            options：可以选择的
           var options={
                //获取请求的路径
                url:'/api/report/excelDetails',
                //类型
                type:'get',
                //自定义的属性，符合Html的规范
                data:{
                    'searchDateSt':searchDateSt,
                    'searchDateEnd':searchDateEnd
                },
                success:function(data){ //保存提交后的数据，大部分是json格式的数据
                }
           };
           //获取文件的路径
           $("#defaultForm").attr("action","/api/report/excelDetails");
           //提交当前的数据
           $("#defaultForm").submit();
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

