<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<body onload="tipMsg('${tip}')">
<div class="container-fluid" ">
         <div class="col-lg-12">
         	<form role="form" class="form-horizontal" data-toggle="validator"  method="post"
         	action="/dms/ProjectServlet?service=addPrj">
    			<div class="panel panel-info">
                        <div class="panel-heading">
                            <b>增加新项目（<font color="#ff0000">*</font>必填）</b>
                        </div>
						<div class="panel-body">
							<div class="table-responsive">
                                <table class="table table-striped">                 
                                    <tbody>
                                    	<tr>
                                    		<td>
  												<div class="col-sm-6">
  												
                  										<div class="input-group">
  															<span class="input-group-addon">项目编码<font color="#ff0000">*</font></span>
         													<input class="form-control" placeholder="项目编码最多有8位" id="projectCode"
															name="projectCode" type="text"  maxlength="10" data-error="项目编码最多10位,最小4位" required>
														
      													</div>
      					 						</div>
      					 					</td>
      					 					<td id="tipCode">
      					 					&nbsp;
      					 					</td>
		 								</tr>
		 								<tr>
		 									<td>
		 										<div class="col-sm-6">
                  										<div class="input-group">
  															<span class="input-group-addon">项目名称<font color="#ff0000">*</font></span>
         													<input type="text" class="form-control" name="projectName"
         													maxlength="20" placeholder="项目名称最多有20位" required>
      													</div>
      					 							</div>
		 									</td>
		 									<td id="tipCode">
      					 					&nbsp;
      					 					</td>
										</tr>
										<tr>											
											<td>
												<div class="col-sm-6">
														<div class="input-group">
															<span class="input-group-addon">开始时间<font color="#ff0000">*</font></span>
															<input type="text" readonly id="start-date"
																class="form-datetime form-control" data-lte="#end-date"
																data-max="today" data-default="today" data-name="start-Date"
																placeholder="请选择" required>
														</div>
													</div>											
											</td>
											<td id="tipCode">
      					 					&nbsp;
      					 					</td>
										</tr>
										<tr>
											<td>
												<div class="col-sm-6">
														<div class="input-group">
															<span class="input-group-addon">结束时间<font color="#ff0000">*</font></span>
															<input type="text" readonly id="start-date"
																class="form-datetime form-control" data-lte="#end-date"
																data-default="today" data-name="end-Date"
																placeholder="请选择" required>
														</div>
													</div>
											</td>
										</tr>
										<tr >
											<td>
												<div class="col-sm-10">
                  										<div class="input-group" style="height:80px">
  															<span class="input-group-addon">备注</span>
         													<input type="text" class="form-control" id="remark"
         													maxlength="200" placeholder="备注最多有200位" style="height:80px">
      													</div>
      					 							</div>
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td>
												<div class="col-sm-12" align="center">
													<button type="submit" class="btn btn-primary" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<button type="reset" class="btn btn-primary">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a type="button" class="btn btn-primary" onClick="goBack()">返回</a>
												</div>
											</td>
										</tr>
              						</tfoot>
              					
                           		</table>
                            </div>
                       </div>
                  </div> 
            </form>
            
         </div>
</div>
</body>
<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>