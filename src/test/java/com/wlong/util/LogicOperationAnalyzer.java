package com.wlong.util;


import com.wlong.vo.AnalyzerNode;

import java.util.ArrayList;
import java.util.List;

public class LogicOperationAnalyzer {



    public static void main(String[]args){
        String logicRepresentation="a || ((bb || (ccc&&dddd) || eeeee) && (ffffff||ggggggg)) || hhhhhhhh";

        //a|| ( (bb||(ccc&&dddd)||eeeee) && (ffffff||ggggggg) ) ||hhhhhhhh
        logicRepresentation=logicRepresentation.replace(" ","");
        logicRepresentation=logicRepresentation.replace("||","或");
        logicRepresentation=logicRepresentation.replace("&&","且");
        //a或((bb或(ccc且dddd)或eeeee)且(ffffff或ggggggg))或hhhhhhhh

        AnalyzerNode analyzerNode=new AnalyzerNode();
        analyzerNode.setStart(0);
        analyzerNode.setEnd(logicRepresentation.length());
        analyzerNode.setLogicContext(logicRepresentation);
        analyzer(analyzerNode);
        checkAnalyzerNodeVal(analyzerNode);

    }

    private static void analyzer(AnalyzerNode analyzerNode){
        //System.out.println("---------------------------start-------------------------------------");
        String context=analyzerNode.getLogicContext();
        List<AnalyzerNode> listRel=analyzerNode.getRelAnalyzerNode();
        if(listRel==null){
            listRel=new ArrayList<>();
            analyzerNode.setRelAnalyzerNode(listRel);
        }
        //确定解析为几个单元
        int unitCount=0;
        for(int i=0;i<context.length();i++){
            String strVal=String.valueOf(context.charAt(i));
            if(!"且".equals(strVal) && !"或".equals(strVal)
                    && !"(".equals(strVal) && !")".equals(strVal)){
                AnalyzerNode simpleUnit=getLogicContextByStartIndex(i,context);
                unitCount++;
                i=simpleUnit.getEnd();
                /*System.out.println("----unit "+unitCount+" start ----");
                System.out.println("从"+simpleUnit.getStart()+"到"+simpleUnit.getEnd());
                System.out.println(simpleUnit.getLogicContext());
                System.out.println("与上一个逻辑单元的运算符 "+simpleUnit.getPreviousLogicalOperator());
                System.out.println("----unit "+unitCount+" end ----");*/
                analyzerNode.getRelAnalyzerNode().add(simpleUnit);
            }else if("(".equals(strVal)){
                AnalyzerNode anyUnit=getContextByStartIndex(i,context);
                i=anyUnit.getEnd();
                unitCount++;
                /*System.out.println("----unit "+unitCount+" start ----");
                System.out.println("从"+anyUnit.getStart()+"到"+anyUnit.getEnd());
                System.out.println(context.substring(anyUnit.getStart(),anyUnit.getEnd()));
                System.out.println("与上一个逻辑单元的运算符 "+anyUnit.getPreviousLogicalOperator());
                System.out.println("----unit "+unitCount+" end ----");*/
                analyzerNode.getRelAnalyzerNode().add(anyUnit);
                if(anyUnit.getLogicContext().contains("(") || anyUnit.getLogicContext().contains("且")
                        || anyUnit.getLogicContext().contains("或")){
                    analyzer(anyUnit);
                }
            }
        }
        /*System.out.println("从"+analyzerNode.getStart()+"到"+analyzerNode.getEnd());
        System.out.println(context);
        System.out.println("可划分为:"+unitCount+"小单元");
        System.out.println("---------------------------end-------------------------------------");*/

    }

    /**
     * 根据括号的起始位置获取括号中的内容
     * */
    private static AnalyzerNode getContextByStartIndex(int sat,String str){

        AnalyzerNode analyzerNode=new AnalyzerNode();

        int end=getEndIndexByStartIndex(sat,str);
        //完整的字符串
        //String haveBracketsStr=str.substring(sat,end+1);
        //去除当前括号的字符串
        String noBracketsStr=str.substring(sat+1,end);
        analyzerNode.setStart(sat+1);
        analyzerNode.setEnd(end);
        if(sat-1>0){
            analyzerNode.setPreviousLogicalOperator(String.valueOf(str.charAt(sat-1)));
        }
        analyzerNode.setLogicContext(noBracketsStr);

        return analyzerNode;

    }

