package com.reggie.api.dish;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.ApiRestResponse;
import com.reggie.dish.dto.DishDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "菜单接口", tags = {"菜单接口"})
@RequestMapping("/dishInfo")
public interface DishApi {

  @ApiOperation(value = "根据id查看菜单", notes = "根据id查看菜单", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/{id}")
  ApiRestResponse<DishDto> getDishById(@PathVariable Long id);

  @ApiOperation(value = "查看菜单分页", notes = "查看菜单分页", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/page")
  ApiRestResponse<Page<DishDto>> page(int page, int pageSize, String name);

  @ApiOperation(value = "新增菜单", notes = "新增菜单", httpMethod = "POST")
  @ResponseBody
  @PostMapping
  ApiRestResponse<Boolean> insertDish(@RequestBody DishDto dishDto);
}
