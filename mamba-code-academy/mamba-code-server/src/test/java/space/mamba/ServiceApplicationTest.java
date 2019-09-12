package space.mamba;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import space.mamba.service.business.UserInfoService;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *      https://www.cnblogs.com/AdaiCoffee/p/10700097.html
 * </pre>
 */

@ActiveProfiles("kobe")
@Slf4j
//使用powermock自己的Runner
@RunWith(PowerMockRunner.class)
//将powermock整合到spring容器中
@PowerMockRunnerDelegate(SpringRunner.class)
// 这个注解很重要，这也是powermock2.0.0与1.x版本重大不一样的地方，
// 因为powermock自带一个类加载器，使用该注解来禁止powermock类加载器加载一些类，避免和JVM类加载器冲突
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.xml.*", "org.apache.*"})
@SpringBootTest(classes = ServiceApplication.class)
public class ServiceApplicationTest {

}
