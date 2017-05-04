/**
 * Created by Administrator on 2016/11/29.
 */
var model = {
    //查询条件
    searchObj: {},
    //文章列表
    articles: [],
    //文章类别
    aTypes: []
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj=$("#search_form").serializeObject();
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=10;
        //文章类别
        $.get("/api/articletype/list",function(response){
            if (response.success_is_ok){
                vm.aTypes=response.data;
            }
        });
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    mounted:function(){
        $("#search_form select.select2").select2();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        getEditHref: function (aId) {
            return "/article/details/"+ aId;
        },
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            model.searchObj.typeId=$("#typeId").val();
            $("#btnSearch").addClass("disabled");//禁用按钮
            $.get("/api/article/list", model.searchObj, function (response) {
                if (response.success_is_ok){
                    vm.articles=response.data;
                    if (response.count>0){
                        var pageCount = Math.ceil(response.count / model.pageSize);
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr:model.searchObj.pageIndex+1,//设置当前页
                            groups: 7, //连续显示分页数
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    console.log(obj.curr);
                                    vm.searchObj.pageIndex=obj.curr -1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
                $("#btnSearch").removeClass("disabled");//解除禁用
            });
        },
        openDeleteWin:function(aId){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/article/delete",{aId:aId},function(response){
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