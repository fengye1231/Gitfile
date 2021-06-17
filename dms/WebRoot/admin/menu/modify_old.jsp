<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<%-- 内容开始 --%>

<div class="container-fluid">
<div class="row">
  <div class="col-lg-4 col-xs-offset-1">
    <div class="panel panel-default">
      <div class="panel-heading">菜单修改(<font color="red">*</font>必填)</div>
      <form action="/dms/servlet/MenuServlet?service=modifyMenu" id="menu" method="post" data-toggle="validator">
           <div class="panel-body">
           <div class="row">
            <div class="col-sm-6">
            <div class="form-group">
             <input type="hidden" name="parentMenuId" id="parentMenuId"/>
              <lable> 菜单名<font color="red">*</font></lable>
              <input class="form-control" type="text" name="menuName" id="menuName" placeholder="请输入菜单名 " onkeyup="validateName(this.value)" required="required" maxlength="25" >
               <div class="help-block with-errors"></div>
            </div>
            </div>
              <div class="col-sm-6 check_top"><span id="name"></span></div>         
            </div>
      
           <div class="row">
            <div class="col-sm-6">
            <div class="form-group">
              <lable> 菜单编号<font color="red">*</font></lable>
              <input class="form-control" type="text" name="menuCode" id="menuCode" placeholder="请输入菜单编号" onkeyup="validateCode(this.value)" required="required" maxlength="25">
              <div class="help-block with-errors"></div>
            </div>
            </div>
             <div class="col-sm-6 check_top"> <span id="code"></span></div>
           </div>
          <div class="form-group">
            <lable>父菜单:</lable>
             <select id="sel" class="form-control" onchange="getChange()">
             </select>
          </div>
          <div class="form-group">
            <input type="hidden" name="parentMenuId" value="${menu.parentMenuId }">
            <lable> 链接:</lable>
            <input class="form-control" type="text" name="menuPath" id="menuPath" value="${menu.menuPath }"  maxlength="255"/>
          </div>
        </div> 
         <input type="hidden" name="menuId" value="${menu.menuId }">
        <div class="panel-footer">
          <div class="row">
            <div class="col-sm-6"></div>
            <div class="col-sm-3">
              <input class="btn btn-primary" type="submit" value="确认"/>
            </div>
            <div class="col-sm-3">
              <input class="btn btn-primary btn-outline btn-block" type="reset" value="重置"/>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<%-- 内容结束 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
