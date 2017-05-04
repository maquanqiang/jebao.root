/**
 * Created by Administrator on 2017/1/17.
 */
/**
 * Created by Administrator on 2016/12/14.
 */
//Vue实例
//Model
var model = {
    //银行卡信息
    bankInfo: {},
    inTime: "",
    hasFundAccount: true
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".account-content",
    data: model,
    beforeCreate: function () {
        var now = new Date();
        var tomorrow = new Date(now.setDate(now.getDate() + 1));
        model.inTime = tomorrow.toFormatString('yyyy-MM-dd');
    },
    //初始化远程数据
    created: function () {
        this.init();
        this.change();
    },
    mounted: function () {
        this.initValidateForm("#quickPay_form");
        this.initValidateForm("#fastRecharge_form");
        this.initValidateForm("#withdrawDeposit_form");
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        change: function () {
            var index = 0;
            $(".account-rex-tit h4").removeClass('active');
            $(".account-rex-item").removeClass('active');
            $(".account-rex-tit h4").eq(index).addClass('active');
            $('.account-rex-item').eq(index).addClass('active');
        },
        init: function () {
            $.get("/api/user/getuser", function (response) {
                if (response.success_is_ok) {
                    var data = response.data;
                    if (data != null) {
                        vm.bankInfo = data;
                        vm.hasFundAccount = data.hasFundAccount;
                    }
                }
            });
        },
        initValidateForm: function (form) {
            return $(form).bootstrapValidator({
                fields: {
                    money: {
                        validators: {
                            notEmpty: {
                                message: '金额不能为空'
                            }
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                e.preventDefault();//阻止默认事件提交

                var $form = $(e.target);
                $form.attr("action", common.apiOrigin + $form.attr("action"));
                $form[0].submit();
            })
        },
        submit: function (form) {
            $(form).submit();
        }
    }
});
