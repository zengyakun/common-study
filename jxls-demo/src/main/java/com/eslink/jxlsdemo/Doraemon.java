package com.eslink.jxlsdemo;

import java.util.List;

/**
 * @ClassName Doraemon
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/20 16:08
 * @Version 1.0
 **/
public class Doraemon {
    private String name; // 哆啦A梦的名字
    private List<Tool> tools; // 拥有的道具，这是一个链表，存放的是Tool类

    public Doraemon(String name, List<Tool> tools) {
        super();
        this.name = name;
        this.tools = tools;
    }

    public Doraemon() {
    }

    /**
     * 以下省略所有get/set方法，请自行添加
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    @Override
    public String toString() {
        return "Doraemon{" +
                "name='" + name + '\'' +
                ", tools=" + tools +
                '}';
    }
}
