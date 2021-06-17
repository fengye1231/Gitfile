<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.neusoft.dms.prj.domain.*,com.neusoft.dms.daily.entry.controller.*,com.neusoft.dms.daily.entry.service.*,com.neusoft.dms.util.ServletUtil,com.neusoft.dms.util.State,
	com.neusoft.dms.domain.Page,com.neusoft.dms.daily.entry.domain.*,net.sf.json.JSONObject,com.neusoft.dms.employee.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"%>

<%
	DailyService dailyService = DailyServiceImpl.getInstance();
    //DailyServlet dailyServlet = new DailyServlet();
    DailyCon dailyCon = (DailyCon)session.getAttribute("dailyCon");
    if(dailyCon==null){
    	dailyCon =new DailyCon();
    	dailyCon.setEmpId(Integer.valueOf(((EmployeeVo)session.getAttribute("employee")).getEmpId()));
    	session.setAttribute("dailyCon",dailyCon);
    }
    Page dailyPage = dailyService.listDaily(dailyCon);
    //dailyServlet.listDaily(this.service(request,response));
    //JsonObject obj=new JsonObject();
    //outputJson(response, JSONObject.fromObject(dailyPage), State.OK, null);
	List<PrjEmpVo> prjs = dailyService.getPrjsEmp(Integer.valueOf(((EmployeeVo)session.getAttribute("employee")).getEmpId()));
	Map<String, List<PrjPrpVo>> prjPrpMap = new HashMap<String, List<PrjPrpVo>>();
	for (PrjEmpVo prj : prjs) {
		prjPrpMap.put(String.valueOf(prj.getPrjID()), dailyService
				.getPrp(prj.getPrjID()));
	}

	request.setAttribute("prjs", prjs);
%>
<jsp:include page="/include/header.jsp"></jsp:include>

<script type="text/javascript">
var projectPrp = <%=JSONObject.fromObject(prjPrpMap)%>;
<%--var table = <%=JSONObject.fromObject(dailyPage)%>;--%>
<%--<%ServletUtil.output(response, dailyPage);%>;--%>
</script>
<init-msg>${msg}</init-msg>

<div class="container-fluid">
	<form action="#" method="post" id="form-query" data-toggle="validator">
		<div class="panel panel-default">
			<div class="panel-body no-padding-bottom">
				<div class="row">
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">开始日期</span>
							<input type="text" readonly id="start-date"
								class="form-datetime form-control" data-lte="#end-date"
								data-max="today" data-default="today" data-name="startDate"
								placeholder="请选择" required>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">结束日期</span>
							<input type="text" readonly id="end-date"
								class="form-datetime form-control" data-max="today"
								data-default="today" data-name="endDate" placeholder="请选择"
								required>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">项目</span>
							<select class="form-control" id="projId" name="projId">
								<option value="">
									全部
								</option>
								<c:forEach items="${prjs}" var="prjs">
									<option value="${prjs.prjID}">
										${prjs.prjName}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">PRP阶段</span>
							<select class="form-control" name="prpId" id="prpId">
							</select>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group input-group">
							<span class="input-group-addon">审核状态</span>
							<select class="form-control" name="status">
								<option value="">
									全部
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
								<option value="未审核">
									未审核
								</option>
							</select>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div class="form-group input-group">
							<span class="input-group-addon">任务描述</span>
							<input class="form-control" type="text" name="desc">
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group">
							<button class="btn btn-default btn-block" type="reset">
								重 置
							</button>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group">
							<a class="btn btn-primary btn-block"
								href="/dms/daily/entry/manage/add.jsp"> 添 加 </a>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group">
							<button id="del-btn" class="btn btn-primary btn-block">
								删 除
							</button>
						</div>
					</div>
					<div class="col-sm-6 col-md-3">
						<div class="form-group">
							<button id="change-btn" class="btn btn-primary btn-block">
								修 改
							</button>
						</div>
					</div>
					<div class="col-sm-12 col-md-3">
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								查 询
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table id="table" data-dms-table></table>
			</div>
		</div>
	</form>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content"></div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<jsp:include page="/include/footer.jsp"></jsp:include>