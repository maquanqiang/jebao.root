/**
 * Created by wenyq on 2017/2/28.
 */
var model = {
    //查询条件
    searchObj: {},
    //收支明细列表
    voucherList:[],
    isHasDate:true
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".wrap",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=100;
    },
    //初始化远程数据
    created:function(){
        this.search(0);
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(type) {
            model.searchObj.vStatus = type;
            $.get("/api/voucher/getVoucherForPage",model.searchObj,function(response){
                vm.voucherList = [];
                if (response.success_is_ok){
                    var data = response.data;
                    vm.voucherList= data;
                    if(data!=null && data.length > 0){
                        vm.isHasDate = true;
                    }else{
                        vm.isHasDate = false;
                    }
                }
            });
        }
    }
});

$(function() {


    tab($('.investRecord-tit h4'));
    function tab(btn){
        btn.click(function (){
            btn.removeClass('active');
            $(this).addClass('active');
        });
    }
})
