package com.zhuzichu.article;

import com.zhuzichu.blog.model.Article;
import com.zhuzichu.blog.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleTest{
    @Autowired
    ArticleService articleService;
    @Test
    public void addArticle(){
        List<Article> all = articleService.findAll();

        for (int i = 0; i < 50; i++) {
            articleService.save(all);
        }
    }
}
