/**
 * Created by wenyq on 2017/2/17.
 */

//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //列表
    products1: [],
    products2: [],
    recentInvest : [],
    cycleType:["","天","个月","季","年"],
    status:["","","立即投资","满标","募集结束","","","还款中","","","已还款"]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".project-list",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj.bpDisplayIsMobile=1;
        model.searchObj.pageIndex = 0;
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //updated: function() {
    //    $('.circle').circliful();
    //},
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {
            model.searchObj.bpType = 1;
            $.post("/api/product/list", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.products1 = response.data;
                }
            });
            model.searchObj.pageSize = 1;
            model.searchObj.bpType = 2;  //新手标
            $.post("/api/product/list", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.products2 = response.data;
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
vm.$watch('products1',function(val){
    vm.$nextTick(function() {
        $('.circle').circliful();
    });
})

//   活动轮播图
var myVue = new Vue({
    el: ".banner",
    data: {
        items:[]
    },

    created: function () {
            $.post("/mobileApi/banner/bannerList",function(response){
                if(response.success_is_ok){
                    //alert("数据大小"+response.data.length)
                    myVue.items = response.data;
                }
            })

    },
    updated: function() {

        //swiper轮播图
        var swiper=new Swiper(".banner",{
            pagination: '.swiper-pagination',
            paginationClickable: true,
            autoplay:2000,
            autoplayDisableOnInteraction:false,
            loop:true
        });
    }

})
//myVue.$watch('items',function(val){
//    vm.$nextTick(function() {
//        var swiper=new Swiper(".banner",{
//            pagination: '.swiper-pagination',
//            paginationClickable: true,
//            autoplay:2000,
//            autoplayDisableOnInteraction:false,
//            loop:true
//        });
//    });
//})




