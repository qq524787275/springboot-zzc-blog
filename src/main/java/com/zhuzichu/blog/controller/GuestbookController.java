package com.zhuzichu.blog.controller;
import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.model.Guestbook;
import com.zhuzichu.blog.service.GuestbookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
* Created by zzc on 2018/05/18.
*/
@RestController
@RequestMapping("/api/guestbook")
public class GuestbookController {
    @Resource
    private GuestbookService guestbookService;

    @PostMapping("/add")
    public Result add(@Valid Guestbook guestbook) {
        guestbook.setNickname(TextUtils.isBlank(guestbook.getNickname())?"佚名":guestbook.getNickname());
        guestbook.setDate((int) (System.currentTimeMillis()/1000));
        guestbookService.save(guestbook);
        return ResultGenerator.genSuccessResult();
    }

//    @PostMapping("/delete")
//    public Result delete(@RequestParam Integer id) {
//        guestbookService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/update")
//    public Result update(Guestbook guestbook) {
//        guestbookService.update(guestbook);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @RequestMapping("/detail")
//    public Result detail(@RequestParam Integer id) {
//        Guestbook guestbook = guestbookService.findById(id);
//        return ResultGenerator.genSuccessResult(guestbook);
//    }

    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(Guestbook.class);

        condition.orderBy("date").desc();
        List<Guestbook> list = guestbookService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
