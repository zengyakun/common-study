<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.dfsoft.ranqi.aj.aj0110.*,com.dfsoft.ranqi.ext.SearchPageWorker,com.dfsoft.common.html.*,com.dfsoft.ranqi.common.*,com.dfsoft.common.*,com.dfsoft.common.web.*"%>
<%
	/* ҳ���ʼ�� */
	HttpContext context = new HttpContext(request, response);
	MenuHelper helper = new MenuHelper(context);
	/* ������ݲ��� */
	SearchPageWorker worker = new SearchPageWorker(request);

	/* ����ʼ��-չʾ�����û��б� */
	HTMLTable table = new HTMLTable();
	table.createRow("�û���,�û���,�û���ַ,�ֻ�/�绰,��������,����Ա,�������,��������,��ע,����", "5%,5%,20%,5%,10%,5%,5%,10%,5%,5%");
	table.columns = "yqdz_userid_old,yhxm,yqdz_sm,sjdh,ajjl_ajrhrq,ajjl_ajr_name,ajjl_jieguo_name,ajx_des,ajjl_beizhu,fj";
	table.setData((VOList)context.getAttribute("DATA"));

	helper.removeRelationType("anjian_jilu","ajjl_id");
	RadioBox box = new RadioBox("Relation_anjian_jilu_ajjl_id");
	table.addRowAnchor("ajjl_id", box);

	/* 2����ť����ѯ/���� */
	String[] buttons = {
	    "",
		"",
		"<img border='0' src=\"../images/table/func-search.gif\" onclick=\"javascript:finder('AJ0110AnjianjiluQueryServlet','queryBox',600,400)\"  onmouseover=\"javascript:this.style.cursor='hand'\">",
		""
	};
%>
<!--
�޸�LogoͼƬ
�޸�ҳƬ��Դ
�޸ı���ͼƬ
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
			alert("��ѡ��һ����¼!");
			return;
		}
		for(var i = 0;i<elements.length;i++){
			if(elements[i].checked==true){
				checkedCount++;
			}
		}

		if(checkedCount==0){
			alert("��ѡ��һ����¼");
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
