package com.zhuzichu.blog.service.impl;

import com.zhuzichu.blog.dao.ArticleMapper;
import com.zhuzichu.blog.model.Article;
import com.zhuzichu.blog.service.ArticleService;
import com.zhuzichu.blog.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zzc on 2018/05/10.
 */
@Service
@Transactional
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

}
