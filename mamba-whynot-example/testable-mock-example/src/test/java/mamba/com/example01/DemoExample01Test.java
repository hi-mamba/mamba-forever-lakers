package mamba.com.example01;

import com.alibaba.testable.core.annotation.MockConstructor;
import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.matcher.InvokeMatcher;
import com.alibaba.testable.core.matcher.InvokeVerifier;
import mamba.com.example01.model.BlackBox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @author mamba
 * @date 2021/3/2 18:02
 */
public class DemoExample01Test {

    private DemoExample01 demoExample01 = new DemoExample01();

    // 目前这个版本的 testable mock 这个类名称必须是这个
    // https://alibaba.github.io/testable-mock/#/zh-cn/doc/use-mock
    // 测试类和Mock容器的命名约定：
    public static class Mock {

        //mock 构造方法
        @MockConstructor
        private BlackBox createBlackBox(String text) {
            return new BlackBox("mock_" + text);
        }

        // mock 类
        @MockMethod(targetClass = BlackBox.class)
        private String put(String text) {
            return "mock_" + text;
        }

        @MockMethod(targetClass = BlackBox.class)
        private String getColor() {
            return "mock_getColor";
        }

        @MockMethod(targetClass = BlackBox.class)
        private String staticMethod(String text) {
            return "mock_" + text;
        }

        @MockMethod(targetClass = BlackBox.class)
        private String getResult(String text) {
            return "mock_" + text;
        }
    }


    @Test
    public void testConstructor() {

        Assertions.assertEquals("mock_something", demoExample01.getConstructor());
        InvokeVerifier.verify("getResult").with("something");
    }


    @Test
    public void testGetResult() {
        Assertions.assertEquals("mock_", demoExample01.getResult(""));
        InvokeVerifier.verify("getResult").with(InvokeMatcher.anyString());

    }

    @Test
    public void testStatic() {
        Assertions.assertEquals("mock_static", demoExample01.blackStaticMethod("static"));
    }

}
