/**
 * Created by wenyq on 2017/2/26.
 */
// 省份列表
var iosProvinces = [
/*******华北五省********/
    {"id":"110","value":"北京市","parentId":"0"},{"id":"120","value":"天津市","parentId":"0"},{"id":"130","value":"河北省","parentId":"0"},{"id":"140","value":"山西省","parentId":"0"},{"id":"150","value":"内蒙古自治区","parentId":"0"},{"id":"210","value":"辽宁省","parentId":"0"},{"id":"220","value":"吉林省","parentId":"0"},{"id":"230","value":"黑龙江省","parentId":"0"},{"id":"310","value":"上海市","parentId":"0"},{"id":"320","value":"江苏省","parentId":"0"},{"id":"330","value":"浙江省","parentId":"0"},{"id":"340","value":"安徽省","parentId":"0"},{"id":"350","value":"福建省","parentId":"0"},{"id":"360","value":"江西省","parentId":"0"},{"id":"370","value":"山东省","parentId":"0"},{"id":"410","value":"河南省","parentId":"0"},{"id":"420","value":"湖北省","parentId":"0"},{"id":"430","value":"湖南省","parentId":"0"},
    {"id":"440","value":"广东省","parentId":"0"},{"id":"450","value":"广西壮族自治区","parentId":"0"},{"id":"460","value":"海南省","parentId":"0"},{"id":"500","value":"重庆市","parentId":"0"},{"id":"510","value":"四川省","parentId":"0"},{"id":"520","value":"贵州省","parentId":"0"},{"id":"530","value":"云南省","parentId":"0"},{"id":"540","value":"西藏自治区","parentId":"0"},{"id":"610","value":"陕西省","parentId":"0"},{"id":"620","value":"甘肃省","parentId":"0"},{"id":"630","value":"青海省","parentId":"0"},{"id":"640","value":"宁夏回族自治区","parentId":"0"},
    {"id":"650","value":"新疆维吾尔自治区","parentId":"0"}]
