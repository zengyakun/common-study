<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.aj.aj0110.*,com.dfsoft.ranqi.ext.SearchPageWorker,com.dfsoft.common.html.*,com.dfsoft.ranqi.common.*,com.dfsoft.common.*,com.dfsoft.common.web.*"%>
<%
    String impUrl = "http://192.168.0.49:11003/wm_cb/workOrderImp";
    /* ҳ���ʼ�� */
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);

    String[] buttons = {
            "",
            "",
            "",
            "",
            ""
    };
%>
<%=helper.generalViewPage1("WorkOrderImpServlet", "images/table/txt/GC0109-logo.gif", "images/table/txt/yangben-insert.gif", buttons)%>
<style type="text/css">
    .tdtitle {
        background-color: #EFF3F5;
        width: 140px;
    }

    #table td {
        height: 55px;
        padding-left: 10px;
    }

    #table, #table td {
        border: solid 1px #E2E8EC;
        border-collapse: collapse
    }
</style>
<div>
    <form id="fileform" action="#" method="post" enctype="multipart/form-data">
        <table style="width: 500px;" id="table">
            <tr>
                <td class="tdtitle">��������</td>
                <td>
                    <select name="secondTypeId">
                        <option value="2227">�ұ�-����</option>
                        <option value="2219">���-����</option>
                        <option value="2230">����-���ڻ���</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tdtitle">��������</td>
                <td>
                    <textarea name="content" cols="35" rows="2"></textarea>
                </td>
            </tr>
            <tr>
                <td class="tdtitle">�ϴ�����</td>
                <td>
                    <input type="file" name="excel"
                           accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: right; padding-right: 20px;">
                    <img border='0' src="../images/Submit.gif" onmouseover="javascript:this.style.cursor='hand'" onclick="submitForm();">
                </td>
            </tr>
        </table>
    </form>
</div>
<img src="css/images/default/grid/wait.gif" style="display:none" id="imgWait" />
<%=helper.generalViewPage2(null)%>
<script language="JavaScript" src="js/jquery-1.9.1.js"></script>
<script language="JavaScript" src="js/jquery.form.min.js"></script>
<script language="JavaScript">
    function submitForm(){
        alert(1);
        $("#imgWait").show();
        var options = {
            url:"http://192.168.0.49:11003/wm_cb/workOrderImp", //�ϴ��ļ���·��
            type:'post',
            success:function(data){
                alert(data);
                $("#imgWait").hide();
            },
            error: function () {
                alert("�ϴ�ʧ�ܣ�");
                $("#imgWait").hide();
            }
        };
        alert(2);
        $("#fileform").ajaxSubmit(options);
        alert(3);
    }
    $(function () {
        alert("init");
    });
</script>