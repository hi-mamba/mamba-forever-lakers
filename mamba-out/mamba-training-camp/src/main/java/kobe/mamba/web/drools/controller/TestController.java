package kobe.mamba.web.drools.controller;

import kobe.mamba.web.drools.model.Address;
import kobe.mamba.web.drools.model.fact.AddressCheckResult;
import kobe.mamba.web.drools.service.ReloadDroolsRulesService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author mamba
 * @ 2020/7/22
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private ReloadDroolsRulesService rules;

    @Resource
    private KieContainer kieContainer2;

    @RequestMapping("/address2")
    public void test2(int num){
        Address address = new Address();
        address.setPostcode(generateRandom(num));
        KieSession kieSession = kieContainer2.newKieSession();

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if(result.isPostCodeResult()){
            System.out.println("规则校验通过");
        }

    }

    @RequestMapping("/address")
    public void test(int num) {
        Address address = new Address();
        address.setPostcode("" + num);
        KieSession kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if (result.isPostCodeResult()) {
            System.out.println("规则校验通过");
        }

    }

    /**
     * 从数据加载最新规则
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/reload")
    public String reload() throws IOException {
        rules.reload();
        return "ok";
    }


    /**
     * 生成随机数
     *
     * @param num
     * @return
     */
    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number = number.append(chars.charAt(rand));
        }
        System.out.println(number.toString());
        return number.toString();
    }
}
