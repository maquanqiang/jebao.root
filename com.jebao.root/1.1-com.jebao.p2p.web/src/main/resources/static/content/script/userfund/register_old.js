/**
 * Created by Jack on 2016/12/13.
 */
var model={
    formSelector:"#defaultForm",
    form:{},
};
var vm = new Vue({
    el:"#defaultForm",
    data:model,
    beforeCreate:function(){
        model.form = $(model.formSelector).serializeObject();
    },
    mounted:function(){
        var $form = this.initValidateForm();
        var $validator = $form.data('bootstrapValidator');
        var real_name_error = $("#real_name_error").val();
        if (real_name_error){
            $validator.updateStatus("realName", "INVALID", "notEmpty");
        }
        var id_card_error = $("#id_card_error").val();
        if (id_card_error){
            $validator.updateStatus("idCard", "INVALID", "idCard");
        }
    },
    methods:{
        initValidateForm: function () {
            return $('#defaultForm').bootstrapValidator({
                live:"submitted",
                fields: {
                    realName: {
                        threshold:2,
                        validators: {
                            notEmpty: {
                                message: '请输入真实姓名'
                            },
                            regexp: {
                                regexp: /^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$/,
                                message: '真实姓名只能是中文'
                            }
                        }
                    },
                    idCard: {
                        validators: {
                            notEmpty: {
                                message: '请输入身份证号'
                            },
                            idCard:{
                                message: '请输入正确的身份证号'
                            }
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                var $form = $(e.target);
                $form.attr("action",common.apiOrigin + $form.attr("action"));
                $form[0].submit();
            });
        },
        submit: function () {
            $(model.formSelector).submit();//必须使用jquery的submit
        }
    }
});