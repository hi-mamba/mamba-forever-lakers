package kobe.mamba.web.base;

import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

/**
 * @author pankui
 * @date 2019/9/12
 * <pre>
 *
 * </pre>
 */
public class AbstractBaseApiTest extends AbstractBaseDbTest {

    @Autowired
    protected WebApplicationContext ctx;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("### start");
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }


    /**
     * 项目mock 之后需要 reset
     */
    @After
    public void tearDown() {
        Mockito.reset();
        //Mockito.reset(userInfoService);
    }

    protected ResultActions get(String requestMapping, Object k, Object v) throws Exception {
        return get(requestMapping, ImmutableMap.of(k, v));
    }

    protected ResultActions get(String requestMapping, Object k1, Object v1, Object k2, Object v2) throws Exception {
        return get(requestMapping, ImmutableMap.of(k1, v1, k2, v2));
    }

    protected ResultActions get(String requestMapping, Object k1, Object v1, Object k2, Object v2, Object k3, Object v3)
            throws Exception {
        return get(requestMapping, ImmutableMap.of(k1, v1, k2, v2, k3, v3));
    }

    protected ResultActions get(String requestMapping, Object k1, Object v1, Object k2, Object v2, Object k3, Object v3,
                                Object k4, Object v4) throws Exception {
        return get(requestMapping, ImmutableMap.of(k1, v1, k2, v2, k3, v3, k4, v4));
    }

    protected ResultActions get(String requestMapping, Object k1, Object v1, Object k2, Object v2, Object k3, Object v3,
                                Object k4, Object v4, Object k5, Object v5) throws Exception {
        return get(requestMapping, ImmutableMap.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5));
    }

    protected ResultActions get(String requestMapping, Map<Object, Object> params) throws Exception {
        String url = getUrlPrefix() + requestMapping;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        params.forEach((key, value) -> {
            builder.param(key.toString(), value.toString());
        });

        return mockMvc.perform(builder);
    }

    protected ResultActions get(String requestMapping) throws Exception {
        String url = getUrlPrefix() + requestMapping;
        return mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_UTF8));
    }

    protected ResultActions put(String requestMapping) throws Exception {
        String url = getUrlPrefix() + requestMapping;
        return mockMvc.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON_UTF8));
    }

    protected ResultActions delete(String requestMapping) throws Exception {
        String url = getUrlPrefix() + requestMapping;
        return mockMvc.perform(MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON_UTF8));
    }


    protected ResultActions upload(String requestMapping, MockMultipartFile payload) throws Exception {
        String url = getUrlPrefix() + requestMapping;
        return mockMvc.perform(MockMvcRequestBuilders.fileUpload(url)
                .file(payload));
    }

    protected ResultActions post(String requestMapping, String payload) throws Exception {
        String url = getUrlPrefix() + requestMapping;
        return post(url, payload, MediaType.APPLICATION_JSON_UTF8);
    }

    protected ResultActions post(String url, String payload, MediaType contentType) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(payload)
                .contentType(contentType));
    }

    /**
     * 获取url前缀
     *
     * @return
     */
    protected String getUrlPrefix() {
        return "";
    }

}
