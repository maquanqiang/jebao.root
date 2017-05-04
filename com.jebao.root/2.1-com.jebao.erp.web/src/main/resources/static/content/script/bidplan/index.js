/**
 * Created by lee on 2016/11/18.
 */
$(function () {

    $(".select2").select2();

});
//Vue实例
//Model
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //借款人列表
    loaners:[]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj = $("#defaultForm").serializeObject(); //初始化 model.search 对象
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=10;
    },
    //初始化远程数据
    //初始化远程数据
    created:function(){
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            $.get("/api/loaner/list",model.searchObj,function(response){
                if (response.success_is_ok){
                    console.log(response.data)
                    vm.loaners=response.data;
                    if (response.count>0){
                        var pageCount = Math.ceil(response.count / model.pageSize);
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
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
                    }
                }
                $("#btnSearch").removeClass("disabled");//解除禁用
            });
        },
        addPlanBtn : function(id){
            window.location.href = "/bidplan/addplan/"+id
        }
    }
});