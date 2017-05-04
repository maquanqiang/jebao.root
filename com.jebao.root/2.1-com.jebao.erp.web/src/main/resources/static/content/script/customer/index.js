/**
 * Created by Jack on 2016/11/18.
 */
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //弹窗vm实例
    openFormVm:{},
    customers:[]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: "#content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj=$("#search_form").serializeObject();
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=10;
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    mounted:function(){
        //在 el 被替换后，做页面元素变动的操作
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            var loadIndex = layer.load(2);
            $.get("/api/customer/getCustomerList",model.searchObj,function(response){
                if (response.success_is_ok){
                    vm.custmers=response.data;
                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount>0){
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr:model.searchObj.pageIndex+1,//设置当前页
                            groups: 7, //连续显示分页数
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex=obj.curr -1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
                $("#btnSearch").removeClass("disabled");//解除禁用
                layer.close(loadIndex);
            });
        },
        findParentDepartmentFunc:function(teamId){
        },
        //绑定表单验证
        bindFormValidate:function($form){
            $form.bootstrapValidator({
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '姓名不能为空'
                            },
                            stringLength: {
                                min: 2,
                                max: 5,
                                message: '姓名长度必须在2到5位之间'
                            }
                        }
                    },
                    mobile: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^1[3-8]\d{9}$/,
                                message: '请输入正确的手机号码'
                            }
                        }
                    }
                }
            })
        },
        post:function($form){
            var $button =$form.parent().parent().children(".layui-layer-btn").children("a:first");
            $button.addClass("btn disabled");
            var layerIndex = layer.load(2);
            var submitModel = $form.serializeObject();
            $.post("/api/employee/post",submitModel,function(response){
                if (response.success_is_ok){
                    layer.msg(response.msg);
                    vm.search();
                    layer.closeAll();
                }else{
                    vm.openFormVm.error.hide=false;
                    vm.openFormVm.error.message=response.msg;
                }
                layer.close(layerIndex);
                $button.removeClass("disabled");
            });
        },
        createOpenVm:function(form,empId){
            var openVmModel ={
                form:form,
                ranks:vm.ranks,
                departments:vm.departments,
                formData:$(form).serializeObject(),
                noTeamRankId:1, //不显示团队select的职级id
                error:{hide:true,message:""}
            };
            openVmModel.formData.empId=empId;
            vm.openFormVm = new Vue({
                el: openVmModel.form,
                data: openVmModel,
                computed:{
                    //根据选择部门动态变化所属团队
                    partOfTeams:function(){
                        var departmentId = this.formData.departmentId;
                        var localPartOfTeams = new Array();
                        //递归查找子级
                        var recursiveFunc = function(teamId){
                            for(var i=0;i<vm.teams.length;i++){
                                var item = vm.teams[i];
                                if(item.parentId === teamId){
                                    localPartOfTeams.push(item);
                                    recursiveFunc(item.id);//继续寻找子级的子级
                                }
                            }
                        }
                        if(departmentId>0){
                            recursiveFunc(departmentId);
                        }
                        if(openVmModel.formData.empId==0){
                            this.formData.teamId=0;//回归 请选择
                        }
                        return localPartOfTeams;
                    },
                    isEdit:function(){
                        return this.formData.empId>0;
                    }
                },
                beforeCreate:function(){
                    var empId =openVmModel.formData.empId;
                    //填充窗体数据
                    if (empId>0){
                        for (var i=0;i<vm.employees.length;i++){
                            var item =vm.employees[i];
                            if (item.id==empId){
                                openVmModel.formData.name = item.name;
                                openVmModel.formData.mobile = item.mobile;
                                openVmModel.formData.cardNo = item.cardNo;
                                openVmModel.formData.rankId = item.rankId;
                                openVmModel.formData.departmentId = vm.findParentDepartmentFunc(item.teamId).id;
                                openVmModel.formData.teamId = item.teamId || 0;
                                openVmModel.formData.loginStatus = item.loginStatus;
                            }
                        }
                    }
                },
                created:function(){

                },
                mounted:function(){
                    var $form = $(openVmModel.form);
                    vm.bindFormValidate($form);
                }
            });
        },
        openPostForm:function(empId){
            if (isNaN(empId)){empId=0;}
            var tempObj= $('#addInforModal').clone();
            tempObj.find('form').prop('id','insertFormId');
            var tempHtml=tempObj.html();
            layer.open({
                title:empId>0?'修改员工信息':'添加员工',
                content:tempHtml,
                btn: ['保存', '重置'],
                area:['500px'],
                btn1: function(){
                    var $form = $("#insertFormId");
                    var bootstrapValidator = $form.data('bootstrapValidator').validate();
                    if(!bootstrapValidator.isValid()){
                        return false;
                    }else{
                        vm.post($form);
                    }
                },
                btn2: function(){
                    var $form =$("#insertFormId");
                    var bootstrapValidator =$form.data('bootstrapValidator');
                    if(typeof bootstrapValidator !== "undefined"){
                        bootstrapValidator.resetForm();
                    }
                    if (vm.openFormVm.formData.empId==0){
                        $form[0].reset();
                    }
                    return false;
                }
            });
            vm.createOpenVm("#insertFormId",empId);

        },
        openEditForm:function(empId){
            vm.openPostForm(empId);
        },
        openDeleteWin:function(empId){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/employee/delete",{empId:empId},function(response){
                    layer.closeAll();
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                        vm.search();
                    }else{
                        layer.alert(response.msg);
                    }
                });

            });
        }
    }
});
