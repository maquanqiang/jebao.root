$(function () {

    //输入金额实时
    $('.entry-num').bind('input propertychange', function() {
        var money = parseInt($(this).val() * 1);
        $('.money-num').html(money);
        //var bpExpectLoanDate = new Date(model.product.bpExpectLoanDate);
        //var bpExpectRepayDate = new Date(model.product.bpExpectRepayDate);
        //var days = bpExpectRepayDate.getTime() - bpExpectLoanDate.getTime();
        //var time = parseInt(days / (1000 * 60 * 60 * 24));
        //var interest = parseInt(($(this).val())*time*(model.product.bpRate)/100/365);
        if(money >= model.product.bpStartMoney && money <= model.product.bpTopMoney){
            iMoney = $(this).val();
            var form = {
                bpRate: model.product.bpRate,
                bpExpectLoanDate: model.product.bpExpectLoanDate,
                bpExpectRepayDate: model.product.bpExpectRepayDate,
                investMoney: $(this).val()
            }

            $.post("/api/product/expectRevenue", form, function (response) {
                if (response.success_is_ok) {
                    $('.money-income').html(response.data.expectRevenue);

                    if(model.voucherList!=null && model.voucherList.length>0){
                        model.amount = 0;
                        for(i=0; i<model.voucherList.length;i++){
                            voucher = model.voucherList[i];
                            if(iMoney>=voucher.vMinPrice){
                                model.amount = voucher.vAmount;

                                break;
                            }
                        }
                    }
                } else {
                    $('.money-income').html(0);
                    layer.tips("输入金额有误", '#investMoney', {
                        tips: [1, '#0FA6D8']
                    });
                    model.amount = 0;
                }
            });
        }else{
            $('.money-income').html(0);
            model.amount = 0;
        }
    });


    $(".voucher a").hover(function(){
        $("#voucherList").show();
    },function(){
        $("#voucherList").hide();
    })

});



//Vue实例
//Model
var model = {
    product: {},
    loanerInfo : {},
    statistics : {},
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
    bpMortgageInfo:'',
    voucherList:[],
    amount:0
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".project",
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
                    $("#bpMortgageInfohtml").html(toChangeLine(vm.product.bpMortgageInfo));
                    $("#bpRepayingSource").html(toChangeLine(vm.product.bpRepayingSource));
                    $("#bpRiskOpinion").html(toChangeLine(vm.product.bpRiskOpinion));
                    $("#bpFundsPurpose").html(toChangeLine(vm.product.bpFundsPurpose));
                    $("#bpPersonalCredit").html(toChangeLine(vm.product.bpPersonalCredit));
                    //alert(toChangeLine(vm.product.bpRepayingSource))

                    sta_str=(vm.product.bpStartTime).replace(/-/g,"/");
                    end_str=(vm.product.bpEndTime).replace(/-/g,"/");//得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss。
                    var end_str=new Date(end_str);//将字符串转化为时间
                    var st_str = new Date(sta_str);
                    var now = new Date();
                    if(end_str >= now){
                        window.setInterval(function () {
                            tick(end_str);
                        },1000);
                    }
                    if(st_str <= now){
                        vm.flag = true;
                    }
                    //loanerInfo
                    $.post("/api/product/loanerInfo", {lid:vm.product.bpLoanerId}, function (response) {
                        if (response.success_is_ok) {
                            vm.loanerInfo = response.data;
                        }
                    });
                    //riskDataList
                    $.post("/api/product/riskListByBpId", form, function (response) {
                        if (response.success_is_ok) {
                            vm.riskDataList = response.data;
                            setTimeout(function(){
                                var mySwiper = new Swiper ('.swiper-container', {
                                    loop: true,
                                    slidesPerView : 3,
                                    slidesPerGroup : 3,
                                    fade: {
                                        crossFade: true
                                    },
                                    paginationClickable: true
                                });
                                $('.button-prev').on('click', function(e){
                                    e.preventDefault();
                                    mySwiper.swipePrev()
                                });
                                $('.button-next').on('click', function(e){
                                    e.preventDefault();
                                    mySwiper.swipeNext()
                                });
                            },500)
                        }
                    });
                    //voucherList
                    $.post("/api/voucher/getByMinWhereAll",{vMinCycle:vm.product.bpMonthTerm},function(response){
                        if (response.success_is_ok) {
                            vm.voucherList = response.data;
                        }else{
                            vm.voucherList = []
                        }
                    })

                    //investInfoList
                    $.post("/api/product/investInfoByBpId", form, function (response) {
                        if (response.success_is_ok) {
                            vm.investInfoList = response.data;
                        }
                    });
                    //incomeDetailList
                    $.post("/api/product/incomeDetailByBpId", form, function (response) {
                        if (response.success_is_ok) {
                            vm.incomeDetailList = response.data;
                        }
                    });

                    //statistics
                    $.get("/api/invest/statistics", function (response) {
                        if (response.success_is_ok) {
                            vm.statistics = response.data;
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
                iMoney=($("#investMoney").val())*1;
                ////voucher
                //$.post("/api/voucher/getByMinWhere",{vMinCycle:vm.product.bpMonthTerm, vMinPrice:iMoney}, function(response){
                //    if (response.success_is_ok) {
                //        vm.voucher = response.data;
                //    }
                //})

                var form = {bpId:$("#bpId").val(), investMoney:iMoney}
                //投资弹出框
                layer.open({
                    title:'投资提示',
                    content:'确认投资：金额为'+form.investMoney+"元",
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
        },
        validAccount:function(){
            //投资弹出框
            layer.open({
                title:'提示',
                content:"你尚未开通第三方，是否马上开通",
                btn: ['开通', '稍后'],
                area: '340px',
                icon:3,
                yes: function() {
                    layer.closeAll();
                    window.location.href="/userfund/register";
                },
                btn2: function(){
                    layer.closeAll();
                }
            })
        }
    }
});

//切换封装
function tab(btn,box){
    btn.click(function (){
        btn.removeClass('active');
        box.removeClass('active');
        $(this).addClass('active');
        var index=$(this).index();
        box.eq(index).addClass('active');
    });

}
//项目详情
tab($('.project-detail-tit h4'),$('.project-detail-info'));


function tick(endTime) {
    var  end_str= endTime;
    var oNow=new Date();
    var  total=parseInt((end_str.getTime()-oNow.getTime())/1000);
    if(total>0){
        var  d=parseInt(total/86400);
        total%=86400;
        var  h=parseInt(total/3600);
        total%=3600;
        var  m=parseInt(total/60);
        total%=60;
        var  s=total;
        //console.log(d+'天'+h+'时'+m+'分'+s+'秒');
        $('#span1').html(d+'天'+h+'时'+m+'分'+s+'秒');
    }else{
        $('#span1').html(0+'天'+0+'时'+0+'分'+0+'秒');
    }

}

function  toChangeLine(str) {
    return str.replace(/&lt;br\/&gt;/g,"<br />")
}


