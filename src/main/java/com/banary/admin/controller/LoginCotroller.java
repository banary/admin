package com.banary.admin.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录
 *
 * @author xiyongchun
 * @date 2017/7/7 下午4:42
 */
@RestController
@RequestMapping("/")
public class LoginCotroller {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("index");
        }
    }
}
