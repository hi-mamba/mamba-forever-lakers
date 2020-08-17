package kobe.mamba.web.controller;

import kobe.mamba.web.base.AbstractBaseApiTest;
import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author mamba
 * @date 2020/8/17 23:27
 */
public class HelloWorldControllerTest extends AbstractBaseApiTest {

    @Test
    public void testHi() throws Exception {
        get("/api/test/hi?name=kobe")
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$").value("hi kobe"));

    }
}
