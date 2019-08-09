package space.mamba.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.model.UserInfo;
import space.mamba.service.business.UserInfoService;

import java.util.List;

/**
 * @author pankui
 * @date 2019-08-09
 * <pre>
 *
 * </pre>
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 返回有问题
     * */
    @GetMapping("/user_info/list")
    public List<UserInfo> list(UserInfo userInfo) {
        return userInfoService.list(userInfo);
    }

    /**
     * 返回有问题
     * */
    @GetMapping("/user_info/v2/list")
    public String listString(UserInfo userInfo) {
        List<UserInfo> userInfoList = userInfoService.list(userInfo);
        return JSONArray.toJSONString(userInfoList);
    }
}
