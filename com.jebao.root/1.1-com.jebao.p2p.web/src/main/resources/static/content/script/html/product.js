$(function () {
    //调用分页
    //laypage({
    //    cont: $('.page'), //容器。值支持id名、原生dom对象，jquery对象,
    //    pages: 100, //总页数
    //    skip: true, //是否开启跳页
    //    skin: '#e88a6e',
    //    groups: 3 //连续显示分页数
    //});
    $('.project-filter dl dt').click(function () {
        $(this).parent().find('dt').removeClass('active');
        $(this).addClass('active');
    });


    $('.project-filter dl dt').click(function () {
        //初始化本地数据
        model.searchObj.bpDisplayIsPc = 1;
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
        model.searchObj.bpInterestPayType = null;
        model.searchObj.bpPeriodStr = null;
        model.searchObj.searchMoneyStr = null;
        model.searchObj.bpStatus = null;
        $(this).parent().find('dt').removeClass('active');
        $(this).addClass('active');

        $('.project-filter dl dt').each(function () {

            var clazz = $(this).attr("class");
            if (clazz == "active") {
                //获得被选中的a链接的类型和值
                var fType = $(this).attr("fType");
                var fValue = $(this).attr("fValue");
                if (fType == "bpInterestPayType") {
                    model.searchObj.bpInterestPayType = fValue;
                } else if (fType == "bpPeriodStr") {
                    model.searchObj.bpPeriodStr = fValue;
                } else if (fType == "searchMoneyStr") {
                    model.searchObj.searchMoneyStr = fValue;
                } else if (fType == "bpStatus") {
                    model.searchObj.bpStatus = fValue;
                }
            }
        });
        $("#pageNum").html("");
        vm.search()
    });


});


//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //列表
    products: [],
    cycleType:["","天","个月","季度","年"],
    status:["","","立即投资","满标","募集结束","","","还款中","","","已完成"]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".project-list",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj.bpDisplayIsPc = 1;
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {
            $.post($("#loginForm").attr("action"), model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.products = response.data;
                    if (response.count > 0) {
                        var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                        //调用分页
                        laypage({
                            skip: true, //是否开启跳页
                            skin: '#e88a6e',
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr:model.searchObj.pageIndex+1,
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex = obj.curr - 1;
                                    vm.search();
                                }
                            }
                        });
                    }
                }
            });
        },
        openDetail: function (id) {
            //同步账户金额
            $.get("/api/user/syncUserBalance");
            window.location.href = "/product/detail/" + id;
        }
    }
});

