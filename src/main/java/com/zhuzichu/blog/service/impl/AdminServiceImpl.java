package com.zhuzichu.blog.service.impl;

import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.core.utils.ProjectMD5Utils;
import com.zhuzichu.blog.core.utils.ProjectTokenUtils;
import com.zhuzichu.blog.dao.AdminMapper;
import com.zhuzichu.blog.model.Admin;
import com.zhuzichu.blog.service.AdminService;
import com.zhuzichu.blog.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zzc on 2018/05/08.
 */
@Service
@Transactional
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    @Resource
    private AdminMapper sysUserMapper;

    @Override
    public Result login(Admin admin) throws Exception {
        admin.setPassword(ProjectMD5Utils.getMD5String(admin.getPassword()));
        List<Admin> list = sysUserMapper.select(admin);
        int i = list.size();
        if (i == 1) {
            String jwtToken = ProjectTokenUtils.createJWTToken(list.get(0).getUid(), list.get(0).getUsername());
            return ResultGenerator.genSuccessResult(jwtToken);
        } else if (i == 0) {
            return ResultGenerator.genFailResult("用户名或密码错误");
        }
        return ResultGenerator.genFailResult("用户异常");
    }

    @Override
    public Result add(Admin admin) {
        Admin target = new Admin();
        target.setUsername(admin.getUsername());
        List<Admin> list = sysUserMapper.select(target);
        if (list.size() == 1) {
            return ResultGenerator.genFailResult("该用户名" + admin.getUsername() + "已经存在");
        } else if (list.size() == 0) {
            admin.setPassword(ProjectMD5Utils.getMD5String(admin.getPassword()));
            save(admin);
            return ResultGenerator.genSuccessResult().setMessage("添加成功");
        }
        return ResultGenerator.genFailResult("该用户名异常");
    }
}
