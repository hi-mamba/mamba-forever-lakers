package space.lakers.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.lakers.family.mapper.UsersMapper;
import space.lakers.family.model.Users;
import space.lakers.family.req.AuthRequest;
import space.lakers.family.resp.vo.UserVO;
import space.lakers.family.service.UsersService;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pankui
 */
@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public UserVO save(AuthRequest authRequest) {
        log.info("### save test data ={}",authRequest);
        //测试数据
        UserVO userVO = new UserVO();
        userVO.setId(atomicInteger.getAndIncrement() + "");
        userVO.setEmail(atomicInteger + "@666.com");
        userVO.setPassword("123");
        userVO.setRole("1");
        return userVO;
    }
}
