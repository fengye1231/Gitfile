<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<%-- 内容开始 --%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="<%= basePath %>admin/menu/servlet/MenuServlet?service=modifyMenu" id="menu"
				method="post" data-toggle="validator" class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						修改菜单
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="menuName" class="col-sm-3 control-label">
								菜单名<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="menuName"
									id="menuName" value="${menu.menuName }"
									onkeyup="validateName(this.value)" required
									maxlength="25">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="menuCode" class="col-sm-3 control-label">
								菜单编号<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="menuCode"
									id="menuCode" value="${menu.menuCode }"
									onkeyup="validateCode(this.value)" required
									maxlength="25">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="sel" class="col-sm-3 control-label">
								父菜单
							</label>
							<div class="col-sm-9">
								<select id="sel" class="form-control" onchange="getChange()">
								  <option selected="selected" value="0">请选择</option>
								</select>
								<input type="hidden" name="parentMenuId" id="parentMenuId" />
							</div>
						</div>
						<div class="form-group">
							<label for="menuPath" class="col-sm-3 control-label">
								链接
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="menuPath"
									id="menuPath" value="${menu.menuPath }" maxlength="255">
							</div>
							<input type="hidden" name="parentMenuId" value="${menu.parentMenuId }">
						</div>
					</div>
					<input type="hidden" name="menuId" value="${menu.menuId }">
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
						    <div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block" href="../" onClick="goBack()">
									返 回
								</a>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3">
								<input class="btn btn-primary btn-block" id="add" type="submit"
									value="确认" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<%-- 内容结束 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
