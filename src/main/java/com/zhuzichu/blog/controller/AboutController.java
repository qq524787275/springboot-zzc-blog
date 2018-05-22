package com.zhuzichu.blog.controller;
import com.zhuzichu.blog.annotations.Access;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.model.About;
import com.zhuzichu.blog.service.AboutService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
* Created by zzc on 2018/05/22.
*/
@RestController
@RequestMapping("/api/about")
public class AboutController {

    @Resource
    private AboutService aboutService;

//    @PostMapping("/add")
//    public Result add(About about) {
//        aboutService.save(about);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/delete")
//    public Result delete(@RequestParam Integer id) {
//        aboutService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
    @PostMapping("/update")
    @Access(authorities = "admin")
    public Result update(@Valid About about) {
        about.setId(1);
        aboutService.update(about);
        return ResultGenerator.genSuccessResult();
    }
//
//    @RequestMapping("/detail")
//    public Result detail(@RequestParam Integer id) {
//        About about = aboutService.findById(id);
//        return ResultGenerator.genSuccessResult(about);
//    }
//
//    @RequestMapping("/list")
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<About> list = aboutService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
    @GetMapping("/getAbout")
    public Result getAbout(){
        About about = aboutService.findById(1);
        return ResultGenerator.genSuccessResult(about);
    }
}
