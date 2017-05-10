var card = '6225880136722676';
jQuery.ajax({
    url: "https://authpay.jd.com/card/verifyCardBin",
    dataType: "jsonp",
    jsonp: "_callback",
    timeout: 20000,
    data: '_input_charset=utf-8&cardNo=' + card + '&cardBinCheck=true',
    success: function(data){
        console.log("校验成功")
        console.log(data)
    },
    //系统级异常 404 or 500...
    error: function(xhr, status, error){
        console.log("校验异常")
    }
});
