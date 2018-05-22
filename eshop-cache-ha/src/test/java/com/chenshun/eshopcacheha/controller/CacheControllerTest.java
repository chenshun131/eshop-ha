package com.chenshun.eshopcacheha.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * User: mew <p />
 * Time: 18/5/21 15:29  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheControllerTest {

    private MockMvc mvc;

    @Autowired
    private CacheController cacheController;

    @Before
    public void init() {
        this.mvc = MockMvcBuilders.standaloneSetup(cacheController).build();
    }

    @Test
    public void changeProduct() {
    }

}
