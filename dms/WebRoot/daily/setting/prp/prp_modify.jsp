<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<jsp:include page="/include/header.jsp"></jsp:include>
<!--  
 <div class="container-fluid">
  <div class="row">
  <div class="col-sm-1"></div>
  <div class="col-sm-12" align="center"><div class="alert alert-info" style="text-align:center">
                               修改PRP阶段（红色部分必填）
                            </div></div>
  </div>
  <form action="/dms/servlet/PrpServlet" method="post">
  <div class="row">
  <div class="col-sm-4">
   <div class="form-group input-group"> <span class="input-group-addon">PRP缩写</span>
          <input type="text" class="form-control" name="prpAbbr" placeholder=<%=new String(request.getParameter("prpAbbr").getBytes("iso-8859-1"),"utf-8") %>>
          <input type="hidden" name="service" value="update">
          <input type="hidden" name="prpId" id="pid" value="">
          <script type="text/javascript">
  			function GetRequest() {
   var url = location.search; //获取url中"?"符后的字串?prpId=2
   var theRequest = new Object();
   var str=url.split("=");
   document.getElementById("pid").value=str[1];  
}
   GetRequest();
          </script>
   </div>
   </div>
   <div class="col-sm-4">
   <div class="form-group input-group"> <span class="input-group-addon">PRP阶段</span>
          <input type="text" class="form-control" name="prpName" placeholder=<%=new String(request.getParameter("prpName").getBytes("iso-8859-1"),"utf-8")%> >
        </div>
   </div>
  </div>
  
  <div class="row ">
  <div class="form-group">
  <div class="col-sm-12">
                                            <label>备注</label>
                                            <textarea class="form-control" rows="3" name="remark"></textarea>
  </div>
  </div>
  </div>
 
  <div class="row ">
  <div class="col-sm-12"></div>
  </div>
  <div class="row">
  <div class="col-sm-9"></div>
  
  <div class="col-sm-1" >
						
							<button type="submit" class="btn btn-primary btn-block btn_top ">提交</button>
						
			  		</div>
			  		<div class="col-sm-1">
						
							<button type="reset" class="btn btn-primary btn-block btn_top">重置</button>
						
			  		</div>
			  		<div class="col-sm-1">
						
							<button  type="button" class="btn btn-primary btn-block btn_top" onclick="{location.href='/dms/daily/setting/prp/index.jsp'}">返回</button>
						
			  		</div>
			  		
			  		</div>
</form>
  d
  </div>
-->
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="/dms/daily/setting/prp/servlet/PrpServlet?service=update" method="post"
			data-toggle="validator" class="form-horizontal">
			<input type="hidden" name="prpId" id="pid" value=${param.prpId}>
      
				<div class="panel panel-default">
					<div class="panel-heading">
						修改PRP
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="prpName" class="col-sm-3 control-label">
								PRP缩写<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="prpAbbr"
									id="prpAbbr" value=<%=new String(request.getParameter("prpAbbr").getBytes("iso-8859-1"),"utf-8") %> required>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="prpName" class="col-sm-3 control-label">
								PRP名称<font color="#f00"> *</font>
							</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="prpName"
									id="prpName" value=<%=new String(request.getParameter("prpName").getBytes("iso-8859-1"),"utf-8") %> required>
								<div class="help-block with-errors"></div>
							</div>
						</div>
							<div class="form-group">
								<label for="prpAbbr" class="col-sm-3 control-label">
									备注<font color="#f00"> *</font>
								</label>
								<div class="col-sm-9">
									<textarea class="form-control" rows="3" name="remark"></textarea>
								</div>
							</div>
					</div>
					<div class="panel-footer">
						<div class="form-group no-margin-bottom">
							<div class="col-sm-3 col-sm-offset-6">
								<button class="btn btn-default btn-block" type="reset">
									重 置
								</button>
							</div>
							<div class="col-sm-3">
								<input class="btn btn-primary btn-block" type="submit" value="确 认" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/include/footer.jsp"></jsp:include>