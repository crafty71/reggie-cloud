package com.reggie.category.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.api.category.CategoryApi;
import com.reggie.category.Category;
import com.reggie.category.service.impl.CategoryServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryApi {

  @Resource
  private CategoryServiceImpl categoryService;

  @Override
  public ApiRestResponse<List<Category>> getCategoryList() {
    return ApiRestResponse.success(categoryService.list());
  }

  @Override
  public ApiRestResponse<List<Category>> getCategoryListByType(String type) {
    LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(type != null, Category::getType, type);
    wrapper.orderByAsc(Category::getSort).orderByAsc(Category::getUpdateTime);
    return ApiRestResponse.success(categoryService.list(wrapper));
  }

  @Override
  public ApiRestResponse<Boolean> updateCategory(Category category) {
    return ApiRestResponse.success(categoryService.updateById(category));
  }

  @Override
  public ApiRestResponse<Boolean> deleteCategory(String id) {
    return ApiRestResponse.success(categoryService.removeById(id));
  }

  @Override
  public ApiRestResponse<Category> getCategoryById(String id) {
    return ApiRestResponse.success(categoryService.getById(id));
  }

  @Override
  public ApiRestResponse<Page<Category>> getCategory(int page, int pageSize) {
    Page<Category> categoryPage = new Page<>(page, pageSize);

    LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();


    wrapper.orderByAsc(Category::getSort);

    Page<Category> categoryList = categoryService.page(categoryPage, wrapper);

    return ApiRestResponse.success(categoryList);
  }
}
