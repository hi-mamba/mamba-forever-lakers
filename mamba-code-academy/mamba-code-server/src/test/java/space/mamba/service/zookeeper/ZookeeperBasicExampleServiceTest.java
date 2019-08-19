package space.mamba.service.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import space.mamba.ServiceApplicationTest;

import java.io.IOException;

/**
 * @author pankui
 * @date 2019-08-15
 * <pre>
 *
 * </pre>
 */
public class ZookeeperBasicExampleServiceTest extends ServiceApplicationTest {

    @Autowired
    private ZookeeperBasicExampleService zookeeperBasicExampleService;


    @Test
    public void test() throws InterruptedException, IOException, KeeperException {
        //zookeeperBasicExampleService.zkExample();
        System.out.println("###");
    }
}
