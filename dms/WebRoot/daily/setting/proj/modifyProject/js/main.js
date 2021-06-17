function selStatus(prjStatus){
	$("#projectStatus option").each(function() {
		if (prjStatus == $(this).val()) {
			$(this).attr("selected", "selected");
		}
	});
}

function goBack(){
	history.go(-1);
};

$(function(){
	selStatus(prjStatus);
});