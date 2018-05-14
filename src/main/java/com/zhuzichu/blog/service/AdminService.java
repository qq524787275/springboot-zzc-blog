package com.zhuzichu.blog.service;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.model.Admin;
import com.zhuzichu.blog.core.service.Service;


/**
 * Created by zzc on 2018/05/08.
 */
public interface AdminService extends Service<Admin> {

    Result login(Admin admin) throws Exception;

    Result add(Admin admin);
}
