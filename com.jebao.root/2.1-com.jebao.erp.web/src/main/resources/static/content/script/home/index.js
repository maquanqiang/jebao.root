/**
 * Created by Administrator on 2016/11/22.
 */
var model = {
    accountCount:{}
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function() {
        //初始化本地数据
    },
    //初始化远程数据
    created:function(){
        $.get("/api/account/accountCount",function(response){
            if (response.success_is_ok){
                var data=response.data;
                if(data!=null) {
                    vm.accountCount = data;
                }
            }
        });
    }
});