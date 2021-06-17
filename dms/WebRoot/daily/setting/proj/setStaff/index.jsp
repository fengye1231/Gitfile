<%@page import="com.neusoft.dms.prj.domain.PrjEmpVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%!

private String getOtherStaffNames(List<PrjEmpVo> pes) {
	StringBuilder sb = new StringBuilder();
	for (PrjEmpVo pe : pes) {
		if (pe.getprID() == 12) {
			sb.append(pe.getEmpName()).append("  ");
		}
	}
	return sb.toString();
}

%>

<%

	List<PrjEmpVo> pes = (List<PrjEmpVo>) request.getAttribute("pes");
	

%>

<%-- 内容开始 --%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading"><center>${ prjName }负责人设置</center></div>
				<div class="panel-body no-padding-bottom">
					<form class="form-horizontal" role="form">
					
						<c:forEach items="${pes}" var="pe">
							<c:if test="${ pe.prID != 12 }">
								<div class="col-md-4 col-sm-6">
									<div class="row">
									    <label for="inputEmail3" class="col-sm-4 control-label">${ pe.resName }</label>
									    <div class="col-sm-4">
									      <input type="text" class="form-control" placeholder="请选择" readonly 
									      		value="${ pe.empName }">
									    </div>
										<div class="form-group col-sm-4">
											<a class="btn btn-primary btn-block" 
												href="/dms/daily/setting/proj/ProjectServlet?service=choseStaff&prjID=${prjID}&prID=${pe.prID}">
												选 择
											</a>
										</div>
								    </div>
								</div>
							</c:if>
						</c:forEach>
						
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading"><center>${ pe.prjName }参与人员设置</center></div>
				<div class="panel-body no-padding-bottom">
					<form role="form">
						<div class="form-group">
							<textarea class="form-control" rows="3" id="other-textarea"
									placeholder="请选择" readonly><%= getOtherStaffNames(pes) %>
							</textarea>
						</div>
						<div class="form-group">
							<center>
								<a class="btn btn-primary"
									href="/dms/daily/setting/proj/ProjectServlet?service=choseStaff&prjID=${prjID}&prID=12">
									选 择
								</a>
							</center>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-footer">
					<a type="button" class="btn btn-default" onclick="goBack()">
						返 回
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>