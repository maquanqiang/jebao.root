/**
 * Created by Jack on 2016/11/18.
 */
(function () {
    function storageGet(key, isLocal) {
        var value = isLocal ? localStorage.getItem(key) : sessionStorage.getItem(key);//获取指定key本地存储的值
        try {
            return JSON.parse(value);
        } catch (e) {
            return value;
        }
    }
    function storageSet(key, value, isLocal) {
        if (value == null) {
            isLocal ? localStorage.removeItem(key) : sessionStorage.removeItem(key);
            return true;
        }
        if (typeof value === "object") {
            value = JSON.stringify(value);
        }
        //将value存储到key字段 -- storage只支持存储字符串
        isLocal ? localStorage.setItem(key, value) : sessionStorage.setItem(key, value);
    }
    //页面公共对象
    var common = {
        apiOrigin:"http://localhost:9089",
        //获取url参数
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            else return null;
        },
        //html 5 的缓存 数据存储.key/value形式存储。value 任意格式数据. local指示是否持久化存储。默认会话存储
        storage: function (key, value, local) {
            if (arguments.length == 1) {
                return storageGet(key, false);
            } else if (arguments.length == 3) {
                return storageSet(key, value, local);
            } else {
                if (typeof arguments[1] === "boolean") {
                    return storageGet(key, arguments[1]);
                } else {
                    return storageSet(key, value, false);
                }
            }
        },
        //显示文本内容
        toText: function (obj) {
            if (obj == null) {
                return '';
            }
            return obj.toString();
        },
        //将json日期格式化显示 数字转日期
        formatJsonDate: function (str, format) {
            if (str == null || str.length == 0) { return ''; }
            str = str.replace(/T/g, " ").replace(/-/g, "/").substr(0, 19);
            var d = new Date(str);
            format = format || 'yyyy-MM-dd';
            return d.toFormatString(format);
        }
    };

    window.common = common;
})(jQuery);

/********js/jquery等 扩展**********/
///日期格式化方法
Date.prototype.toFormatString = function (format) {
    var strMonth = (this.getMonth() + parseInt(1)).toString();
    strMonth = (strMonth.length == 1) ? ("0" + strMonth) : strMonth;
    var strDate = this.getDate().toString();
    strDate = (strDate.length == 1) ? ("0" + strDate) : strDate;
    var strHour = this.getHours().toString();
    strHour = (strHour.length == 1) ? ("0" + strHour) : strHour;
    var strMin = this.getMinutes().toString();
    strMin = (strMin.length == 1) ? ("0" + strMin) : strMin;
    var strSen = this.getSeconds().toString();
    strSen = (strSen.length == 1) ? ("0" + strSen) : strSen;
    if (format) {
        if (format.indexOf("yyyy")>-1) {
            format = format.replace("yyyy", this.getFullYear());
        } else {
            format = format.replace("yy", this.getYear() - 100);
        }
        format = format.replace("MM", strMonth);
        format = format.replace("dd", strDate);
        format = format.replace("HH", strHour);
        format = format.replace("mm", strMin);
        format = format.replace("ss", strSen);
        return format;
    } else {
        return this.getFullYear() + "-" + strMonth + "-" + strDate;
    }
};
//jQuery扩展
(function ($) {
    $.extend($.fn, {
        //序列化表单元素为一个对象
        serializeObject: function () {
            var obj = {};
            $(this).find("input[name]:not([type='button']),textarea[name],select[name]").map(function (index,item) {
                obj[item.name] = $(item).val();
            });
            return obj;
        },
        //设置禁用时长delay //$(this).setDisabled(3000);
        setDisabled: function (timeout) {
            var $target = $(this).addClass("disabled");
            setTimeout(function () {
                $target.removeClass("disabled");
            }, timeout);
        }
    });
    $(document).ajaxError(function (event, xhr, settings) {
        if (xhr.status == 403) {
            window.location.href = "/account/login";
        }else if (xhr.status == 500){
            $(".layui-layer-loading").each(function(i,ele){
                var times = $(ele).attr("times");
                layer.close(times);
            });
            layer.msg("系统异常，请稍后再试");
        }
    });
    //扩展身份证校验
    $.fn.bootstrapValidator.validators.idCard = {
        validate: function(validator, $field, options) {
            var value = $field.val();
            if (value===''){return true;}
            value = value.toUpperCase();
            var idCard = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))\d{3}[xX\d]$/;
            var city = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江 ", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北 ", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏 ", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外 " };
            if (!idCard.test(value)) {
                return false;
            }
            else if (!city[value.substr(0, 2)]) {
                return false;
            }
            else {
                //18位身份证需要验证最后一位校验位
                if (value.length == 18) {
                    value = value.split('');
                    //∑(ai×Wi)(mod 11)
                    //加权因子
                    var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                    //校验位
                    var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
                    var sum = 0;
                    var ai = 0;
                    var wi = 0;
                    for (var i = 0; i < 17; i++) {
                        ai = value[i];
                        wi = factor[i];
                        sum += ai * wi;
                    }
                    var last = parity[sum % 11];
                    if (parity[sum % 11] != value[17]) {
                        return false;
                    }
                }
            }
            return true;
        }
    };

}(jQuery));

function dataToHtml(jctTpl, data) {
    if (data == undefined) {
        data = {};
    }
    var tplText = $(jctTpl).html();
    var instance = new jCT(tplText);
    var collection = data;
    //if (collection.length == 0)
    //    return;
    var innerHtml = instance.GetView(collection);
    collection = null;
    instance = null;
    return innerHtml;
};
//制保留2位小数，如：2，会在2后面补上00.即2.00
function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(x*100)/100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}

