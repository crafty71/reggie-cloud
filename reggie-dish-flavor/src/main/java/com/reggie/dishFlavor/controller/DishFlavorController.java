package com.reggie.dishFlavor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reggie.ApiRestResponse;
import com.reggie.api.dishFlavor.dishFlavorApi;
import com.reggie.dishFlavor.DishFlavor;
import com.reggie.dishFlavor.service.impl.DishFlavorServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishFlavorController implements dishFlavorApi {

  @Resource
  private DishFlavorServiceImpl dishFlavorService;

  @Override
  public ApiRestResponse<List<DishFlavor>> getDishFlavorList() {
    return ApiRestResponse.success( dishFlavorService.list());
  }

  @Override
  public ApiRestResponse<DishFlavor> getDishFlavorById(String id) {
    return ApiRestResponse.success(dishFlavorService.getById(id));
  }

  @Override
  public ApiRestResponse<List<DishFlavor>> getDishFlavorByDishId(String id) {
    LambdaQueryWrapper<DishFlavor> DishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
    DishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, id);
    List<DishFlavor> dishFlavors = dishFlavorService.list(DishFlavorLambdaQueryWrapper);
    return ApiRestResponse.success(dishFlavors);
  }

  @Override
  public ApiRestResponse<Boolean> update(DishFlavor dishFlavor) {
    boolean updateById = dishFlavorService.updateById(dishFlavor);
    return ApiRestResponse.success(updateById);
  }

  @Override
  public ApiRestResponse<Boolean> addNewDishFlavor(DishFlavor dishFlavor) {
    boolean save = dishFlavorService.save(dishFlavor);
    return ApiRestResponse.success(save);
  }

  @Override
  public ApiRestResponse<Boolean> deleteDishFlavorByDishId(String id) {
    return ApiRestResponse.success( dishFlavorService.removeById(id));
  }

}
