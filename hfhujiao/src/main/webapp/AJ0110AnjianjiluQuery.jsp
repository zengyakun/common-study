<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.aj.aj0110.*,com.dfsoft.ranqi.ext.SearchPageWorker,com.dfsoft.common.html.*,com.dfsoft.ranqi.common.*,com.dfsoft.common.*,com.dfsoft.common.web.*"%>
<%
	/* 页面初始化 */
	HttpContext context = new HttpContext(request, response);
	MenuHelper helper = new MenuHelper(context);
	/* 相关数据操作 */
	SearchPageWorker worker = new SearchPageWorker(request);

	/* 表格初始化-展示安检用户列表 */
	HTMLTable table = new HTMLTable();
	table.createRow("用户号,用户名,用户地址,手机/电话,安检日期,安检员,安检情况,隐患详情,备注,附件", "5%,5%,20%,5%,10%,5%,5%,10%,5%,5%");
	table.columns = "yqdz_userid_old,yhxm,yqdz_sm,sjdh,ajjl_ajrhrq,ajjl_ajr_name,ajjl_jieguo_name,ajx_des,ajjl_beizhu,fj";
	table.setData((VOList)context.getAttribute("DATA"));

	helper.removeRelationType("anjian_jilu","ajjl_id");
	RadioBox box = new RadioBox("Relation_anjian_jilu_ajjl_id");
	table.addRowAnchor("ajjl_id", box);

	/* 2个按钮：查询/返回 */
	String[] buttons = {
	    "",
		"",
		"<img border='0' src=\"../images/table/func-search.gif\" onclick=\"javascript:finder('AJ0110AnjianjiluQueryServlet','queryBox',600,400)\"  onmouseover=\"javascript:this.style.cursor='hand'\">",
		""
	};
%>
<!--
修改Logo图片
修改页片来源
修改标题图片
-->
<%=helper.generalQueryPage("AJ0110AnjianjiluQueryServlet", "Relation_anjian_jilu_ajjl_id", worker, table, buttons, null, "../images/table/txt/AJ0105-logo.gif", "../images/table/txt/AJ0105-query.gif")%>
<script type="text/javascript">

	window.onload=function(){
		var elements = document.getElementsByName("Relation_anjian_jilu_ajjl_id");
		for(var i=0; i < elements.length; i++) {
			elements[i].checked = false;
		}
	}

	function clickItem1(url, action) {
		var checkedCount = 0;
		var elements = document.getElementsByName("Relation_anjian_jilu_ajjl_id");
		if (elements.length < 1) {
			alert("请选择一条记录!");
			return;
		}
		for(var i = 0;i<elements.length;i++){
			if(elements[i].checked==true){
				checkedCount++;
			}
		}

		if(checkedCount==0){
			alert("请选择一条记录");
			return;
		}

		form1.action = url;
		form1.Context_action.value = action;
		form1.submit();
	}

	function showPhoto(arg) {
		window.open("AJ0110AnjianjiluPhotoQueryServlet",'newwindow2',
                'height='+window.screen.height+', width='+window.screen.width+', top='+0+', left='+0+', toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');

		//form1.action = "showPhotoServlet?ajjl_id=" + arg;
		//form1.Context_action.value = "show";
		//form1.submit();
	}
</script>
