/**
 * Created by Administrator on 2016/12/27.
 */

var model = {
    mediaNew: {}
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".aboutUs-content",
    data: model,
    beforeCreate:function() {
        //初始化本地数据
    },
    //初始化远程数据
    created:function(){
        var dataVal = $("#defaultForm").serializeObject();
        $.get("/api/article/details",dataVal,function(response){
            if (response.success_is_ok){
                var data=response.data;
                if(data!=null) {
                    vm.mediaNew = data;
                    $("#id_content").html(vm.mediaNew.content);
                }
            }
        });
    }
});