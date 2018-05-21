package com.zhuzichu.blog.service.impl;

import com.zhuzichu.blog.dao.ProjectMapper;
import com.zhuzichu.blog.model.Project;
import com.zhuzichu.blog.service.ProjectService;
import com.zhuzichu.blog.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zzc on 2018/05/16.
 */
@Service
@Transactional
public class ProjectServiceImpl extends AbstractService<Project> implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;

}
