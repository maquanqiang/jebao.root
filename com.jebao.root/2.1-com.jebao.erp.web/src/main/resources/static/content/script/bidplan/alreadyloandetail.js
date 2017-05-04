/**
 * Created by Lee on 2016/11/17.
 */

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
    searchObj: {},
    //标的
    plan: {},
    //
    riskDataList:[],
    investInfoList : [],
    incomeDetailList :[],
    total : 0,
    fundType : ['','本金','利息']
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj.indBpId = $("#bpId").val();
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=1000;
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
                $("#bpExpectExpireDate").val(dateStr)

                var dateEt = new Date(vm.plan.bpRepayTime);
                dateEt = dateEt.valueOf();
                dateEt = dateEt - 24 * 60 * 60 * 1000;
                var dateEtStr = new Date(dateEt).toFormatString("yyyy-MM-dd");
                $("#bpInterestEt").val(dateEtStr)
            }
        });
        $.get("/api/bidRiskData/getRiskDataListForPage", dataVal, function (response) {
            if (response.success_is_ok) {
                vm.riskDataList = response.data;
            }
        });
        $.get("/api/investInfo/list", dataVal, function (response) {
            if (response.success_is_ok) {
                vm.investInfoList = response.data;
            }
        });
        $.get("/api/incomeDetail/repaymentList",model.searchObj,function(response) {
            if (response.success_is_ok) {
                vm.incomeDetailList = response.data;
                if (response.count > 0) {
                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    //调用分页
                    laypage({
                        cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                        pages: pageCount, //总页数
                        curr: model.searchObj.pageSize + 1,
                        groups: 7, //连续显示分页数
                        jump: function (obj, first) { //触发分页后的回调
                            if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                console.log(obj.curr);
                                vm.searchObj.pageIndex = obj.curr - 1;
                                vm.search();
                            }
                        },
                        skin: '#3c8dbc'
                    });
                }
            }
        })
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
        }
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
        },
        //createIntentBtn:function(){
        //    $.get("/api/bidPlan/getLoanFundIntents",vm.plan,function(response){
        //        if (response.success_is_ok){
        //            vm.intentList = response.data;
        //            for(var i=0; i<vm.intentList.length; i++){
        //                vm.principalTotal +=vm.intentList[i].principal;
        //                vm.interestTotal += vm.intentList[i].interest;
        //            }
        //            vm.total = vm.principalTotal +vm.interestTotal;
        //        }
        //    });
        //},
        closeBtn:function(){
            window.location.href = "/bidplan/alreadyLoanList";
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