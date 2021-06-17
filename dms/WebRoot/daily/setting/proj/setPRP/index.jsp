<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>

<init-msg>${tip}</init-msg>

<script>
	var prps = ${prps};
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
			<form action="#" method="post" data-toggle="validator"
				class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						PRP配置
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-12">
								<table id="table" data-dms-table></table>
							</div>
						</div>
					</div>
					<input type="hidden" id="prjid" value="${prjID}"/>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block" href="/dms/daily/setting/proj/index.jsp">
									返 回
								</a>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-primary btn-block" id="set-btn"
									type="submit">
									确 认
								</button>
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