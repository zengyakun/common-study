<%@page contentType="text/html; charset=GBK"%>
<%
    String impUrl = "http://127.0.0.1:8081/mvcdemo/workOrderImp";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>��������</title>
</head>
<body>
<div>
    <form action="<%=impUrl%>" method="post">
        <table>
            <tr>
                <td>�������ͣ�</td>
                <td>
                    <select name="">
                        <option value="0">�ұ�</option>
                        <option value="1">���</option>
                        <option value="2">����</option>
                    </select>
                </td>
                <td>�ļ���</td>
                <td>
                    <input type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                </td>
                <td>
                    <input type="submit" name="�ύ">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>