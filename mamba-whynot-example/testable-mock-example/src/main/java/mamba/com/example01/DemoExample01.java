package mamba.com.example01;

import mamba.com.example01.model.BlackBox;

/**
 * @author mamba
 * @date 2021/3/2 17:50
 * https://github.com/alibaba/testable-mock/blob/master/demo/java-demo/src/main/java/com/alibaba/testable/demo/basic/DemoMock.java
 */
public class DemoExample01 {

    public String getConstructor() {
        BlackBox component = new BlackBox();
        return component.getResult("something");
    }

    public String getResult(String text) {
        BlackBox component = new BlackBox();
        return component.getResult(text);
    }

    /**
     * method with new operation
     */
    public String getColor() {
        BlackBox component = new BlackBox("something");
        return component.getColor();
    }

    public String blackStaticMethod(String text) {
        return BlackBox.staticMethod(text);
    }

}
