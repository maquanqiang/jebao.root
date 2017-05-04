/**
 * Created by Administrator on 2016/11/28.
 */
var model = {
    //查询条件
    searchObj: {},
    //列表
    riskDataList: [],
    openFormVm:{}
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj.page = 0;
        model.searchObj.rows = 10;
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function(){
            var bpId = $("#bpId").val();
            model.searchObj.bpId = bpId;
            $.get("/api/bidRiskData/getRiskDataListForPage", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.riskDataList = response.data;
                    console.log(response.count);
                    if (response.count > 0) {
                        var pageCount = Math.ceil(response.count / model.searchObj.rows);
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr: model.searchObj.page + 1,
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    console.log(obj.curr);
                                    vm.searchObj.page = obj.curr - 1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
            })
        },
        openDeleteWin:function(id){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/bidRiskData/removeRiskData",{id:id},function(response){
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                        vm.search();
                    }else{
                        layer.alert(response.msg);
                    }
                });
                layer.closeAll();
            });
        },
        //绑定表单验证
        bindFormValidate:function($form){
            $form.bootstrapValidator({
                fields: {
                    name:{
                        validators: {
                            notEmpty: {
                                message: '材料名称不能为空'
                            }
                        }
                    },
                    remark:{
                        validators: {
                            notEmpty: {
                                message: '备注不能为空'
                            }
                        }
                    },
                    path:{
                        validators: {
                            notEmpty: {
                                message: '图片未上传成功'
                            }
                        }
                    }
                }
            });
        },
        add: function($form){
            var $button =$form.parent().parent().children(".layui-layer-btn").children("a:first");
            $button.addClass("btn disabled");
            layer.load(2);
            var submitModel = $form.serializeObject();
            $.post("/api/bidRiskData/addRiskData",submitModel,function(response){
                if (response.success_is_ok){
                    layer.msg(response.msg);
                    vm.search();
                }else{
                    vm.openFormVm.error.hide=false;
                    vm.openFormVm.error.message=response.msg;
                }
                $button.removeClass("disabled");
                layer.closeAll();
            });
        },
        createOpenVm:function(form,id){
            var openVmModel ={
                form:form,
                formData:$(form).serializeObject(),
                error:{hide:true,message:""}
            };
            openVmModel.formData.id=id;
            vm.openFormVm = new Vue({
                el: openVmModel.form,
                data: openVmModel,
                beforeCreate:function(){
                    var id =openVmModel.formData.id;
                    /*                    console.log("el:"+openVmModel.form);
                     console.log("createOpenVm-id:"+id);*/
                    //填充窗体数据
                    if (id>0){
                        for (var i=0;i<vm.riskDataList.length;i++){
                            var item =vm.riskDataList[i];
                            if (item.brdId==id){
                                //openVmModel.formData.no = item.no;
                                openVmModel.formData.name = item.brdName;
                                //openVmModel.formData.idNumber = item.idNumber;
                                openVmModel.formData.remark = item.brdRemark;
                                openVmModel.formData.path = item.brdPath;
                                //openVmModel.formData.url = item.url;
                                //openVmModel.formData.createTime = item.createTime;
                            }
                        }
                    }
                },
                created:function(){

                },
                mounted:function(){
                    var $form = $(openVmModel.form);
                    vm.bindFormValidate($form);
                    $form.find(".btn-fileupload").wrap("<form id='_myUpload_' action='/filePlugin/uploadFile?dir=image'method='post' enctype='multipart/form-data'></form>");
                },
                methods: {
                    fileupload: function(){
                        var fileUploadUrl = $(openVmModel.form).find(".uploadFileUrl");
                        // var fileName = $(openVmModel.form).find(".btn-fileupload").val();
                        $("#_myUpload_").ajaxSubmit({
                            dataType:  'json', //数据格式为json
                            success:function(data){
                                if(data)
                                {
                                    if(data.error==0)
                                    {
                                        /*                                        console.log("data.url:"+data.url);
                                         console.log("fileName:"+fileName);*/
                                        //  alert(data.url);
                                        fileUploadUrl.val(data.url);
                                        return;
                                    }
                                    alert(data.message);
                                    //console.log("message:"+data.message);
                                    return;
                                }
                                alert("--上传失败---");
                                //console.log("上传失败");
                                return;
                            },
                            error:function(xhr){
                                // console.log("error:"+fileUploadUrl.html());
                                //  console.log("error:"+xhr.responseText);
                                alert(fileUploadUrl.html());
                                alert(xhr.responseText);
                            }
                        });
                    }
                }
            });
        },
        openAddForm:function(id){
            if (isNaN(id)){id=0;}
            var tempObj= $('#addMaterialModal').clone();
            tempObj.find('form').prop('id','insertFormId');
            var tempHtml=tempObj.html();
            layer.open({
                title:'添加材料',
                content:tempHtml,
                btn: ['添加', '取消'],
                area:['500px'],
                btn1: function(){
                    var $form = $("#insertFormId");
                    var bootstrapValidator = $form.data('bootstrapValidator').validate();
                    if(!bootstrapValidator.isValid()){
                        return false;
                    }else{
                        vm.add($form);
                    }
                },
                btn2: function(){
                    layer.closeAll();
                    return false;
                }
            });
            vm.createOpenVm("#insertFormId",id);
        },
        openViewForm:function(id){
            //console.log(id);
            if (isNaN(id)){id=0;}
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
            vm.createOpenVm("#ViewFormId",id);
        }
    }
});