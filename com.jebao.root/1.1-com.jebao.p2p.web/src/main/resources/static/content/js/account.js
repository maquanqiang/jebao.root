/**
 * Created by lihui on 2016/12/3.
 */
$(function(){
    //我的账户公共左侧导航切换
    $('.sidebar-menu a').click(function(){
        $('.sidebar-menu a').removeClass('on');
        $(this).addClass('on');
    });
    //账户设置--收起
    $('.install-btn').click(function(){
        if($(this).html() == '修改'){
            $(this).html('收起');
            $(this).parent().next().show();
        }else{
            $(this).html('修改');
            $(this).parent().next().hide();
        }
    });
    //短信倒计时
    var  oBtn=$('.get_code_btn');
    var  n=90;
    var  bFlag=false;
    oBtn.click(function(){
        if(bFlag){
            return;
        }
        bFlag=true;
        tick();
        var timer=setInterval(tick,1000);
        function tick(){
            n--;
            oBtn.val(n+'s后可重发');
            oBtn.addClass('color');
            if(n==0){
                oBtn.val('重新获取验证码');
                oBtn.removeClass('color');
                clearInterval(timer);
                n=90;
                bFlag=false;
            }
        }
    });
});