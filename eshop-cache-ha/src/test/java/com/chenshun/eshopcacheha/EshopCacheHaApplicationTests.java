package com.chenshun.eshopcacheha;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EshopCacheHaApplicationTests {

    @Test
    public void contextLoads() {
        log.debug(" This is debug!!!");
        log.info(" This is info!!!");
        log.warn(" This is warn!!!");
        log.error(" This is error!!!");
        log.trace(" This is fatal!!!");
    }

}
