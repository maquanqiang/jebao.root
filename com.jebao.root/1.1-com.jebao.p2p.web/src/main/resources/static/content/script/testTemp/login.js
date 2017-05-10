//表单
$('#form-btn').click(function(){
    alert(1);
    $.axForForm($('#login'),function(e){
        alert(e);
    });
});
$('#form-btn-webApi').click(function(){
    alert(2);
    $.axForForm($('#login-webApi'),function(data){
        if(data.success_is_ok) {
            window.location.href = "http://localhost:9089/api/tempTest/doLoginToken?token=" + data.msg;
            return;
        }
        alert(data.error)
        return;
    });
});