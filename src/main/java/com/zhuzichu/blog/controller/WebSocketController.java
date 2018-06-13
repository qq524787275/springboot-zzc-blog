package com.zhuzichu.blog.controller;

import com.zhuzichu.blog.core.result.Result;
import com.zhuzichu.blog.core.result.ResultGenerator;
import com.zhuzichu.blog.core.service.websocket.WebSocketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {
    @Resource private WebSocketService webSocketService;

    @PostMapping("/sendAll")
    public Result sendAll(@RequestParam String msg) {
        webSocketService.sendAll(msg);
        return ResultGenerator.genSuccessResult();
    }
}
