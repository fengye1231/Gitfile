function tipMsg(tip){
	if(tip!=null&&tip!=""){
		bootbox.alert(tip);
	}
}

function selectAll(){ 
	var handleEl = document.getElementById("selectAll"); 
	var els = document.getElementsByName("selectPrj"); 
	for(i=0;i<els.length;i++){ 
		els[i].checked = handleEl.checked; 
	}
}

function goBack(){
	javascript :history.go(-1);
};