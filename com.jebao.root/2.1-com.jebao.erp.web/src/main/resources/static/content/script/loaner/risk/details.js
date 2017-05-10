/**
 * Created by Administrator on 2016/11/28.
 */
/**
 * Created by Administrator on 2016/11/24.
 */
var model = {
    details: {}
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    //初始化远程数据
    created:function(){
        var dataVal = $("#search_form").serializeObject();
        $.get("/api/risk/details",dataVal,function(response){
            if (response.success_is_ok){
                var data=response.data;
                if(data!=null) {
                    //设置kindEditorContent编辑器
                    KindEditor.html("#kindEditorContent", data.desc);
                    vm.details = data;
                }
            }
        });
    },
    methods:{
        openViewForm:function(imgPath){
            var tempObj= $('#viewMaterialModal').clone();
            tempObj.find('form').prop('id','ViewFormId');
            var tempHtml=tempObj.html();
            layer.open({
                title:'预览材料',
                content:tempHtml,
                btn: ['确定'],
                area:['600px','600px'],
                btn1: function(){
                    layer.closeAll();
                }
            });
            var tempVm = new Vue({
                el: "#ViewFormId",
                data:{imgPath:imgPath}
            });
        }
    }
});