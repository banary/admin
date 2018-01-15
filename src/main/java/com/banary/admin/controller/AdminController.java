package com.banary.admin.controller;

import com.banary.admin.entity.User;
import com.banary.admin.base.Pagenation;
import com.banary.admin.service.UserService;
import com.banary.admin.view.AdministratorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xiyongchun on 2017/7/7.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserList(@RequestParam(value = "userName", required=false) String userName) {
        int count = this.userService.countUserWithUserName(userName);
        List<User> userList = this.userService.listWithUserName(userName);
        List<AdministratorView> viewList = userList.stream()
                .map(user -> userCovertToAdministratorView(user))
                .collect(Collectors.toList());
        return new ModelAndView("admin-list", "data",
                Pagenation.newPage(1, Pagenation.DEFAULT_PAGE_SIZE, count, viewList));
    }

    private AdministratorView userCovertToAdministratorView(User user){
        AdministratorView view = new AdministratorView();
        view.setId(user.getId());
        view.setEmail(user.getEmail());
        view.setMobileNumber(user.getMobile());
        view.setUserName(user.getUserName());
        view.setRealName(user.getRealName());
        view.setRoleName(user.getRole());
        view.setJoinTime(user.getCreateTime());
        view.setStatus(user.getStatus());
        return view;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@ModelAttribute(value="user")User user){
        Map<String, Object> result = new HashMap<>();
        try{
            userService.save(user);
            //TODO 可以封装成工具类
            result.put("flag", true);
            result.put("msg", "保存成功");
        }catch (Exception e){
            result.put("flag", false);
            result.put("msg", "系统错误，请联系管理员！");
        }
        return result;
    }
}
