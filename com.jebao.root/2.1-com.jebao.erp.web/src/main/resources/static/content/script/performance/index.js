
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //所属团队
    teams: [],
    //部门
    departments:[],
    //销售级别
    ranks: [],
    //查询数据
    dataList:[]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj=$("#search_form").serializeObject();
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=10;
        //在这里的远程数据更新，必须是在对象已经具备相应属性。否则有可能不会绑定映射
        //团队
        $.get("/api/department/list",function(response){
            if (response.success_is_ok){
                var teams = response.data;
                var departments = new Array();
                teams.forEach(function(item){
                    if(item.isDepartment){
                        departments.push(item);
                    }
                });
                vm.teams=response.data;
                vm.departments=departments;
            }
        });
        //销售级别
        $.get("/api/rank/list",function(response){
            if (response.success_is_ok){
                vm.ranks=response.data;
            }
        });
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    mounted:function(){
        //在 el 被替换后，做页面元素变动的操作
        $("#search_form select.select2").select2().on("change",function(){
            model.searchObj[this.name]=this.value;
        });
    },
    filters: {
        percent: function (value) {
            if (isNaN(value)) return '';
            return (value * 100).toFixed(2)+" %";
        },
        money :function(number){
            number = number || 0;
            var places = 2;
            var thousand = ",";
            var decimal = ".";
            var negative = number < 0 ? "-" : "",
                i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
                j = (j = i.length) > 3 ? j % 3 : 0;
            return negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
        }
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        //递归查询所属部门
        findParentDepartment:function(parentId){
            for(var i=0;i<vm.teams.length;i++){
                var item = vm.teams[i];
                if(item.id === parentId){
                    return item.isDepartment ? item.name :this.findParentDepartment(item.parentId);
                }
            }
            return "";
        },
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            var loadIndex = layer.load(2);
            $.get("/api/performance/list",model.searchObj,function(response){
                if (response.success_is_ok){
                    for (var i=0;i<response.data.length;i++){
                        var item =response.data[i];
                        item.departmentName =item.isDepartment ? item.name :vm.findParentDepartment(item.parentId);
                    }
                    vm.dataList=response.data;
                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount>0){
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr:model.searchObj.pageIndex+1,//设置当前页
                            groups: 7, //连续显示分页数
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex=obj.curr -1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
                $("#btnSearch").removeClass("disabled");//解除禁用
                layer.close(loadIndex);
            });
        },

    }
});
