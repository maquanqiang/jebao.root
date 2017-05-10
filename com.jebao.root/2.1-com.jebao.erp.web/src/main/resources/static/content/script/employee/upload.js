/**
 * Created by Jack on 2016/11/25.
 */

//写在document ready里ajax提交就无效了，不解
$("#upfile").fileinput({
    uploadUrl: '/api/employee/upload', //上传地址
    uploadExtraData:{dir:"file"},//额外请求数据
    allowedFileExtensions: ["xls","xlsx"],
    maxFileSize: 4096,//kb
    minFileCount: 1,
    maxFileCount: 1,
    dropZoneEnabled:false,//不显示拖拽区域
});

var model = {
    theads:[],
    tbodies:[],
    filename:""
};
var vm = new Vue({
    el:"table.table",
    data:model
});
//上传成功
$("#upfile").on("fileuploaded", function (event, data, previewId, index) {
    if (data.response.success_is_ok){
        model.theads = data.response.data[0];
        model.tbodies=data.response.data.slice(1);
        model.filename=data.response.msg;
    }else{
        layer.alert(data.response.msg);
    }

});
////上传完成，不论对错
//$('#upfile').on('filebatchuploadcomplete', function (event, previewId, index) {
//
//});

//选择了文件后
$('#upfile').on('fileloaded', function(event, file, previewId, index, reader) {
    $(this).fileinput('upload');
    $(".fileinput-upload-button").off("click").on("click",function(){
        if (model.filename){
            $.post("/api/employee/uploadconfirm",{filename:model.filename},function(response){
                if (response.success_is_ok){
                    layer.alert(response.msg,function(){
                        window.location.href="/employee/index";
                    });
                }else{
                    layer.alert(response.msg);
                }
            });
        }else{
            layer.alert("上传失败");
        }
        return false;
    });
});

