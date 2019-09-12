package space.mamba.service.business;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
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
@Slf4j
public class UserInfoServiceTest extends ServiceApplicationTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testInsertSelective() {
        //  log.info("#### UserInfoServiceTest");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test12");
        userInfo.setStatus(Byte.valueOf("12"));
        userInfo.setPassword("1232");
        userInfo.setEmail("test2@lakers.com");
        userInfo.setMobile("12112");
        userInfo.setRegTime(LocalDateTime.now());
        userInfoService.insertSelective(userInfo);
    }

    @Test
    public void testSelectById() {
//        log.info("#### UserInfoServiceTest");

        UserInfoService userInfoService = Mockito.mock(UserInfoService.class);
        PowerMockito.when(userInfoService.selectByPrimaryKey(Mockito.anyLong()))
                .thenReturn(null);

        UserInfo userInfo = userInfoService.selectByPrimaryKey(1L);
        System.out.println("## " + JSONObject.toJSONString(userInfo));
    }
}
