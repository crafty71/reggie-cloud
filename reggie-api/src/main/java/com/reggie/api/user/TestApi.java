package com.reggie.api.user;

import com.reggie.ApiRestResponse;
import com.reggie.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "测试功能相关接口", tags = {"测试功能相关接口"})
@RequestMapping("/users/test")
public interface TestApi {

  @ApiOperation(value = "测试公共返回类", notes = "测试公共返回类", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/commonResult")
  ApiRestResponse<String> testApiRest();

  @ApiOperation(value = "测试Mysql链接", notes = "测试Mysql链接", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/testMysql")
  ApiRestResponse<List<User>> testUserContent();
}
