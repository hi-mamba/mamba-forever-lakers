package space.lakers.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.lakers.component.annotion.EnableSnakeToCamel;
import space.lakers.model.UserInfo;
import space.lakers.service.business.UserInfoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author pankui
 * @date 2019-08-09
 * <pre>
 *
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping("/api/user_info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 返回有问题
     */
    @GetMapping("/list")
    public List<UserInfo> list(UserInfo userInfo) {
        return userInfoService.list(userInfo);
    }

    /**
     * 返回有问题
     */
    @GetMapping("/v2/list")
    public String listString(UserInfo userInfo) {
        List<UserInfo> userInfoList = userInfoService.list(userInfo);
        return userInfoList.toString();
    }

    /**
     * 随机创建账号
     */
    @GetMapping("/random/create")
    public int randomCreateUserInfo() {
        long la = System.currentTimeMillis();
        UserInfo userInfo = new UserInfo();
      //  userInfo.setId(SnowflakeShardingKeyGeneratorUtil.generateKey());
        userInfo.setRegTime(LocalDateTime.now());
        userInfo.setMobile(la + "");
    //    userInfo.setUserId(SnowflakeShardingKeyGeneratorUtil.generateKey());
        userInfo.setEmail(la + new Random(10).nextInt() + "@lakers.com");
        userInfo.setPassword("123");
        userInfo.setStatus(Byte.valueOf("1"));
        userInfo.setLastLoginIp(1271111L);
        userInfo.setUsername("kobe_" + la);
        int result = userInfoService.insertSelective(userInfo);
        return result;
    }

    /**
     * 下划线格式的get请求参数转换成对应的驼峰命名风格实体类
     */
    @PostMapping("/create")
    public int insertUserInfo(@RequestBody UserInfo userInfo) {
        log.info("{}", userInfo);
        return 1;
    }

    /**
     * 下划线格式的get请求参数转换成对应的驼峰命名风格实体类
     * <p>
     * 当然会有朋友说使用Jackson配置里的命名策略就可以了。
     * 对，是没错，但jackson 只支持@ResponseBody 以及@RequestBody,
     * 而对于没带任何注解的实体类参数是没法处理的
     */
    @PostMapping("/v1/create")
    public int insertUserInfoV1(UserInfo userInfo) {
        log.info("{}", userInfo);
        return 1;
    }

    /**
     * 下划线格式的get请求参数转换成对应的驼峰命名风格实体类
     * <p>
     * 当然会有朋友说使用Jackson配置里的命名策略就可以了。
     * 对，是没错，但jackson 只支持@ResponseBody 以及@RequestBody,
     * 而对于没带任何注解的实体类参数是没法处理的
     */
    @GetMapping("/v2/create")
    public int insertUserInfoV2(UserInfo userInfo, BindingResult bindingResult) {
        log.info("{}", userInfo);
        return 1;
    }

    /**
     * http://www.appblog.cn/2019/06/10/Spring%20Boot%20Get%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0%E4%B8%BA%E4%B8%8B%E5%88%92%E7%BA%BF%E5%BC%8F/
     * <p>
     * 对于GET 请求 下划线 只能这样接，因为无法使用自定义的注解 来 下划线转换 驼峰
     */
    @GetMapping("/v3/create")
    public int insertUserInfoV3(@RequestParam("reg_ip") Long regIp, @EnableSnakeToCamel Long lastLoginIp) {
        log.info("regIp={},lastLoginIp={}", regIp, lastLoginIp);
        return 1;
    }

}
