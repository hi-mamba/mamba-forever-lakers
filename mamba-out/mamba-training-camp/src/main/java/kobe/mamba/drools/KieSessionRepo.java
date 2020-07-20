package kobe.mamba.drools;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mamba
 * @ 2020/7/20
 */
public class KieSessionRepo {

    private static Map<String, KieContainer> kieContainerMap = new ConcurrentHashMap<String, KieContainer>();

    private static Map<String, KieSession> kieSessionMap = new ConcurrentHashMap<String, KieSession>();

    public static void setKieContainer(String key, KieContainer kieContainer) {
        KieSession newKieSession = kieContainer.newKieSession();
        kieContainerMap.put(key, kieContainer);
        kieSessionMap.put(key, newKieSession);
    }

    public KieSession getKieSession(String key) {

        return kieSessionMap.get(key);
    }
}
