package com.zhuzichu.blog.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

public class Article {
    /**
     * 文章id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 时间戳
     */
    private Integer date;

    /**
     * 对应sys_user标的uid
     */
    private Integer uid;

    /**
     * 点赞次数
     */
    private Integer like;

    /**
     * 富文本数据
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 获取文章id
     *
     * @return id - 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置文章id
     *
     * @param id 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取时间戳
     *
     * @return date - 时间戳
     */
    public Integer getDate() {
        return date;
    }

    /**
     * 设置时间戳
     *
     * @param date 时间戳
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * 获取对应sys_user标的uid
     *
     * @return uid - 对应sys_user标的uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置对应sys_user标的uid
     *
     * @param uid 对应sys_user标的uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取点赞次数
     *
     * @return like - 点赞次数
     */
    public Integer getLike() {
        return like;
    }

    /**
     * 设置点赞次数
     *
     * @param like 点赞次数
     */
    public void setLike(Integer like) {
        this.like = like;
    }

    /**
     * 获取富文本数据
     *
     * @return content - 富文本数据
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置富文本数据
     *
     * @param content 富文本数据
     */
    public void setContent(String content) {
        this.content = content;
    }
}