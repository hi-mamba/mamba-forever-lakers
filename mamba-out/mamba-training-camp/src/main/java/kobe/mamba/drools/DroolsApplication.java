package kobe.mamba.drools;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;

/**
 * @author mamba
 * @ 2020/7/20
 * <p>
 * 执行的主类
 */
public class DroolsApplication {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        KieSession kieSession = check(getRule());
        IrssetDroolsVo drools = new IrssetDroolsVo();
        drools.setSurpDayCnt(2);
        kieSession.insert(drools);
        int i = kieSession.fireAllRules();
        System.out.println("命中： " + i + "返回结果：" + drools.getMsg());
    }

    /**
     * 不进行检查
     *
     * @param rule
     * @return
     */
    public static KieSession getSession(String rule) {
        KieSession kieSession = null;
        try {
            KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            builder.add(ResourceFactory.newByteArrayResource(rule.getBytes("UTF-8")), ResourceType.DRL);
            InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
            Collection<KiePackage> packages = builder.getKnowledgePackages();
            knowledgeBase.addPackages(packages);
            kieSession = knowledgeBase.newKieSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kieSession;
    }

    /**
     * 检查
     *
     * @param sq
     * @return
     * @throws Exception
     */
    private static KieSession check(String sq) throws Exception {
        KieSessionRepo kieSession = new KieSessionRepo();
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();

        kfs.write("src/main/resources/test.drl", sq);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            for (Message msg : results.getMessages()) {
                System.out.println("drools script error info : " + msg.getText());
            }
            throw new Exception("drools script error");
        }

        kieSession.setKieContainer("test", kieServices.newKieContainer(KieServices.Factory.get().getRepository().getDefaultReleaseId()));

        return kieSession.getKieSession("test");
    }

    public static String getRule() {
        StringBuffer ruleSb = new StringBuffer();
        ruleSb.append(" package rule_10001;\n");
        ruleSb.append("import com.learn.rule.model.IrssetDroolsVo\n");
        ruleSb.append("rule rule_10001 \n");
        ruleSb.append("when \n");
        ruleSb.append("$riskDroolsVo : IrssetDroolsVo(surpDayCnt>=2 && surpDayCnt<=10); \n");
        ruleSb.append("then \n");
        ruleSb.append("$riskDroolsVo.setMsg(\"命中了\"); \n");
        ruleSb.append("end");

        System.out.println(ruleSb.toString());
        return ruleSb.toString();
    }


}
