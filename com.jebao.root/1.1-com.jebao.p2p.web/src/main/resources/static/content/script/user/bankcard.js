/**
 * Created by Jack on 2016/12/16.
 */
var model = {
    hasFundAccount:true,
    bankName:"",
    bankCardNo:"",
    newBankName:"",
    newBankCardNo:""
};
var vm = new Vue({
    el:".account-content",
    data:model,
    beforeCreate:function(){
        $.get("/api/user/getuser",function(response){
            if (response.success_is_ok) {
                model.hasFundAccount = response.data.hasFundAccount;
                model.bankName=response.data.bankName;
                model.bankCardNo=response.data.bankCardNo;
                model.newBankName = response.data.newBankName;
                model.newBankCardNo = response.data.newBankCardNo;
            }
        });
    },
    methods:{
        goChangeCard:function(){
            window.location.href= common.apiOrigin + "/api/userfund/goChangeCard";
        }
    }
});