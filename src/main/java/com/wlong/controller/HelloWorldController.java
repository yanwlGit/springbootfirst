package com.wlong.controller;

import com.alibaba.fastjson.JSONObject;
import com.wlong.dodata.SimpleVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWord(){
        return "helloWorld";
    }

    @PostMapping("/testPost")
    public Map<String,Object> testPost(){
        Map<String,Object>returnMap=new HashMap<>();
        List<SimpleVO> listVal=new ArrayList<>();
        SimpleVO simpleVO=new SimpleVO();
        simpleVO.setText("姓名");
        simpleVO.setVal("张蕊");
        SimpleVO simpleVO1=new SimpleVO();
        simpleVO1.setText("姓名");
        simpleVO1.setVal("张天");
        SimpleVO simpleVO2=new SimpleVO();
        simpleVO2.setText("姓名");
        simpleVO2.setVal("张梦博");
        listVal.add(simpleVO1);
        listVal.add(simpleVO2);
        returnMap.put("status","1");
        returnMap.put("msg","成功");
        returnMap.put("data","[{\"a\":\"1\",\"b\":\"2\"}]");
        returnMap.put("dataVO", JSONObject.toJSONString(simpleVO));
        returnMap.put("dataList", JSONObject.toJSONString(listVal));
        return returnMap;

    }
}
