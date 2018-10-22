<%@page contentType="text/html; charset=GBK"%>
<%
	String attachment = request.getParameter("attachment");
	String picUrl = "http://127.0.0.1:8081/mvcdemo/showPic";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>�鿴ͼƬ</title>
</head>
<script type="text/javascript">
var attachment = '<%=attachment%>';
var picUrl = '<%=picUrl%>';
window.onload = function () {
	document.getElementById("img").src = picUrl + "?attachment=" + attachment;
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
<div id="pic">
	<img id="img" width="100px" height="200px" onload="AutoResizeImage(250,0,this)">
</div>
</body>
</html>