<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="<%= basePath %>daily/setting/prp/servlet/PrpServlet?service=add"
				method="post" data-toggle="validator" class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-heading">
						添加PRP阶段
					</div>
					<div class="panel-body">

						<div class="form-group">
							<label for="namesearch1" class="col-sm-3 control-label">
								PRP缩写<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="prpAbbr"  id="namesearch1" required="required">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="namesearch2" class="col-sm-3 control-label">
								PRP名称<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="prpName" id="namesearch2" required="required">
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">
								备注
							</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="remark"
									maxlength="200"
									rows="3" name="remark" >${prj.remark}</textarea>
							</div>
						</div>
					</div>
					<input type="hidden" name="menuId" value="${menu.menuId }">
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-3">
								<a class="btn btn-default btn-block" href="<%= basePath %>daily/setting/prp">
									返 回
								</a>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-primary btn-block"
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
                           
<jsp:include page="/include/footer.jsp"></jsp:include>