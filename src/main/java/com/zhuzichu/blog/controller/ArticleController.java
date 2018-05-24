package com.zhuzichu.blog.controller;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.core.utils.ProjectDateUtils;
import com.zhuzichu.blog.model.Article;
import com.zhuzichu.blog.model.Guestbook;
import com.zhuzichu.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.el.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

/**
* Created by zzc on 2018/05/15.
*/
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("/add")

    public Result add(@Valid Article article, HttpServletRequest request) {
        article.setDate((int) (System.currentTimeMillis()/1000));
        article.setUid((int) request.getAttribute("uid"));
        articleService.save(article);
        return ResultGenerator.genSuccessResult().setMessage("文章发表成功~!");
    }
//
//    @PostMapping("/delete")
//    public Result delete(@RequestParam Integer id) {
//        articleService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/update")
//    public Result update(Article article) {
//        articleService.update(article);
//        return ResultGenerator.genSuccessResult();
//    }
//
    @RequestMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Article article = articleService.findById(id);
        if(article!=null){
            return ResultGenerator.genSuccessResult(article);
        }
        return ResultGenerator.genFailResult("无数据");
    }

    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
//        Condition condition=new Condition(Article.class);
//        condition.orderBy("date").desc();
//        List<Article> list = articleService.findByCondition(condition);
        List<Article> list=articleService.findAllDesc();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}
