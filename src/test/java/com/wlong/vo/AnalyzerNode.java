package com.wlong.vo;


import java.util.List;

public class AnalyzerNode {


    //本单元的开始位置
    private int start;

    //本单元的结束位置
    private int end;

    //当前逻辑单元的内容
    private String logicContext;

    //关联前面的逻辑运算符
    private String previousLogicalOperator;

    //与当前单元相关的运算单元
    private List<AnalyzerNode> relAnalyzerNode;





    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getLogicContext() {
        return logicContext;
    }

    public void setLogicContext(String logicContext) {
        this.logicContext = logicContext;
    }

    public String getPreviousLogicalOperator() {
        return previousLogicalOperator;
    }

    public void setPreviousLogicalOperator(String previousLogicalOperator) {
        this.previousLogicalOperator = previousLogicalOperator;
    }


    public List<AnalyzerNode> getRelAnalyzerNode() {
        return relAnalyzerNode;
    }

    public void setRelAnalyzerNode(List<AnalyzerNode> relAnalyzerNode) {
        this.relAnalyzerNode = relAnalyzerNode;
    }

}
