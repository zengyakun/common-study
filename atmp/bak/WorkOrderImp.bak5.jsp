<!--/ob/ob0108/OB0108SampleInsert.jsp-->
<%@page contentType="text/html; charset=GB2312"%>
<%@page import="com.dfsoft.ranqi.common.*,com.dfsoft.common.web.*"%>

<%@ page errorPage="/main/error/errorPage.jsp"%>
<%/* 页面初始化 */
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);

    String[] buttons = {
            "",
            "",
            "",
            "",
            "<a href=\"JavaScript:clickItem('ob0108sampleinsert','insertAction')\"><img border='0' src=\"images/table/func-ok.gif\"></a>",
            "<a href=\"JavaScript:clickItem('ob0108samplequery','query')\"><img border='0' src=\"images/table/func-back.gif\"></a>",
    };
%>
<%=helper.generalViewPage1WithMutipart("OB0108SampleInsert", "images/table/txt/GC0109-logo.gif", "images/table/txt/yangben-insert.gif", buttons)%>

<table width="100%" border="1" cellspacing="0" cellpadding="0" class="black_1">
    <tr>
        <td class='DataTD'>样本名称<font color='red'>*</font></td>
        <td class='DataTD'><input type='text' name="SAMPLEBATCHNAME" style="width:90%" /></td>
    </tr>
    <tr>
        <td width="25%" class="DataTD">样本上传<font color="Red">*</font>(文件类型为.xls)</td>
        <td class="DataTD" ><input type='file' name="uploadfile"></td>
    </tr>
</table>
<%=helper.generalViewPage2(null)%>

<script language="JavaScript" type="text/JavaScript">

    function clickItem(url, action) {
        alert(url);
        alert(action);
        form1.action = "http://192.168.0.49:11003/wm_cb/workOrderImp";
        form1.method="post";
        alert(1);
        form1.submit();
        alert(2);
    }

</script>

