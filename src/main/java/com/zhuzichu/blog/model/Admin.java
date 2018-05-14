package com.zhuzichu.blog.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "sys_user")
@Data
public class Admin {
    @Id
    private Integer uid;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Column(name = "user_password")
    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;

}