package com.zhuzichu.redis;



import com.zhuzichu.Tester;
import com.zhuzichu.blog.core.service.redis.RedisService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class redisTest extends Tester {
    @Resource
    private RedisService redisService;

    private static final String KEY = "KEY";
    private static final String VALUE = "VALUE";

    @Test
    public void set() {
        String result = redisService.set(KEY, VALUE);
        Assert.assertEquals("OK", result);
    }

    @Test
    public void get() {
        redisService.set(KEY, VALUE);
        String result;
        result = redisService.get(KEY);
        Assert.assertEquals(VALUE, result);
    }

    @Test
    public void del() {

        long effectCount = redisService.delete(KEY);
        Assert.assertEquals(1, effectCount);
    }


}
