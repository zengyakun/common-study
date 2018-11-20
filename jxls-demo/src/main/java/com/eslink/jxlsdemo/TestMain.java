package com.eslink.jxlsdemo;

import org.junit.Test;
import org.jxls.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/20 15:42
 * @Version 1.0
 **/
public class TestMain {

    private static final String basePath = "E:/IdeaProjects/study/common-study/jxls-demo/src/main/java/com/eslink";

    @Test
    public void test() throws Exception {
        // 模板路径和输出流
        String templatePath = basePath + "/template/template.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out.xls");
        // 定义一个Map，往里面放入要在模板中显示数据
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", "001");
        model.put("name", "张三");
        model.put("age", 18);
        //调用之前写的工具类，传入模板路径，输出流，和装有数据Map
        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");
    }

    @Test
    public void test2() throws Exception {
        // 模板位置，输出流
        String templatePath = basePath + "/template/template2.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out2.xls");

        // 一个装有对象数据的链表
        List<Person> persons = new ArrayList<Person>();
        Person p1 = new Person("001", "张三", 18);
        Person p2 = new Person("002", "李四", 19);
        Person p3 = new Person("003", "王五", 20);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("person", persons);    // 把链表放进model中

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");
    }

    @Test
    public void test3() throws Exception {
        // 模板位置，输出流
        String templatePath = basePath + "/template/template3.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out3.xls");

        // 一个装有对象数据的链表
        List<Person> persons = new ArrayList<Person>();
        Person p1 = new Person("001", "张三", 18);
        Person p2 = new Person("002", "李四", 19);
        Person p3 = new Person("003", "王五", 20);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("person", persons);    // 把链表放进model中

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");
    }

    @Test
    public void test4() throws Exception {
        String templatePath = basePath + "/template/template4.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out4.xls");

        Tool tool1 = new Tool("任意门", "想去哪就去哪");
        Tool tool2 = new Tool("竹蜻蜓", "想飞哪就飞哪");
        Tool tool3 = new Tool("空气炮", "空气炮");
        Tool tool4 = new Tool("翻译饼干", "翻译饼干");

        List<Doraemon> list = new ArrayList<Doraemon>();

        //制作一个哆啦A梦
        Doraemon doraemon1 = new Doraemon();
        //制作一号哆啦A梦的道具
        List<Tool> toolList1 = new ArrayList<Tool>();
        toolList1.add(tool1);
        toolList1.add(tool2);
        //设定一号哆啦A梦信息
        doraemon1.setName("哆啦A梦一号");
        doraemon1.setTools(toolList1);

        //制作一个哆啦A梦
        Doraemon doraemon2 = new Doraemon();
        //制作二号哆啦A梦的道具
        List<Tool> toolList2 = new ArrayList<Tool>();
        toolList2.add(tool3);
        toolList2.add(tool4);
        toolList2.add(tool1);
        //设定二号哆啦A梦信息
        doraemon2.setName("哆啦A梦二号");
        doraemon2.setTools(toolList2);

        list.add(doraemon1);
        list.add(doraemon2);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("data", list);

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");
    }

    /**
     * Excel 的分页名（页码）的封装
     * 此方法用来获取分好页的页名信息，将信息放入一个链表中返回
     */
    private static ArrayList<String> getSheetName(List<Page> page) {
        ArrayList<String> al = new ArrayList<String>();
        for (int i = 0; i < page.size(); i++) {
            al.add(page.get(i).getSheetName());
        }
        return al;
    }

    /**
     * 模拟生成数据
     */
    private static List<Student> generateData() {
        List<Student> list = new ArrayList<Student>();
        Student stu1 = new Student("001", "AAA", 10, 20, 30, 40, 50, 60);
        Student stu2 = new Student("002", "BBB", 20, 30, 40, 50, 60, 70);
        Student stu3 = new Student("003", "CCC", 30, 40, 50, 60, 70, 80);
        Student stu4 = new Student("004", "DDD", 40, 50, 60, 70, 80, 90);
        Student stu5 = new Student("005", "EEE", 50, 60, 70, 80, 90, 100);
        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        list.add(stu4);
        list.add(stu5);
        return list;
    }

    @Test
    public void test5() throws Exception {
        // 模板位置，输出流
        String templatePath = basePath + "/template/template5.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out5.xls");

        List<Student> list = generateData(); //    模拟数据库获取数据
        List<Page> page = DataByPage.byPage(list); // 把获取的数据进行分页转换
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pages", page);
        model.put("sheetNames", getSheetName(page));
        model.put("className", "六年三班");
        model.put("teacherComment", "已核实");
        model.put("directorComment", "已核实");

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();

        // 删除多出来的sheet
        DelSheet.deleteSheet(basePath + "/output/out5.xls", "tpl");
        System.out.println("完成");
    }

    /**
     * 将数据获取的数据封装成一页一个人的List
     */
    private static List<Page> individual(List<Student> list) {
        List<Page> pages = new ArrayList<Page>();
        for (int i = 0; i < list.size(); i++) {
            Page p = new Page();
            p.setOnlyOne(list.get(i));
            p.setSheetName(list.get(i).getName());
            pages.add(p);
        }
        return pages;
    }

    @Test
    public void test6() throws Exception {
        // 模板位置，输出流
        String templatePath = basePath + "/template/template6.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out6.xls");

        List<Student> list = generateData(); //    模拟数据库获取数据
