<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改用户</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../../css/bootstrap-theme.min.css"/>

    <script src="../../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <style>
        *{
            margin: 0px;
            font-family: STKaiti;
            font-weight: 700;
            font-size: 22px;
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

        #addDrugDevice_text1,
        #addDrugDevice_text2,
        #addDrugDevice_text3,
        #addDrugDevice_text4,{
            width: 100%;
            font-weight: 700;
            font-size: 20px;
        }
        #addDrugDevice_text5{
            margin-top: 12%;
            width: 78%;
            font-size: 16px;
        }
        #addDrugDevice_text6{
            font-size:26px;
        }


    </style>

</head>
<body>
<div class="container-fluid" id="addDrugDevice_main">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-4" id="addDrugDevice_row_one">
            <p id="container-fluid_p1">修改用户</p>
        </div>

        <form action="/project2/UpdateUser" method="post" onsubmit="document.charset='GBK';">
        <input type="hidden" name="id" value="${userBean.id}">
            <div class="row" id="addDrugDevice_row_userName">
                <div class="col-sm-3 col-sm-offset-2">
                    <label>用户名</label>
                </div>
                <div class="col-sm-5">
                   <lable>${userBean.userName}</lable>
                </div>
            </div>
            <div class="row" id="addDrugDevice_row_pwd">
                <div class="col-sm-3 col-sm-offset-2">
                    <label  for="addDrugDevice_text2" class="addDrugDevice_label">新密码</label>
                </div>
                <div class="col-sm-5">
                    <input type="text" id="addDrugDevice_text2" name="pwd"/>
                </div>
            </div>
            <div class="row" id="addDrugDevice_row_pwd1">
                <div class="col-sm-3 col-sm-offset-2">
                    <label  for="addDrugDevice_text3" class="addDrugDevice_label">确认密码</label>
                </div>
                <div class="col-sm-5">
                    <input type="text" id="addDrugDevice_text3"/>
                </div>
            </div>
            <div class="row" id="addDrugDevice_row_name">
                <div class="col-sm-3 col-sm-offset-2">
                    <label>真实姓名</label>
                </div>
                <div class="col-sm-5">
                    <label>${userBean.name}</label>
                </div>
            </div>
            <div class="row"  id="addDrugDevice_row_fout">
                <div  class="col-sm-3 col-sm-offset-2" >
                    <label for="addDrugDevice_text3" class="addDrugDevice_label">用户等级</label>
                </div>
                <div class="col-sm-3">
                    <select name="level" class="btn btn-default btn-sm btn-primary form-control" id="addDrugDevice_text5">
                        <option value="超级管理员">超级管理员</option>
                        <option value="资料管理员">资料管理员</option>
                        <option value="灾情管理员">灾情管理员</option>
                        <option value="专家管理员">专家管理员</option>
                        <option value="库房管理员">库房管理员</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3 col-sm-offset-5">
                    <input type="submit" value="修改" class="btn btn-info btn-sm" id="addDrugDevice_text6"/>
                </div>
            </div>

        </form>

    </div>
</div>
</body>
</html>