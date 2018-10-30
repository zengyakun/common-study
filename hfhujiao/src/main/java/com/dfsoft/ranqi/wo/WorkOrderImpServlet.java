package com.dfsoft.ranqi.wo;

import com.dfsoft.common.web.HttpContext;
import com.dfsoft.ranqi.common.MenuHelper;
import com.dfsoft.ranqi.common.RanqiCommonRedirectServlet;
import com.dfsoft.ranqi.common.RanqiServletBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName WorkOrderImpServlet
 * @Description 工单导入页面
 * @Author zeng.yakun (0178)
 * @Date 2018/10/23 10:44
 * @Version 1.0
 **/
public class WorkOrderImpServlet extends RanqiCommonRedirectServlet {

    public WorkOrderImpServlet() {
    }

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String defaultpage = "/ranqi/wo/v2/WorkOrderImp.jsp";
        HttpContext context = new HttpContext(req, resp);
        MenuHelper helper = new MenuHelper(context);
        helper.buildMenu("115400");
        return defaultpage;
    }
}
