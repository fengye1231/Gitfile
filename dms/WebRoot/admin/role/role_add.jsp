<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<!--  
<div class="container-fluid">
  <div class="row">
    <div class="col-lg-4 col-xs-offset-1">
      <div class="panel panel-default">
        <div class="panel-heading">添加角色</div>
        <form action="/dms/servlet/RoleServlet?service=add" method="post" >
          <div class="panel-body">
            <div class="form-group">
              <lable> 角色ID:</lable>
              <input class="form-control" type="text" name="roleCode"  placeholder="请输入角色ID">
            </div>
            <div class="form-group">
              <lable> 角色名称:</lable>
              <input class="form-control" type="text" name="roleName" placeholder="请输入角色名称">
            </div>
          </div>
          <div class="panel-footer">
            <div class="row">
              <div class="col-sm-6"></div>
              <div class="col-sm-3 ">
                <input class="btn btn-primary" type="submit" value="确认">
              </div>
              <div class="col-sm-3">
                <input class="btn btn-primary" type="reset" value="重置">
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
			<form action="/dms/admin/role/servlet/RoleServlet?service=add" method="post"
			 data-toggle="validator" class="form-horizontal">
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
									id="prpAbbr" placeholder="请输入角色ID" required >
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="roleName" class="col-sm-3 control-label">
								角色名称<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="roleName"
									id="roleName" placeholder="请输入角色名称" required>
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