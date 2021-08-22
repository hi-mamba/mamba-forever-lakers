package space.mamba.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.mamba.family.model.Users;
import space.mamba.family.req.AuthRequest;
import space.mamba.family.resp.vo.UserVO;

/**
 * @author pankui
 */
public interface UsersService extends IService<Users>{


    /**
     * @param authRequest
     * @return
     */
    UserVO save(AuthRequest authRequest);
}
