<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>添加药剂/器械</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <style>
        *{
            margin: 0px;
            font-family: STKaiti;
            font-weight: 700;
            font-size: 26px;
        }
        #addDrugDevice_main{
            width: 100%;
            height: 530px;
            overflow-x: hidden;
        }
        #container-fluid_p1{
            font-size: 36px;
            font-weight: 700;
            font-family: STKaiti;
            text-shadow: black;

        }
        #addDrugDevice_row_two{
        margin-top: 92px;

        }
        #addDrugDevice_row_three{
            margin-top: 11px;
        }
        #addDrugDevice_row_fout{
            margin-top: 10px;
        }

        #addDrugDevice_text1{
            width: 87%;
            font-weight: 700;
            font-size: 14px;
            
        }
        #addDrugDevice_text2,#addDrugDevice_text2{
            height: 36px;
            width: 48%;
            font-size: 17px;
        }
        #addDrugDevice_text2,#addDrugDevice_text3{

            height: 36px;
            width: 110%;
            font-size: 17px;
        }
        #addDrugDevice_five{
            margin-top: 5%;
        }
        #addDrugDevice_text5{
            margin-top: 12%;
            width: 78%;
            font-size: 26px;
        }

		#addDrugDevice_text1_span1{
				    margin-top: 6px;
		     	color: #07b344;
	           font-size: 14px;
	           display: none;
		}
		#addDrugDevice_text1_span2{
		  margin-top: 6px;
		   	color: red;
            font-size: 14px;
            display: none;
		}
    </style>

</head>
<body>
    <div class="container-fluid" id="addDrugDevice_main">
        <div class="row">
			            <div class="col-sm-8 col-sm-offset-4" id="addDrugDevice_row_one">
			                <p id="container-fluid_p1">添加药剂/器械</p>
			            </div>
		
		        <div class="row">
                        <form action="/project2/addMachine"  method="post" id="addDrugDevice_row_two_form">
                          <div class="row">
                            <div class="input-group col-sm-12" id="addDrugDevice_row_two">
                             
                                <div class="col-sm-3 col-sm-offset-2">
                                    <label  for="addDrugDevice_text1" class="addDrugDevice_label" id="addDrugDevice_label_name">名称</label>
                                </div>
                                <div class="col-sm-5">
                                    <input type="text" name="names" id="addDrugDevice_text1" placeholder="名字必须是字母数字或者汉字组成的" />
                                	  	<span class="glyphicon glyphicon-ok " id="addDrugDevice_text1_span1"></span>
                         				<span class="glyphicon glyphicon-remove" id="addDrugDevice_text1_span2"></span>
                                </div>
                            </div>
						</div>
							 </div>
                            <div class="row"  id="addDrugDevice_row_three">
                            <div class="col-sm-3 col-sm-offset-2">
                                <label for="addDrugDevice_text2" class="addDrugDevice_label">防止类型</label>
                            </div>
                                    <div class="col-sm-3">
                                        <select name="addDrugDeviceSel" class="btn btn-default btn-sm btn-primary form-control"  id="addDrugDevice_text2">
                                            <option value="鼠害">鼠害</option>
                                            <option value="虫害">虫害</option>
                                            <option value="病害">病害</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row"  id="addDrugDevice_row_fout">
                                    <div  class="col-sm-3 col-sm-offset-2" >
                                         <label for="addDrugDevice_text3" class="addDrugDevice_label">类别</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="sel" class="btn btn-default btn-sm btn-primary form-control" id="addDrugDevice_text3">
                                            <option value="药剂">药剂</option>
                                            <option value="器械">器械</option>
                                        </select>
                                    </div>
                                </div>
                            <div class="row" id="addDrugDevice_five">
                                <div class="col-sm-3 col-sm-offset-2">
                                    <label for="addDrugDevice_text4" class="addDrugDevice_label">主要用途</label>
                                </div>
                                <div >
                                    <textarea name="text" id="addDrugDevice_text4" cols="30" rows="4" placeholder="介绍必需在五个文字及以上" ></textarea>
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
<script>
	//还是加上正则吧
		//下面是输入框节点
		var InName = $("#addDrugDevice_text1");
		//下面是文本输入框
		var text = $("#addDrugDevice_text4");
		
		//下面是正则
		  var nameRegex = /^[0-9a-zA-Z\u2E80-\u9FFF]+$/
			var textRegex = /^.{5,}$/
	  		//创建失焦事件
	  		var bool1 = false;
			InName.blur(function(){
				bool1 = nameRegex.test($(this).val())
				
				if(bool1){
					$("#addDrugDevice_text1_span2").css({display:"none"});
					$("#addDrugDevice_text1_span1").css({display:"inline"});
				}else{
					$("#addDrugDevice_text1_span1").css({display:"none"});
					$("#addDrugDevice_text1_span2").css({display:"inline"});
				}
				
			});
			
			var bool2 = false;
			//文本框判断
			function IfText(){
				bool2 = textRegex.test(text.val());
			
			}
			//登陆判断
			$("#addDrugDevice_row_two_form").submit(function(){
				InName.blur();
				IfText();
				
				if(bool1&&bool2==false){
					text.val("");
					return bool1&&bool2;
				}
				return bool1&&bool2;
			});
			
			
</script >
</html>