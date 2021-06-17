<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>
<%-- 内容开始 --%>

<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-body no-padding-bottom">
			<div class="row">
				<form action="#" method="post" id="form-query"
					data-toggle="validator">
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">菜单名</span>
							<input class="form-control" type="text" name="menuName" />
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">父菜单</span>
							<select id="sel" class="form-control" onchange="getChange()">
							  <option selected="selected" value="0">请选择</option>
							</select>
							<input type="hidden" name="parentMenuId" id="parentMenuId" />
							<div id="menuName" value=""></div>
						</div>
					</div>
					<div class="col-sm-4 col-md-2">
						<div class="form-group">
							<button type="reset" class="btn btn-default btn-block">
								重 置
							</button>
						</div>
					</div>
					<div class="col-sm-4 col-md-2">
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								查 询
							</button>
						</div>
					</div>
					<div class="col-sm-4 col-md-2">
						<div class="form-group">
							<a class="btn btn-primary btn-block"
								href="<%=basePath%>admin/menu/addmenu.jsp">增 加 </a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table id="table" data-dms-table></table>
		</div>
	</div>
</div>

<%-- 内容结束 --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
