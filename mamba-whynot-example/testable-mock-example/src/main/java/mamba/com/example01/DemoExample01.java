package mamba.com.example01;

import mamba.com.example01.model.BlackBox;

/**
 * @author mamba
 * @date 2021/3/2 17:50
 * https://github.com/alibaba/testable-mock/blob/master/demo/java-demo/src/main/java/com/alibaba/testable/demo/basic/DemoMock.java
 */
public class DemoExample01 {

    /**
     * method with new operation
     */
    public String getColor() {
        BlackBox component = new BlackBox("something");
        return component.getColor();
    }
}
