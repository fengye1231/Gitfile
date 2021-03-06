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
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">拾到时间：</label> <input
						type="text" name="findDate" value="${modifybean.findDate}" id="j_custom_fname<%=labelindex++%>"
						data-rule="required" size="25" data-toggle="datepicker" readonly="readonly" data-pattern="yyyy-MM-dd HH:mm:ss"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">车次：</label> <input
						type="text" name="checi" value="${modifybean.checi}" id="j_custom_fname<%=labelindex++%>"
						data-rule="required" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">失物内容：</label> <input
						type="text" name="wuping" value="${modifybean.wuping}" id="j_custom_fname<%=labelindex++%>"
						data-rule="required" size="55" ></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">领取人姓名：</label> <input
						type="text" name="name" value="${modifybean.name}" id="j_custom_fname<%=labelindex++%>"
						data-rule="" size="15"></td>
				</tr>
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">领取人身份证：</label> <input
						type="text" name="idcard" value="${modifybean.idcard}" id="j_custom_fname<%=labelindex++%>"
						data-rule="" size="15" maxlength="18"></td>
				</tr>
				 
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">领取时间：</label> <input
						type="text" name="lingDate" value="${modifybean.lingDate}" id="j_custom_fname<%=labelindex++%>"
						data-rule="" size="25" data-toggle="datepicker" readonly="readonly" data-pattern="yyyy-MM-dd HH:mm:ss"></td>
				</tr>
				 
				<tr>
					<td><label for="j_custom_fname<%=labelindex%>" class="control-label x120">领取人电话号码：</label> <input
						type="text" name="phone" value="${modifybean.phone}" id="j_custom_fname<%=labelindex++%>"
						data-rule="mobile" size="15" maxlength="11"></td>
				</tr>
				 
				 


			</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>
