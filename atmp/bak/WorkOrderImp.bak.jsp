<%@page contentType="text/html; charset=GBK" %>
<%@page import="java.util.List" %>
<%@page import="com.dfsoft.ranqi.common.*,com.dfsoft.common.web.*" %>
<%@page import="cc.dfsoft.j2ee.viewer.HTMLList" %>

<%
    String impUrl = "http://127.0.0.1:8081/mvcdemo/workOrderImp";
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);
%>
<%=helper.generalViewPage2(null)%>
<style type="text/css">
    .tdtitle {
        background-color: #EFF3F5;
        width: 140px;
    }

    td {
        height: 55px;
        padding-left: 10px;
    }

    table, table tr th, table tr td {
        border: solid 1px #E2E8EC;
        border-collapse: collapse
    }
</style>
<div>
    <h1>��������</h1>
    <form>
        <table style="width: 500px;">
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
                    <input type="submit" value="�ύ">
                </td>
            </tr>
        </table>
    </form>
</div>