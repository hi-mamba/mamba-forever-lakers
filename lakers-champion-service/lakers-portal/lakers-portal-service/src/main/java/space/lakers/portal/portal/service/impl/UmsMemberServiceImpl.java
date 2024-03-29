package space.lakers.portal.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import space.lakers.domain.UserDTO;
import space.lakers.portal.portal.model.UmsMember;
import space.lakers.portal.portal.service.UmsMemberService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import space.lakers.utils.vo.DataResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * @author mini kobe
 * @date 2021/8/24
 * <pre>
 *
 * </pre>
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

   // @Resource
   // private UmsMemberMapper memberMapper;
   // @Resource
   // private UmsMemberLevelMapper memberLevelMapper;
   // @Resource
   // private UmsMemberCacheService memberCacheService;
   // @Value("${redis.key.authCode}")
   // private String REDIS_KEY_PREFIX_AUTH_CODE;
   // @Value("${redis.expire.authCode}")
   // private Long AUTH_CODE_EXPIRE_SECONDS;
   // @Resource
   // private AuthService authService;
   // @Resource
   // private HttpServletRequest request;
//
   // @Override
   public UmsMember getByUsername(String username) {
     // UmsMemberExample example = new UmsMemberExample();
     // example.createCriteria().andUsernameEqualTo(username);
     // List<UmsMember> memberList = memberMapper.selectByExample(example);
     // if (!CollectionUtils.isEmpty(memberList)) {
     //     return memberList.get(0);
     // }
       return null;
   }
   // @Override
   // public UmsMember getById(Long id) {
   //     return memberMapper.selectByPrimaryKey(id);
   // }
//
   // @Override
   // public void register(String username, String password, String telephone, String authCode) {
   //     //验证验证码
   //     if(!verifyAuthCode(authCode,telephone)){
   //         Asserts.fail("验证码错误");
   //     }
   //     //查询是否已有该用户
   //     UmsMemberExample example = new UmsMemberExample();
   //     example.createCriteria().andUsernameEqualTo(username);
   //     example.or(example.createCriteria().andPhoneEqualTo(telephone));
   //     List<UmsMember> umsMembers = memberMapper.selectByExample(example);
   //     if (!CollectionUtils.isEmpty(umsMembers)) {
   //         Asserts.fail("该用户已经存在");
   //     }
   //     //没有该用户进行添加操作
   //     UmsMember umsMember = new UmsMember();
   //     umsMember.setUsername(username);
   //     umsMember.setPhone(telephone);
   //     umsMember.setPassword(BCrypt.hashpw(password));
   //     umsMember.setCreateTime(new Date());
   //     umsMember.setStatus(1);
   //     //获取默认会员等级并设置
   //     UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
   //     levelExample.createCriteria().andDefaultStatusEqualTo(1);
   //     List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
   //     if (!CollectionUtils.isEmpty(memberLevelList)) {
   //         umsMember.setMemberLevelId(memberLevelList.get(0).getId());
   //     }
   //     memberMapper.insert(umsMember);
   //     umsMember.setPassword(null);
   // }
//
   // @Override
   // public String generateAuthCode(String telephone) {
   //     StringBuilder sb = new StringBuilder();
   //     Random random = new Random();
   //     for(int i=0;i<6;i++){
   //         sb.append(random.nextInt(10));
   //     }
   //     memberCacheService.setAuthCode(telephone,sb.toString());
   //     return sb.toString();
   // }
//
   // @Override
   // public void updatePassword(String telephone, String password, String authCode) {
   //     UmsMemberExample example = new UmsMemberExample();
   //     example.createCriteria().andPhoneEqualTo(telephone);
   //     List<UmsMember> memberList = memberMapper.selectByExample(example);
   //     if(CollectionUtils.isEmpty(memberList)){
   //         Asserts.fail("该账号不存在");
   //     }
   //     //验证验证码
   //     if(!verifyAuthCode(authCode,telephone)){
   //         Asserts.fail("验证码错误");
   //     }
   //     UmsMember umsMember = memberList.get(0);
   //     umsMember.setPassword(BCrypt.hashpw(password));
   //     memberMapper.updateByPrimaryKeySelective(umsMember);
   //     memberCacheService.delMember(umsMember.getId());
   // }
//
   // @Override
   // public UmsMember getCurrentMember() {
   //     String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
   //     if(StrUtil.isEmpty(userStr)){
   //         Asserts.fail(ResultCode.UNAUTHORIZED);
   //     }
   //     UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
   //     UmsMember member = memberCacheService.getMember(userDto.getId());
   //     if(member!=null){
   //         return member;
   //     }else{
   //         member = getById(userDto.getId());
   //         memberCacheService.setMember(member);
   //         return member;
   //     }
   // }
//
   // @Override
   // public void updateIntegration(Long id, Integer integration) {
   //     UmsMember record=new UmsMember();
   //     record.setId(id);
   //     record.setIntegration(integration);
   //     memberMapper.updateByPrimaryKeySelective(record);
   //     memberCacheService.delMember(id);
   // }
//
     @Override
     public UserDTO loadUserByUsername(String username) {
         UserDTO userDto = new UserDTO();
         userDto.setId(1L);
         userDto.setUsername("macro");
         userDto.setPassword("123456");
         userDto.setStatus(1);
         userDto.setClientId("portal-app");
         userDto.setRoles(Lists.newArrayList("ADMIN","admin"));
         return userDto;
       // UmsMember member = getByUsername(username);
       // if(member!=null){
       //     UserDTO userDto = new UserDTO();
       //     BeanUtil.copyProperties(member,userDto);
       //     userDto.setRoles(CollUtil.toList("前台会员"));
       //     return userDto;
       // }
       // return null;
     }

//
   // @Override
   // public DataResponse login(String username, String password) {
   //     if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
   //         Asserts.fail("用户名或密码不能为空！");
   //     }
   //     Map<String, String> params = new HashMap<>();
   //     params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
   //     params.put("client_secret","123456");
   //     params.put("grant_type","password");
   //     params.put("username",username);
   //     params.put("password",password);
   //     return authService.getAccessToken(params);
   // }
//
   // //对输入的验证码进行校验
   // private boolean verifyAuthCode(String authCode, String telephone){
   //     if(StringUtils.isEmpty(authCode)){
   //         return false;
   //     }
   //     String realAuthCode = memberCacheService.getAuthCode(telephone);
   //     return authCode.equals(realAuthCode);
   // }
}
