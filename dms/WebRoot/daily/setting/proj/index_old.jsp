<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<body onload="tipMsg('${tip}');setPage(${page.pageNum},${page.totalPage})">
<div class="container-fluid">
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<form onclick="return checkAllEmpty();" action="/dms/ProjectServlet?service=searchPrj" onclick="checkAllEmpty();" data-toggle="validator" method="post">
                 	<div class="col-sm-5">
                 		<div class="form-group">
                  			<div class="input-group">
  								<span class="input-group-addon">项目编码</span>
         						<input type="text" class="form-control" id="projectCode" placeholder="项目编码最多有8位"
         						 maxlength="8">
         					 	<div class="help-block with-errors"></div>
      					 	</div>
      					 </div>
                  	</div>
                  	<div class="col-sm-5">
                  		<div class="form-group">
                  			<div class="input-group">
  								<span class="input-group-addon">项目名称</span>
         						<input type="text" class="form-control" id="projectName"
         						 maxlength="8" placeholder="项目名称最都有20位">
      						 </div>
      					</div>
                  	</div>
                  	
					 <div class="col-sm-2">
						<button type="reset" class="btn btn-primary btn-outline btn-block">重置</button>
					 </div>
					
					<div class="col-sm-5">
						<div class="form-group input-group">
							<span class="input-group-addon">开始日期</span>
							<input type="text" readonly id="start-date" class="form-datetime form-control" data-lte="#end-date"
								data-max="today"  placeholder="请选择" data-name="start-date">
						</div>
					</div>
					<div class="col-sm-5">
						<div class="form-group input-group">
							<span class="input-group-addon">结束日期</span>
							<input type="text" readonly id="end-date" class="form-datetime form-control"
								data-max="today" placeholder="请选择" data-name="end-date">
						</div>
					</div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary btn-block">查询</button>
					</div>
				</form>
				<form onclick="doDeletCheck()" action="" onclick="doDeletCheck()">
					<div class="col-sm-12"><p></p></div>
					<div class="col-sm-12">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th><input type=checkbox onclick="selectAll()" name="selectAll">全选</th>
										<th>项目编码</th>
										<th>项目名称</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>项目状态</th>
										<th>项目备注</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
		     						<c:if test="${page.list==null||fn:length(page.list)==0}">
		        						<tr align="center">
		          							<td colspan="8">没有符合条件的数据</td>
		       							 </tr>
		     						</c:if>
		     						<c:forEach items="${page.list}" var="prj">
		     							<tr>
		     								<td><label><input type="checkbox" name="selectPrj" id="${prj.prjID}"></label></td>
		     	 							<td>${prj.prjCode}</td>
											<td>${prj.prjName}</td>
											<td>${prj.startDate}</td>
											<td>${prj.endDate}</td>
											<td>${prj.status}</td>
											<td>${prj.remark}</td>
											<td>
<!-- 				    							<a href="action=PrjojectServlet?service=modifyProject&prjID=${prj.prjID}" class="btn btn-link">修改项目</a> -->
				    							<button type="button" class="btn btn-link" id="update" onsubmit="PrjojectServlet?service=modifyProject&prjID=${prj.prjID}">修改项目</button>
												<a href="PrjojectServlet?service=setPRP&prjID=${prj.prjID}" class="btn btn-link">配置PRP阶段</a>
												<a href="PrjojectServlet?service=setDepartment&prjID=${prj.prjID}" class="btn btn-link">配置部门</a>
												<a href="PrjojectServlet?service=setStaff&prjID=${prj.prjID}" class="btn btn-link">配置人员</a>
											</td>
										</tr>
										
									</c:forEach>
									<tr>
		     								<td><label><input type="checkbox" name="selectPrj" id="${prj.prjID}"></label></td>
		     	 							<td>${prj.prjCode}</td>
											<td>${prj.prjName}</td>
											<td>${prj.startDate}</td>
											<td>${prj.endDate}</td>
											<td>${prj.status}</td>
											<td>${prj.remark}</td>
											<td>
				    							<a href="/dms/ProjectServlet?service=modifyProject&prjID=2" class="btn btn-link">修改项目</a>
												<a href="/dms/ProjectServlet?service=setPRP&prjID=2" class="btn btn-link">配置PRP阶段</a>
												<a href="ProjectServlet?service=setDepartment&prjID=2" class="btn btn-link">配置部门</a>
												<a href="ProjectServlet?service=setStaff&prjID=2" class="btn btn-link">配置人员</a>
											</td>
										</tr>
								</tbody>
							</table>
							<table align="center" class="title">
    							<tr  align="right">
									<td> 
										<form action="ProjectServlet?service=searchPrj" method="post" name="f2">
											<input type="hidden" name="orgNo" value="${condition[0]}"/>
											<input type="hidden" name="orgName" value="${condition[1]}"/>
											<input type="hidden" name="pageNum" value="${page.pageNum}" id="pageNum"/>
										</form>
										&lt;&lt;
										<input type="button" class="btn" value="首页" onclick="getList(1)"/> &lt;
    									<input type="button" class="btn" value="上一页" id="pre" onclick="getList(${page.pageNum-1})"/> | 
    									<input type="button" class="btn" value="下一页" id="next" onclick="getList(${page.pageNum+1})"/> &gt; 
    									<input type="button" class="btn" value="尾页" onclick="getList(${page.totalPage})"/> &gt;&gt;&nbsp;
         								  共${page.totalNum}条记录&nbsp;&nbsp;当前${page.pageNum}/${page.totalPage}页
	 								</td>
								</tr>
							</table>
							<div class="col-sm-4" ></div>
							<div class="col-sm-2">
								<a href="addProject" class="btn btn-primary btn-block">添加</a>
							</div>
							<div class="col-sm-2">
								<a href="#">
								<button type="button" class="btn btn-primary btn-block">删除</button>
								</a>
			  				</div>
						</div>
					
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</body>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>