package space.lakers.portal.portal.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import space.lakers.domain.UserDTO;
import space.lakers.portal.portal.model.UmsMember;
import space.lakers.portal.portal.service.UmsMemberService;
import space.lakers.portal.service.UmsMemberFeignClient;
import space.lakers.utils.vo.DataResponse;

import javax.annotation.Resource;

/**
 * @author mini kobe
 * @date 2021/8/24
 * <pre>
 *会员登录注册管理Controller
 * </pre>
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/api/lakers/sso")
public class UmsMemberController implements UmsMemberFeignClient {

    @Resource
    private UmsMemberService memberService;

    @ApiOperation("会员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public DataResponse register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
       // memberService.register(username, password, telephone, authCode);
        return new DataResponse<>("注册成功");
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResponse login(@RequestParam String username,
                              @RequestParam String password) {
       // return memberService.login(username, password);
        return null;
    }

    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public DataResponse info() {
       // UmsMember member = memberService.getCurrentMember();
        //return CommonResult.success(member);
        return new DataResponse<>("1");
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public DataResponse getAuthCode(@RequestParam String telephone) {
        //String authCode = memberService.generateAuthCode(telephone);
        //return CommonResult.success(authCode,"获取验证码成功");
        return null;

    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public DataResponse updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        //memberService.updatePassword(telephone,password,authCode);
        //return DataResponse.success(null,"密码修改成功");
        return null;
    }

    @Override
    @ApiOperation("根据用户名获取通用用户信息")
    public UserDTO loadUserByUsername(@RequestParam String username) {
        log.info("#### 根据用户名获取通用用户信息 ={}", username);
        return memberService.loadUserByUsername(username);
    }
}