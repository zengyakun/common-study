package com.eslink.jxlsdemo;

/**
 * @ClassName Tool
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/20 16:09
 * @Version 1.0
 **/
public class Tool {

    private String toolName; // 道具名
    private String toolFunction; // 道具功能

    public Tool(String toolName, String toolFunction) {
        super();
        this.toolName = toolName;
        this.toolFunction = toolFunction;
    }

    public Tool() {
    }

    /** 以下省略所有get/set方法，请自行添加 */
    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolFunction() {
        return toolFunction;
    }

    public void setToolFunction(String toolFunction) {
        this.toolFunction = toolFunction;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "toolName='" + toolName + '\'' +
                ", toolFunction='" + toolFunction + '\'' +
                '}';
    }
}
