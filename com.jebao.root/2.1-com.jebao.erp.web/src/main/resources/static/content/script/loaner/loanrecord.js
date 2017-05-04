/**
 * Created by Administrator on 2016/12/5.
 */
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //借款记录列表
    loanRecords: [],
    //借款汇总
    loanSum: {}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj = $("#search_form").serializeObject();
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
        model.loanSum.jkCount = 0;
        model.loanSum.jkAmounts = 0;
        model.loanSum.yhAmounts = 0;
        model.loanSum.dhAmounts = 0;
    },
    //初始化远程数据
    created: function () {
        this.search();
        var dataVal = $("#search_form").serializeObject();
        $.get("/api/loanrecord/statistics", dataVal, function (response) {
            if (response.success_is_ok) {
                var data = response.data;
                if (data != null) {
                    vm.loanSum = data;
                }
            }
        });
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {
            if (typeof event !== "undefined") { //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex = 0;
            }
            $.get("/api/loanrecord/list", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.loanRecords = response.data;
                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount > 0) {
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex = obj.curr - 1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
            });
        }
    }
});