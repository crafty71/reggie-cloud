package com.reggie.api.setMealDish;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.setMeal.SetMealDish;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "套餐菜品关系", tags = {"套餐菜品关系"})
@RequestMapping("/setMealDishInfo/")
public interface SetMealDishApi {

  @ApiOperation(value = "根据id查看套餐菜品关系", notes = "根据id查看套餐菜品关系", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/{id}")
  ApiRestResponse<SetMealDish> getSetMealDishById(@PathVariable Long id);

  @ApiOperation(value = "查看套餐菜品关系分页", notes = "查看套餐菜品关系分页", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/page")
  ApiRestResponse<Page<SetMealDish>> setMealDishPage(int page, int pageSize, String name);
}