    /**
     * 根据括号的起始位置获取结束位置
     * */
    private static int getEndIndexByStartIndex(int start,String s){
        int val=1;
        int startNum=1;
        int endNum=0;
        for(int i=(start+1);i<s.length();i++){
            if(String.valueOf(s.charAt(i)).equals("(")){
                startNum++;
            }else if(String.valueOf(s.charAt(i)).equals(")")){
                endNum++;
            }
            if(startNum==endNum || i==s.length()-1){
                val=i;
                break;
            }
        }
        return val;
    }

    /**
     * 获取表达式中某一个逻辑单元的内容
     * */
    public static AnalyzerNode getLogicContextByStartIndex(int start,String s){
        AnalyzerNode analyzerNode=new AnalyzerNode();
        String val="";
        int end=0;
        for(int i=start;i<s.length();i++){
            String any=String.valueOf(s.charAt(i));
            if("或".equals(any) || "且".equals(any) || "(".equals(any) || ")".equals(any)){
                break;
            }else{
                val+=any;
            }
            end++;
        }
        analyzerNode.setStart(start);
        analyzerNode.setEnd(start+end);
        analyzerNode.setLogicContext(val);
        if(start-1>0){
            analyzerNode.setPreviousLogicalOperator(String.valueOf(s.charAt(start-1)));
        }


        return analyzerNode;
    }

    public static boolean checkAnalyzerNodeVal(AnalyzerNode analyzerNode){
        if(analyzerNode.getLogicContext().contains("(") || analyzerNode.getLogicContext().contains("或")
                || analyzerNode.getLogicContext().contains("且")){
            List<AnalyzerNode> list=analyzerNode.getRelAnalyzerNode();
            boolean val=false;
            for(int i=0;i<list.size();i++){
                AnalyzerNode curUnit=list.get(i);
                AnalyzerNode nextUnit=null;
                if(i+1<list.size()){
                    nextUnit=list.get(i+1);
                }
                boolean curVal=false;

                if(i==0){
                    curVal=checkAnalyzerNodeVal(curUnit);
                } else if(val){
                    curVal=true;
                }

                if(curVal==true && nextUnit!=null && "或".equals(nextUnit.getPreviousLogicalOperator())){
                    val=true;
                }else if(curVal==true && nextUnit!=null && "且".equals(nextUnit.getPreviousLogicalOperator())){
                    boolean nextVal=checkAnalyzerNodeVal(nextUnit);
                    val=nextVal;
                }else if(curVal==false && nextUnit!=null && "且".equals(nextUnit.getPreviousLogicalOperator())){
                    val=false;
                }else if(curVal==false && nextUnit!=null && "或".equals(nextUnit.getPreviousLogicalOperator())){
                    boolean nextVal=checkAnalyzerNodeVal(nextUnit);
                    val= nextVal;
                }else if(nextUnit==null){
                    val= curVal;
                }else{
                    val= false;
                }
            }
            System.out.println(analyzerNode.getLogicContext()+" 的值为 "+val);
            return val;

        }else{
            boolean val=checkLogicContextIsTrue(analyzerNode.getLogicContext());
            System.out.println(analyzerNode.getLogicContext()+" 的值为 "+val);
            return val;
        }

    }



    public static boolean checkLogicContextIsTrue(String logicContext){
        boolean val=false;
        switch (logicContext){
            case "a":  val=false;break;

            case "bb": val=true;break;

            case "ccc":   val=false;break;

            case "dddd":  val=false;break;

            case "eeeee": val=false;break;

            case "ffffff":val=true;break;

            case "ggggggg":val=false;break;

            case "hhhhhhhh": val=false;break;

            default: val=false;break;

        }
        return val;
    }

}
