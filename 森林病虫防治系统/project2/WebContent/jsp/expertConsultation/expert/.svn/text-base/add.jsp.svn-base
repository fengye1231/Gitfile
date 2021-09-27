<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/project2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/project2/css/bootstrap-theme.min.css"/>
    <script src="/project2/js/jQuery-2.2.2-min.js"></script>
    <script src="/project2/js/bootstrap.min.js"></script>
    <script src="/project2/js/bootstrap-paginator.min.js"></script>
    <script src="/project2/js/DatePicker/WdatePicker.js"></script>

    <style>
        #main{
            width: 100%;
            height: 500px;
        }
        .page-header{
            text-align: center;
            margin: 0px;
        }
        h1{
            font-weight: 700;
            font-family: STKaiti;
        }
        .control-group{
            margin-bottom: 5%;
            margin-left: 3%;
            width:350px;
            height:30px;
            display: inline-block;
            float:left;
        }
        .control-label{
            width: 80px;
        }
        .form-horizontal .control-label {
            text-align: left;
        }
        .button-add{
            text-align: center;
        }
        button{
        	float:left;
        	margin-left:35%;
            width: 200px;
        }
        input[type=file] {
            display: inline-block;
        }
        span{
        	margin-left:80px;
        	color:red;
        }

        #false1,#false2,#false3,#false4,#false5,#false6,#false7,#false8,#false9,#false10{
        	display:none;
        }
    </style>
</head>
<body>
	<div id="main">
        <div class="page-header">
            <h1>添加专家信息</h1>
        </div>
        <form class="form-horizontal" action="/project2/addExpert" method="post" id="form1" enctype="multipart/form-data">
            <div class="control-group">
                <label class="control-label" for="input01">姓名</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="name" id="input01">
                </br><span id="false1">姓名只能是2-4位汉字</span>
            </div>
            <div class="control-group">
                <label class="control-label">性别</label>
                <input type="radio" name="gender" value="男"/>男&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="gender" value="女"/>女
                </br><span id="false2">必须填写性别</span>
            </div>
            <div class="control-group">
                <label class="control-label" for="input02">出生年月</label>
                <input type="text" placeholder="请选择生日" class="input-xlarge" name="date" id="birthday">
                <img src="/project2/js/DatePicker/skin/datePicker.gif" alt="" style="cursor: pointer" onclick="WdatePicker({el:'birthday'})"/>
                </br><span id="false3">必须选择出生年月日</span>
            </div>
            <div class="control-group">
                <label class="control-label">照片</label>
                <input class="input-file" name="fileImg" type="file" id="input02">
                </br><span id="false4">照片只能为jpg、gif、jpeg或png格式</span>
            </div>
            <div class="control-group">
                <label class="control-label" for="input03">专长</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="special" id="input03">
                </br><span id="false5">专长必须是2-10位汉字</span>
            </div>
            <div class="control-group">
                <label class="control-label" for="input04">职务</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="position" id="input04">
                </br><span id="false6">职务必须是2-10位汉字</span>
            </div>
            <div class="control-group">
                <label class="control-label" for="input05">电话</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="phoneNum" id="input05">
                </br><span id="false7">电话号码必须为13,15,18开头的11位数字</span>
            </div>
            <div class="control-group">
                <label class="control-label" for="input06" id="workId">工作单位</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="workPlace" id="input06">
                </br><span id="false8">工作单位必须是4-20位汉字</span>
                </br>
            </div>
            <div class="control-group">
                <label class="control-label" for="input07">通讯地址</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="address" id="input07">
                </br><span id="false9">通讯地址必须是4-20位汉字</span>
                </br>
            </div>
            <div class="control-group">
                <label class="control-label" for="input08">邮箱</label>
                <input type="text" placeholder="placeholder" class="input-xlarge" name="email" id="input08">
                </br><span id="false10">邮箱格式必须是xx@xx.com</span>
            </div>
            <hr/>
            <div class="button-add">
                <button type="submit" class="btn btn-success" id="addButton">添加</button>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
	var form = $("#form1");
	
	var boo1 = false;
	var boo2 = false;
	var boo3 = false;
	var boo4 = false;
	var boo5 = false;
	var boo6 = false;
	var boo7 = false;
	var boo8 = false;
	var boo9 = false;

	//判断姓名
	$("#input01").blur(function(){
	    var regex = /^[\u4e00-\u9fa5]{2,6}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false1").css({display:"none"});
	    }else{
	    	$("#false1").css({display:"inline"});
	    }
	    boo1=a;
	})

    
    //判断上传图片后缀
    $("#input02").blur(function(){
	    var regex = /\.(gif|jpg|jpeg|png|GIF|JPG|JPEG|PNG)$/;
	    console.log(this.value);
	    var a = regex.test(this.value);
	    if(a){
	    	$("#false4").css({display:"none"});
	    }else{
	    	$("#false4").css({display:"inline"});
	    }
	    boo2=a;
	})
    
    
    
    //判断生日
    $("#birthday").blur(function(){
    	var regex = /^\d{4}(\-)\d{1,2}\1\d{1,2}$/;
    	var a = regex.test(this.value);
    	 if(a){
          	$("#false3").css({display:"none"});
          }else{
          	$("#false3").css({display:"inline"});
          }
          boo3=a;

    })
      
    //判断专长 
    $("#input03").blur(function(){
    	 var regex = /^[\u4e00-\u9fa5]{2,10}$/;
         var a = regex.test(this.value);
         if(a){
         	$("#false5").css({display:"none"});
         }else{
         	$("#false5").css({display:"inline"});
         }
         boo4=a;
    })
    
   //判断职务   
    $("#input04").blur(function(){
    	 var regex = /^[\u4e00-\u9fa5]{2,10}$/;
         var a = regex.test(this.value);
         if(a){
         	$("#false6").css({display:"none"});
         }else{
         	$("#false6").css({display:"inline"});
         }
         boo5=a;
    })
    
    
    //判断电话     
    $("#input05").blur(function(){
    	 var regex = /^((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+(\d{8})$/;
         var a = regex.test(this.value);
         if(a){
         	$("#false7").css({display:"none"});
         }else{
         	$("#false7").css({display:"inline"});
         }
         boo6=a;
    })
    
    
    //判断工作单位
     $("#input06").blur(function(){
    	 var regex = /^[\u4e00-\u9fa5]{2,10}$/;
         var a = regex.test(this.value);
         if(a){
         	$("#false8").css({display:"none"});
         }else{
         	$("#false8").css({display:"inline"});
         }
         boo7=a;
    })
    
   
    //判断通讯地址 
     $("#input07").blur(function(){
    	 var regex = /^[\u4e00-\u9fa5]{2,10}$/;
         var a = regex.test(this.value);
         if(a){
         	$("#false9").css({display:"none"});
         }else{
         	$("#false9").css({display:"inline"});
         }
         boo8=a;
    })
    
    
    
    //判断邮箱 
    $("#input08").blur(function(){
    	 var regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
         var a = regex.test(this.value);
         if(a){
         	$("#false10").css({display:"none"});
         }else{
         	$("#false10").css({display:"inline"});
         }
         boo9=a;
    })
    

form.submit(function(){
	$("#input01").blur();
	$("#input03").blur();
	$("#input04").blur();
	$("#input05").blur();
	$("#input06").blur();
	$("#input07").blur();
	$("#input08").blur();
    return boo1&&boo2&&boo3&&boo4&&boo5&&boo6&&boo7&&boo8&&boo9;
})

</script>
</html>