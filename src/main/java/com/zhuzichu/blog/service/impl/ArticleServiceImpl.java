package com.zhuzichu.blog.service.impl;

import com.zhuzichu.blog.dao.ArticleMapper;
import com.zhuzichu.blog.model.Article;
import com.zhuzichu.blog.service.ArticleService;
import com.zhuzichu.blog.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zzc on 2018/05/15.
 */
@Service
@Transactional
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findAllDesc() {
        return articleMapper.selectAllDesc();
    }

    @Override
    public List<Article> findAllDescVisible() {
        return articleMapper.selectAllDescVisible();
    }
}
