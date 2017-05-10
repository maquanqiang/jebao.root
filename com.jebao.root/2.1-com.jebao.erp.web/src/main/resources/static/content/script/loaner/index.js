/**
 * Created by Administrator on 2016/11/22.
 */
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //借款人列表
    loaners: [],
    //弹窗vm实例
    openFormVm: {}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: "#content",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj = $("#search_form").serializeObject();
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {
            if (typeof event !== "undefined") { //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex = 0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            $.get("/api/loaner/list", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.loaners = response.data;
                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount > 0) {
                        // var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    //console.log(obj.curr);
                                    vm.searchObj.pageIndex = obj.curr - 1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
                $("#btnSearch").removeClass("disabled");//解除禁用
            });
        },
        //绑定表单验证
        bindFormValidate: function ($form) {
            $form.bootstrapValidator({
                fields: {
                    phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^1[3-8]\d{9}$/,
                                message: '请输入正确的手机号码'
                            }
                        }
                    },
                    monthlySalary: {
                        validators: {
                            numeric: {
                                message: '请输入数字'
                            }
                        }
                    }
                }
            });
        },
        post: function ($form) {
            var $button = $form.parent().parent().children(".layui-layer-btn").children("a:first");
            $button.addClass("btn disabled");
            var layerIndex = layer.load(2);
            var submitModel = $form.serializeObject();
            $.post("/api/loaner/post", submitModel, function (response) {
                if (response.success_is_ok) {
                    layer.msg(response.msg);
                    vm.search();
                    layer.closeAll();
                } else {
                    vm.openFormVm.error.hide = false;
                    vm.openFormVm.error.message = response.msg;
                }
                layer.close(layerIndex);
                $button.removeClass("disabled");
            });
        },
        createOpenVm: function (form, id) {
            var openVmModel = {
                form: form,
                formData: $(form).serializeObject(),
                error: {hide: true, message: ""},
                userInfo: {}
            };
            openVmModel.formData.id = id;
            vm.openFormVm = new Vue({
                el: openVmModel.form,
                data: openVmModel,
                beforeCreate: function () {
                    var id = openVmModel.formData.id;
                    //填充窗体数据
                    if (id > 0) {
                        for (var i = 0; i < vm.loaners.length; i++) {
                            var item = vm.loaners[i];
                            if (item.id == id) {
                                openVmModel.formData.phone = item.phone;
                                openVmModel.formData.nickName = item.nickName;
                                openVmModel.formData.trueName = item.trueName;
                                openVmModel.formData.idNumber = item.idNumber;
                                openVmModel.formData.sex = item.sex;
                                openVmModel.formData.age = item.age;
                                openVmModel.formData.email = item.email;
                                openVmModel.formData.registerTime = item.registerTime;
                                openVmModel.formData.lastLoginTime = item.lastLoginTime;
                                openVmModel.formData.maritalStatus = item.maritalStatus;
                                openVmModel.formData.homeAdd = item.homeAdd;
                                openVmModel.formData.hkadr = item.hkadr;
                                openVmModel.formData.politicsStatus = item.politicsStatus;
                                openVmModel.formData.education = item.education;
                                openVmModel.formData.creditStatus = item.creditStatus;
                                openVmModel.formData.monthlySalary = item.monthlySalary;
                                openVmModel.formData.workCity = item.workCity;
                                openVmModel.formData.ishaveHouse = item.ishaveHouse;
                                openVmModel.formData.ishaveCar = item.ishaveCar;
                            }
                        }
                    }
                },
                created: function () {

                },
                mounted: function () {
                    var $form = $(openVmModel.form);
                    vm.bindFormValidate($form);
                },
                methods: {
                    doImport: function () {
                        var $form = $(openVmModel.form);
                        $form.find(".import-btn").addClass("disabled");//禁用按钮
                        var phone = $form.find(".import-phone").val();
                        //console.log(phone);
                        $.get("/api/loaner/doImport", {phone: phone}, function (response) {
                            if (response.success_is_ok) {
                                vm.openFormVm.error.hide = true;
                                openVmModel.userInfo = response.data;
                            } else {
                                // console.log(response.error);
                                vm.openFormVm.error.hide = false;
                                vm.openFormVm.error.message = response.error;
                            }
                            $form.find(".import-btn").removeClass("disabled");//解除禁用
                        });
                    }
                }
            });
        },
        openPostForm: function (id) {
            if (isNaN(id)) {
                id = 0;
            }
            var tempObj = $('#addInforModal').clone();
            tempObj.find('form').prop('id', 'insertFormId');
            var tempHtml = tempObj.html();
            layer.open({
                title: '添加个人借款用户',
                content: tempHtml,
                btn: ['保存', '重置'],
                area: ['800px'],
                btn1: function () {
                    var $form = $("#insertFormId");
                    var bootstrapValidator = $form.data('bootstrapValidator').validate();
                    if (!bootstrapValidator.isValid()) {
                        return false;
                    } else {
                        vm.post($form);
                    }
                },
                btn2: function () {
                    var $form = $("#insertFormId");
                    var bootstrapValidator = $form.data('bootstrapValidator');
                    if (typeof bootstrapValidator !== "undefined") {
                        bootstrapValidator.resetForm();
                    }
                    $form[0].reset();
                    vm.openFormVm.error.hide = true;
                    return false;
                }
            });
            vm.createOpenVm("#insertFormId", id);
        },
        openEditForm: function (id) {
            if (isNaN(id)) {
                id = 0;
            }
            var tempObj = $('#modInforInfModal').clone();
            tempObj.find('form').prop('id', 'ModifyFormId');
            var tempHtml = tempObj.html();
            layer.open({
                title: '修改个人借款用户',
                content: tempHtml,
                btn: ['保存', '取消'],
                area: ['800px'],
                btn1: function () {
                    var $form = $("#ModifyFormId");
                    var bootstrapValidator = $form.data('bootstrapValidator').validate();
                    if (!bootstrapValidator.isValid()) {
                        return false;
                    } else {
                        vm.post($form);
                    }
                },
                btn2: function () {
                    /*                    var $form =$("#ModifyFormId");
                     var bootstrapValidator =$form.data('bootstrapValidator');
                     if(typeof bootstrapValidator !== "undefined"){
                     bootstrapValidator.resetForm();
                     }
                     $form[0].reset();*/
                    layer.closeAll();
                }
            });
            vm.createOpenVm("#ModifyFormId", id);
        },
        openDeleteWin: function (id) {
            layer.confirm('确定要删除吗?', {icon: 3, title: '询问'}, function (index) {
                layer.load(2);
                $.post("/api/loaner/delete", {id: id}, function (response) {
                    layer.closeAll();
                    if (response.success_is_ok) {
                        layer.msg(response.msg);
                        vm.search();
                    } else {
                        layer.alert(response.msg);
                    }
                });
            });
        }
    }
});