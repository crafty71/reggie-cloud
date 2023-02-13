package com.reggie.api.user;

import com.reggie.ApiRestResponse;
import com.reggie.user.userDto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "用户相关api", tags = { "用户相关api" })
@RequestMapping("/userInfo/")
public interface UserApi {

  @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "POST")
  @ResponseBody
  @PostMapping("/getVerificationCode/{phone}")
  ApiRestResponse<String> getVerificationCode(@PathVariable(value = "phone") String phone);

  // registration
  @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
  @ResponseBody
  @PostMapping("/registration")
  ApiRestResponse<String> registrationUser(@RequestBody UserDto user);

}
