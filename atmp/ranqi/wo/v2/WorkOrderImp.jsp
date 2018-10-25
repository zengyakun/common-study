<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.common.*,com.dfsoft.common.web.*"%>
<%
    /* Ò³Ãæ³õÊ¼»¯ */
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);
    SessionHelper sh = new SessionHelper(context);
    String csrId = sh.getCsrID();
    String csrBmId = sh.getBumenID();
    String url = "http://192.168.0.49:11003/workOrderUpload.jsp?csrId="
            + csrId + "&csrBmId=" + csrBmId;

    String[] buttons = {
            "",
            "",
            "",
            ""
    };
%>
<%=helper.generalViewPage1("WorkOrderImpServlet", "images/table/txt/GC0109-logo.gif", "images/table/txt/115400-insert.gif", buttons)%>
<iframe src="<%=url%>" frameborder=0 scrolling="no" width="100%"></iframe>