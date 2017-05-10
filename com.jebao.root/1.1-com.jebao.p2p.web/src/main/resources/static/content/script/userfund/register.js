var model = {
    formSelector: "#defaultForm",
    form: {},
    banks: {},
    regions: [],
    cities:[],
    countries:[],
    error: {hasError: false, message: "提交错误信息显示的地方"}
};
var vm = new Vue({
    el: "#defaultForm",
    data: model,
    beforeCreate: function () {
        model.form = $(model.formSelector).serializeObject();
        model.form.bankCardNoFormat = "";
        $.get("/api/data/bankList", function (response) {
            if (response.success_is_ok) {
                model.banks = response.data;
            }
        });
        $.get("/api/data/regionList", function (response) {
            if (response.success_is_ok) {
                model.regions = response.data;
            }
        });
    },
    mounted: function () {
        this.initValidateForm();
        $('.bankCard input').keyup(function () {
            var value=$(this).val().replace(/\s/g,'').replace(/(\d{4})(?=\d)/g,"$1 ");
            $(this).val(value);
        });
    },
    watch: {
        "form.bankProvinceCode": function (val, oldVal) {
            for (var i=0;i<model.regions.length;i++){
                if(model.regions[i].code === val){
                    model.cities = model.regions[i].children;
                    break;
                }
            }
        },
        "form.bankCardNoFormat":function(val,oldVal){
            var bankCardNo = model.form.bankCardNoFormat.replace(/\s/g,'');
            model.form.bankCardNo = bankCardNo;
            if(bankCardNo.length === 16 || bankCardNo.length === 19){
                vm.validateBankCard();
            }
        }
    },
    methods: {
        initValidateForm: function () {
            return $('#defaultForm').bootstrapValidator({
                fields: {
                    realName: {
                        threshold: 2,
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
                            idCard: {
                                message: '请输入正确的身份证号'
                            }
                        }
                    },
                    bankCode: {
                        validators: {
                            notEmpty: {
                                message: '请选择银行'
                            }
                        }
                    },
                    bankProvinceCode: {
                        validators: {
                            greaterThan: {
                                value: 0,
                                inclusive: false,
                                message: '请选择开户行省市'
                            }
                        }
                    },
                    bankCityCode: {
                        validators: {
                            greaterThan: {
                                value: 0,
                                inclusive: false,
                                message: '请选择开户行区县'
                            }
                        }
                    },
                    bankCardNo: {
                        threshold:1,
                        validators: {
                            notEmpty: {
                                message: '请输入银行卡号'
                            },
                            callback: {
                                message: '请输入正确的储蓄卡号',
                                callback: function(value, validator) {
                                    var bankCardNo = value.replace(/\s/g,'');
                                    if (bankCardNo.length === 16 || bankCardNo.length === 19){
                                        return true;
                                    }
                                    return false;
                                }
                            }
                        }
                    },
                    payPassword: {
                        validators: {
                            notEmpty: {
                                message: '请输入支付密码'
                            },
                            regexp: {
                                regexp: /^(?![0-9]+$)(?![a-zA-Z]+$)[A-Za-z0-9_-]{8,20}$/,
                                message: '请设置8-20位密码（由字母、数字组成）'
                            }
                        }
                    },
                    payPasswordAgain: {
                        validators: {
                            notEmpty: {
                                message: '请输入支付确认密码'
                            },
                            identical: {
                                field: 'payPassword',
                                message: '两次密码不一致'
                            }
                        }
                    }

                }
            })
                .on('success.form.bv', function (e) {
                    e.preventDefault();

                    var $form = $(e.target);
                    var loadIndex = layer.load(2);
                    $.post($form.attr('action'), model.form, function (response) {
                        if (response.success_is_ok) {
                            window.location.href = "/userfund/registerSuccess";
                            return;
                        } else {
                            layer.close(loadIndex);
                            layer.alert(response.error);
                        }
                    }, "json");

                })
                .on('success.field.bv',function(e,data){
                    if (data.field == "bankCardNo" && data.result){
                        vm.getBankCardInfo();
                    }
                });
        },
        submit: function () {
            $(model.formSelector).submit();//必须使用jquery的submit
        },
        validateBankCard:function(){
            var bankCardNo = model.form.bankCardNo;
            $.get("/api/userfund/validateBankCard?bankCardNo="+bankCardNo,function(response){
                    if (response.success_is_ok) {
                        $("#defaultForm").data('bootstrapValidator').updateStatus("bankCardNo","VALID","callback");
                    }else{
                        $("#defaultForm").data('bootstrapValidator').updateStatus("bankCardNo","INVALID","callback");
                    }
            });
        },
        getBankCardInfo:function(){
            var bankCardNo = model.form.bankCardNo;
            if (bankCardNo.length < 16){
                return false;
            }
            $.ajax({
                url: "/api/userfund/getBankCardInfo?bankCardNo="+bankCardNo,
                timeout: 2000,
                data: '_input_charset=utf-8&cardNo=' + bankCardNo + '&cardBinCheck=true',
                success: function(response){
                    if (response.success_is_ok) {
                        var $div = $(response.data);
                        var $dls = $div.children("dl");
                        if ($dls.length === 0){
                            return false;
                        }
                        var $bankName = $dls.eq(2).find("dt").find("font").remove().end();
                        var bankName = $bankName.length>0?$bankName.text().trim():"";
                        var $regions = $dls.eq(1).find("dt").find("font").remove().end();
                        var regionsArray = $regions.length>0?$regions.text().split("-"):["",""];
                        console.log(regionsArray)
                        var province = regionsArray[0].trim();
                        var city = regionsArray.length>1?regionsArray[1].trim():"";

                        //银行
                        if (bankName){
                            for (var i=0;i<model.banks.length;i++){
                                if (model.banks[i].name.indexOf(bankName)>-1){
                                    model.form.bankCode = model.banks[i].code;
                                    break;
                                }
                            }
                        }

                        //省
                        if (province){
                            for (var i=0;i<model.regions.length;i++){
                                var provinceItem = model.regions[i];
                                if (provinceItem.name.indexOf(province)>-1){
                                    model.form.bankProvinceCode = provinceItem.code;
                                    //城市
                                    if (city){
                                        for (var j=0;j<provinceItem.children.length;j++){
                                            var cityItem = provinceItem.children[j];
                                            if (cityItem.name.indexOf(city)>-1){
                                                model.form.bankCityCode = cityItem.code;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }


                    }
                },
                error: function(xhr, status, error){
                    console.log("getBankCardInfo error.")
                }
            });

        }
    }
});
