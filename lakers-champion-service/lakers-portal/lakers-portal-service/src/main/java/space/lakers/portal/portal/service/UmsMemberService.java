package space.lakers.portal.portal.service;

import space.lakers.domain.UserDTO;
import space.lakers.portal.portal.model.UmsMember;
import space.lakers.utils.vo.DataResponse;

/**
 * @author pankui
 * @date 2021/8/24
 * <pre>
 *
 * </pre>
 */
public interface UmsMemberService {

 //  /**
 //   * 根据用户名获取会员
 //   */
 //  UmsMember getByUsername(String username);

 //  /**
 //   * 根据会员编号获取会员
 //   */
 //  UmsMember getById(Long id);

 //  /**
 //   * 用户注册
 //   */
 //  void register(String username, String password, String telephone, String authCode);

 //  /**
 //   * 生成验证码
 //   */
 //  String generateAuthCode(String telephone);

 //  /**
 //   * 修改密码
 //   */
 //  void updatePassword(String telephone, String password, String authCode);

 //  /**
 //   * 获取当前登录会员
 //   */
 //  UmsMember getCurrentMember();

 //  /**
 //   * 根据会员id修改会员积分
 //   */
 //  void updateIntegration(Long id,Integer integration);


    /**
     * 获取用户信息
     */
    UserDTO loadUserByUsername(String username);

    /**
     * 登录后获取token
   //  */
  //  DataResponse login(String username, String password);
}
