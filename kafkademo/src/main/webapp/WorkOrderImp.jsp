<%@page contentType="text/html; charset=GBK"%>
<%
    String impUrl = "http://127.0.0.1:8081/mvcdemo/workOrderImp";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>工单导入</title>
</head>
<body>
<div>
    <form action="<%=impUrl%>" method="post">
        <table>
            <tr>
                <td>工单类型：</td>
                <td>
                    <select name="">
                        <option value="0">挂表</option>
                        <option value="1">点火</option>
                        <option value="2">换表</option>
                    </select>
                </td>
                <td>文件：</td>
                <td>
                    <input type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                </td>
                <td>
                    <input type="submit" name="提交">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>