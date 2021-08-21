package space.mamba.auth.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.common.annotion.IgnoreResponseData;
import space.mamba.domain.User;
import space.mamba.vo.PageResponseData;
import space.mamba.vo.Paging;

import java.util.List;

/**
 * @author pankui
 * @date 2021/7/7
 * <pre>
 *
 * </pre>
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/get")
    public User get(Long userId) {
        if (userId.equals(0L)) {
            throw new IllegalArgumentException("test");
        }
        User user = new User();
        user.setId(userId);
        user.setName("test");
        return user;
    }

    @GetMapping("/list")
    public PageResponseData<List<User>> list() {
        PageResponseData<List<User>> pageResponseData = new PageResponseData<>();
        User user = new User();
        user.setId(1L);
        user.setName("test");
        pageResponseData.setData(Lists.newArrayList(user));
        Paging paging = new Paging();
        paging.setLimit(10);
        paging.setTotal(10L);
        pageResponseData.setPaging(paging);
        return pageResponseData;
    }

    @GetMapping("/get_str")
    public String getStr(){
        //返回了一个字符串
        return "baidu.com";
    }

    @IgnoreResponseData
    @GetMapping("/get2")
    public User ingnoreResponseData(Long userId) {
        if (userId.equals(0L)) {
            throw new IllegalArgumentException("test");
        }
        User user = new User();
        user.setId(userId);
        user.setName("test");
        return user;
    }
}
