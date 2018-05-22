package com.chenshun.eshopcacheha.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * User: mew <p />
 * Time: 18/5/21 14:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    private MockMvc mvc;

    @Autowired
    private HelloController helloController;

    @Before
    public void init() {
        this.mvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void hello() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/hello").
                accept(MediaType.APPLICATION_JSON)
                .param("name", "chenshun"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

}
