/**
 * Created by Administrator on 2016/12/12.
 */
//Vue实例
//Model
var model = {
    //红包列表
    voucherList: [],
    //红包类型
    type:["新注册红包"],
    prodType:["","涨额壹月","","涨额叁月","","","涨额陆月","","","","","","涨额十二月"],
    isHasData: true,
    searchObj:{}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".account-content",
    data: model,
    beforeCreate: function () {
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 4;
    },
    //初始化远程数据
    created: function () {
        this.searchList(0);
    },
    //方法，可用于绑定事件或直接调用
    methods: {

        pageSearch:function(type){
            model.searchObj.vStatus = type;
            $.get("/api/voucher/getVoucherForPage", model.searchObj, function (response) {
                vm.voucherList = [];
                if (response.success_is_ok) {
                    var data = response.data;
                    vm.voucherList = data;
                    if (data != null && data.length > 0) {
                        vm.isHasData = true;
                    } else {
                        vm.isHasData = false;
                    }
                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount > 0) {
                        //调用分页
                        laypage({
                            skin: '#e88a6e',
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            curr:model.searchObj.pageIndex + 1,
                            pages: pageCount, //总页数
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex = obj.curr - 1;
                                    vm.pageSearch(type);
                                }
                            }
                        });
                    }
                }
            });
        },
        searchList: function (type) {
            $("#pageNum").html("");
            model.searchObj.pageIndex = 0;
            model.searchObj.pageSize = 4;
            this.pageSearch(type);
        }
        //searchListed: function (type) {
        //    model.searchObj.vStatus = type;
        //    $.get("/api/voucher/getVoucherForPage", model.searchObj, function (response) {
        //        vm.voucherList = [];
        //        if (response.success_is_ok) {
        //            var data = response.data;
        //            vm.voucherList = data;
        //            if (data != null && data.length > 0) {
        //                vm.isHasData = true;
        //            } else {
        //                vm.isHasData = false;
        //            }
        //            var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
        //            if (pageCount > 0) {
        //                //调用分页
        //                laypage({
        //                    skin: '#e88a6e',
        //                    cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
        //                    curr:model.searchObj.pageIndex + 1,
        //                    pages: pageCount, //总页数
        //                    groups: 7, //连续显示分页数
        //                    jump: function (obj, first) { //触发分页后的回调
        //                        if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
        //                            vm.searchObj.pageIndex = obj.curr - 1;
        //                            vm.searchList(type);
        //                        }
        //                    }
        //                });
        //            }
        //        }
        //    });
        //},
        //searchListno: function (type) {
        //    model.searchObj.vStatus = type;
        //    $.get("/api/voucher/getVoucherForPage", model.searchObj, function (response) {
        //        vm.voucherList = [];
        //        if (response.success_is_ok) {
        //            var data = response.data;
        //            vm.voucherList = data;
        //            if (data != null && data.length > 0) {
        //                vm.isHasData = true;
        //            } else {
        //                vm.isHasData = false;
        //            }
        //            var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
        //            if (pageCount > 0) {
        //                //调用分页
        //                laypage({
        //                    skin: '#e88a6e',
        //                    cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
        //                    curr:model.searchObj.pageIndex + 1,
        //                    pages: pageCount, //总页数
        //                    groups: 7, //连续显示分页数
        //                    jump: function (obj, first) { //触发分页后的回调
        //                        if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
        //                            vm.searchObj.pageIndex = obj.curr - 1;
        //                            vm.searchList(type);
        //                        }
        //                    }
        //                });
        //            }
        //        }
        //    });
        //}
    }
});