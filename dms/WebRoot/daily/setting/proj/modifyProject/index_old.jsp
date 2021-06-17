<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>
<body onload="tipMsg('${tip}'),selStatus('${prjStatus}')">
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<form action="/dms/daily/setting/proj/ProjectServlet?service=updatePrj&prjID=${prj.prjID}" method="post" data-toggle="validator" role="form">
				<div class="panel panel-info">
                	<div class="panel-heading">
                     	<b>项目设定（<font color="#ff0000">*</font>必填）</b>
                     </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">                 
                                    <tbody>
                                    	<tr>
                                    		<td>
  												<div class="col-sm-6">
                  										<div class="input-group">
  															<span class="input-group-addon">项目编码<font color="#ff0000">*</font></span>
         													<input type="text" class="form-control" id="projectCode" name="projectCode"
         													maxlength="8" required value="${prj.prjCode}">
      													</div>
      					 							</div>
      					 					</td>
		 								</tr>
		 								<tr>
		 									<td>
		 										<div class="col-sm-6">
                  										<div class="input-group">
  															<span class="input-group-addon">项目名称<font color="#ff0000">*</font></span>
         													<input type="text" class="form-control" id="projectName" name="projectName"
         													maxlength="20" required value="${prj.prjName}">
      													</div>
      					 							</div>
		 									</td>
										</tr>
										<tr>											
											<td>
												<div class="col-sm-6">
														<div class="input-group">
															<span class="input-group-addon">开始时间<font color="#ff0000">*</font></span>
															<input type="text" id="start-date" class="form-datetime form-control"
									 						data-lte="#end-date" data-name="start-Date" placeholder="请选择" required value="${prj.startDate}">
														</div>
													</div>											
											</td>
										</tr>
										<tr>
											<td>
												<div class="col-sm-6">
														<div class="input-group">
															<span class="input-group-addon">结束时间<font color="#ff0000">*</font></span>
															<input type="text" id="end-date" class="form-datetime form-control" data-name="end-Date"
									 					data-lte="#end-date" placeholder="请选择" required value="${prj.endDate}">
														</div>
													</div>
											</td>
										</tr>
		
										<tr>
											<td>
												<div class="col-sm-6">
														<div class="input-group">
															<span class="input-group-addon">项目状态</span>
															<select id="projectStatus" class="form-control" name="projectStatus">
                                								<option value="运行中">运行中</option>
                               									<option value="挂起">挂起</option>
                                								<option value="关闭">关闭</option>
                          									</select>
														</div>
													</div>
												</div>
											</td>
										</tr>
										<tr >
											<td>
												<div class="col-sm-12">
                  										<div class="input-group" style="height:80px">
  															<span class="input-group-addon">备注</span>
         													<input type="text" class="form-control" id="remark" value="${prj.remark}"
         													maxlength="200" placeholder="备注最多有200位" style="height:80px" name="remark">
      													</div>
      					 							</div>
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td>
												<div class="col-sm-12" align="center">
													<button type="submit" class="btn btn-primary" >设置</button>&nbsp;&nbsp;&nbsp;&nbsp;
													<button type="reset" class="btn btn-primary">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;
													<a type="button" class="btn btn-primary" onClick="goBack()">返回</a>
												</div>
											</td>
										</tr>
              						</tfoot>
              					
                           		</table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    </form>
                    <!-- /.panel -->
                </div>
             </div>
             </div>
   </body> 

<jsp:include page="/include/footer.jsp"></jsp:include>