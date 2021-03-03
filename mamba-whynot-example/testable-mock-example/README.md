

# testable-mock
 
<https://github.com/alibaba/testable-mock>


TestableMock的原理可以用一句话概括：利用JavaAgent动态修改字节码，把被测的业务类中与所有与Mock方法定义匹配的调用在单元测试运行时替换成对Mock方法的调用。

