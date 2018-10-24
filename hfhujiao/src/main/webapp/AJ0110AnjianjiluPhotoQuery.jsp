<%@ page import="com.dfsoft.ranqi.aj.aj0110.*,com.dfsoft.ranqi.ext.SearchPageWorker,com.dfsoft.common.html.*,com.dfsoft.ranqi.common.*,com.dfsoft.common.*,com.dfsoft.common.web.*,java.util.*" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    /* 页面初始化 */
    HttpContext context = new HttpContext(request, response);
    Map<String, Object> map=(Map<String, Object>) context.getAttribute("DATA");
    System.out.println(">>>>>>>>>测试：" + map.get("photoUrls"));
    List<String> photoUrlsList = (List<String>) map.get("photoUrls");
    VOList voList = (VOList) map.get("wtxUrls");
    List<String> urlsList = new ArrayList<String>();
    
    /**if (null != voList) {
        VO vo = new VO();
        for (int i=0; i < voList.size(); i++) {
            vo.clear();
            vo = voList.get(i);
            urlsList.add(vo.getAttr("urls"));
        }
    }*/
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" >
    <title>照片浏览</title>
</head>
<body>
    <table  width='100%' border=1  valign='top' class='btable' cellspacing=0 cellpadding=0>
        <tr>
            <td class="DataTD1"><font color="red">安检图片</font></td>
        </tr>
        <tr style="height:25px">
            <td class="DataTD1">
        <%
            if (null != photoUrlsList && !photoUrlsList.isEmpty()) {
                for (int i = 0; i < photoUrlsList.size(); i++) {
                    String photoUrl = photoUrlsList.get(i);
        %>
        
            <img src="<%=photoUrl%>">
        
        <%
                }
            } else {
        %>
            无图片
        
        <%
            }
        %>
            </td>
        </tr>
        <tr>
            <td class="DataTD1"><font color="red">安检问题项图片</font></td>
        </tr>
        
        <%
            if (null != voList && voList.size() > 0) {
                VO vo = new VO();
                for (int i = 0; i < voList.size(); i++) {
                    vo.clear();
                    vo = voList.get(i);
        %>
        <tr style="height:25px">
            <td class="DataTD1">隐患描述：<%=vo.getAttr("ajx_des")%></td>
        </tr>
        <tr style="height:25px">
            <td class="DataTD1">
        <%
                    String urls = vo.getAttr("urls");
                    if (null != urls && !"".equals(urls)) {
                        String[] urlsArray = urls.split(",");
                        for (int j = 0; j < urlsArray.length; j++) {
                            String yhUrl = urlsArray[j];
        %>
                <img src="<%=yhUrl %>">
        <%
                        }
                    } else {
        %>
                无图片
        <%
                    }
        %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr style="height:25px">
            <td class="DataTD1">无图片</td>
        </tr>
        <%
            }
        %>
    </table>

</body>
</html>

