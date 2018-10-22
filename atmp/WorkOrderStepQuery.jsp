<!--/ranqi/wo/v2/WorkOrderStepQuery.jsp-->
<%@page contentType="text/html; charset=GBK"%>
<%@page import="cc.dfsoft.j2ee.viewer.*,cc.dfsoft.j2ee.*"%>
<%@page import="cc.dfsoft.ranqi.bo.WorkOrderStep"%>
<%@page import="cc.dfsoft.ranqi.bo.SchemeChangeLog"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List,java.util.ArrayList"%>
<%@page	import="cc.dfsoft.ranqi.service.bl.workorder.WorkOrderTypeList"%>
<%@page	import="cc.dfsoft.ranqi.action.workorder.model.VisitResultModel"%>
<%
    /* 页面初始化 */
    MenuHelper helper = new MenuHelper(request);
    
    String workorder_type = (String)request.getAttribute("RELATION_WORKORDER_TYPE");
	String filePath = (String)request.getAttribute("filePath");
    //String filePath2 = "ftp://ftpuser:ftpuser@192.168.0.29/";
	String filePath2 = "http://192.168.0.6:8081/"; //需要改动
    List data = (List)request.getAttribute(Configurer.TABLE_DATA);
    
    List vistResultList = (List)request.getAttribute("vistResultList");
    if(vistResultList == null){
       vistResultList = new ArrayList();
    }
    
    List schemeChangeLogList = (List)request.getAttribute("schemeChangeLogList");
    if(schemeChangeLogList == null){
       schemeChangeLogList = new ArrayList();
    }
    
    
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   
    String[] buttons = {
        "",
        "",
        "",
        "",
        ""			
    };
%>
<%=helper.generalViewPage1("v2WorkOrderStepQueryAction.action", "../images/table/txt/KH0101-logo.gif", "../images/table/txt/workorderstep_query.gif", buttons)%>
	<table id = 'data_table' cellspacing=0 cellpadding=0 border=1 align=center width="100%">
    <tr >
      <th class="HeaderRow" width="10%">处理人</th>
      <th class="HeaderRow" width="12%">处理部门</th>
      <th class="HeaderRow" width="10%">初始状态</th>
      <th class="HeaderRow" width="10%">到达状态</th>
      <th class="HeaderRow" width="14%">开始时间</th>
      <th class="HeaderRow" width="14%">结束时间</th>
      <th class="HeaderRow" width="N">附件名称</th>
      <th class="HeaderRow" width="7%">下载</th>
	  <th class="HeaderRow" width="13%">&nbsp;&nbsp;外勤图片</th>
  </tr>
