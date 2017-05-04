/**
 * Created by wenyq on 2017/2/28.
 */
var model = {
    //查询条件
    searchObj: {},
    //资金汇总
    fundSum: {incomeAmount: 0, totalAssets: 0, balance: 0, freezeAmount: 0, dueInPrincipal: 0, dueInIncome: 0}}
var vm = new Vue({
    el: ".wrap",
    data: model,
    beforeCreate: function () {
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=1;
    },
    created: function () {
        $.get("/api/invest/statistics", function (response) {
            if (response.success_is_ok) {

                var data = response.data;
                if (data != null) {
                    vm.fundSum = data;
                }
            }
        });
    },
    methods: {
    openfunds: function () {
        $.get("/api/funds/details",model.searchObj,function(response){
            if (response.success_is_ok){
                var data = response.data;
                if(data!=null && data.length > 0){
                    window.location.href = "/account/funds";
                }else{
                    layer.msg("暂无资金记录");
                    return;
                }

            }
        });

    },
        voucher:function(){
            window.location.href = "/account/voucher";
        }
    }
});