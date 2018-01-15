package com.banary.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index-2").setViewName("index-2");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/error").setViewName("404");
        registry.addViewController("/welcome").setViewName("welcome");
        //system
        registry.addViewController("/system/base").setViewName("system-base");
        registry.addViewController("/system/category").setViewName("system-category");
        registry.addViewController("/system/category/add").setViewName("system-category-add");
        registry.addViewController("/system/data").setViewName("system-data");
        registry.addViewController("/system/shielding").setViewName("system-shielding");
        registry.addViewController("/system/log").setViewName("system-log");
        //admin
        registry.addViewController("/admin/add").setViewName("admin-add");
        //member
        registry.addViewController("/member/list").setViewName("member-list");
        registry.addViewController("/member/del").setViewName("member-del");
        registry.addViewController("/member/level").setViewName("member-level");
        registry.addViewController("/member/scoreoperation").setViewName("member-list");
        registry.addViewController("/member/record/browse").setViewName("member-list");
        registry.addViewController("/member/record/download").setViewName("member-list");
        registry.addViewController("/member/record/share").setViewName("member-list");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