var iosCitys = [{"id":"1000","value":"北京市","parentId":"110"},{"id":"1100","value":"天津市","parentId":"120"},{"id":"1210","value":"石家庄市","parentId":"130"},{"id":"1240","value":"唐山市","parentId":"130"},{"id":"1260","value":"秦皇岛市","parentId":"130"},{"id":"1270","value":"邯郸市","parentId":"130"},{"id":"1310","value":"邢台市","parentId":"130"},{"id":"1340","value":"保定市","parentId":"130"},{"id":"1380","value":"张家口市","parentId":"130"},{"id":"1410","value":"承德市","parentId":"130"},{"id":"1430","value":"沧州市","parentId":"130"},{"id":"1460","value":"廊坊市","parentId":"130"},{"id":"1480","value":"衡水市","parentId":"130"},{"id":"1630","value":"阳泉市","parentId":"140"},{"id":"1640","value":"长冶市","parentId":"140"},{"id":"1610","value":"太原市","parentId":"140"},{"id":"1620","value":"大同市","parentId":"140"},{"id":"1680","value":"晋城市","parentId":"140"},{"id":"1690","value":"朔州市","parentId":"140"},{"id":"1710","value":"忻州市","parentId":"140"},{"id":"1730","value":"吕梁市","parentId":"140"},{"id":"1750","value":"晋中市","parentId":"140"},{"id":"1770","value":"临汾市","parentId":"140"},{"id":"1810","value":"运城市","parentId":"140"},{"id":"1920","value":"包头市","parentId":"150"},{"id":"1930","value":"乌海市","parentId":"150"},{"id":"1940","value":"赤峰市","parentId":"150"},
    {"id":"1960","value":"呼伦贝尔市","parentId":"150"},{"id":"1980","value":"兴安盟","parentId":"150"},
    {"id":"1990","value":"通辽市","parentId":"150"},{"id":"2010","value":"锡林郭勒盟","parentId":"150"},
    {"id":"2030","value":"乌兰察布市","parentId":"150"},{"id":"2050","value":"鄂尔多斯市","parentId":"150"},
    {"id":"2070","value":"巴彦淖尔市","parentId":"150"},{"id":"2080","value":"阿拉善盟","parentId":"150"},
    {"id":"1910","value":"呼和浩特市","parentId":"150"},{"id":"2210","value":"沈阳市","parentId":"210"},
    {"id":"2220","value":"大连市","parentId":"210"},{"id":"2230","value":"鞍山市","parentId":"210"},
    {"id":"2240","value":"抚顺市","parentId":"210"},{"id":"2250","value":"本溪市","parentId":"210"},
    {"id":"2260","value":"丹东市","parentId":"210"},{"id":"2270","value":"锦州市","parentId":"210"},
    {"id":"2280","value":"营口市","parentId":"210"},{"id":"2290","value":"阜新市","parentId":"210"},
    {"id":"2310","value":"辽阳市","parentId":"210"},{"id":"2320","value":"盘锦市","parentId":"210"},
    {"id":"2330","value":"铁岭市","parentId":"210"},{"id":"2340","value":"朝阳市","parentId":"210"},
    {"id":"2410","value":"长春市","parentId":"220"},{"id":"2420","value":"吉林市","parentId":"220"},
    {"id":"2430","value":"四平市","parentId":"220"},{"id":"2440","value":"辽源市","parentId":"220"},
    {"id":"2450","value":"通化市","parentId":"220"},{"id":"2460","value":"白山市","parentId":"220"},
    {"id":"2470","value":"白城市","parentId":"220"},{"id":"2490","value":"延边朝鲜族自治州","parentId":"220"},
    {"id":"2520","value":"松原市","parentId":"220"},{"id":"2610","value":"哈尔滨市","parentId":"230"},
    {"id":"2640","value":"齐齐哈尔市","parentId":"230"},{"id":"2660","value":"鸡西市","parentId":"230"},
    {"id":"2670","value":"鹤岗市","parentId":"230"},{"id":"2680","value":"双鸭山市","parentId":"230"},
    {"id":"2690","value":"佳木斯市","parentId":"230"},{"id":"2710","value":"伊春市","parentId":"230"},
    {"id":"2720","value":"牡丹江市","parentId":"230"},{"id":"2740","value":"七台河市","parentId":"230"},
    {"id":"2760","value":"绥化市","parentId":"230"},{"id":"2780","value":"黑河市","parentId":"230"},
    {"id":"2790","value":"大兴安岭地区","parentId":"230"},{"id":"2650","value":"大庆市","parentId":"230"},
    {"id":"2900","value":"上海","parentId":"310"},{"id":"3010","value":"南京","parentId":"320"},
    {"id":"3020","value":"无锡市","parentId":"320"},{"id":"3030","value":"徐州市","parentId":"320"},
    {"id":"3040","value":"常州市","parentId":"320"},{"id":"3050","value":"苏州市","parentId":"320"},
    {"id":"3060","value":"南通市","parentId":"320"},{"id":"3070","value":"连云港市","parentId":"320"},
    {"id":"3080","value":"淮安市","parentId":"320"},{"id":"3110","value":"盐城市","parentId":"320"},
    {"id":"3120","value":"扬州市","parentId":"320"},{"id":"3140","value":"镇江市","parentId":"320"},
    {"id":"3090","value":"宿迁市","parentId":"320"},{"id":"3420","value":"舟山市","parentId":"330"},
    {"id":"3310","value":"杭州市","parentId":"330"},{"id":"3320","value":"宁波市","parentId":"330"},
    {"id":"3330","value":"温州市","parentId":"330"},{"id":"3350","value":"嘉兴市","parentId":"330"},
    {"id":"3360","value":"湖州市","parentId":"330"},{"id":"3370","value":"绍兴市","parentId":"330"},
    {"id":"3380","value":"金华市","parentId":"330"},{"id":"3410","value":"衢州市","parentId":"330"},
    {"id":"3430","value":"丽水市","parentId":"330"},{"id":"3450","value":"台州市","parentId":"330"},
    {"id":"3610","value":"合肥市","parentId":"340"},{"id":"3620","value":"芜湖市","parentId":"340"},
    {"id":"3630","value":"蚌埠市","parentId":"340"},{"id":"3640","value":"淮南市","parentId":"340"},
    {"id":"3650","value":"马鞍山市","parentId":"340"},{"id":"3660","value":"淮北市","parentId":"340"},
    {"id":"3670","value":"铜陵市","parentId":"340"},{"id":"3680","value":"安庆市","parentId":"340"},
    {"id":"3710","value":"黄山市","parentId":"340"},{"id":"3720","value":"阜阳市","parentId":"340"},{"id":"3740","value":"宿州市","parentId":"340"},{"id":"3750","value":"滁州市","parentId":"340"},{"id":"3760","value":"六安市","parentId":"340"},{"id":"3790","value":"池州市","parentId":"340"},{"id":"3960","value":"永安市","parentId":"350"},{"id":"3910","value":"福州市","parentId":"350"},{"id":"3930","value":"厦门市","parentId":"350"},{"id":"3940","value":"莆田市","parentId":"350"},{"id":"3950","value":"三明市","parentId":"350"},{"id":"3970","value":"泉州市","parentId":"350"},{"id":"3990","value":"漳州市","parentId":"350"},{"id":"4010","value":"南平市","parentId":"350"},{"id":"4030","value":"宁德市","parentId":"350"},{"id":"4050","value":"龙岩市","parentId":"350"},{"id":"4210","value":"南昌","parentId":"360"},{"id":"4220","value":"景德镇市","parentId":"360"},{"id":"4230","value":"萍乡市","parentId":"360"},{"id":"4240","value":"九江市","parentId":"360"},{"id":"4260","value":"新余市","parentId":"360"},{"id":"4270","value":"鹰潭市","parentId":"360"},{"id":"4280","value":"赣州市","parentId":"360"},{"id":"4310","value":"宜春市","parentId":"360"},{"id":"4330","value":"上饶市","parentId":"360"},{"id":"4350","value":"吉安市","parentId":"360"},{"id":"4370","value":"抚州市","parentId":"360"},{"id":"4610","value":"济宁市","parentId":"370"},{"id":"4630","value":"泰安市","parentId":"370"},{"id":"4650","value":"威海市","parentId":"370"},{"id":"4660","value":"滨州市","parentId":"370"},{"id":"4680","value":"德州市","parentId":"370"},{"id":"4710","value":"聊城市","parentId":"370"},{"id":"4730","value":"临沂市","parentId":"370"},{"id":"4750","value":"菏泽市","parentId":"370"},{"id":"4510","value":"济南市","parentId":"370"},{"id":"4520","value":"青岛市","parentId":"370"},{"id":"4530","value":"淄博市","parentId":"370"},{"id":"4540","value":"枣庄市","parentId":"370"},{"id":"4550","value":"东营市","parentId":"370"},{"id":"4560","value":"烟台市","parentId":"370"},{"id":"4580","value":"潍坊市","parentId":"370"},{"id":"4910","value":"郑州市","parentId":"410"},{"id":"4920","value":"开封市","parentId":"410"},{"id":"4930","value":"洛阳市","parentId":"410"},{"id":"4950","value":"平顶山市","parentId":"410"},{"id":"4960","value":"安阳市","parentId":"410"},{"id":"4970","value":"鹤壁市","parentId":"410"},{"id":"4980","value":"新乡市","parentId":"410"},{"id":"5010","value":"焦作市","parentId":"410"},{"id":"5020","value":"濮阳市","parentId":"410"},{"id":"5030","value":"许昌市","parentId":"410"},{"id":"5040","value":"漯河市","parentId":"410"},{"id":"5050","value":"三门峡市","parentId":"410"},{"id":"5060","value":"商丘市","parentId":"410"},{"id":"5080","value":"周口市","parentId":"410"},{"id":"5110","value":"驻马店市","parentId":"410"},{"id":"5130","value":"南阳市","parentId":"410"},{"id":"5150","value":"信阳市","parentId":"410"},{"id":"5210","value":"武汉市","parentId":"420"},{"id":"5220","value":"黄石市","parentId":"420"},{"id":"5230","value":"十堰市","parentId":"420"},{"id":"5280","value":"襄阳市","parentId":"420"},{"id":"5310","value":"鄂州市","parentId":"420"},{"id":"5320","value":"荆门市","parentId":"420"},{"id":"5330","value":"黄冈市","parentId":"420"},{"id":"5350","value":"孝感市","parentId":"420"},{"id":"5360","value":"咸宁市","parentId":"420"},{"id":"5370","value":"荆州市","parentId":"420"},{"id":"5410","value":"恩施土家族苗族自治州","parentId":"420"},{"id":"5260","value":"宜昌市","parentId":"420"},{"id":"5510","value":"长沙","parentId":"430"},{"id":"5520","value":"株洲市","parentId":"430"},{"id":"5530","value":"湘潭市","parentId":"430"},{"id":"5540","value":"衡阳市","parentId":"430"},{"id":"5550","value":"邵阳市","parentId":"430"},{"id":"5570","value":"岳阳市","parentId":"430"},{"id":"5580","value":"常德市","parentId":"430"},{"id":"5590","value":"张家界市","parentId":"430"},{"id":"5610","value":"益阳市","parentId":"430"},{"id":"5620","value":"娄底市","parentId":"430"},
    {"id":"5670","value":"怀化市","parentId":"430"},{"id":"5690","value":"湘西土家族苗族自治州","parentId":"430"},{"id":"5630","value":"郴州市","parentId":"430"},{"id":"5650","value":"永州市","parentId":"430"},{"id":"5810","value":"广州","parentId":"440"},{"id":"5820","value":"韶关市","parentId":"440"},{"id":"5840","value":"深圳","parentId":"440"},{"id":"5850","value":"珠海市","parentId":"440"},{"id":"5860","value":"汕头市","parentId":"440"},{"id":"5880","value":"佛山市","parentId":"440"},{"id":"5890","value":"江门市","parentId":"440"},{"id":"5910","value":"湛江市","parentId":"440"},{"id":"5920","value":"茂名市","parentId":"440"},{"id":"5930","value":"肇庆市","parentId":"440"},{"id":"5950","value":"惠州市","parentId":"440"},{"id":"5960","value":"梅州市","parentId":"440"},{"id":"5970","value":"汕尾市","parentId":"440"},{"id":"5980","value":"河源市","parentId":"440"},{"id":"5990","value":"阳江市","parentId":"440"},{"id":"6010","value":"清远市","parentId":"440"},{"id":"6020","value":"东莞市","parentId":"440"},{"id":"6030","value":"中山市","parentId":"440"},{"id":"6110","value":"南宁市","parentId":"450"},{"id":"6140","value":"柳州市","parentId":"450"},{"id":"6170","value":"桂林市","parentId":"450"},{"id":"6210","value":"梧州市","parentId":"450"},
    {"id":"6230","value":"北海市","parentId":"450"},{"id":"6240","value":"玉林市","parentId":"450"},{"id":"6330","value":"防城港市","parentId":"450"},{"id":"6410","value":"海口","parentId":"460"},{"id":"6420","value":"三亚","parentId":"460"},{"id":"6530","value":"重庆市","parentId":"500"},{"id":"6670","value":"万州区","parentId":"500"},{"id":"6690","value":"涪陵区","parentId":"500"},{"id":"6870","value":"黔江区","parentId":"500"},{"id":"6510","value":"成都市","parentId":"510"},{"id":"6550","value":"自贡市","parentId":"510"},{"id":"6560","value":"攀枝花市","parentId":"510"},{"id":"6570","value":"泸州市","parentId":"510"},{"id":"6580","value":"德阳市","parentId":"510"},{"id":"6590","value":"绵阳市","parentId":"510"},{"id":"6610","value":"广元市","parentId":"510"},{"id":"6620","value":"遂宁市","parentId":"510"},{"id":"6630","value":"内江市","parentId":"510"},{"id":"6650","value":"乐山市","parentId":"510"},{"id":"6710","value":"宜宾市","parentId":"510"},{"id":"6730","value":"南充市","parentId":"510"},{"id":"6750","value":"达州市","parentId":"510"},{"id":"6770","value":"雅安市","parentId":"510"},{"id":"6790","value":"阿坝藏族羌族自治州","parentId":"510"},{"id":"6810","value":"甘孜藏族自治州","parentId":"510"},{"id":"6840","value":"凉山彝族自治州","parentId":"510"},{"id":"7010","value":"贵阳市","parentId":"520"},{"id":"7020","value":"六盘水市","parentId":"520"},{"id":"7030","value":"遵义市","parentId":"520"},{"id":"7050","value":"铜仁地区","parentId":"520"},{"id":"7070","value":"黔西南布依族苗族自治州","parentId":"520"},{"id":"7090","value":"毕节地区","parentId":"520"},{"id":"7110","value":"安顺市","parentId":"520"},{"id":"7130","value":"黔东南苗族侗族自治州","parentId":"520"},{"id":"7150","value":"黔南布依族苗族自治州","parentId":"520"},{"id":"7310","value":"昆明市","parentId":"530"},{"id":"7340","value":"昭通市","parentId":"530"},{"id":"7360","value":"曲靖市","parentId":"530"},{"id":"7380","value":"楚雄彝族自治州","parentId":"530"},{"id":"7410","value":"玉溪市","parentId":"530"},{"id":"7430","value":"红河哈尼族彝族自治州","parentId":"530"},{"id":"7450","value":"文山壮族苗族自治州","parentId":"530"},{"id":"7470","value":"普洱市","parentId":"530"},{"id":"7490","value":"西双版纳傣族自治州","parentId":"530"},{"id":"7510","value":"大理白族自治州","parentId":"530"},{"id":"7530","value":"保山市","parentId":"530"},{"id":"7540","value":"德宏傣族景颇族自治州","parentId":"530"},{"id":"7550","value":"丽江市","parentId":"530"},{"id":"7560","value":"怒江傈僳族自治州","parentId":"530"},{"id":"7570","value":"迪庆藏族自治州","parentId":"530"},{"id":"7580","value":"临沧市","parentId":"530"},{"id":"7810","value":"阿里地区","parentId":"540"},{"id":"7830","value":"林芝地区","parentId":"540"},{"id":"7730","value":"边坝县","parentId":"540"},{"id":"7750","value":"错那县","parentId":"540"},{"id":"7770","value":"康马县","parentId":"540"},{"id":"7700","value":"拉萨市","parentId":"540"},{"id":"7800","value":"双湖","parentId":"540"},{"id":"7720","value":"昌都地区","parentId":"540"},{"id":"7740","value":"山南地区","parentId":"540"},{"id":"7760","value":"日喀则地区","parentId":"540"},{"id":"7790","value":"那曲地区","parentId":"540"},{"id":"7910","value":"西安市","parentId":"610"},{"id":"7920","value":"铜川市","parentId":"610"},{"id":"7930","value":"宝鸡市","parentId":"610"},{"id":"7950","value":"咸阳市","parentId":"610"},{"id":"7970","value":"渭南市","parentId":"610"},{"id":"7990","value":"汉中市","parentId":"610"},{"id":"8010","value":"安康市","parentId":"610"},{"id":"8030","value":"商洛市","parentId":"610"},{"id":"8040","value":"延安市","parentId":"610"},{"id":"8060","value":"榆林市","parentId":"610"},{"id":"8210","value":"兰州市","parentId":"620"},{"id":"8220","value":"嘉峪关市","parentId":"620"},{"id":"8230","value":"金昌市","parentId":"620"},{"id":"8240","value":"白银市","parentId":"620"},{"id":"8250","value":"天水市","parentId":"620"},{"id":"8260","value":"酒泉市","parentId":"620"},{"id":"8270","value":"张掖市","parentId":"620"},{"id":"8280","value":"武威市","parentId":"620"},{"id":"8290","value":"定西市","parentId":"620"},{"id":"8310","value":"陇南市","parentId":"620"},{"id":"8330","value":"平凉市","parentId":"620"},{"id":"8340","value":"庆阳市","parentId":"620"},{"id":"8360","value":"临夏回族自治州","parentId":"620"},{"id":"8380","value":"甘南藏族自治州","parentId":"620"},{"id":"8510","value":"西宁市","parentId":"630"},{"id":"8520","value":"海东地区","parentId":"630"},{"id":"8540","value":"海北藏族自治州","parentId":"630"},{"id":"8550","value":"黄南藏族自治州","parentId":"630"},{"id":"8560","value":"海南藏族自治州","parentId":"630"},{"id":"8570","value":"果洛藏族自治州","parentId":"630"},{"id":"8580","value":"玉树藏族自治州","parentId":"630"},{"id":"8590","value":"海西蒙古族藏族自治州","parentId":"630"},{"id":"8710","value":"银川市","parentId":"640"},{"id":"8720","value":"石嘴山市","parentId":"640"},{"id":"8810","value":"乌鲁木齐","parentId":"650"},{"id":"8820","value":"克拉玛依市","parentId":"650"},{"id":"8830","value":"吐鲁番地区","parentId":"650"},{"id":"8840","value":"哈密地区","parentId":"650"},{"id":"8850","value":"昌吉回族自治州","parentId":"650"},{"id":"8870","value":"博尔塔拉蒙古自治州","parentId":"650"},{"id":"8880","value":"巴音郭楞蒙古自治州","parentId":"650"},{"id":"8910","value":"阿克苏地区","parentId":"650"},{"id":"8930","value":"克孜勒苏柯尔克孜自治州","parentId":"650"},{"id":"8940","value":"喀什地区","parentId":"650"},{"id":"8960","value":"和田地区","parentId":"650"},{"id":"8980","value":"伊犁哈萨克自治州","parentId":"650"},{"id":"9010","value":"塔城地区","parentId":"650"},{"id":"9020","value":"阿勒泰地区","parentId":"650"}]
