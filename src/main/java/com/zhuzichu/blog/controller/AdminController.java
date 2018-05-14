package com.zhuzichu.blog.controller;

import com.zhuzichu.blog.annotations.Access;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.core.utils.ProjectMD5Utils;
import com.zhuzichu.blog.model.Admin;
import com.zhuzichu.blog.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by zzc on 2018/05/08.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    
    @Resource
    private AdminService adminService;

    @PostMapping("/add")
    @Access(authorities = {"admin"})
    public Result add(@Valid Admin admin, HttpServletRequest request) {
            logger.info(""+request.getAttribute("uid"));
        return adminService.add(admin);
    }


    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Admin> list = adminService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/login")
    public Result login(@Valid Admin admin) {
        try {
            return adminService.login(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("生成token失败");
    }

}
