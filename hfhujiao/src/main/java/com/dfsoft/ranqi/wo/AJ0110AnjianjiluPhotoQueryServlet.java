package com.dfsoft.ranqi.wo;

import com.dfsoft.common.ServletBase;
import com.dfsoft.common.VO;
import com.dfsoft.common.web.HttpContext;
import com.dfsoft.ranqi.common.MenuHelper;
import com.dfsoft.ranqi.common.RanqiServletBase;
import com.dfsoft.ranqi.common.SessionHelper;
import com.dfsoft.ranqi.ext.SearchPageWorker;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AJ0110AnjianjiluPhotoQueryServlet
        extends RanqiServletBase
{
    public String perform(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        HttpContext context = new HttpContext(req, res);
        SessionHelper session = new SessionHelper(context);
        MenuHelper helper = new MenuHelper(context);
        helper.buildMenu(getBTKey());
        try
        {
            if (!session.isLogin())
            {
                showError("安全警告", "用户没有登录，或用户身份不能识别", req, res);
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(ServletBase.logs);
            showError("系统错误", "系统身份验证失败，系统暂时不可用", req, res);
            return null;
        }
        String defaultPage = "/ranqi/aj/aj0110/AJ0110AnjianjiluPhotoQuery.jsp";
        AJ0110AnjianjiluBean bean = new AJ0110AnjianjiluBean(new SessionHelper(context));
        VO params = context.parameters();
        params.setAttr("yqdz_org_id", new SessionHelper(context).getLocalAreaCode());
        VO mvo = new VO();
        Map<String, Object> map = bean.queryAnjianjiluPhoto(params, mvo);
        context.setAttribute("DATA", map);
        SearchPageWorker.parseFromBean(params, context.request());

        return defaultPage;
    }

    public String getBTFuncCode()
    {
        return "AJ011003";
    }
}