package space.mamba.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import space.mamba.family.mapper.UsersMapper;
import space.mamba.family.model.Users;
import space.mamba.family.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * @author pankui
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
