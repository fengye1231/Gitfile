<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/include/header.jsp"></jsp:include>

<%-- 内容开始 --%>
<body onload="selPrp(${prps})">
<init-msg>${tip}</init-msg>
<div class="container-fluid">
<!--<div class="row">-->
<!--	<div class="col-sm-12">-->
<!--		<table id="table" data-dms-table></table>-->
<!--	</div>-->
<!--</div>-->
<div class="panel panel-info">
	<div class="panel-heading">
         <b>配置-配置PRP</b>
    </div>
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12">
				<table id="table" data-dms-table></table>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form  action="#" data-toggle="validator" method="post">
					<div class="row">
						<div class="col-sm-12" >
							<div class="row">
								<div class="col-sm-2">
								</div>
								<div class="col-sm-6" align="center">
								    <input type="hidden" id="prjid" value="${prjID}"/>
									<button type="button" id="set-btn" class="btn btn-primary ">设置</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" class="btn btn-primary btn-outline">重置</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a type="button" class="btn btn-primary" onClick="goBack()">返回</a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<div class="row">

</div>
</body>

<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>