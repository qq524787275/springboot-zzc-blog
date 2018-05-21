package com.zhuzichu.blog.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

public class Guestbook {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 留言内容
     */
    @NotBlank(message = "内容不能为空")
    @Length(max = 100,min = 8,message = "最小字数8，最大字数100")
    private String content;

    /**
     * 时间戳
     */
    private Integer date;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取留言内容
     *
     * @return content - 留言内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置留言内容
     *
     * @param content 留言内容
     */
    public void setContent(String content) {
        this.content = content;
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
}