package mamba.com.example01;

import com.alibaba.testable.core.annotation.MockConstructor;
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

    public static class Mock {
        @MockConstructor
        private BlackBox createBlackBox(String text) {
            return new BlackBox("mock_" + text);
        }
    }

    @Test
    public void testGet() {
       // createBlackBox("test");
       //
       // String result = demoExample01.getColor();
       // Assertions.assertEquals(result, "something");

        Assertions.assertEquals("mock_something", demoExample01.getColor());
        InvokeVerifier.verify("createBlackBox").with("something");
    }
}
