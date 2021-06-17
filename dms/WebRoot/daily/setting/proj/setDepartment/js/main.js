$(function(){
	
	var arr = new Array();
	getTree();
	function getTree(){
	    $.ajax({
	      type: "post", //请求方式
	      url: "/dms/daily/setting/proj/ProjectServlet?service=getCheckBoxTree",//请求路径
	      cache: false,
	      //data: {tree:"jsonString"}, //传参
	      dataType: "json",//返回值类型
	      success:function(data){
	    	  $('#treeview').treeview({
	    			bootstrap2: true, 
	    			showTags: true,
	    			enableLinks: true,
	    			data: data,
	    			showCheckbox: true,
	    			
	    			onNodeChecked: function(event,data){
	    		  		arr.push(data.id);
	    		  		console.log("The onNodeChecked arr is: %s",arr.join());
	    			},
	    	  		onNodeUnchecked : function(event,data){
	    				arr.remove(data.id);  
	    		  		console.log("The onNodeUnchecked arr is: %s",arr.join());
	    			}
	    		});
	      },
	      error:function(){
	    	  bootbox.alert("获取数据时发生错误");
	      }
	    });
	    
	}
	
	getDeptID();
	function getDeptID(){
	    $.ajax({
	      type: "post", //请求方式
	      url: "/dms/daily/setting/proj/ProjectServlet?service=getDeptID",//请求路径
	      cache: false,
	      dataType: "json",//返回值类型
	      success:function(data){
	    	  arr=data;
	    	  console.log("The arr is: %s",arr.join());
	      },
	      error:function(){
	    	  bootbox.alert("获取数据时发生错误");
	      }
	    });
	    
	}
	
	Array.prototype.indexOf = function(val) {              
	    for (var i = 0; i < this.length; i++) {  
	        if (this[i] == val) return i;  
	    }  
	    return -1;  
	};
	
	Array.prototype.remove = function(val) {  
	    var index = this.indexOf(val);  
	    if (index > -1) {
		    for(var i=0,n=0;i<this.length;i++) 
		    { 
		        if(this[i]!=this[index]) 
		        { 
		            this[n++]=this[i];
		        } 
		    } 
		    this.length-=1;
	    }  
	};  
	
	var prjid = $("#prjid").val(); 
	var setDeptUrl = "/dms/daily/setting/proj/ProjectServlet?service=updateDept&prjID="+prjid;
	
	var $setBtn = $("#set-btn");
	$setBtn.on("click.set", updateDept);
	
	function updateDept() {
		var jsonArr = {};
		for(var i=0;i<arr.length;i++){
			jsonArr[i] = arr[i];
		}
		var jsonString = JSON.stringify(jsonArr);
		console.log("The jsonString: %s",jsonString);
		
		$.ajax({
		    dataType: "json",
		    type: "POST",
		    url: setDeptUrl,
		    cache: false,
		    data: {"0":jsonString},
		    success: function (responseJSON) {
		    },
		});
		//{"0":"19","1":"21"}
		bootbox.alert("配置部门[" + jsonString + "]");
		
	}

});

function goBack(){
	javascript :history.back(-1);
};


function doDeleteCheck(){
	alert("确认删除?");
};


