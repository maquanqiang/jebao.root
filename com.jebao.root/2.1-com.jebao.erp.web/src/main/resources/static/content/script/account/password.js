/**
 * Created by Jack on 2016/11/25.
 */
var model = {
    form:"#password_form",
    submitObj:$("#password_form").serializeObject(),
    error:{hide:true,message:""}
};

var vm = new Vue({
    el:".content",
    data:model,
    mounted:function(){
        this.bindFormValidate($(this.form));
    },
    methods:{
        submit:function(){
            var bootstrapValidator = $(vm.form).data('bootstrapValidator').validate();
            if(!bootstrapValidator.isValid()){
                return false;
            }
            layer.load(2);
            $.post("/api/account/password",vm.submitObj,function(response){
                if (response.success_is_ok){
                    layer.msg(response.msg, function () {
                        window.location.href="/account/logout";
                    });
                }else{
                    vm.error.hide=false;
                    vm.error.message=response.msg;
                }
                layer.closeAll();
            });
        },
        //绑定表单验证
        bindFormValidate:function($form){
            $form.bootstrapValidator({
                fields: {
                    currentPassword: {
                        validators: {
                            notEmpty: {
                                message: '请输入当前密码'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '密码6-20位'
                            },
                            different: {
                                field: 'newPassword',
                                message: '新旧密码不能相同'
                            }
                        }
                    },
                    newPassword: {
                        validators: {
                            notEmpty: {
                                message: '请输入新密码'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '密码6-20位'
                            },
                            different: {
                                field: 'currentPassword',
                                message: '新旧密码不能相同'
                            },
                            identical: {
                                field: 'newPassword2',
                                message: '两次密码输入不一致'
                            }
                        }
                    },
                    newPassword2: {
                        validators: {
                            notEmpty: {
                                message: '请输入确认密码'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '密码6-20位'
                            },
                            identical: {
                                field: 'newPassword',
                                message: '两次密码输入不一致'
                            }
                        }
                    },
                }
            });
        }
    }
});