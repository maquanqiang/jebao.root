/**
 * Created by Administrator on 2016/11/28.
 */

var model = {
    //查询条件
    searchObj: {},
    risks: []
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj=$("#search_form").serializeObject();
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        getEditHref: function (lid, rid) {
            return "/loaner/risk/info/" + lid + "/rId/" + rid;
        },
        search: function(){
            $.get("/api/risk/list", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    vm.risks = data;
                }
            });
        },
        openDeleteWin:function(id){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/risk/delete",{id:id},function(response){
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                        vm.search();
                    }else{
                        layer.alert(response.msg);
                    }
                });
                layer.closeAll();
            });
        }
    }
});
