package space.mamba.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.mamba.model.UserInfo;
import space.mamba.service.business.UserInfoService;

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
        return JSONArray.toJSONString(userInfoList);
    }

    /**
     * 随机创建账号
     */
    @GetMapping("/random/create")
    public int randomCreateUserInfo() {
        long la = System.currentTimeMillis();
        UserInfo userInfo = new UserInfo();
        userInfo.setRegTime(LocalDateTime.now());
        userInfo.setMobile(la + "");
        userInfo.setEmail(la + new Random(10).nextInt() + "@lakers.com");
        userInfo.setPassword("123");
        userInfo.setStatus(Byte.valueOf("1"));
        userInfo.setLastLoginIp(1271111L);
        userInfo.setUsername("kobe_" + la);
        return userInfoService.insertSelective(userInfo);
    }

    @PostMapping("/create")
    public int insertUserInfo(@RequestBody UserInfo userInfo) {
        log.info(JSONObject.toJSONString(userInfo));
        return 1;
    }

}
