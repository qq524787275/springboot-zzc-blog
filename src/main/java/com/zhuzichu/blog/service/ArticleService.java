package com.zhuzichu.blog.service;
import com.zhuzichu.blog.model.Article;
import com.zhuzichu.blog.core.service.Service;

import java.util.List;


/**
 * Created by zzc on 2018/05/15.
 */
public interface ArticleService extends Service<Article> {

    List<Article> findAllDesc();
}
