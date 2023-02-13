package com.reggie.realm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reggie.service.impl.UserServiceImpl;
import com.reggie.shiro.realm.IhrmRealm;
import com.reggie.user.User;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class UserRealm extends IhrmRealm {


  @Resource
  RedisTemplate<Object, Object> redisTemplate;

  @Resource
  private UserServiceImpl userService;


  //认证方法
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
    log.info(String.valueOf(upToken));
    String username = upToken.getUsername();
    String password = String.valueOf(upToken.getPassword());
    log.info(password);
    String codeInSession = (String) redisTemplate.opsForValue().get(username);
    log.info(codeInSession);
    if (codeInSession != null && codeInSession.equals(password)) {
      User result = null;
      LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.eq(User::getPhone, username);
      User user1 = userService.getOne(queryWrapper);
      if (user1 == null) {
        //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
        user1 = new User();
        user1.setId(String.valueOf(UUID.randomUUID()));
        user1.setPhone(username);
        user1.setStatus(1);
        userService.save(user1);
      }
      //如果用户登录成功，删除Redis中缓存的验证码
      redisTemplate.delete(username);
      return new SimpleAuthenticationInfo(user1, password, username);
    }
    return null;
  }

}
