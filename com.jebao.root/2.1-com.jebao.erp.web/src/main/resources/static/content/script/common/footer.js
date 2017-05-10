//Vue实例
//Model
var model = {
    //列表
    dueTotal : 0,
    repayTotal : 0
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".dropdown",
    data: model,
    //初始化远程数据
    created:function(){
        //$.get("/api/bidPlan/incomeCount/1",function(response){
        //    if (response.success_is_ok){
        //        vm.dueTotal = response.data;
        //    }
        //});
        //$.get("/api/bidPlan/incomeCount/2",function(response){
        //    if (response.success_is_ok){
        //        vm.repayTotal = response.data;
        //    }
        //});
    },
    //watch可以监视数据变动，针对相应的数据设置监视函数即可
    watch: {
        //
    },
    //方法，可用于绑定事件或直接调用
    methods: {

    }
});
