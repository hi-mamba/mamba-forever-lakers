package space.mamba.service.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.mamba.dao.UserInfoMapper;
import space.mamba.model.UserInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pankui
 */
@Slf4j
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

   // @Autowired
   // private KafkaProduceService kafkaProduceService;

    public List<UserInfo> list(UserInfo userInfo) {
        return userInfoMapper.selectByAll(userInfo);
    }

    public int deleteByPrimaryKey(Long id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    public int insertSelective(UserInfo record) {
        log.info("## insert ...");
       // kafkaProduceService.sendMessage(JSONObject.toJSONString(record));
        return userInfoMapper.insertSelective(record);
    }

    public UserInfo selectByPrimaryKey(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<UserInfo> list) {
        return userInfoMapper.updateBatch(list);
    }

    public int batchInsert(List<UserInfo> list) {
        return userInfoMapper.batchInsert(list);
    }
}






