$(function(){
	/**
	 * ajax封装
	 * url 发送请求的地址,若没有请求地址，停止下面的运行
	 * data 发送到服务器的数据
	 * type 请求方式("POST" 或 "GET")， 默认为 "GET"
	 * async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
	 *       注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
	 * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text,默认是json
	 * successfn 成功回调函数
	 * errorfn 失败回调函数
	 */
	jQuery.ax=function(url, opt_data, type , async, dataType, successfn, errorfn) {
		var senddata=(typeof  opt_data == 'object')? opt_data :{};
		var senddata=opt_data || '';
		async = (async==null || async=="" || typeof async =="undefined")? "true" : type;
		type = (type==null || type=="" || typeof type =="undefined")? "GET" : type;
		dataType = (dataType==null || dataType=="" || typeof dataType =="undefined")? "json" : dataType;
		successfn=(typeof successfn == 'function')?successfn:function(){};
		errorfn=(typeof errorfn == 'function')?errorfn:function(){};
		$.ajax({
			url: url,
			data: opt_data,
			async: async,
			type: type,
			dataType: dataType,
            cache:false,
			success:function(e){
				successfn(e);
			},
			error: function(d){
				errorfn(d);
			}
		});
	};
//		form表单
	jQuery.axForForm=function (formId,successfn) {
		var url=formId.attr('action');
		data=formId.serialize();
		type=formId.attr('method').toUpperCase();
		jQuery.ax(url,data,type,null,'json',successfn,function (){});
	};
//		直接给接口传入
	jQuery.axForUrl=function(url,data,successfn){
		if(!url)return;
		var errorfn=errorfn || function (){};
//			注意后台返回的数据类型是json，还是text
		jQuery.ax(url,null,null,null,'json',successfn,errorfn);
	};
})