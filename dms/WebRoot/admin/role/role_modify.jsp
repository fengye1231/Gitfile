<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp"></jsp:include>
<!--  
  <div class="container-fluid">
  <div class="row">
    <div class="col-lg-4 col-xs-offset-1">
      <div class="panel panel-default">
        <div class="panel-heading">角色修改</div>
        <form action="/dms/servlet/RoleServlet?service=update"  method="post" data-toggle="validator">
          <div class="panel-body">
           <div class="row">
           <div class="col-sm-6">
            <div class="form-group">
              <lable>角色ID:</lable>
              <lable>${param.roleCode}</lable>
              <input type="hidden" name="roleCode" value="${param.roleCode}" />
            </div>
            </div>
            <div class="col-sm-6 check_top"><span id="name"></span></div>         
            </div>
            <div class="form-group">
              <lable>角色名称:</lable>
              <input class="form-control" type="text" name="roleName"  placeholder=<%=new String(request.getParameter("roleName").getBytes("iso-8859-1"),"utf-8") %> required="required" maxlength="128"/>
            </div>
          </div>
          <div class="panel-footer">
            <div class="row">
              <div class="col-sm-6"></div>
              <div class="col-sm-3 ">
                <input class="btn btn-primary" type="submit" value="确认"/>
              </div>
              <div class="col-sm-3">
                <input class="btn btn-primary" type="reset" value="重置"/>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
-->
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="/dms/admin/role/servlet/RoleServlet?service=update" method="post"
			 data-toggle="validator" class="form-horizontal">
			 <input type="hidden" name="roleCode" value="${param.roleCode}" />
				<div class="panel panel-default">
					<div class="panel-heading">
						添加角色
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="roleId" class="col-sm-3 control-label">
									角色ID<font color="#f00"> *</font>
								</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="roleCode"
									id="prpAbbr" placeholder="${param.roleCode}" required disabled>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="roleName" class="col-sm-3 control-label">
								角色名称<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="roleName"
									id="roleName" value=<%=new String(request.getParameter("roleName").getBytes("iso-8859-1"),"utf-8") %> required>
								<div class="help-block with-errors"></div>
							</div>
						</div>
				</div>
				<div class="panel-footer">
					<div class="form-group no-margin-bottom">
						<div class="col-sm-3 col-sm-offset-6">
							<button class="btn btn-default btn-block" type="reset">
								重 置
							</button>
						</div>
						<div class="col-sm-3">
							<input class="btn btn-primary btn-block" type="submit" value="确认" />
						</div>
					</div>
				</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/include/footer.jsp"></jsp:include>