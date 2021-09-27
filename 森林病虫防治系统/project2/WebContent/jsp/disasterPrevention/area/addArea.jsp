<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域添加</title>
   <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css">

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script type="javascript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap-paginator.min.js"></script>
</head>
<style>
        *{
            margin: 0px;
            font-family: STKaiti;
            font-weight: 700;
            font-size: 26px;
        }
        #addDrugDevice_main{
            width: 731px;
            height: 529px;
        }
        #container-fluid_p1{
            font-size: 36px;
            font-weight: 700;
            font-family: STKaiti;
            text-shadow: black;

        }
        .col-sm-3{
            margin-top: 15px;

        }
        #addDrugDevice_row_fout{
            margin-top: 10px;
        }

        #addDrugDevice_text1{
            width: 100%;
            font-weight: 700;
            font-size: 20px;
        }
        #addDrugDevice_text5{
            margin-top: 12%;
            width: 78%;
            font-size: 26px;
        }
        #area1, #area2, #area3{
        color: red;
        display:none;
        
        }


    </style>



<body>
<div class="container-fluid" id="addDrugDevice_main">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-4" id="addDrugDevice_row_one">
            <p id="container-fluid_p1">添加区域</p>
        </div>

        <form action="/project2/add"  method="post"  onsubmit="document.charset='GBK';" id="form1">
            <div class="row" id="addDrugDevice_row_name">
                <div class="col-sm-3 col-sm-offset-2">
                    <label  for="addDrugDevice_text1" class="addDrugDevice_label">名称</label>
                </div>
                <div class="col-sm-5">
                    <input type="text" id="addDrugDevice_text1" name="name" placeholder="请输入中文区域名称" />
                    <span id="area1">必须填写区域名称2~10个汉字</span>
                </div>
            </div>
            <div class="row" id="addDrugDevice_row_forest">
                <div class="col-sm-3 col-sm-offset-2">
                    <label  for="addDrugDevice_text2" class="addDrugDevice_label">林种</label>
                </div>
                <div class="col-sm-5">
                    <input type="text" id="addDrugDevice_text2" name="species" placeholder="请输入中文林中" />
                    <span id="area2">必须填写林种2~10个汉字</span>
                </div>
            </div>
            <div class="row" id="addDrugDevice_row_two">
                <div class="col-sm-3 col-sm-offset-2">
                    <label  for="addDrugDevice_text3" class="addDrugDevice_label">优势树种</label>
                </div>
                <div class="col-sm-5">
                    <input type="text"  id="addDrugDevice_text3" name="great"  placeholder="请输入中文优势树种" />
                    <span id="area3">必须填写优势树种2~10个汉字</span>
                </div>
            </div>
            <div class="row"  id="addDrugDevice_row_fout">
                <div  class="col-sm-3 col-sm-offset-2" >
                    <label for="addDrugDevice_text3" class="addDrugDevice_label">类别</label>
                </div>
                <div class="col-sm-3">
                    <select name="sel" class="btn btn-default btn-sm btn-primary form-control" id="addDrugDevice_text4" >
                        <option value="林地">林地</option>
                        <option value="疏林地">疏林地</option>
                        <option value="灌林地">灌林地</option>
                        <option value="苗圃地">苗圃地</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3 col-sm-offset-5">
                    <input type="submit" value="添加" class="btn btn-info btn-sm" id="addDrugDevice_text5"/>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
<script type="text/javascript">

var  form= $("#form1");

    var boo1 = false;
	var boo2 = false;
	var boo3 = false;
	
	//判断区域
	$("#addDrugDevice_text1").blur(function(){
	    var regex = /^[\u4e00-\u9fa5]{2,10}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#area1").css({display:"none"});
	    }else{
	    	$("#area1").css({display:"inline"});
	    }
	    boo1=a;
	})
	//判断林中
	$("#addDrugDevice_text2").blur(function(){
	    var regex = /^[\u4e00-\u9fa5]{2,10}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#area2").css({display:"none"});
	    }else{
	    	$("#area2").css({display:"inline"});
	    }
	    boo2=a;
	})
	
	$("#addDrugDevice_text3").blur(function(){
	    var regex = /^[\u4e00-\u9fa5]{2,10}$/;
	    var a = regex.test(this.value);
	    if(a){
	    	$("#area3").css({display:"none"});
	    }else{
	    	$("#area3").css({display:"inline"});
	    }
	    boo3=a;
	})

$("#form1").submit(function(){
	
	$("#addDrugDevice_text1").blur();
	$("#addDrugDevice_text2").blur();
	$("#addDrugDevice_text3").blur();
	return boo1&&boo2&&boo3;
	
})
		

    
</script>
</html>