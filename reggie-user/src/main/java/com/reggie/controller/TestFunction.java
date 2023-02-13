package com.reggie.controller;

import com.reggie.ApiRestResponse;
import com.reggie.api.user.TestApi;
import com.reggie.service.UserService;
import com.reggie.user.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFunction implements TestApi {

  @Resource
  private UserService userService;

  @Override
  public ApiRestResponse<String> testApiRest() {
    return ApiRestResponse.success("通用返回类测试成功");
  }

  @Override
  public ApiRestResponse<List<User>> testUserContent() {

    List<User> users = userService.list();
    return ApiRestResponse.success(users);
  }
}
