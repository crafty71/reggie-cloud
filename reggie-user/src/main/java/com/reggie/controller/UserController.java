package com.reggie.controller;

import com.exception.ExceptionEnum;
import com.reggie.ApiRestResponse;
import com.reggie.api.user.UserApi;
import com.reggie.service.impl.UserServiceImpl;
import com.reggie.user.userDto.UserDto;
import com.utils.ValidateCodeUtils;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController implements UserApi {

  @Resource
  private UserServiceImpl userService;

  @Resource
  RedisTemplate<Object, Object> redisTemplate;

  @Override
  public ApiRestResponse<String> getVerificationCode(String phone) {
    String code = ValidateCodeUtils.generateValidateCode(4).toString();
    log.info(code);
    redisTemplate.opsForValue().set( phone,code,5, TimeUnit.MINUTES);
    return ApiRestResponse.success(code);
  }

  @Override
  public ApiRestResponse<String> registrationUser(UserDto user) {
    log.info(user.toString());
    log.info(user.toString());
    try {
      //1.构造登录令牌 UsernamePasswordToken
      UsernamePasswordToken upToken = new UsernamePasswordToken(user.getPhone(), user.getCode());
      //2.获取subject
      Subject subject = SecurityUtils.getSubject();
      //3.调用login方法，进入realm完成认证
      subject.login(upToken);
      //4.获取sessionId
      String sessionId = (String) subject.getSession().getId();
      //5.构造返回结果
      redisTemplate.opsForValue().set(user.getPhone(), sessionId, 30, TimeUnit.MINUTES);
      return ApiRestResponse.success(sessionId);
    } catch (Exception e) {
      return ApiRestResponse.error(ExceptionEnum.WRONG_PASSWORD);
    }
  }
}
