/**
 * Created by Jack on 2016/12/16.
 */
var model = {
    user:{hasFundAccount:true}
};
var vm = new Vue({
    el:".account-install-list",
    data:model,
    beforeCreate:function(){
        $.get("/api/user/getuser",function(response){
            if (response.success_is_ok) {
                model.user = response.data;
            }
        });
    }
});
