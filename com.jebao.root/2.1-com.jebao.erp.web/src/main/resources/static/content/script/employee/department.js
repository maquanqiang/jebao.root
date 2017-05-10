/**
 * Created by Jack on 2016/11/30.
 */
// define the item component
Vue.component('tree-item', {
    template: '#item-template',
    props: {
        model: Object
    },
    data: function () {
        return {
            open: true
        }
    },
    computed: {
        hasChildren: function () {
            return this.model.children &&
                this.model.children.length
        }
    },
    methods: {
        toggle: function () {
            if (this.hasChildren) {
                this.open = !this.open
            }
        }
    }
});

var model = {
    //查询条件
    searchObj: {},
    //部门
    departments:[],
    //团队
    teams:[],
    dataList:[],
    //弹窗vm实例
    openFormVm:{}
};
//Vue实例
var vm = new Vue({
    el:".content",
    data:model,
    computed:{
        treeData:function(){
            var dataSource = this.teams;
            if (dataSource.length === 0){
                return {name:"金额宝"};
            }
            var findChildrenFunc = function(teamId){
                var children = [];
                for (var i=0;i<dataSource.length;i++){
                    var item = dataSource[i];
                    if (item.parentId === teamId){
                        var itemChildren = findChildrenFunc(item.id);
                        item.children = itemChildren;
                        children.push(item);
                    }
                }
                return children;
            }
            var children = findChildrenFunc(0);
            return {name:"金额宝",children:children};
        },
    },
    beforeCreate:function(){
        //初始化数据
        model.searchObj=$("#search_form").serializeObject();
        model.searchObj.parentAndSelf=true;
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=10000;
        //在这里的远程数据更新，必须是在对象已经具备相应属性。否则有可能不会绑定映射
        //获取部门、团队数据
        $.get("/api/department/list",{pageIndex:0,pageSize:1000},function(response){
            if (response.success_is_ok){
                var teams = response.data;
                var departments = new Array();
                teams.forEach(function(item){
                    if(item.isDepartment){
                        departments.push(item);
                    }
                });
                vm.teams=teams;
                vm.departments=departments;
                vm.search();//此页面vm.search 查询依赖于vm.teams
            }
        });
    },
    created:function(){
        //this.search();
    },
    mounted:function(){
        //在 el 被替换后，做页面元素变动的操作
        $("#search_form select.select2").select2().on("change",function(){
            model.searchObj[this.name]=this.value;
        });
    },

    methods:{
        //查询
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            var loadIndex = layer.load(2);
            $.get("/api/department/list",model.searchObj,function(response){
                if (response.success_is_ok){
                    var teamList = response.data;
                    for (var i=0;i<teamList.length;i++){
                        var item =teamList[i];
                        var parentTeam = vm.getTeam(item.parentId);
                        item.parentName=parentTeam.name;
                    }
                    vm.dataList=teamList;
                    //设置分页
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
                                    vm.searchObj.pageIndex=obj.curr -1;//设置查询指定页
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
        getTeam:function(teamId){
            for (var i=0;i<vm.teams.length;i++){
                if (vm.teams[i].id==teamId){
                    return vm.teams[i];
                }
            }
            return {name:""};
        },
        openPostForm:function(itemId){
            if (isNaN(itemId)){itemId=0;}
            var tempObj= $('.js-open-modal:first').clone();
            tempObj.find('form').prop('id','openForm');
            var tempHtml=tempObj.html();
            layer.open({
                title:itemId>0?'修改':'添加',
                content:tempHtml,
                btn: ['保存'],
                area:['500px'],
                btn1: function(){
                    var $form = $("#openForm");
                    var bootstrapValidator = $form.data('bootstrapValidator').validate();
                    if(!bootstrapValidator.isValid()){
                        return false;
                    }else{
                        vm.post($form);
                    }
                }
            });
            vm.createOpenVm("#openForm",itemId);

        },
        createOpenVm:function(formSelector,itemId){
            var openVmModel ={
                formSelector:formSelector,
                formData:$(formSelector).serializeObject(),
                departments:vm.departments,
                error:{hide:true,message:""}
            };
            openVmModel.formData.id=itemId;
            vm.openFormVm = new Vue({
                el: formSelector,
                data: openVmModel,
                computed:{
                    isEdit:function(){
                        return this.formData.id>0;
                    }
                },
                beforeCreate:function(){
                    var itemId =openVmModel.formData.id;
                    //填充窗体数据
                    if (itemId>0){
                        for (var i=0;i<vm.dataList.length;i++){
                            var item =vm.dataList[i];
                            if (item.id==itemId){
                                openVmModel.formData.name = item.name;
                                openVmModel.formData.parentId = item.parentId;
                                openVmModel.formData.isDepartment = item.isDepartment;
                            }
                        }
                    }
                },
                mounted:function(){
                    //在 el 被替换后，做页面元素变动的操作
                    var $form = $(this.formSelector);
                    vm.bindFormValidate($form);
                    $(this.formSelector).find("select.select2").select2().on("change",function(){
                        vm.openFormVm.formData[this.name]=this.value;
                    });
                }
            });
        },
        post:function($form){
            var $button =$form.parent().parent().children(".layui-layer-btn").children("a:first");
            $button.addClass("btn disabled");
            var layerIndex = layer.load(2);
            $.post("/api/department/post",vm.openFormVm.formData,function(response){
                if (response.success_is_ok){
                    layer.msg(response.msg);
                    vm.search();
                    layer.closeAll();
                }else{
                    vm.openFormVm.error.hide=false;
                    vm.openFormVm.error.message=response.msg;
                    layer.close(layerIndex);
                }
                $button.removeClass("disabled");
            });
        },
        openDeleteWin:function(itemId){
            layer.confirm('确定要删除吗?', {icon: 3, title:'询问'}, function(index){
                layer.load(2);
                $.post("/api/department/delete",{id:itemId},function(response){
                    layer.closeAll();
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                        vm.search();
                    }else{
                        layer.alert(response.msg);
                    }
                });
            });
        },
        //绑定表单验证
        bindFormValidate:function($form){
            $form.bootstrapValidator({
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '名称不能为空'
                            },
                            stringLength: {
                                min: 2,
                                max: 10,
                                message: '名称长度必须在2到10位之间'
                            }
                        }
                    }
                }
            });
        },
    }
});





