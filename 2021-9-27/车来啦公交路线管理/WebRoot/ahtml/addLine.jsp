<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	function pic_upload_success(file, data) {
		var json = $.parseJSON(data)
		$(this).bjuiajax('ajaxDone', json)
		if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
			$('#j_custom_pic').val(json.filename).trigger('validate')
			$('#j_custom_span_pic').html('<img src="'+ json.filename +'" width="100" />')
		}
	}
	function do_OK(json, $form) {
		console.log(json)
		console.log($form)
	}
	//护照有效日期  = 签发日期 + 10年
	$('#j_custom_issuedate').on('afterchange.bjui.datepicker', function(e, data) {
		var pattern = 'yyyy-MM-dd'
		var start = end = data.value

		end.setFullYear(start.getFullYear() + 10)
		end.setDate(start.getDate() - 1)

		$('#j_custom_indate').val(end.formatDate(pattern))
	})
</script>
<div class="bjui-pageContent">
	<%
		int labelindex = 1;
	%>
	<form action="${ctx}/sys/add${actionclass1}.action" id="j_custom_form" data-toggle="validate"
		data-alertmsg="false">
		<table class="table table-condensed table-hover" width="100%">
			<tbody>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">车次：</label> <input
						type="text" name="sid" value="${modifybean.sid}" id="j_custom_fname<%=labelindex++%>"
						data-rule="required" size="55"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">IC卡：</label> 
					<select name="iccard">
								<option value="有效">有效</option>
								<option value="无效">无效</option>
							</select>
					</td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">时间：</label> <input
						type="text" name="linetime" value="${modifybean.linetime}" id="j_custom_fname<%=labelindex++%>"
						data-rule="required" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">站点列表：</label> 
					<textarea rows="5" cols="100" name="lineStationList" id="lineStationList" ></textarea>
					</td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x85">选择站点：</label> 
					<input type="text" id="choseStation" list="pastalist"><button type="button" class="btn" data-icon="" style="color:blue" onclick="addStation()">加入线路</button>
					</td>
				</tr>

			</tbody>
		</table>
	</form>
	<datalist id="pastalist">
	<c:forEach items="${list}" var="fitem">
		<option>${fitem.name }</option>
	</c:forEach>
</datalist>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>
<script>
function addStation(){
	$("#lineStationList").val($("#lineStationList").val()+$("#choseStation").val()+", ");
	$("#choseStation").val("");
}

</script>