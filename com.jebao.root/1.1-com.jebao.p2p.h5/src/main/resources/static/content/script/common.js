/**
 * Created by Jack on 2016/11/18.
 */
(function () {
    //页面公共对象
    var common = {
        apiOrigin: "",
        //获取url参数
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            else return null;
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
            if (str == null || str.length == 0) {
                return '';
            }
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
        if (format.indexOf("yyyy") > -1) {
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
    var rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
        rsubmittable = /^(?:input|select|textarea|keygen)/i,
        manipulation_rcheckableType = /^(?:checkbox|radio)$/i;
    $.extend($.fn, {
        //序列化表单元素为一个对象
        serializeObject: function () {
            var newObj = {};
            this.map(function () {
                    var elements = jQuery.prop(this, "elements");
                    return elements ? jQuery.makeArray(elements) : this;
                })
                .filter(function () {
                    var type = this.type;
                    return this.name &&
                        rsubmittable.test(this.nodeName) && !rsubmitterTypes.test(type) &&
                        ( this.checked || !manipulation_rcheckableType.test(type) );
                })
                .map(function (i, elem) {
                    var val = jQuery(this).val();

                    return val == null ?
                        null :
                        jQuery.isArray(val) ?
                            jQuery.map(val, function (val) {
                                return newObj[elem.name] = $.trim(val);
                                ;
                            }) :
                            newObj[elem.name] = $.trim(val);
                });
            return newObj;
        },
        //设置禁用时长delay //$(this).setDisabled(3000);
        setDisabled: function (timeout) {
            var $target = $(this).addClass("disabled");
            setTimeout(function () {
                $target.removeClass("disabled");
            }, timeout);
        }
    });
    $(document).ajaxSend(function (event, jqxhr, settings) {
        if (settings.url.indexOf("/api") === 0) {
            settings.url = common.apiOrigin + settings.url;
            settings.xhrFields = {
                withCredentials: true
            };
            settings.crossDomain = true;
        }
    });
    $(document).ajaxSend(function (event, jqxhr, settings) {
        if (settings.url.indexOf("/mobileApi") === 0) {
            settings.url = common.apiOrigin + settings.url;
            settings.xhrFields = {
                withCredentials: true
            };
            settings.crossDomain = true;
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
}(jQuery));
