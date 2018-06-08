package com.zhuzichu.blog.dao;

import com.zhuzichu.blog.core.mapper.Mapper;
import com.zhuzichu.blog.model.Article;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    List<Article> selectAllDesc();

    List<Article> selectAllDescVisible();

}