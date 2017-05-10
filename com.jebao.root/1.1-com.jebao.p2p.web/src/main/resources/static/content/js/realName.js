/**
 * Created by lihui on 2016/10/21.
 */
$(document).ready(function() {
    initValidateForm();
    $(".authenticate").click(function(){
        var bootstrapValidator = $("#defaultForm").data('bootstrapValidator').validate();
        if(!bootstrapValidator.isValid()){return;}
        //is ok
        //todo
        alert(bootstrapValidator.isValid());
    });
});
//表单登录验证封装
function initValidateForm(){
    //$.fn.bootstrapValidator.DEFAULT_OPTIONS.group="div";
    $('#defaultForm').bootstrapValidator({
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    },
                    regexp: {
                        regexp: /^([\u4e00-\u9fa5]){2,7}$/,
                        message: '真实姓名只能是中文'
                    },
                    stringLength: {
                        min:2,
                        max: 7,
                        message: '真实姓名长度必须在2到7位之间'
                    }
                }
            },
            ID: {
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    regexp: {
                        regexp:/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/,
                        message: '身份证格式有误'
                    }
                }
            }
        }
        // group:"div"
    });
}

