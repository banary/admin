package com.banary.admin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails)auth.getDetails();

        System.out.println("sessionId:"+details.getSessionId());
        System.out.println("ip:"+details.getRemoteAddress());
        System.out.println("是否登录：" + auth.isAuthenticated());
        System.out.println("name:"+auth.getName());
        System.out.println("password:"+auth.getCredentials());
        System.out.println("权限:"+auth.getAuthorities());

        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("name", auth.getName());
        responseResult.setData(data);
        return responseResult;
    }



}
