package com.reggie.setMealDish.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.api.setMealDish.SetMealDishApi;
import com.reggie.setMeal.SetMealDish;
import com.reggie.setMealDish.service.impl.SetmealDishServiceImpl;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class setMealDishController  implements SetMealDishApi {

  @Resource
  private SetmealDishServiceImpl setmealDishService;

  @Override
  public ApiRestResponse<SetMealDish> getSetMealDishById(Long id) {
    return ApiRestResponse.success(setmealDishService.getById(id));
  }

  @Override
  public ApiRestResponse<Page<SetMealDish>> setMealDishPage(int page, int pageSize, String name) {
    Page<SetMealDish> dishPage = new Page<>();
    LambdaQueryWrapper<SetMealDish> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.like(name != null, SetMealDish::getName, name);
    queryWrapper.orderByDesc(SetMealDish::getUpdateTime);
    Page<SetMealDish> setMealDishPage = setmealDishService.page(dishPage, queryWrapper);
    return ApiRestResponse.success(setMealDishPage);
  }
}
