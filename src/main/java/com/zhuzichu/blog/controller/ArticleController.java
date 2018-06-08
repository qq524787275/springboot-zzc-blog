package com.zhuzichu.blog.controller;

import com.zhuzichu.blog.annotations.Access;
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
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());

    @PostMapping("/add")
    public Result add(@Valid Article article, HttpServletRequest request) {
        article.setDate((int) (System.currentTimeMillis() / 1000));
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
    @Access(authorities = "admin")
    @PostMapping("/update")
    public Result update(@Valid Article article) {
        Article a = articleService.findById(article.getId());
        if(a==null){
            ResultGenerator.genFailResult("找不到该文章");
        }
        a.setTitle(article.getTitle());
        a.setContent(article.getContent());
        a.setDescription(article.getDescription());
        articleService.update(a);
        return ResultGenerator.genSuccessResult();
    }
//


    @RequestMapping("/visibleDetail")
    public Result visibleDetail(@RequestParam Integer id) {
        Condition condition = new Condition(Article.class);
        condition.createCriteria().andCondition("visible=1 and id=" + id);
        Article article = articleService.findByCondition(condition).get(0);
        article.setEyes(article.getEyes()+1);
        articleService.update(article);
        if (article != null) {
            return ResultGenerator.genSuccessResult(article);
        }
        return ResultGenerator.genFailResult("无数据");
    }

    @RequestMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Article article = articleService.findById(id);
        if (article != null) {
            return ResultGenerator.genSuccessResult(article);
        }
        return ResultGenerator.genFailResult("无数据");
    }

    @PostMapping("/visible")
    @Access(authorities = "admin")
    public Result visible(@RequestParam Integer id, @RequestParam Integer visible) {

        Article article = articleService.findById(id);
        if (article != null) {
            if (visible == 1) {
                logger.info("1111111111111");
                article.setVisible(true);
            } else if (visible == 0) {
                logger.info("000000000000");
                article.setVisible(false);
            } else {
                return ResultGenerator.genFailResult("visible只能为1或0");
            }
            articleService.update(article);
            return ResultGenerator.genSuccessResult(article);
        }
        return ResultGenerator.genFailResult("设置失败");
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
//        Condition condition=new Condition(Article.class);
//        condition.orderBy("date").desc();
//        List<Article> list = articleService.findByCondition(condition);
        List<Article> list = articleService.findAllDesc();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @RequestMapping("/visibleList")
    public Result visibleList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Article> list = articleService.findAllDescVisible();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
