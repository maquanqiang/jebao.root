/**
 * Created by Administrator on 2016/12/27.
 */
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //公司动态列表
    activitys: [],
    isHasDate: true
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".aboutUs-content",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj.typeId = 1;
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        getDetailHref: function (id) {
            return "/html/activity/details/" + id;
        },
        search: function () {
            $.get("/api/article/list", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    vm.activitys = data;
                    if (data != null && data.length > 0) {
                        vm.isHasDate = true;
                    } else {
                        vm.isHasDate = false;
                    }

                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount > 0) {
                        //调用分页
                        laypage({
                            cont: $('.page'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex = obj.curr - 1;
                                    vm.search(fs);
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
            });
        }
    }
});
