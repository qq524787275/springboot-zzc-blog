package com.zhuzichu.blog.controller;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.model.Project;
import com.zhuzichu.blog.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zzc on 2018/05/16.
*/
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @PostMapping("/add")
    public Result add(Project project) {
        projectService.save(project);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        projectService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Project project) {
        projectService.update(project);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Project project = projectService.findById(id);
        return ResultGenerator.genSuccessResult(project);
    }

    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);

        Condition condition=new Condition(Project.class);
        condition.orderBy("rank").asc();
        List<Project> list = projectService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