<%
	String strBumen = "";
  	for(int i = 0; i < data.size(); i ++){ 
    	WorkOrderStep ws = (WorkOrderStep)data.get(i);
    	
    	if(ws.getSubmitCsr() == null)continue;
  		if(ws.getSubmitdept() != null)
  		{
  			strBumen = ws.getSubmitdept().getBmMc();
  		}
  		
  		if(strBumen == null)strBumen = "";
%>
  <tr height = '25'>
    <td class="NormalRow" ><%=ws.getSubmitCsr() != null? ws.getSubmitCsr().getCsrname(): ""%>&nbsp;</td>
    <td class="NormalRow" ><%=ws.getDispatchDept() != null? ws.getDispatchDept().getBmMc():strBumen%>&nbsp;</td>
    <td class="NormalRow" ><%=ws.getCurrentStatus()!= null? ws.getCurrentStatus().getDes(): ""%>&nbsp;</td>
    <td class="NormalRow" ><%=ws.getTransferStatus() != null? ws.getTransferStatus().getDes(): ""%>&nbsp;</td>
    <td class="NormalRow" ><%=ws.getCheckoutTime() != null? sf.format(ws.getCheckoutTime()): ""%>&nbsp;</td>
    <td class="NormalRow" ><%=ws.getSubmitTime() != null? sf.format(ws.getSubmitTime()): ""%>&nbsp;</td>
    <td class="NormalRow" ><%=ws.getAttachment() != null? ws.getAttachment(): "" %>&nbsp;</td>
    <%if(ws.getAttachment() != null && !"".equals(ws.getAttachment())){%>
		<%--<%if (ws.getId().length() < 17) {%>
			<td class="NormalRow" >
			<a href = '<%=(filePath + ws.getAttachment())%>'>下载</a>
			</td>
			<td class="NormalRow" >&nbsp;</td> 
		<%} else {%>
			<td class="NormalRow" >&nbsp;</td> 
			<td class="NormalRow" >
			<%String[] split = ws.getAttachment().split(",");%>
			<%for (int j = 0; j < split.length; j++) {%>
				<%String attach = split[j];%>
				<a href = '<%=(filePath2 + attach)%>'>下载&nbsp;</a>
			<%}%>
			</td>
		<%}%>--%>
        <td class="NormalRow" >&nbsp;</td>
        <td class="NormalRow" >
            <a href="javascript:void(0)" onclick='window.open("WorkOrderStepPic.jsp?attachment=<%=ws.getAttachment()%>", "_blank",
                "height=450px,width=600px,scrollbars=yes,location=no");'>查看图片</a>
        </td>
      <%}else{%>
		<td class="NormalRow" >&nbsp;</td> 
		<td class="NormalRow" >&nbsp;</td> 
    <%}%>
   </tr>
  <%} %>
  </table>
  
  <table cellspacing=0 cellpadding=0 border=0 align=center width="100%">
    <tr height = '25'>
      <td>&nbsp;</td>
    </tr>
  </table>
  
  <%if(vistResultList.size() > 0 ){ %>
  <table  cellspacing=0 cellpadding=0 border=1 align=center width="100%">
    <tr >
      <th class="HeaderRow" width="10%">回访人</th>
      <th class="HeaderRow" width="12%">回访时间</th>
      <th class="HeaderRow" width="10%">回访结果</th>
      <th class="HeaderRow" width="10%">客户满意度</th>
      <th class="HeaderRow" width="N">回访内容</th>
  </tr>
  <%for(int i = 0; i < vistResultList.size(); i ++){ 
     VisitResultModel vr = (VisitResultModel)vistResultList.get(i);
  
  %>
  <tr height = '25'>
    <td class="NormalRow" ><%=vr.getVisitCsr()%>&nbsp;</td>
    <td class="NormalRow" ><%=vr.getVisitTime()%>&nbsp;</td>
    <td class="NormalRow" ><%=vr.getVisitResultState()%>&nbsp;</td>
    <td class="NormalRow" ><%=vr.getVisitMind()%>&nbsp;</td>
    <td class="NormalRow" ><%=vr.getVisitResult()%>&nbsp;</td>
  </tr>
  <%} %>
  </table>
  <%}%>
  
  <table cellspacing=0 cellpadding=0 border=0 align=center width="100%">
    <tr height = '25'>
      <td>&nbsp;</td>
    </tr>
  </table>
  
  <%if(schemeChangeLogList.size() > 0 ){ %>
  <table  cellspacing=0 cellpadding=0 border=1 align=center width="100%">
    <tr >
      <th class="HeaderRow" width="10%">修改人</th>
      <th class="HeaderRow" width="12%">修改时间</th>
      <th class="HeaderRow" width="10%">原来预案</th>
      <th class="HeaderRow" width="10%">升级预案</th>
  </tr>
  <%for(int i = 0; i < schemeChangeLogList.size(); i ++){ 
     SchemeChangeLog sl = (SchemeChangeLog)schemeChangeLogList.get(i);
  
  %>
  <tr height = '25'>
    <td class="NormalRow" ><%=sl.getChangeCsr().getCsrname()%>&nbsp;</td>
    <td class="NormalRow" ><%=sl.getChangeTime() != null? sf.format(sl.getChangeTime()):""%>&nbsp;</td>
    <td class="NormalRow" ><%=sl.getFromScheme() != null? sl.getFromScheme().getDes():""%>&nbsp;</td>
    <td class="NormalRow" ><%=sl.getToScheme() != null? sl.getToScheme().getDes():""%>&nbsp;</td>
  </tr>
  <%} %>
  </table>
  <%}%>
<%=helper.generalViewPage2(WorkOrderTypeList.getLinks(1, workorder_type))%>

