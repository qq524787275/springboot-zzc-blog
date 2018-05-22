package com.zhuzichu.blog.service.impl;

import com.zhuzichu.blog.dao.AboutMapper;
import com.zhuzichu.blog.model.About;
import com.zhuzichu.blog.service.AboutService;
import com.zhuzichu.blog.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zzc on 2018/05/22.
 */
@Service
@Transactional
public class AboutServiceImpl extends AbstractService<About> implements AboutService {
    @Resource
    private AboutMapper aboutMapper;

}
