/**
 * Created by Administrator on 2016/11/22.
 */
var model = {
    loaner: {},
    haveHouse:'',
    haveCar:'',
    //学历
    educationArr: [],
    //政治面貌
    politicsStatusArr: [],
    //婚姻状况
    maritalStatusArr: [],
    //有或无
    haveArr: [],
    //性别
    sexArr: []
};
// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function() {
        //初始化本地数据
        model.educationArr = new Array("", "小学", "初中", "高中", "大专", "本科", "硕士", "博士", "其他");
        model.politicsStatusArr = new Array("", "团员", "党员", "群众");
        model.maritalStatusArr = new Array("", "已婚", "未婚", "离异", "丧偶");
        model.haveArr = new Array("","有","无");
        model.sexArr = new Array("","男","女");
    },
    //初始化远程数据
    created:function(){
        var dataVal = $("#defaultForm").serializeObject();
        $.get("/api/loaner/details",dataVal,function(response){
            if (response.success_is_ok){
                var data=response.data;
                if(data!=null) {
                    vm.loaner = data;
                }
            }
        });
    }
});