package com.heardfate.springboot.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @since: 2018/11/4
 * @author: Mr.HeardFate
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Swagger2MarkupTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void createSpringfoxSwaggerJson() throws Exception {
        String outputDir = "target/asciidoc/json";
        //将api-docs的json数据写入文件
        MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //将获取的JSON写入文件
        MockHttpServletResponse response = mvcResult.getResponse();
        String swaggerJson = response.getContentAsString();
        Files.createDirectories(Paths.get(outputDir));
        Files.write(Paths.get(outputDir, "swagger.json"), swaggerJson.getBytes());

        //将多个ADOC整合成一个INDEX.ADOC文件
        String generated = "target/asciidoc/generated";
        Files.createDirectories(Paths.get(generated));
        Files.write(Paths.get(generated, "index.adoc"), ("include::overview.adoc[]\ninclude::definitions" +
                ".adoc[]\ninclude::paths.adoc[]").getBytes());
    }
}