//        List<Page> page = DataByPage.byPage(list); // 把获取的数据进行分页转换
        List<Page> page = individual(list); // 一页一个人
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pages", page);
        model.put("sheetNames", getSheetName(page));
        model.put("className", "六年三班");
        model.put("teacherComment", "已核实");
        model.put("directorComment", "已核实");

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();

        // 删除多出来的sheet
        DelSheet.deleteSheet(basePath + "/output/out6.xls", "tpl");
        System.out.println("完成");
    }

    @Test
    public void test7() throws Exception {
        // 模板位置，输出流
        String templatePath = basePath + "/template/template7.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out7.xls");

        List<Student> list = generateData(); //    模拟数据库获取数据
        //List<Page> page = DataByPage.byPage(list); // 把获取的数据进行分页转换
        List<Page> page = individual(list); // 一页一个人

        // 定义一个Map
        Map<String, String> tableInfo = new HashMap<String, String>();
        tableInfo.put("className", "六年三班2");
        tableInfo.put("teacherComment", "已核实2");
        tableInfo.put("directorComment", "已核实2");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pages", page);
        model.put("sheetNames", getSheetName(page));
        model.put("tableInfo", tableInfo);
        //model.put("className", "六年三班");
        //model.put("teacherComment", "已核实");
        //model.put("directorComment", "已核实");

        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        // 删除多出来的sheet
//        DelSheet.deleteSheet(basePath + "/output/out7.xls", "tpl");
        System.out.println("完成");
    }

    @Test
    public void test8() throws Exception {
        // 模板位置，输出流
        String templatePath = basePath + "/template/template8.xls";
        OutputStream os = new FileOutputStream(basePath + "/output/out8.xls");
        // 文件流，输入一张叫fly的png图片
        InputStream imageInputStream = new FileInputStream(basePath + "/template/png.png");
        // 使用工具方法把流转成byte数组
        byte[] imageBytes = Util.toByteArray(imageInputStream);

        // 一个装有对象数据的链表
        List<Person> persons = new ArrayList<Person>();
        Person p1 = new Person("001", "张三", 18);
        Person p2 = new Person("002", "李四", 19);
        Person p3 = new Person("003", "王五", 20);
        // 把图片转换的字节数组存进person对象中
        p1.setImg(imageBytes);
        p2.setImg(imageBytes);
        p3.setImg(imageBytes);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("person", persons);    // 把链表放进model中
        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
        System.out.println("完成");
    }
}

