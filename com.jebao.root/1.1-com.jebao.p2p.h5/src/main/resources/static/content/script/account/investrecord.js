/**
 * Created by wenyq on 2017/2/28.
 */
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
    isHasDatepd: true,
    cycleType:["","天","个月","季","年"]
};
var vm = new Vue({
    el: ".wrap",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj1.freezeStatus = 1;
        model.searchObj1.pageIndex = 0;
        model.searchObj1.pageSize = 1000;

        model.searchObj2.freezeStatus = 2;
        model.searchObj2.pageIndex = 0;
        model.searchObj2.pageSize = 1000;

        model.searchObj3.freezeStatus = 3;
        model.searchObj3.pageIndex = 0;
        model.searchObj3.pageSize = 1000;
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
                }
            });
        }
    }
});