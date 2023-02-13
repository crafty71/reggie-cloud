package com.reggie.dish.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.api.dish.DishApi;
import com.reggie.category.Category;
import com.reggie.dish.Dish;
import com.reggie.dish.client.CategoryFeignClient;
import com.reggie.dish.client.FlavorFeignClient;
import com.reggie.dish.dto.DishDto;
import com.reggie.dish.service.impl.DishServiceImpl;
import com.reggie.dishFlavor.DishFlavor;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishApiController implements DishApi {

  @Resource
  private FlavorFeignClient flavorFeignClient;

  @Resource
  private DishServiceImpl dishService;

  @Resource
  private CategoryFeignClient categoryFeignClient;

  @Override
  public ApiRestResponse<DishDto> getDishById(Long id) {
    Dish dish = dishService.getById(id);
    DishDto dishDto = new DishDto();
    BeanUtils.copyProperties(dish, dishDto);
    ApiRestResponse<List<DishFlavor>> dishFlavorByDishId = flavorFeignClient.getDishFlavorByDishId(
        String.valueOf(dish.getId()));
    dishDto.setFlavors(dishFlavorByDishId.getData());
    dishDto.setCategoryName(null);
    dishDto.setCopies(1);
    return ApiRestResponse.success(dishDto);
  }

  @Override
  public ApiRestResponse<Page<DishDto>> page(int page, int pageSize, String name) {
    //构造分页构造器对象
    Page<Dish> pageInfo = new Page<>(page, pageSize);
    Page<DishDto> dishDtoPage = new Page<>();

    //条件构造器
    LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
    //添加过滤条件
    queryWrapper.like(name != null, Dish::getName, name);
    //添加排序条件
    queryWrapper.orderByDesc(Dish::getUpdateTime);

    //执行分页查询
    Page<Dish> dishPage = dishService.page(pageInfo, queryWrapper);

    //对象拷贝
    BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

    List<Dish> records = pageInfo.getRecords();

    List<DishDto> list = records.stream().map((item) -> {
      DishDto dishDto = new DishDto();

      BeanUtils.copyProperties(item, dishPage);

      Long categoryId = item.getCategoryId();//分类id
//       根据id查询分类对象 后续微服务调用
      ApiRestResponse<Category> category = categoryFeignClient.getCategoryById(
          String.valueOf(categoryId));
      String categoryName = category.getData().getName();
      dishDto.setCategoryName(categoryName);

      return dishDto;
    }).collect(Collectors.toList());

    dishDtoPage.setRecords(list);

    return ApiRestResponse.success(dishDtoPage);
  }

  @Override
  public ApiRestResponse<Boolean> insertDish(DishDto dishDto) {
    List<DishFlavor> flavors = dishDto.getFlavors();
    for (DishFlavor flavor : flavors) {
      flavorFeignClient.addNewDishFlavor(flavor);
    }
    boolean save = dishService.save(dishDto);
    return ApiRestResponse.success(save);
  }
}
