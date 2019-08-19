package space.mamba.service.business;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import space.mamba.ServiceApplicationTest;
import space.mamba.model.UserInfo;

import java.time.LocalDateTime;

/**
 * @author pankui
 * @date 2019-08-19
 * <pre>
 *
 * </pre>
 */
public class UserInfoServiceTest extends ServiceApplicationTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testInsertSelective() {

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test12");
        userInfo.setStatus(Byte.valueOf("12"));
        userInfo.setPassword("1232");
        userInfo.setEmail("test2@lakers.com");
        userInfo.setMobile("12112");
        userInfo.setRegTime(LocalDateTime.now());
        userInfoService.insertSelective(userInfo);
    }
}
