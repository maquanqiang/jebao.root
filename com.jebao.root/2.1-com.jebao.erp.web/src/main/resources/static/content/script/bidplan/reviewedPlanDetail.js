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
    search: {},
    //标的
    plan: {},
    //台账列表
    intentList : [],
    //
    riskDataList:[],
    principalTotal : 0,
    interestTotal : 0,
    total : 0

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
                $("#bpExpectExpireDate").val(dateStr)

                KindEditor.html("#kindEditorContent", data.bpDesc);
            }
        });
        $.get("/api/bidRiskData/getRiskDataListForPage", dataVal, function (response) {
            if (response.success_is_ok) {
                vm.riskDataList = response.data;
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
        reviewBtn : function(state){
            var val = $("#bpId").val();
            var formValue={};
            formValue.bpId = val;
            formValue.status = state;
            if(state==1){
                layer.prompt({
                    formType: 2,
                    value: '',
                    title: "请写明拒绝原因",
                    area: ['300px', '170px'] //自定义文本域宽高
                }, function(value, index, elem){
                    formValue.remark = value;
                    layer.close(index);
                    $.post("/api/bidPlan/reviewedPlan",formValue,function(response){
                        if (response.success_is_ok){
                            layer.msg("已打回");
                            vm.reviewedPlanList();
                        }else{
                            layer.alert(response.error);
                        }
                    });
                });
            }else if(state==2){
                $.post("/api/bidPlan/reviewedPlan",formValue,function(response){
                    if (response.success_is_ok){
                        layer.msg("开标成功");
                        vm.reviewedPlanList();
                    }else{
                        layer.alert(response.error);
                    }
                });
            }
        },
        reviewedPlanList : function(){
            window.location.href="/bidplan/reviewedPlanList";
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