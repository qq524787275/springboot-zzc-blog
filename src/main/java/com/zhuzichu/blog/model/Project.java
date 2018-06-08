package com.zhuzichu.blog.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目标题
     */
    @NotBlank(message = "项目名不能为空")
    private String title;

    /**
     * 项目描述信息
     */
    @NotBlank(message = "描述信息不能为空")
    private String description;

    /**
     * 图片url
     */
    private String image;

    /**
     * 开发周期
     */
    @Column(name = "` cycle`")
    private String cycle;

    /**
     * 访问数量
     */
    private Integer num;

    private String link;

    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取项目标题
     *
     * @return title - 项目标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置项目标题
     *
     * @param title 项目标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取项目描述信息
     *
     * @return description - 项目描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置项目描述信息
     *
     * @param description 项目描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取图片url
     *
     * @return image - 图片url
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片url
     *
     * @param image 图片url
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取开发周期
     *
     * @return  cycle - 开发周期
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * 设置开发周期
     *
     * @param cycle 开发周期
     */
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    /**
     * 获取访问数量
     *
     * @return num - 访问数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置访问数量
     *
     * @param num 访问数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }
}