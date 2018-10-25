<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>工单批量录入</title>
</head>
<script language="JavaScript" src="js/jquery-1.9.1.js" type="application/javascript"></script>
<script language="JavaScript" src="js/jquery.form.js" type="application/javascript"></script>
<script language="JavaScript" type="application/javascript">
    function submitForm() {
        alert(1);
        // //$("#imgWait").show();
        // var options = {
        //     url: "http://192.168.0.49:11003/wm_cb/workOrderImp", //上传文件的路径
        //     type: "post",
        //     dataType: "json",
        //     success: function (data) {
        //         alert(data);
        //         //$("#imgWait").hide();
        //     },
        //     error: function () {
        //         alert("上传失败！");
        //         //$("#imgWait").hide();
        //     }
        // };
        // alert(2);
        // $(form1).submit(function () {
        //     return false;
        // });
        // alert(3);
        // $(form1).ajaxSubmit(options);
        // alert(4);
    }
</script>
<body>
<form name="form1" action="/wm_cb/workOrderImp" enctype="multipart/form-data" method="post">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="black_1">
        <tr>
            <td class='DataTD' width="25%">工单类型<font color='red'>*</font></td>
            <td class='DataTD'>
                <select name="secondTypeId">
                    <option value="2227">挂表-居民户</option>
                    <option value="2219">点火-居民户</option>
                    <option value="2230">换表-到期换表</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="DataTD">工单内容</td>
            <td class='DataTD'>
                <textarea name="content" cols="35" rows="2"></textarea>
            </td>
        </tr>
        <tr>
            <td class="DataTD">上传附件<font color="Red">*</font>(文件类型为.xls)</td>
            <td class="DataTD"><input type='file' name="excel"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right; padding-right: 20px;">
                <button onclick="alert(true);">提交</button>
                <input type="button" onclick="alert(true);">
            </td>
        </tr>
    </table>
</form>
</body>
</html>