package com.reggie.api.setMeal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.setMeal.setMealDto.SetMealDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "套餐api", tags = {"套餐api"})
@RequestMapping("/mealInfo")
public interface setMealApi {

  @ApiOperation(value = "套餐分页查询", notes = "套餐分页查询", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/page")
  ApiRestResponse<Page<SetMealDto>> getDishByPage(int pages, int pageSize, String name);

  @ApiOperation(value = "新增套餐", notes = "新增套餐", httpMethod = "POST")
  @ResponseBody
  @PostMapping
  public ApiRestResponse<Boolean> insertDish(@RequestBody SetMealDto setmealDto);
}
