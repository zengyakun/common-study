<%@page contentType="text/html; charset=GBK" %>
<%
    String impUrl = "http://192.168.0.49:11003/wm_cb/workOrderImp";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>批量建单</title>
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
</head>
<body>
<div>
    <h1>批量建单</h1>
    <form action="<%=impUrl%>" method="post" enctype="multipart/form-data">
        <table style="width: 500px;">
            <tr>
                <td class="tdtitle">工单类型</td>
                <td>
                    <select name="secondTypeId">
                        <option value="2227">挂表-居民户</option>
                        <option value="2219">点火-居民户</option>
                        <option value="2230">换表-到期换表</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tdtitle">工单内容</td>
                <td>
                    <textarea name="content" cols="35" rows="2"></textarea>
                </td>
            </tr>
            <tr>
                <td class="tdtitle">上传附件</td>
                <td>
                    <input type="file" name="excel"
                           accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: right; padding-right: 20px;">
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>