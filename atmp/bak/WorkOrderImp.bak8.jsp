<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.common.*,com.dfsoft.common.web.*"%>
<%
    /* ҳ���ʼ�� */
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);
    SessionHelper sh = new SessionHelper(context);
    String csrId = sh.getCsrID();
    String csrBmId = sh.getBumenID();

    String[] buttons = {
            "",
            "",
            "",
            "",
            "<img border='0' src=\"../images/Submit.gif\" onmouseover=\"javascript:this.style.cursor='hand'\" onclick=\"submitForm();\">"
    };
%>
<%=helper.generalViewPage1WithMutipart("WorkOrderImpServlet", "images/table/txt/GC0109-logo.gif", "images/table/txt/115400-insert.gif", buttons)%>

<table width="100%" border="1" cellspacing="0" cellpadding="0" class="black_1">
    <tr>
        <td class='DataTD' width="25%">��������<font color='red'>*</font></td>
        <td class='DataTD'>
            <select name="thirdTypeId">
                <option value="222701">�ұ�-����</option>
                <option value="221901">���-����</option>
                <option value="223007">����-���ڻ���</option>
            </select>
        </td>
    </tr>
    <tr>
        <td class="DataTD">��������</td>
        <td class='DataTD'>
            <textarea name="content" cols="35" rows="2"></textarea>
        </td>
    </tr>
    <tr>
        <td class="DataTD">�ϴ�����<font color="Red">*</font>(�ļ�����Ϊ.xls)</td>
        <td class="DataTD" ><input type='file' name="excel"></td>
        <input type="hidden" name="inputCsrId" value="<%=csrId%>">
        <input type="hidden" name="inputDeptId" value="<%=csrBmId%>">
    </tr>
</table>

<iframe src="http://192.168.0.49:11003/workOrderUpload.jsp" width="500px" height="500px"></iframe>
<img src="css/images/default/grid/wait.gif" style="display:none" id="imgWait" />
<%=helper.generalViewPage2(null)%>
<script language="JavaScript" src="js/jquery-1.8.1.min.js"></script>
<script language="JavaScript" src="js/jquery.form.js"></script>
<script language="JavaScript">
    function submitForm(){
        $("#imgWait").show();
        var options = {
            //todo ���ߺ��޸�Ϊ����������ַ
            url:"http://192.168.0.49:11003/wm_cb/workOrderImp", //�ϴ��ļ���·��
            type:"post",
            crossDomain:true,
            dataType:"text/html",
            success: function(data){
                alert(data);
                $("#imgWait").hide();
            },
            error: function () {
                alert("�ϴ�ʧ�ܣ�");
                $("#imgWait").hide();
            }
        };
        $(form1).submit(function(){
            return false;
        });
        $(form1).ajaxSubmit(options);
    }
</script>