<%@ page contentType="application/javascript;charset=UTF-8" %>

/***************************************************************/
DMS.getQueryString = function(data) {
	var params = [];
	for (var key in data) {
		params.push(encodeURIComponent(key) + "=" + encodeURIComponent(data[key]));
	}
	return params.join("&");
}

DMS.getQueryPart = function(url) {
	return url.indexOf("?") !== -1? url.split("?")[1]: "";
}

DMS.queryStringToObj = function(str) {
	var obj = {};
	var params = str.split("&");
	for (var key in params) {
		var parts = params[key].split("=");
		obj[parts[0]] = parts[1];
	}
	return obj;
}

DMS.serializeData = function(data) {
	if (typeof data !== "undefined") {
		if (data instanceof jQuery) {
			return data.serialize();
		} else if (typeof data === "object") {
			return DMS.getQueryString(data);
		}
	}
	return "";
}

DMS.mergeURL = function(url, queryString) {
	var startWith = url.indexOf("?") === -1? "?": "&";
	var result = url + (queryString != ""? startWith + queryString: "");
	return result;
}

/***************************************************************/
/**
 * 表格适配
 */
DMS.table = function(selector) {
	var _this = (function(_$table) {
		var _params = {
			classes: "table table-hover",
			//method: "post",
			contentType: "application/x-www-form-urlencoded",
			pagination: true, 
			pageSize: DMS.pageSize, 
			queryParamsType: "page",
			queryParams: _queryParams, 
			responseHandler: _responseHandler,
			sidePagination: 'server',
			pageList: [10, 25, 50, 100],
			postQueryParams: null
		};
		var _columnDefault = {
			cardVisible: true,
			checkbox: false,
			checkboxEnabled: true,
			clickToSelect: true,
			order: "asc",
			radio: false,
			searchable: true,
			sortable: false,
			switchable: true,
			visible: true
		};
		var _this = {},
			_data = {},
			_hasInited = false,
			_isForShow = false,
			_url;

		function _queryParams(params) {
			if (_isForShow) {
				return false;
			}
			params = {
				pageSize: params.pageSize,
				pageNum: params.pageNumber
			};
			$.extend(params, _data);
			if (typeof _params.postQueryParams === "function") {
				params = _params.postQueryParams(params);
			}
			return params;
		}

		function _responseHandler(resp) {
			if (resp.sc != DMS.state.OK) return false;
			var options = _$table.bootstrapTable("getOptions");
			var res = $.extend({}, resp.result);
			var opt = {
				columns: res.columns,
				totalRows: res.totalNum, 
				pageNumber: res.pageNum, 
				pageSize: res.pageSize, 
				showFooter: !!res.footer && !!res.list && res.list.length
			};
			if (res.footer) {
				if (res.columns) {
					$.each(res.footer, function(i, m) {
						var index = _getIndexByKeyValue(opt.columns, "field", m.field);
						opt.columns[index].footerFormatter = (function(value) {
							return function() { return value + ""; };
						})(m.value);
					});
				} else {
					$.each(res.footer, function(i, m) {
						var index = _getIndexByKeyValue(options.columns, "field", m.field);
						options.columns[index].footerFormatter = (function(value) {
							return function() { return value + ""; };
						})(m.value);
					});
				}
			}
			$.extend(options, opt);
			if (opt.columns) {
				$.each(options.columns, function(i, m) {
					$.extend(m, _columnDefault, (_params.columns? _params.columns[i]: {}), opt.columns[i]);
				});				
			}
			return {
				total: res.totalNum,
				rows: res.list
			};
		}

		function _getIndexByKeyValue(list, key, value) {
			if (list) {
				for(var i = 0; i < list.length; i++) {
					if (list[i][key] == value) {
						return i;
					}
				}
			}
			return -1;
		}

		function _setDataSource(url, data) {
			var queryString = DMS.getQueryPart(url);
			var queryObj = DMS.queryStringToObj(queryString);
			_params.url = _url = url;
			_data = $.extend({}, queryObj, DMS.queryStringToObj(DMS.serializeData(data)));
			return _this;
		}

		function _set(params) {
			$.extend(_params, params);
			if (_hasInited) {
				$.extend(_$table.bootstrapTable("getOptions"), params);
			}
			return _this;
		}

		function _loadData() {
			_setTable();
		}

		function _setTable() {
			_$table.show();
			if (_hasInited) {
				$.extend(_$table.bootstrapTable("getOptions"), {url: _url});
				_$table.bootstrapTable("refresh");
			} else {
				_$table.bootstrapTable(_params);
				$(window).resize(function() {
					_$table.bootstrapTable("resetView");
				});
			}
			_hasInited = true;
		}

		function _show() {
			_$table.show();
			if (_hasInited) {
				_$table.bootstrapTable("resetView");
			} else {
				_isForShow = true;
				_setDataSource("#");
				_loadData();
				_isForShow = false;
			}
			return _this;
		}

		_this.setDataSource = _setDataSource;
		_this.set = _set;
		_this.loadData = _loadData;
		_this.show = _show;
		_this.bootstrapTable = _$table.bootstrapTable;

		return _this;
	})($(selector));
	
	return _this;
};

	/***************************************************************/
	(function(){
		/**
		 * 日期选择器增强
		 */
		DMS.date = {};
		
	    DMS.date.init = function($date){
	    	
		    $date.datetimepicker({
		        format: "yyyy-mm-dd",
		        autoclose: true
		    });
		    $date.each(function(){
		    	var $that = $(this);
			    $that.data("default", toDate($that.attr("data-default")));
			    $that.data("min", toDate($that.attr("data-min")));
			    $that.data("max", toDate($that.attr("data-max")));
			    $that.data("scope", toScopeDate($that.attr("data-maxinterval")));
			    $that.data("name", $that.attr("data-name"));
		    });	    	
	    	
	    	$date.each(function(){
	    		var $that = $(this),
	    		min = $that.data("min"),
	    		max = $that.data("max"),
	    		date = $that.data("default"),
	    		name = $that.data("name");

	    		date && $that.datetimepicker("update",  date);
	    		min && $that.datetimepicker("setStartDate", min);
	    		max && $that.datetimepicker("setEndDate", max);	    		

	    		if (name) {
	    			var $form = $that.closest("form"),
	    				$hidden = $("<input type='hidden'></input>").attr("name", name);
	    			$form.append($hidden);
	    			$that.datetimepicker().on("changeDate.name", function(e){
						$hidden.val(e.date.getTime());
					});
					if (date instanceof Date) {
						$hidden.val(date.getTime());
					}
	    		}
	    	});
	    	
			/**
			 * 日期选择区间增强
			 */
		    $date.filter("[data-lte]").each(function(){
		    	var $that = $(this),
		    		$tar = $($that.attr("data-lte")),
		    		scope = $that.data("scope");
	
		    	$that.datetimepicker().on("changeDate.dms", function(e){
		    		adjustScope($tar, "start", e.date, scope);
		    	});
		    	$tar.datetimepicker().on("changeDate.dms", function(e){
		    		adjustScope($that, "end", e.date, scope);
		    	});
		    	//adjustScope($tar, "start", $that.datetimepicker("getDate"));
		    });
	    }

	    function refrashHiddenValue($tar) {
			var $form = $tar.closest("form"),
				$hidden = $form.find("[name='" + $tar.attr("data-name") + "']");
			$hidden.val($tar.datetimepicker("getDate").getTime());
	    }

	    function adjustScope($tar, type, date, scope){
			var tarMin = $tar.data("min"),
				tarMax = $tar.data("max"),
				endDate, stratDate;

			if (type == "start") {
				// if (tarMin < date) {
				// 	$tar.datetimepicker("setStartDate", date);
				// }
				if (scope) {
					if (!tarMax || (tarMax.getTime() - date.getTime() > scope.getTime())) {
						endDate = new Date(date.getTime() + scope.getTime());
						//$tar.datetimepicker("setEndDate", endDate);
				    	if ($tar.datetimepicker("getDate") > endDate) {
				    		$tar.datetimepicker("update", endDate);
				    		refrashHiddenValue($tar);
				    	}
					}
				}
		    	if ($tar.datetimepicker("getDate") < date) {
		    		$tar.datetimepicker("update", date);
		    		refrashHiddenValue($tar);
		    	}
			} else {
				// if (tarMax > date) {
				//	$tar.datetimepicker("setEndDate", date);
				// }
		    	if ($tar.datetimepicker("getDate") > date) {
		    		$tar.datetimepicker("update", date);
		    		refrashHiddenValue($tar);
		    	}
				if (scope) {
					if (!tarMin || (date.getTime() - tarMin.getTime() > scope.getTime())) {
						stratDate = new Date(date.getTime() - scope.getTime());
						//$tar.datetimepicker("setStartDate", stratDate);
				    	if ($tar.datetimepicker("getDate") < stratDate) {
				    		$tar.datetimepicker("update", stratDate);
				    		refrashHiddenValue($tar);
				    	}
					}
				}
			}
	    }

	    function toDate(str){
	    	if (str && "today" == str.toLowerCase()) {
	    		return new Date();
	    	}
	    	var parts = str? str.split(/[-\|\/]+/): [];
	    	if (parts.length === 3) {
	    		var date = new Date();
	    		date.setFullYear(parts[0] * 1, parts[1] * 1 - 1, parts[2] * 1);
	    		return date;
	    	}
	    	return null;
	    }
	    function toScopeDate(str){
	    	var parts = str? str.split(/[-\|\/]+/): [];
	    	if (parts.length === 3) {
	    		var date = new Date(0);
	    		date.setFullYear(parts[0] * 1 + 1970, parts[1] * 1, parts[2] * 1 + 1);
	    		return date;
	    	}	    	
	    	return null;
	    }
	})();