var data = [
    {'code': '0', 'name': '工商银行'},
    {'code': '1', 'name': '农业银行'}
];
var datapr= [
    {'id': '0', 'value': '工商银行'},
    {'id': '1', 'value': '农业银行'}
];

var model = {
    formSelector: "#defaultForm",
    form: {},
    banks: {},
    regions: [],
    cities:[],
    countries:[],
    error: {hasError: false, message: "提交错误信息显示的地方"}
};
$(function () {

    //选择银行
    var showBankDom = document.querySelector('#showBank');
    var bankIdDom = document.querySelector('#bankId');
    showBankDom.addEventListener('click', function () {
        var bankId = showBankDom.dataset['id'];
        var bankName = showBankDom.dataset['value'];
        var bankSelect = new IosSelect(1,
            [data],
            {
                container: '.container1',
                title: '',
                itemHeight: 40,
                itemShowCount: 5,
                oneLevelId: bankId,
                callback: function (selectOneObj) {
                    bankIdDom.value = selectOneObj.id;
                    showBankDom.innerHTML = selectOneObj.value;
                    showBankDom.dataset['id'] = selectOneObj.id;
                    showBankDom.dataset['value'] = selectOneObj.value;
                }
            });

        var selectContactDom = $('#select_contact');
        var showContactDom = $('#show_contact');
        var contactProvinceCodeDom = $('#contact_province_code');
        var contactCityCodeDom = $('#contact_city_code');
        selectContactDom.bind('click', function () {
            var sccode = showContactDom.attr('data-city-code');
            var scname = showContactDom.attr('data-city-name');

            var oneLevelId = showContactDom.attr('data-province-code');
            var twoLevelId = showContactDom.attr('data-city-code');
            var iosSelect = new IosSelect(3,
                [iosProvinces, iosCitys],
                {
                    title: '地址选择',
                    itemHeight: 35,
                    relation: [1, 1, 0, 0],
                    oneLevelId: oneLevelId,
                    twoLevelId: twoLevelId,
                    callback: function (selectOneObj, selectTwoObj) {
                        contactProvinceCodeDom.val(selectOneObj.id);
                        contactProvinceCodeDom.attr('data-province-name', selectOneObj.value);
                        contactCityCodeDom.val(selectTwoObj.id);
                        contactCityCodeDom.attr('data-city-name', selectTwoObj.value);

                        showContactDom.attr('data-province-code', selectOneObj.id);
                        showContactDom.attr('data-city-code', selectTwoObj.id);
                        showContactDom.html(selectOneObj.value + ' ' + selectTwoObj.value);
                    }
                });
        });
    });
});
var vm = new Vue({
    el: "#openForm",
    data: model,
    beforeCreate: function () {
        model.form = $(model.formSelector).serializeObject();
        model.form.bankCardNoFormat = "";
        $.get("/api/data/bankList", function (response) {
            if (response.success_is_ok) {
                model.banks = response.data;
              //  data=model.banks;
                var arrayObj = new Array()
                for(i =0;i<model.banks.length ;i++)
                {
                    var arr  =
                    {
                        "id" : model.banks[i].code,
                        "value" :  model.banks[i].name
                    }
                    arrayObj.push(arr);
                }
                data=arrayObj;
            }
        });
        //$.get("/api/data/regionList", function (response) {
        //    if (response.success_is_ok) {
        //        model.regions = response.data;
        //        var arrayObj = new Array()
        //
        //        for(i =0;i<model.regions.length ;i++)
        //        {
        //            var id=model.regions[i].code;
        //            for (j=0;j<model.regions[i].children.length;j++)
        //            {
        //                var arr  =
        //                {
        //                    "id" : model.regions[i].children[j].code,
        //                    "value" :  model.regions[i].children[j].name,
        //                    "parentId":id
        //                }
        //                arrayObj.push(arr);
        //            }
        //
        //        }
        //   var sss=     JSON.stringify(arrayObj);
        //       $("#c1").html(sss);
        //    }
        //});


    },
    mounted: function () {
        this.initValidateForm();
        //        银行卡输入格式
        $('#bankCard').on('propertychange input',function(){
            var value=$(this).val().replace(/\s/g,'').replace(/(\d{4})(?=\d)/g,"$1 ");
            $(this).val(value);
        });
    },
    methods: {
        initValidateForm: function () {
            $("#openForm").mvalidate({
                type:1,
                onKeyup:true,
                sendForm:true,
                firstInvalidFocus:false,
                valid:function(event,options){
                    //点击提交按钮时,表单通过验证触发函数
                    event.preventDefault();

                    var $form = $("#openForm");
                    $("#province").val($("#show_contact").attr("data-province-code"));
                    var loadIndex = layer.load(2);
                    $.post($form.attr('action'),  $form.serializeObject(), function (response) {
                        if (response.success_is_ok) {
                            window.location.href = "/userfund/registerSuccess";
                            return;
                        } else {
                            layer.close(loadIndex);
                            layer.alert(response.error);
                        }
                    }, "json");
                },
                invalid:function(event, status, options){
                    //点击提交按钮时,表单未通过验证触发函数
                },
                eachField:function(event,status,options){
                    //点击提交按钮时,表单每个输入域触发这个函数 this 执向当前表单输入域，是jquery对象
                },
                eachValidField:function(val){},
                eachInvalidField:function(event, status, options){},
                conditional:{
                    confirmpwd:function(){
                        return $("#pwd").val()==$("#confirmpwd").val();
                    }
                },
                descriptions:{
                    bankId:{
                        required:"请选择银行"
                    },
                    bankCard:{
                        required:"请输入银行卡号"
                    },
                    contact_city_code:{
                        required:"请选择开户行省市"
                    },
                    bankci:{
                        required:"请选择开户行区县"
                    },
                    password:{
                        required : '请输入密码'
                    },
                    confirmpassword:{
                        required : '请再次输入密码',
                        conditional : '两次密码不一样'
                    }
                }
            });
        }
    }
});
