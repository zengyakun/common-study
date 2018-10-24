package com.dfsoft.ranqi.wo;

import com.dfsoft.common.JdbcWorker;
import com.dfsoft.common.VO;
import com.dfsoft.common.VOList;
import com.dfsoft.ranqi.common.SessionHelper;
import com.dfsoft.ranqi.ext.SearchPageWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AJ0110AnjianjiluBean
{
    SessionHelper sh;

    public AJ0110AnjianjiluBean(SessionHelper sh)
    {
        this.sh = sh;
    }

    public VOList queryAnjianjilu(VO vo, VO mvo)
    {
        try
        {
            VOList list = null;
            JdbcWorker jdbc = new JdbcWorker();

            // 获取安检计划类型
            String ajjlLx = vo.getAttr("Search_AJJHYH_LX");
            if ((ajjlLx == null) || (ajjlLx).equals("")) {
                // 默认为居民户
                ajjlLx = "2";
            }

            StringBuffer sql = new StringBuffer("");
            if ("1".equals(ajjlLx)) {
                // 工商户
                sql.append("SELECT ajjl_id, ajjl_ajrhrq, ajjl_ajr_name, ajjl_jieguo,ajjl_yh_bz, ajjl_beizhu, yqdz_userid_old, yqdz_org_id, yqdz_kh_lx, kh_mc AS yhxm, yqdz_sm, kh_dh_bg AS dh, kh_dingzhi_sj AS sj, yqdz_id, yqdz_fwz_id AS xqid ")
                        .append("FROM ANJIAN_JILU a1 ")
                        .append("LEFT JOIN yongqidizhi a2 ON a2.yqdz_id=a1.AJJL_YQDZ_ID ")
                        .append("LEFT JOIN danweikehu a3 ON a3.kh_id=a2.yqdz_kh_id AND a2.yqdz_zt_id='1002' AND a2.yqdz_kh_lx='1' AND a2.yqdz_guabiao_zt='1' ");
            }

            if ("2".equals(ajjlLx)) {
                // 居民户
                sql.append("SELECT ajjl_id, ajjl_ajrhrq, ajjl_ajr_name, ajjl_jieguo,ajjl_yh_bz, ajjl_beizhu, yqdz_userid_old, yqdz_org_id, yqdz_kh_lx, gr_xm AS yhxm, yqdz_sm, gr_jtdh AS dh, gr_sj_for_sms AS sj, yqdz_id, Yqdz_yqdzm_xiaoqu_id AS xqid ")
                        .append("FROM ANJIAN_JILU a1 ")
                        .append("LEFT JOIN yongqidizhi a2 ON a2.yqdz_id=a1.AJJL_YQDZ_ID ")
                        .append("LEFT JOIN gerenkehu a3 ON a3.gr_id=a2.yqdz_kh_id AND a2.yqdz_zt_id='1002' AND a2.yqdz_kh_lx='2' AND a2.yqdz_guabiao_zt='1' ");
            }

            sql.append("WHERE 1=1 ")
                    .append("AND DATEDIFF (YEAR, a1.ajjl_ajrhrq, CONVERT(VARCHAR(4), getdate(), 112)+'-01-01') = 0 ")
                    .append("AND NOT EXISTS (SELECT 1 FROM ANJIAN_JILU b WHERE b.AJJL_YQDZ_ID = a1.AJJL_YQDZ_ID AND b.ajjl_id > a1.ajjl_id) ");

            if ("1".equals(ajjlLx)) {
                // 工商户
                sql.append("AND a2.yqdz_zt_id='1002' AND a2.yqdz_kh_lx='1' AND a2.yqdz_guabiao_zt='1' ");
            }

            if ("2".equals(ajjlLx)) {
                // 居民户
                sql.append("AND a2.yqdz_zt_id='1002' AND a2.yqdz_kh_lx='2' AND a2.yqdz_guabiao_zt='1' ");
            }

            if ((vo.getAttr("Search_AJJHYH_YHH") != null) && (!vo.getAttr("Search_AJJHYH_YHH").equals("")))
            {
                sql.append(" and a2.yqdz_userid_old ='");
                sql.append(vo.getAttr("Search_AJJHYH_YHH"));
                sql.append("' ");
            }
            if ((vo.getAttr("Search_AJJHYH_YHXM") != null) && (!vo.getAttr("Search_AJJHYH_YHXM").equals("")))
            {
                if ("1".equals(ajjlLx)) {
                    sql.append(" and a3.kh_mc ='");
                    sql.append(vo.getAttr("Search_AJJHYH_YHXM"));
                    sql.append("' ");
                } else if ("2".equals(ajjlLx)) {
                    sql.append(" and a3.gr_xm ='");
                    sql.append(vo.getAttr("Search_AJJHYH_YHXM"));
                    sql.append("' ");
                }
            }
            if ((vo.getAttr("Search_AJJHYH_SJ") != null) && (!vo.getAttr("Search_AJJHYH_SJ").equals("")))
            {
                if ("1".equals(ajjlLx)) {
                    sql.append(" and a3.kh_dingzhi_sj ='");
                    sql.append(vo.getAttr("Search_AJJHYH_SJ"));
                    sql.append("' ");
                } else if ("2".equals(ajjlLx)) {
                    sql.append(" and a3.gr_sj_for_sms ='");
                    sql.append(vo.getAttr("Search_AJJHYH_SJ"));
                    sql.append("' ");
                }
            }
            if ((vo.getAttr("Search_AJJHYH_DH") != null) && (!vo.getAttr("Search_AJJHYH_DH").equals("")))
            {
                if ("1".equals(ajjlLx)) {
                    sql.append(" and a3.kh_dh_bg ='");
                    sql.append(vo.getAttr("Search_AJJHYH_DH"));
                    sql.append("' ");
                } else if ("2".equals(ajjlLx)) {
                    sql.append(" and a3.gr_jtdh ='");
                    sql.append(vo.getAttr("Search_AJJHYH_DH"));
                    sql.append("' ");
                }
            }
            if ((vo.getAttr("Search_AJJHYH_AJR") != null) && (!vo.getAttr("Search_AJJHYH_AJR").equals("")))
            {
                sql.append(" and a1.ajjl_ajr_name ='");
                sql.append(vo.getAttr("Search_AJJHYH_AJR"));
                sql.append("' ");
            }
            if ((vo.getAttr("Search_AJJHYH_AJJG") != null) && (!vo.getAttr("Search_AJJHYH_AJJG").equals("")))
            {
                sql.append(" and a1.ajjl_jieguo ='");
                sql.append(vo.getAttr("Search_AJJHYH_AJJG"));
                sql.append("' ");
            }

            sql.append("ORDER BY a1.ajjl_ajrhrq DESC ");

            SearchPageWorker page = new SearchPageWorker(vo, jdbc);
            list = page.execute(new VO("anjian_jilu", "ajjl_id", false), sql.toString());
            if (null != list && null != list.listData && !list.listData.isEmpty()) {
                for (int i = 0, totalCount = list.listData.size(); i < totalCount; i++) {
                    VO ajjlVO = (VO) list.listData.elementAt(i);
                    // 查询安检隐患
                    if (null != ajjlVO.getAttr("ajjl_id") && !"".equals(ajjlVO.getAttr("ajjl_id"))) {
                        StringBuffer searchAjyhSql = new StringBuffer("");
                        searchAjyhSql.append("SELECT DISTINCT ajyh_id, ajjl_id, ajx_des ")
                                .append("FROM anjian_jilu_wtx ajwt ")
                                .append("LEFT JOIN anjian_yinhuan ajyh ON ajyh.ajx_id=ajwt.ajyh_id ")
                                .append("WHERE org_id='")
                                .append(vo.getAttr("yqdz_org_id"))
                                .append("' ")
                                .append("AND ajjl_id='")
                                .append(ajjlVO.getAttr("ajjl_id"))
                                .append("' ");
                        // 获取安检隐患记录列表
                        VOList ajyhList = jdbc.readAll(new VO(), searchAjyhSql.toString());
                        if (null != ajyhList && null != ajyhList.listData && !ajyhList.listData.isEmpty()) {
                            StringBuffer ajyhDes = new StringBuffer("");
                            for (int j = 0, totalAjyhCount = ajyhList.listData.size(); j < totalAjyhCount; j++) {
                                VO ajyhVO = (VO) ajyhList.listData.elementAt(j);
                                ajyhDes.append(ajyhVO.getAttr("ajx_des")).append(",");
                            }

                            ajjlVO.setAttr("ajx_des", ajyhDes.toString());
                        }
                    } else {
                        ajjlVO.setAttr("ajx_des", "");
                    }


                    // 组装手机电话
                    String sj = ajjlVO.getAttr("sj");
                    String dh = ajjlVO.getAttr("dh");
                    String ajjlIdForPic = ajjlVO.getAttr("ajjl_id");
                    if ((null != ajjlIdForPic) && (!"".equals(ajjlIdForPic))) {
                        ajjlVO.setAttr("fj", "<a href='javascript: showPhoto(\"" + ajjlIdForPic + "\")'>查看图片</a>");
                    } else {
                        ajjlVO.setAttr("fj", "暂无");
                    }
                    if ("1".equals(ajjlLx)) {
                        ajjlVO.setAttr("sjdh", dh);
                    } else if ("2".equals(ajjlLx)) {
                        if ((sj != null) && (!sj.equals("")) && (dh != null) && (!dh.equals(""))) {
                            ajjlVO.setAttr("sjdh", sj + "/" + dh);
                        } else {
                            ajjlVO.setAttr("sjdh", "");
                            if ((sj != null) && (!sj.equals(""))) {
                                ajjlVO.setAttr("sjdh", sj);
                            }
                            if ((dh != null) && (!dh.equals(""))) {
                                ajjlVO.setAttr("sjdh", dh);
                            }
                        }
                    }
                }

                list.transList("ajjl_jieguo", "anjian_jilu_zt");
                list.transList("ajjl_yh_bz", "yesno");

                mvo.setAttr("code", "00000");
                return list;
            } else {
                mvo.setAttr("code", "00000");
                return new VOList();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            mvo.setAttr("code", "99999");
        }
        return new VOList();
    }

    public Integer count(VO vo, VO mvo)
    {
        try
        {
            JdbcWorker jdbc = new JdbcWorker();

            // 获取安检计划类型
            String ajjlLx = vo.getAttr("Search_AJJHYH_LX");
            if ((ajjlLx == null) || (ajjlLx).equals("")) {
                // 默认为居民户
                ajjlLx = "2";
            }

            StringBuffer sql = new StringBuffer("");
            if ("1".equals(ajjlLx)) {
                // 工商户
                sql.append("SELECT COUNT(a1.ajjl_id) ")
                        .append("FROM ANJIAN_JILU a1 ")
                        .append("LEFT JOIN yongqidizhi a2 ON a2.yqdz_id=a1.AJJL_YQDZ_ID ")
                        .append("LEFT JOIN danweikehu a3 ON a3.kh_id=a2.yqdz_kh_id AND a2.yqdz_zt_id='1002' AND a2.yqdz_kh_lx='1' AND a2.yqdz_guabiao_zt='1' ");
            }

            if ("2".equals(ajjlLx)) {
                // 居民户
                sql.append("SELECT COUNT(a1.ajjl_id) ")
                        .append("FROM ANJIAN_JILU a1 ")
                        .append("LEFT JOIN yongqidizhi a2 ON a2.yqdz_id=a1.AJJL_YQDZ_ID ")
                        .append("LEFT JOIN gerenkehu a3 ON a3.gr_id=a2.yqdz_kh_id AND a2.yqdz_zt_id='1002' AND a2.yqdz_kh_lx='2' AND a2.yqdz_guabiao_zt='1' ");
            }

            sql.append("WHERE 1=1 ")
                    .append("AND DATEDIFF (YEAR, a1.ajjl_ajrhrq, CONVERT(VARCHAR(4), getdate(), 112)+'-01-01') = 0 ")
                    .append("AND NOT EXISTS (SELECT 1 FROM ANJIAN_JILU b WHERE b.AJJL_YQDZ_ID = a1.AJJL_YQDZ_ID AND b.ajjl_id > a1.ajjl_id) ");

            if ((vo.getAttr("Search_AJJHYH_YHH") != null) && (!vo.getAttr("Search_AJJHYH_YHH").equals("")))
            {
                sql.append(" and a2.yqdz_userid_old ='");
                sql.append(vo.getAttr("Search_AJJHYH_YHH"));
                sql.append("' ");
            }
            if ((vo.getAttr("Search_AJJHYH_YHXM") != null) && (!vo.getAttr("Search_AJJHYH_YHXM").equals("")))
            {
                if ("1".equals(ajjlLx)) {
                    sql.append(" and a3.kh_mc ='");
                    sql.append(vo.getAttr("Search_AJJHYH_YHXM"));
                    sql.append("' ");
                } else if ("2".equals(ajjlLx)) {
                    sql.append(" and a3.gr_xm ='");
                    sql.append(vo.getAttr("Search_AJJHYH_YHXM"));
                    sql.append("' ");
                }
            }
            if ((vo.getAttr("Search_AJJHYH_SJ") != null) && (!vo.getAttr("Search_AJJHYH_SJ").equals("")))
            {
                if ("1".equals(ajjlLx)) {
                    sql.append(" and a3.kh_dingzhi_sj ='");
                    sql.append(vo.getAttr("Search_AJJHYH_SJ"));
                    sql.append("' ");
                } else if ("2".equals(ajjlLx)) {
                    sql.append(" and a3.gr_sj_for_sms ='");
                    sql.append(vo.getAttr("Search_AJJHYH_SJ"));
                    sql.append("' ");
                }
            }
            if ((vo.getAttr("Search_AJJHYH_DH") != null) && (!vo.getAttr("Search_AJJHYH_DH").equals("")))
            {
                if ("1".equals(ajjlLx)) {
                    sql.append(" and a3.kh_dh_bg ='");
                    sql.append(vo.getAttr("Search_AJJHYH_DH"));
                    sql.append("' ");
                } else if ("2".equals(ajjlLx)) {
                    sql.append(" and a3.gr_jtdh ='");
                    sql.append(vo.getAttr("Search_AJJHYH_DH"));
                    sql.append("' ");
                }
            }
            if ((vo.getAttr("Search_AJJHYH_AJR") != null) && (!vo.getAttr("Search_AJJHYH_AJR").equals("")))
            {
                sql.append(" and a1.ajjl_ajr_name ='");
                sql.append(vo.getAttr("Search_AJJHYH_AJR"));
                sql.append("' ");
            }
            if ((vo.getAttr("Search_AJJHYH_AJJG") != null) && (!vo.getAttr("Search_AJJHYH_AJJG").equals("")))
            {
                sql.append(" and a1.ajjl_jieguo ='");
                sql.append(vo.getAttr("Search_AJJHYH_AJJG"));
                sql.append("' ");
            }

            VO ret = jdbc.readInCombination(new VO(), sql.toString());
            return Integer.valueOf(ret.getAttr("num"));

        }
        catch (Exception var6)
        {
            var6.printStackTrace();
            mvo.setAttr("code", "99999");
        }
        return Integer.valueOf(0);
    }

    public Map<String, Object> queryAnjianjiluPhoto(VO vo, VO mvo)
    {
        try
        {
            Map<String, Object> resultMap = new HashMap(2);
            JdbcWorker jdbc = new JdbcWorker();
            List<String> photoUrlList = new ArrayList();

            String ajjh_id = vo.getAttr("ajjl_id");
            if ((null != ajjh_id) && (!"".equals(ajjh_id)))
            {
                String searchAjjlSql = "SELECT ajjl_id, photo_urls FROM anjian_jilu WHERE ajjl_id='" + ajjh_id + "'";
                VO ajjlInfo = jdbc.readInCombination(new VO(), searchAjjlSql);
                String photoUrls = ajjlInfo.getAttr("photo_urls");
                if ((null != photoUrls) && (!"".equals(photoUrls)))
                {
                    String[] photoUrlsArray = photoUrls.split(",");
                    for (int i = 0; i < photoUrlsArray.length; i++) {
                        photoUrlList.add(photoUrlsArray[i]);
                    }
                    resultMap.put("photoUrls", photoUrlList);
                }
                String searchAjjlWtxSql = "SELECT ajjl_id, ajjl_wtx_id, ajx_des, urls FROM anjian_jilu_wtx a LEFT JOIN anjian_yinhuan b ON b.ajx_id=a.ajyh_id WHERE ajjl_id='" + ajjh_id + "'";
                VOList ajjlWtxList = jdbc.readAll(new VO(), searchAjjlWtxSql);
                if ((null != ajjlWtxList) && (null != ajjlWtxList.listData) && (!ajjlWtxList.listData.isEmpty())) {
                    resultMap.put("wtxUrls", ajjlWtxList);
                }
                mvo.setAttr("code", "00000");
            }
            else
            {
                mvo.setAttr("code", "99999");
                mvo.setAttr("info", "暂无安检记录图片");
            }
            return resultMap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            mvo.setAttr("code", "99999");
        }
        return new HashMap();
    }
}
