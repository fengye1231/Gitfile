
function refresh() {  
    document.getElementById("authImg").src = DMS.basePath + 'login/check.servlet?now=' + new Date();
} 

function checkCode(){
	
}

function check(){ 
	
	try{ 
	   var isSave = document.getElementById('checkbox').checked;   //保存按键是否选中 
	   if (isSave) { 
	    var usernm = document.getElementById('username').value; 
	    var userpsw = document.getElementById('password').value; 
	    if(usernm!="" && userpsw!=""){ 
	     SetCookie(usernm,userpsw); 
	    } 
	   }else {   
	    SetCookie("",""); 
	   } 
	}catch(e){ 
	  
	} 
} 

function SetCookie(usern,psw){ 
	var Then = new Date();  
	Then.setTime(Then.getTime() + 1866240000000) ; 
	document.cookie ="username=" + usern + "%%"+psw+";expires="+ Then.toGMTString() ; 
} 
	  
	  
function GetCookie(){  
	var nmpsd; 
	var nm; 
	var psd; 
	var cookieString = new String(document.cookie); 
	var cookieHeader = "username="; 
	var beginPosition = cookieString.indexOf(cookieHeader); 
	cookieString = cookieString.substring(beginPosition); 
	var ends=cookieString.indexOf(";"); 
	if (ends!=-1){ 
	   cookieString = cookieString.substring(0,ends); 
	} 
	if (beginPosition>-1){ 
	   nmpsd = cookieString.substring(cookieHeader.length); 
	   if (nmpsd!=""){ 
	    beginPosition = nmpsd.indexOf("%%"); 
	    nm=nmpsd.substring(0,beginPosition); 
	    psd=nmpsd.substring(beginPosition+2); 
	    document.getElementById('username').value=nm; 
	    document.getElementById('password').value=psd; 
	    if(nm!="" && psd!=""){ 
	     document.forms[0].checkbox.checked = true; 
	    } 
	   }  
	} 
}

$(function(){
	GetCookie();
});