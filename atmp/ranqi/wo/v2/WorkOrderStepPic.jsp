<%@page contentType="text/html; charset=GBK"%>
<%
	String attachment = request.getParameter("attachment");
	// todo 上线修改为生产环境地址
	String picUrl = "http://192.168.0.49:11003/wm_cb/showPic";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看图片</title>
</head>
<script type="text/javascript">
var attachment = '<%=attachment%>';
var picUrl = '<%=picUrl%>';
window.onload = function () {
	var names = attachment.split(",");
    var picTable = document.getElementById("picTable");
    var newTr;
    for(var i = 0; i < names.length; i++){
        if(i%2 == 0){
            newTr = picTable.insertRow();
		}
		var newTd = newTr.insertCell();
        if(names.length > 1) {
            newTd.innerHTML = '<img width="100px" height="200px" onload="AutoResizeImage(250,0,this)" src="' +
                picUrl + '?attachment=' + names[i] + '&number=' + Math.random() + '">';
        } else {
            newTd.innerHTML = '<img width="100px" height="200px" onload="AutoResizeImage(500,0,this)" src="' +
                picUrl + '?attachment=' + names[i] + '&number=' + Math.random() + '">';
		}
    }
}
function AutoResizeImage(maxWidth,maxHeight,objImg){
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth ==0 && maxHeight==0){
        Ratio = 1;
    }else if (maxWidth==0){//
        if (hRatio<1) Ratio = hRatio;
    }else if (maxHeight==0){
        if (wRatio<1) Ratio = wRatio;
    }else if (wRatio<1 || hRatio<1){
        Ratio = (wRatio<=hRatio?wRatio:hRatio);
    }
    if (Ratio<1){
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;
}
</script>
<body>
<div>
	<table id="picTable"></table>
</div>
</body>
</html>