package space.lakers.service.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.lakers.dao.UserInfoMapper;
import space.lakers.model.UserInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mini kobe
 * <p>
 * 由于采用了分表操作，原来mysql的AUTO_INCREMENT这个就不能使用了。这里就是看看shardingjdbc的id生成规则。
 */
@Slf4j
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    public List<UserInfo> list(UserInfo userInfo) {
        return userInfoMapper.selectByAll(userInfo);
    }

    public int deleteByPrimaryKey(Long id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(UserInfo record) {
        // record.setId(SnowflakeShardingKeyGeneratorUtil.generateKey());
        return userInfoMapper.insert(record);
    }

    public int insertSelective(UserInfo record) {
        //  record.setId(SnowflakeShardingKeyGeneratorUtil.generateKey());
        log.info("## insert ...{}", record);
        //kafkaProduceService.sendMessage(JSONObject.toJSONString(record));
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






