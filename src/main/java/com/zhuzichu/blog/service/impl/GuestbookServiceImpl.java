package com.zhuzichu.blog.service.impl;

import com.zhuzichu.blog.dao.GuestbookMapper;
import com.zhuzichu.blog.model.Guestbook;
import com.zhuzichu.blog.service.GuestbookService;
import com.zhuzichu.blog.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zzc on 2018/05/18.
 */
@Service
@Transactional
public class GuestbookServiceImpl extends AbstractService<Guestbook> implements GuestbookService {
    @Resource
    private GuestbookMapper guestbookMapper;

}
