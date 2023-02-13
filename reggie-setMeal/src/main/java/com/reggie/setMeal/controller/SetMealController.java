package com.reggie.setMeal.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.api.setMeal.setMealApi;
import com.reggie.category.Category;
import com.reggie.setMeal.SetMeal;
import com.reggie.setMeal.client.CategoryFeignClient;
import com.reggie.setMeal.service.SetMealService;
import com.reggie.setMeal.setMealDto.SetMealDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetMealController implements setMealApi {

  @Resource
  private SetMealService setMealService;

  @Resource
  private CategoryFeignClient categoryFeignClient;

  @Override
  public ApiRestResponse<Page<SetMealDto>> getDishByPage(int pages, int pageSize, String name) {
    //分页构造器对象
    Page<SetMeal> pageInfo = new Page<>(pages,pageSize);
    Page<SetMealDto> dtoPage = new Page<>();

    LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
    //添加查询条件，根据name进行like模糊查询
    queryWrapper.like(name != null,SetMeal::getName,name);
    //添加排序条件，根据更新时间降序排列
    queryWrapper.orderByDesc(SetMeal::getUpdateTime);

    setMealService.page(pageInfo,queryWrapper);

    //对象拷贝
    BeanUtils.copyProperties(pageInfo,dtoPage,"records");
    List<SetMeal> records = pageInfo.getRecords();

    List<SetMealDto> list = records.stream().map((item) -> {
      SetMealDto setmealDto = new SetMealDto();
      //对象拷贝
      BeanUtils.copyProperties(item,setmealDto);
      //分类id
      Long categoryId = item.getCategoryId();
      //根据分类id查询分类对象
      Category category = categoryFeignClient.getCategoryById(String.valueOf(categoryId)).getData();
      if(category != null){
        //分类名称
        String name1 = category.getName();
        setmealDto.setName(name1);
      }
      return setmealDto;
    }).collect(Collectors.toList());

    dtoPage.setRecords(list);
    return ApiRestResponse.success(dtoPage);
  }

  @Override
  public ApiRestResponse<Boolean> insertDish(SetMealDto setMealDto) {
    return null;
  }
}
