<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>


<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-body no-padding-bottom">
			<div class="row">
				<form action="#" method="post" id="form-query"
					data-toggle="validator">
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">PRP缩写</span>
							<input class="form-control" type="text" name="prpAbbr">
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">PRP名称</span>
							<input class="form-control" type="text" name="prpName">
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
							<a type="button" href="<%= basePath %>daily/setting/prp/prp_add.jsp"
								class="btn btn-primary btn-block">
								添 加
							</a>
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


<jsp:include page="/include/footer.jsp"></jsp:include>