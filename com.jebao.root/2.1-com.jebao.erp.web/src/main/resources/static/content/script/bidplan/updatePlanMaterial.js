/**
 * Created by Jack on 2016/11/18.
 */
$(function () {

    $(".select2").select2();
});
//Vue实例
//Model
var model = {
    //查询条件
    search: {},
    //标的
    plan: {},
    //台账列表
    intentList : [],
    //
    riskDataList:[],
    principalTotal:0,
    interestTotal : 0,
    total : 0,
    bpExpectExpireDate:'',
    selected : '',
    projList: [],
    projectTemp:{}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
    },
    //初始化远程数据
    created:function(){
        var val = $("#bpId").val();
        var dataVal = {
            bpId : val
        }
        $.get("/api/bidPlan/getBidPlanById",dataVal,function(response){
            if (response.success_is_ok){
                var data=response.data;
                vm.plan=data;
                var date = new Date(vm.plan.bpExpectRepayDate);
                date = date.valueOf();
                date = date - 24 * 60 * 60 * 1000;
                var dateStr = new Date(date).toFormatString("yyyy-MM-dd");
                vm.bpExpectExpireDate = dateStr;
                KindEditor.html("#kindEditorContent", data.bpDesc);
            }else{
                layer.msg(response.error);
            }
        });
        var riskPara = {
            page : 0,
            rows : 100,
            bpId : val
        }
        $.get("/api/bidRiskData/getRiskDataListForPage", riskPara, function (response) {
            if (response.success_is_ok) {
                vm.riskDataList = response.data;
            }
        });
        var projParam = {
            pageIndex : 0,
            pageSize : 100,
            bpLoanerId:$("#bpLoanerId").val()
        }
        $.get("/api/bidPlan/getProjList",projParam,function(response){
            if (response.success_is_ok){
                vm.projList = response.data;
            }
        });
    },
    //watch可以监视数据变动，针对相应的数据设置监视函数即可
    watch: {
        //
        "plan.bpType" : function (newVal,oldVal) {
            if(newVal == 3){
                $("#bpUpRateDiv").show()
            }else{
                $("#bpUpRateDiv").hide()
                $("#bpUpRate").val(0)
            }
        },
        "selected": function (newVal,oldVal) {
            var optionVal = $("#rcptId").val();
            var rcptId = {
                rcptId : optionVal
            }
            $.get("/api/bidPlan/getProjectTempById",rcptId,function(response){
                if (response.success_is_ok){
                    vm.projectTemp = response.data;
                    vm.plan.bpBorrowDesc = vm.projectTemp.bpBorrowDesc;
                    vm.plan.bpFundsPurpose = vm.projectTemp.bpFundsPurpose
                    vm.plan.bpRepayingSource = vm.projectTemp.bpRepayingSource
                    vm.plan.bpMortgageInfo = vm.projectTemp.bpMortgageInfo
                    vm.plan.bpPersonalCredit = vm.projectTemp.bpPersonalCredit
                    vm.plan.bpRiskOpinion = vm.projectTemp.bpRiskOpinion
                    KindEditor.html("#kindEditorContent", vm.plan.bpRcptDesc);
                }
            });
        }
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
        },
        createIntentBtn:function(){
            vm.intentList = [];
            vm.principalTotal = 0;
            vm.interestTotal = 0;
            vm.total = 0;
            $.post("/api/bidPlan/getLoanFundIntents",vm.plan,function(response){
                if (response.success_is_ok){
                    vm.intentList = response.data;
                    for(var i=0; i<vm.intentList.length; i++){
                        vm.principalTotal +=vm.intentList[i].principal;
                        vm.interestTotal += vm.intentList[i].interest;
                    }
                    vm.total = vm.principalTotal +vm.interestTotal;
                    vm.principalTotal=toDecimal2(vm.principalTotal);
                    vm.interestTotal=toDecimal2(vm.interestTotal);
                    vm.total=toDecimal2(vm.total);
                }
            });
        },
        cancelBtn : function(){
            window.location.href = "/bidplan/noLendingList";
        },
        submitBtn:function() {
            var formValue = $("#defaultForm").serializeObject();
            $.post("/api/bidPlan/updatePlanMaterial",formValue,function(response){
                if (response.success_is_ok){
                    layer.alert("修改成功")
                    window.location.href = "/bidplan/noLendingList";
                }else{
                    layer.alert("修改失败")
                }
            });
        },
        //认证图片材料
        openDeleteWin:function(id){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/bidRiskData/removeRiskData",{id:id},function(response){
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                        $.get("/api/bidRiskData/getRiskDataListForPage", {bpId: $("#bpId").val()}, function (response) {
                            if (response.success_is_ok) {
                                vm.riskDataList = response.data;
                            }
                        });
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
                    $.get("/api/bidRiskData/getRiskDataListForPage", {bpId:submitModel.brdBpId}, function (response) {
                        if (response.success_is_ok) {
                            vm.riskDataList = response.data;
                        }
                    });
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
                area:['50%','50%'],
                btn1: function(){
                    layer.closeAll();
                }
            });
            vm.createOpenVm("#ViewFormId",id);
        }
    }
});
//
//$("#bpExpectLoanDateId").change(function(){
//    vm.plan.bpExpectLoanDate=$(this).val();
//});
////
//$("#bpExpectRepayDateId").change(function(){
//    vm.plan.bpExpectRepayDate=$(this).val();
//});


function endTime(startTime, d){
    startTime = startTime.replace(/-/g,"/");
    var date = new Date(startTime);
    date = date.valueOf();
    date = date + d * 60 * 60 * 1000;
    var nd = new Date(date);
    var time1 = nd.toFormatString("yyyy-MM-dd HH:mm:ss");
    return time1;
}

function repayDate(loanDate, d, cycle){
    if(cycle!="" && loanDate != "" && d != ""){
        loanDate = loanDate.replace(/-/g,"/");
        var date = new Date(loanDate);
        if(cycle==1){   //天
            date.setDate(date.getDate() + d*1);
        }else if(cycle==2){ //月
            date.setMonth(date.getMonth() + d*1);
        }else if(cycle == 3){   //季
            date.setMonth(date.getMonth() + (d*3));
        }else if(cycle ==4 ){   //年
            date.setFullYear(date.getFullYear() + d*1);
        }
        return date.toFormatString("yyyy-MM-dd");
    }else{
        return null;
    }
}

/*开始时间选择*/
laydate({
    elem:'#bpStartTime',
    istime: true,
    istoday : false,
    format: 'YYYY-MM-DD hh:mm:ss',
    choose : function(datas) {
        var bpOpenTime = $("#bpOpenTime").val();
        if(bpOpenTime!=null && bpOpenTime != ""){
            var time = endTime(datas,bpOpenTime);
            $("#bpEndTime").val(time)
        }
    }
});

laydate({
    elem:'#bpExpectLoanDate',
    istime: false,
    format: 'YYYY-MM-DD',
    istoday : false,
    choose : function(datas){
        var d = $("#bpPeriodsDisplay").val();
        var cycle = $("#bpCycleType").val();
        var date = repayDate(datas, d, cycle);
        $("#bpExpectRepayDate").val(date);
        var date = new Date(date);
        date = date.valueOf()
        date = date - 24 * 60 * 60 * 1000;
        date = new Date(date).toFormatString("yyyy-MM-dd")
        $("#bpExpectExpireDate").val(date);
    }
});

