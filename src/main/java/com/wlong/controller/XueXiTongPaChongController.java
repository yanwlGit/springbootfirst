package com.wlong.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/qian")
public class XueXiTongPaChongController {

    @RequestMapping(value = "/index")
    public String index(String uname){
        return "index";
    }

    @RequestMapping(value = "/testJson",method = RequestMethod.POST)
    @ResponseBody
    public String testJson(String uname){
        JSONObject jsonObject=new JSONObject();
        if(null==uname || "".equals(uname)){
            uname="未获取到参数";
        }
        jsonObject.put("uname",uname);
        return jsonObject.toJSONString();
    }
}
