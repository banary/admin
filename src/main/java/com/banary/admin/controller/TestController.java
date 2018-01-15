package com.banary.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/test", method={RequestMethod.GET})
    public @ResponseBody String test(){
        return "小刘要的数据";
    }

    @RequestMapping(value = "/getData", method={RequestMethod.GET})
    public @ResponseBody ResponseResult<Map<String, String>> get(@RequestParam("id") String id, @RequestParam("name")String name){
        ResponseResult<Map<String, String>> responseResult = new ResponseResult<>();
        responseResult.setCode("0000");
        responseResult.setMsg("请求成功");

        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("name", name);
        responseResult.setData(data);
        return responseResult;
    }



}
