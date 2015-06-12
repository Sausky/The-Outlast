package com.org.outlast.core.entity;

/**
 * Created by shen on 15/6/9.
 */
public class CurrentTool {
    private static String current_tool;
    public static String getCurrentTool(){
        return current_tool;
    }

    public static void setCurrentTool(String tool_res){
        current_tool = tool_res;
    }
}