/***************************************************************/

$(function(){
	/***************************************************************/
	
	DMS.date.init($(".form-datetime"));

	/***************************************************************/
	(function(){
		/**
		 * 分页增强
		 */
		var $pagination = $(".pagination");
			
		$pagination.each(function(){
			var $that = $(this),
				total = $that.attr("data-total") * 1,
				visible = $that.attr("data-visible") * 1,
				start = $that.attr("data-start") * 1;

			if (!isNaN(total) && !isNaN(visible) && !isNaN(start)) {
				$that.twbsPagination({
				    totalPages: total,
				    visiblePages: visible,
				    startPage: start,
				    onPageClick: function (event, page) {
				        $that.trigger("pageclick", page);
				    }
				});
			}
		});
		//$pagination.on("pageclick", function(e, p){ console.log(p); });
	})();
	/***************************************************************/
	(function(){
		/**
		 * 弹窗加标题
		 */
		bootbox.alert = (function(origin) {
			return function() {
				if (typeof arguments[1] === "string") {
					origin.apply(this, arguments);
				} else {
					if (arguments.length === 2) {
						origin.call(this, arguments[0], DMS.title, arguments[1]);
					} else {
						origin.call(this, arguments[0], DMS.title);
					}
				}
			}
		})(bootbox.alert);
		/**
		 * 本地化
		 */
		bootbox.addLocale("dms", {
			OK : "确 定",
			CANCEL : "取 消",
			CONFIRM : "确 定"
		});
		bootbox.setDefaults({
			locale: "dms"
		});
	})();
	/***************************************************************/
	(function(){
		/**
		 * 表格适配
		 */
		$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
	})();
	/***************************************************************/
	(function(){
		/**
		 * 页面初始提示
		 */
		var msg = $("init-msg").text().trim();
		if (msg) {
			bootbox.alert(msg);
		}
	})();
	/***************************************************************/	
});



/**
 * 日期格式化
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth()+1, 
		"d+" : this.getDate(), 
		"h+" : this.getHours(), 
		"m+" : this.getMinutes(), 
		"s+" : this.getSeconds(), 
		"q+" : Math.floor((this.getMonth()+3)/3), 
		"S" : this.getMilliseconds() 
	}
	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
}