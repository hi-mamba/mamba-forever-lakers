package space.lakers.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.lakers.family.model.Users;
import space.lakers.family.req.AuthRequest;
import space.lakers.family.resp.vo.UserVO;

/**
 * @author mini kobe
 */
public interface UsersService extends IService<Users>{


    /**
     * @param authRequest
     * @return
     */
    UserVO save(AuthRequest authRequest);

    Users getByUser(Long id);
}
