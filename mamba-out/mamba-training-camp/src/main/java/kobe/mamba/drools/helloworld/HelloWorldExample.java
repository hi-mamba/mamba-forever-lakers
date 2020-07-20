package kobe.mamba.drools.helloworld;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author mamba
 * @ 2020/7/20
 */
public class HelloWorldExample {

    public static void main(String[] args) {
        // 构建KieServices
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        // 获取META-INF 下的 kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        Message message = new Message();
        message.setMsg("Hello World");
        message.setStatus(Message.HELLO);

        kSession.insert(message);
        kSession.fireAllRules();
        kSession.dispose();

    }
}
