package com.lhb.saoliao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Field;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SaoliaoApplicationTests {

    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }

    @Test
    void logbackTest() {
        log.info("你好,logback");
    }


}
