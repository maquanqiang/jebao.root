/**
 * Created by wenyq on 2017/3/3.
 */
var model = {
    riskDataList : []
};
var vm = new Vue({
    el: ".cert",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    updated : function(){
        (function(){
            /*
             注意：$.mggScrollImg返回的scrollImg对象上有
             next，prev，go三个方法，可以实现外部对滚动索引的控制。
             如：scrollImg.next();//会切换到下一张图片
             scrollImg.go(0);//会切换到第一张图片
             */
            var scrollImg = $.mggScrollImg('.imgbox ul',{
                loop : true,//循环切换
                auto : false,//自动切换
                auto_wait_time:3000,//轮播间隔
                scroll_time:300,//滚动时长
                callback : function(ind){//这里传过来的是索引值
                    $('#page').text(ind+1);
                }
            });
        })()


    },
    methods:{
        search:function(event)
        {
            var form = {
                bpId:$("#bpId").val()
            }
            $.post("/api/product/riskListByBpId", form, function (response) {
                if (response.success_is_ok) {
                    vm.riskDataList = response.data;
                }
            });
        }
    }
});