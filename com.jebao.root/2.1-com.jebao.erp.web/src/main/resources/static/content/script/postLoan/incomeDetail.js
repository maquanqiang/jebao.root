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
    //台账列表
    incomeDetailList : [],
    fundType : ['','本金','利息'],
    repayStatus : ['资金冻结', '未还款', '已还款']


};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
    },
    //初始化远程数据
    created:function(){
        this.search()
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(){
            var form = $("#defaultForm").serializeObject();
            $.post("/api/incomeDetail/incomeListCurrPeriod",form,function(response) {
                if (response.success_is_ok) {
                    vm.incomeDetailList = response.data;
                }
            })
        },
        closeBtn:function(){
            window.location.href = "/postLoan/index";
        }

    }
});


