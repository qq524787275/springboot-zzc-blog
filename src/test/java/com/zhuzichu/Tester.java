package com.zhuzichu;

import com.zhuzichu.blog.BlogApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试继承该类即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@ConfigurationProperties
public abstract class Tester {
}



