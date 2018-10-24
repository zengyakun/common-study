<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.common.*,com.dfsoft.common.web.*"%>
<%
    String url = "http://192.168.0.49:11003/workOrderUpload.jsp";
    /* Ò³Ãæ³õÊ¼»¯ */
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);

    String[] buttons = {
            "",
            "",
            "",
            ""
    };
%>
<%=helper.generalViewPage1("WorkOrderImpServlet", "images/table/txt/GC0109-logo.gif", "images/table/txt/115400-insert.gif", buttons)%>
<iframe src="<%=url%>" frameborder=0 scrolling="no" width="100%" height="100%"></iframe>