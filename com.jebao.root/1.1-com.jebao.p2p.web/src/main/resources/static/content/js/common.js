/**
 * Created by lihui on 2016/11/30.
 */
$(function(){
    // 初始化让#gotTop隐藏
    $('#gotoTop').hide();
    // #gotTop 点击事件
    $('#gotoTop').click(function(){
        $('html,body').animate({'scrollTop':0},500);
        $('#gotoTop').hide();
    });
    //投资中项目和还款中项目
    tab($('.project-menu h4'),$('.project-item'));
    //充值提现--我要充值、我要提现
    tab($('.account-rex-tit h4'),$('.account-rex-item'));
    //充值提现--快捷充值、快捷充值
    tab($('.rechange-type-title h5'),$('.rechange-item'));
    //项目详情
    tab($('.project-detail-tit h4'),$('.project-detail-info'));
    //切换封装
    function tab(btn,box){
        btn.click(function (){
            btn.removeClass('active');
            $(this).addClass('active');
            if(box!=null && box != undefined){
                box.removeClass('active');
                var index=$(this).index();
                box.eq(index).addClass('active');
            }
        });
    }

    //我的红包
    tabVoucher($('.project-menu h4'));
    //切换封装
    function tabVoucher(btn){
        btn.click(function (){
            btn.removeClass('active');
            $(this).addClass('active');
        });
    }



    // window窗口滚动事件
    $(window).scroll(function(){
        var winTop = $(window).scrollTop();
        if (winTop <= 100) {
            $('#gotoTop').hide();
        }else{
            $('#gotoTop').show();
        }
    });
});