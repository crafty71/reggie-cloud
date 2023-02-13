package com.reggie.api.category;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.category.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "菜品分类接口", tags = {"菜品分类接口"})
@RequestMapping("/categoryInfo")
public interface CategoryApi {

  @ApiOperation(value = "获取菜品分类列表", notes = "获取菜品分类列表", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/list")
  ApiRestResponse<List<Category>> getCategoryList();

  @ApiOperation(value = "获取菜品类型菜品分类列表", notes = "获取菜品类型菜品分类列表", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/list/{type}")
  ApiRestResponse<List<Category>> getCategoryListByType(@PathVariable String type);

  @ApiOperation(value = "更新菜品分类", notes = "更新菜品分类", httpMethod = "GET")
  @ResponseBody
  @PostMapping("/update")
  ApiRestResponse<Boolean> updateCategory(@RequestBody Category category);

  @ApiOperation(value = "根据id删除菜品分类", notes = "根据id删除菜品分类", httpMethod = "DELETE")
  @ResponseBody
  @DeleteMapping
  public ApiRestResponse<Boolean> deleteCategory(String id);

  @ApiOperation(value = "根据id查询菜品分类", notes = "根据id查询菜品分类", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/categoryList/{id}")
  ApiRestResponse<Category> getCategoryById(@PathVariable String id);


  @ApiOperation(value = "获取菜品类型菜品分类分页列表", notes = "获取菜品类型菜品分类分页列表", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/page")
  ApiRestResponse<Page<Category>> getCategory(int page, int pageSize);

}
