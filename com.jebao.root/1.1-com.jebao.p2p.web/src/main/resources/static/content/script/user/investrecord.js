/**
 * Created by Administrator on 2016/12/13.
 */
//Vue实例
//Model
var model = {
    //投资中的项目查询条件
    searchObj1: {},
    //还款中的项目查询条件
    searchObj2: {},
    //已还款的项目查询条件
    searchObj3: {},
    //投资中项目列表
    investings: [],
    isHasDateii: true,
    //还款中项目列表
    paymentings: [],
    isHasDatepi: true,
    //已还款项目列表
    paymenteds: [],
    isHasDatepd: true
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".account-content",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj1.freezeStatus = 1;
        model.searchObj1.pageIndex = 0;
        model.searchObj1.pageSize = 10;

        model.searchObj2.freezeStatus = 2;
        model.searchObj2.pageIndex = 0;
        model.searchObj2.pageSize = 10;

        model.searchObj3.freezeStatus = 3;
        model.searchObj3.pageIndex = 0;
        model.searchObj3.pageSize = 10;
    },
    //初始化远程数据
    created: function () {
        this.search(1);
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (fs) {
            var dataVal;
            if(fs == 1){
                dataVal = model.searchObj1;
            }else if(fs == 2){
                dataVal = model.searchObj2;
            }else{
                dataVal = model.searchObj3;
            }
            $.get("/api/invest/record", dataVal, function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    if (fs == 1) {
                        vm.investings = data;
                        if (data != null && data.length > 0) {
                            vm.isHasDateii = true;
                        } else {
                            vm.isHasDateii = false;
                        }
                    } else if (fs == 2) {
                        vm.paymentings = data;
                        if (data != null && data.length > 0) {
                            vm.isHasDatepi = true;
                        } else {
                            vm.isHasDatepi = false;
                        }
                    } else {
                        vm.paymenteds = data;
                        if (data != null && data.length > 0) {
                            vm.isHasDatepd = true;
                        } else {
                            vm.isHasDatepd = false;
                        }
                    }
                    var pageCount = Math.ceil(response.count / dataVal.pageSize);
                    if (pageCount > 0) {
                        //调用分页
                        laypage({
                            skin: '#e88a6e',
                            cont: $('#pageNum' + fs), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    var currIndex = obj.curr - 1;
                                    if(fs==1){
                                        vm.searchObj1.pageIndex = currIndex;
                                    }else if(fs == 2){
                                        vm.searchObj2.pageIndex = currIndex;
                                    }else{
                                        vm.searchObj3.pageIndex = currIndex;
                                    }
                                    vm.search(fs);
                                }
                            }
                        });
                    }
                }
            });
        }
    }
});
