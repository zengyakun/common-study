package com.dfsoft.ranqi.wo;

import com.dfsoft.common.VO;
import com.dfsoft.common.VOList;
import com.dfsoft.common.web.HttpContext;
import com.dfsoft.ranqi.common.MenuHelper;
import com.dfsoft.ranqi.common.RanqiServletBase;
import com.dfsoft.ranqi.common.SessionHelper;
import com.dfsoft.ranqi.ext.SearchPageWorker;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AJ0110AnjianjiluQueryServlet
        extends RanqiServletBase
{
    @Override
    public String perform(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        HttpContext context = new HttpContext(req, res);
        MenuHelper helper = new MenuHelper(context);
        helper.buildMenu(getBTKey());
        String defaultPage = "/ranqi/aj/aj0110/AJ0110AnjianjiluQuery.jsp";
        AJ0110AnjianjiluBean bean = new AJ0110AnjianjiluBean(new SessionHelper(context));
        VO params = context.parameters();
        params.setAttr("yqdz_org_id", new SessionHelper(context).getLocalAreaCode());
        VO mvo = new VO();
        if ((!helper.isAction("redirect")) && (!helper.isAction((String)null)))
        {
            if ((!helper.isAction("query")) && (!helper.isAction("return")) && (!helper.isAction("switch")))
            {
                if (helper.isAction("queryBox"))
                {
                    mvo.setAttr("code", "00000");
                    defaultPage = "/ranqi/aj/aj0110/AJ0110AnjianjiluQueryBox.jsp";
                }
            }
            else
            {
                VOList list = bean.queryAnjianjilu(params, mvo);
                context.setAttribute("DATA", list);
                SearchPageWorker.parseFromBean(params, context.request());
            }
        }
        else {
            mvo.setAttr("code", "00000");
        }
        return helper.checkError(mvo, getBTKey(), defaultPage);
    }

    @Override
    public String getBTFuncCode()
    {
        return "AJ011002";
    }
}