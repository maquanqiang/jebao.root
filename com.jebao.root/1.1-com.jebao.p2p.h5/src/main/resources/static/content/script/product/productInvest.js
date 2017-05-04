/**
 * Created by wenyq on 2017/2/25.
 */

    $(function () {
        //当文本框输入金额的时候
        $('.spinner input').on('propertychange input', function () {
//            $('.spinner-btn button').removeClass('btn-disable');
            var money = parseInt($(this).val() * 1);
            $('.spinner strong').html(money);
            if (money >= model.product.bpStartMoney && money <= model.product.bpTopMoney) {
                var form = {
                    bpRate: model.product.bpRate,
                    bpExpectLoanDate: model.product.bpExpectLoanDate,
                    bpExpectRepayDate: model.product.bpExpectRepayDate,
                    investMoney: $(this).val()
                }
                $.post("/api/product/expectRevenue", form, function (response) {
                    if (response.success_is_ok) {
                        $('.spinner i').html(response.data.expectRevenue);
                    } else {
                        $('.spinner i').html(0);
                        layer.tips("输入金额有误", '#investMoney', {
                            tips: [1, '#0FA6D8']
                        });
                    }
                });
            } else {
                $('.spinner i').html(0);
            }
            //红包使用


            $.each( model.voucher, function(index, content)
            {
                //alert( "the man's no. is: " + index + ",and " + content.name + " is learning " + content.lang );
                if(content.vMinPrice<=money)
                {
                    $("#voucherp").html(content.vAmount+"元");
                    return false;
                }
                else
                {
                    $("#voucherp").html("0元");
                }

            });

        });

    });


//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    product: {},
    loanerInfo : {},
    statistics : {},
    voucher:{},
    cycleType:["","天","个月","季度","年"],
    bpInterestPayTypeArr : ["","一次性还本付息","先息后本，按期付息"],
    bpStatusArr : ["","","立即投资","满标","募集结束","","","还款中","","","已完成"],
    sex:['','男','女'],
    fundType : ['','本金','利息'],
    repayStatus : ['','未还款','已还款'],
    riskDataList : [],
    investInfoList : [],
    incomeDetailList : [],
    flag : false,
    bpMortgageInfo:''
};


// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".wrap",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {
            var form = {
                bpId:$("#bpId").val()
            }
            //product
            $.post($("#defaultForm").attr("action"), form, function (response) {
                if (response.success_is_ok) {
                    vm.product = response.data;

                    sta_str=(vm.product.bpStartTime).replace(/-/g,"/");
                    end_str=(vm.product.bpEndTime).replace(/-/g,"/");//得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss。
                    var end_str=new Date(end_str);//将字符串转化为时间
                    var st_str = new Date(sta_str);
                    var now = new Date();
                    if(st_str <= now){
                        vm.flag = true;
                    }
                    //statistics 账户可用余额
                    $.get("/api/invest/statistics", function (response) {
                        if (response.success_is_ok) {
                            vm.statistics = response.data;
                        }
                    });
                    //红包
                    model.searchObj.vMinCycle=vm.product.bpMonthTerm;
                    $.get("/api/voucher/getByMinWhereAll",model.searchObj, function (response) {
                        if (response.success_is_ok) {
                            vm.voucher = response.data;
                        }
                    });
                }else{
                    layer.msg("标的不存在");
                }
            });



        },
        investBtn: function () {
            var flag = true;
            var investMoney = $("#investMoney").val().trim() * 1;
            if(investMoney > 0){
                if(investMoney > vm.product.bpTopMoney){
                    layer.tips("投资金额大于投资上限"+vm.product.bpTopMoney+"元", '#investMoney', {
                        tips: [1, '#0FA6D8'] //还可配置颜色
                    });
                    flag = false;
                }else if(investMoney > vm.product.bpSurplusMoney){
                    layer.tips("投资金额大于标的剩余金额"+vm.product.bpSurplusMoney+"元", '#investMoney', {
                        tips: [1, '#0FA6D8'] //还可配置颜色
                    });
                    flag = false;
                }else if(investMoney > vm.statistics.balance){
                    layer.tips("投资金额大于您账户的剩余金额"+vm.statistics.balance+"元，请先进行充值", '#investMoney', {
                        tips: [1, '#0FA6D8'] //还可配置颜色
                    });
                    flag = false;
                }

                if(vm.product.bpSurplusMoney > vm.product.bpStartMoney){
                    if((investMoney-vm.product.bpStartMoney)>=0){
                        if((investMoney-vm.product.bpStartMoney) % vm.product.bpRiseMoney !=0){
                            layer.tips("投资金额不符合递增规则", '#investMoney', {
                                tips: [1, '#0FA6D8'] //还可配置颜色
                            });
                            flag = false;
                        }
                    }else{
                        layer.tips("投资金额小于起投金额"+vm.product.bpStartMoney+"元", '#investMoney', {
                            tips: [1, '#0FA6D8'] //还可配置颜色
                        });
                        flag = false;
                    }
                }else{
                    if(investMoney != vm.product.bpSurplusMoney){
                        layer.tips("最后一次投资，需一次性投满", '#investMoney', {
                            tips: [1, '#0FA6D8'] //还可配置颜色
                        });
                        flag = false;
                    }
                }
            }else{
                layer.tips("投资金额输入有误", '#investMoney', {
                    tips: [1, '#0FA6D8'] //还可配置颜色
                });
                flag = false;
            }

            if(!flag){
                $("#investMoney").focus();
                return;
            }else{
                var form = {bpId:$("#bpId").val(), investMoney:($("#investMoney").val())*1}
                //投资弹出框
                layer.open({
                    title:'投资提示',
                    content:'确认投资：金额为'+form.investMoney,
                    btn: ['确认', '稍后'],
                    area: '340px',
                    yes: function() {
                        layer.closeAll();
                        layer.load();
                        $.post("/api/product/investBid", form, function (response) {
                            layer.closeAll();
                            if (response.success_is_ok) {
                                var data = response.data;
                                var failmsg = data.msg==null?"":data.msg;
                                if(data.flag){
                                    window.location.href="/product/productSuccess/"+failmsg;
                                }else{
                                    window.location.href="/product/productFail/"+failmsg;
                                }
                            }else{
                                layer.msg(response.error, {
                                    time: 0 //不自动关闭
                                    ,btn: ['关闭']
                                    ,icon: 6
                                    ,yes: function(index){
                                        layer.close(index);
                                        window.location.reload();
                                    }
                                });
                            }
                        });
                    },
                    btn2: function(){
                        layer.closeAll();
                    }
                })
            }
        }
    }
});
