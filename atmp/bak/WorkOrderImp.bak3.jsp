<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.aj.aj0110.*,com.dfsoft.ranqi.ext.SearchPageWorker,com.dfsoft.common.html.*,com.dfsoft.ranqi.common.*,com.dfsoft.common.*,com.dfsoft.common.web.*"%>
<%
    String impUrl = "http://192.168.0.49:11003/wm_cb/workOrderImp";
    /* 页面初始化 */
    HttpContext context = new HttpContext(request, response);
    MenuHelper helper = new MenuHelper(context);

    String[] buttons = {
            "",
            "",
            "",
            "<img border='0' src=\"../images/Submit.gif\" onmouseover=\"javascript:this.style.cursor='hand'\" onclick=\"alert(true);\">"
    };
%>
<%=helper.generalViewPage1("WorkOrderImpServlet", "images/table/txt/GC0109-logo.gif", "images/table/txt/yangben-insert.gif", buttons)%>
<script type="application/javascript" src="../atest/jquery-1.9.1.js"></script>
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
    <form id="fileform" action="<%=impUrl%>" method="post" enctype="multipart/form-data">
        <table style="width: 500px;" id="table">
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
                    <input type="button" id="fileupload" value="提交">
                </td>
            </tr>
        </table>
    </form>
</div>
<img src="css/images/default/grid/wait.gif" style="display:none" id="imgWait" />
<script type="application/javascript">
    function submitForm(){
        alert(true);
    }
    /*function submitForm(){
        alert(true);
        $("#imgWait").show();
        var targetUrl = '<%=impUrl%>';
        var formData = new FormData($("#fileform")[0]);
        $.ajax({
            url: targetUrl,
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.status == "true") {
                    alert("上传成功！");
                }
                if (data.status == "error") {
                    alert(data.msg);
                }
                $("#imgWait").hide();
            },
            error: function () {
                alert("上传失败！");
                $("#imgWait").hide();
            }
        });
    }
    $(function () {
        $("#fileupload").click(function () {
            submitForm();
        });
    });*/
</script>