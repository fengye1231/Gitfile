<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.neusoft.dms.personal.domain.*"%>
<%@ page import="com.neusoft.dms.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/base.jsp"  %>
<jsp:include page="/include/header.jsp"></jsp:include>

<%

String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
Date now = new Date();
List<Date> dates = new ArrayList<Date>();
List<String> dateShows = new ArrayList<String>();
for (int i = 0; i < 14; i++) {
	dates.add(0, new Date(now.getTime() - i * 1000 * 3600 * 24));
	dateShows.add(0, sdf.format(dates.get(0)) 
			+ "(" + weeks[DateUtil.dayOfWeek(dates.get(0)) - 1] + ")");
}
request.setAttribute("dates", dates);
request.setAttribute("dateShows", dateShows);

ArrayList<DailyVo> dailys = (ArrayList<DailyVo>) request.getAttribute("daily");

List<String> classes = new ArrayList<String>();

dateLoop:
for (Date date : dates) {
	int dayOfWeek = DateUtil.dayOfWeek(date);
	for (DailyVo daily : dailys) {
		if (DateUtil.isSameDay(daily.getSubmitDate(), date)) {
			classes.add("cell-green");
			continue dateLoop;
		}
	}
	if (dayOfWeek == 1 || dayOfWeek == 7) {
		classes.add("cell-gray");
	} else {
		classes.add("cell-red");
	}
}

request.setAttribute("classes", classes);

 %>

<%-- 内容开始 --%>
<div class="container-fluid">
 <div class="row">
  	<div class="col-md-12">
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
			        <thead>
			          <tr>
			            <th>姓名</th>
			            <c:forEach var="dateShow" items="${dateShows}">
			            	<th>${ dateShow }</th>
			            </c:forEach>
			          </tr>
			        </thead>
			        <tbody>
			          <tr>
			          	<td>${ name }</td>
			            <c:forEach var="className" items="${classes}">
			            	<td class="${ className }"></td>
			            </c:forEach>
			          </tr>
			        </tbody>
			</table>
		</div>
	</div>
</div>
</div>




<%-- 内容结束 --%>

<jsp:include page="/include/footer.jsp"></jsp:include>