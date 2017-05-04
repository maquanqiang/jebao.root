/**
 * Created by Administrator on 2016/12/12.
 */
//Vue实例
//Model
var model = {
    //可用红包数
    enableCount: 0,
    searchObj:{},
    apiOrigin:""
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".account-sidebar",
    data: model,
    beforeCreate: function () {
        model.apiOrigin = document.getElementById("apiOrigin").value;
        model.searchObj.vLoginId = document.getElementById("vLoginId").value;
    },
    //初始化远程数据
    created: function () {
        this.search(0);
    },
    //方法，可用于绑定事件或直接调用
    methods: {

        search:function(type){
            console.log(model.apiOrigin);
            model.searchObj.vStatus = type;
            $.get(model.apiOrigin+"/api/voucher/getEnableCount", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    vm.enableCount = data;
                }
            });
        }
    }
});