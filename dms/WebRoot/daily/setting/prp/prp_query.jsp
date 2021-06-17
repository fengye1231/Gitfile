<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp"></jsp:include>


<div class="container-fluid">
  <div class="row">
  	<form action="/dms/daily/setting/prp/servlet/PrpServlet">
      <div class="col-sm-3">
        <div class="input-group form-group"> <span class="input-group-addon">PRP缩写</span>
          <input class="form-control" type="text" name="prpAbbr">
          <input type="hidden" name="service" value="listPrp">
        </div>
      </div>
      <div class="col-sm-3">
        <div class="input-group form-group"> <span class="input-group-addon">PRP阶段名称</span>
          <input class="form-control" type="text" name="prpName">
        </div>
      </div>
      <div class="col-sm-1 ">
        <input class="btn btn-primary btn-block" type="submit" value="查询">
      </div>
      <div class="col-sm-1 ">
        <input class="btn btn-primary btn-block" type="button" value="增加" onclick="location.href='/dms/daily/setting/prp/prp_add.jsp'">
      </div>
      <div class="col-sm-1 ">
        <input class="btn btn-primary btn-block" type="reset" value="重置">
      </div>
    </form>
      <div class="row">
    <div class="col-md-12">
      <div class="table-responsive">
        <table class="table table-bordered table-striped">
          <tbody>
          <tr>
            <td>序号</td>
            <td>PRP缩写</td>
            <td>PRP阶段名称</td>
            <td colspan="2">操作</td>
          </tr>
          
          <c:forEach items="${page.list}" var="prp" varStatus="status">
          	<tr>
          		<td>${status.count}</td>
          		<td>${prp.prpAbbr}</td>
          		<td>${prp.prpName}</td>
          		<td><a href="/dms/daily/setting/prp/prp_modify.jsp?prpId=${prp.prpId}">修改</a></td>
          		<td><a href="/dms/daily/setting/prp/servlet/PrpServlet?prpName=${prp.prpName}&service=del">删除</a></td>
          	</tr>
          
          </c:forEach>
        </tbody>
        </table>
        <center>
          <ul id="pagination" class="pagination" data-total="5" data-visible="7" data-start="1">
          <li class="first disabled"><a href="#">起始页</a></li><li class="prev disabled"><a href="#">上一页</a></li><li class="page active"><a href="#">1</a></li><li class="page"><a href="#">2</a></li><li class="page"><a href="#">3</a></li><li class="page"><a href="#">4</a></li><li class="page"><a href="#">5</a></li><li class="next"><a href="#">下一页</a></li><li class="last"><a href="#">最末页</a></li></ul>
        </center>
      </div>
    </div>
  </div>
  </div>
 

</div>
<jsp:include page="/include/footer.jsp"></jsp:include>