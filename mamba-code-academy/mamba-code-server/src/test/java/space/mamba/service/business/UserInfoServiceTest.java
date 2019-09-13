package space.mamba.service.business;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import space.mamba.ServiceApplicationTest;
import space.mamba.base.AbstractBaseApiTest;
import space.mamba.base.AbstractBaseDbTest;
import space.mamba.model.UserInfo;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author pankui
 * @date 2019-08-19
 * <pre>
 *
 * </pre>
 */
@Slf4j
public class UserInfoServiceTest extends AbstractBaseApiTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testInsertSelective() {

       // PowerMockito.when(userInfoService.insert(Mockito.any()))
       //         .thenCallRealMethod();
        //  log.info("#### UserInfoServiceTest");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test"+new Random().nextInt(100));
        userInfo.setStatus(Byte.valueOf("12"));
        userInfo.setPassword("1232");
        userInfo.setEmail("test2@lakers.com");
        userInfo.setMobile("12112");
        userInfo.setRegTime(LocalDateTime.now());
        userInfoService.insertSelective(userInfo);
        System.out.println("##### finish");
    }

    @Test
    public void testSelectById() {
//        log.info("#### UserInfoServiceTest");

        PowerMockito.when(userInfoService.selectByPrimaryKey(Mockito.anyLong()))
                .thenReturn(null);

        UserInfo userInfo = userInfoService.selectByPrimaryKey(1L);
        System.out.println("## " + JSONObject.toJSONString(userInfo));
    }
}
