package com.reggie.api.dishFlavor;

import com.reggie.ApiRestResponse;
import com.reggie.dishFlavor.DishFlavor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "菜品口味", tags = {"菜品口味"})
@RequestMapping("/dishFlavorInfo")
public interface dishFlavorApi {

  @ApiOperation(value = "查询口味列表", notes = "查询口味列表", httpMethod = "GET")
  @ResponseBody
  @GetMapping("/list")
  ApiRestResponse<List<DishFlavor>> getDishFlavorList();

  @ApiOperation(value = "根据id查询口味", notes = "根据id查询口味", httpMethod = "GET")
  @ResponseBody
  @RequestMapping(value="/{id}",method = RequestMethod.GET)
  ApiRestResponse<DishFlavor> getDishFlavorById(@PathVariable(value="id") String id);

  @ApiOperation(value = "根据DishId查询口味", notes = "根据DishId查询口味", httpMethod = "GET")
  @ResponseBody
  @RequestMapping(value="/dishId/{id}",method = RequestMethod.GET)
  ApiRestResponse<List<DishFlavor>> getDishFlavorByDishId(@PathVariable(value="id") String id);

  @ApiOperation(value = "根据id更新口味", notes = "根据id更新口味", httpMethod = "PUT")
  @ResponseBody
  @RequestMapping(value= "updateDishFlavor",method = RequestMethod.PUT)
  ApiRestResponse<Boolean> update(@RequestBody DishFlavor dishFlavor);

  @ApiOperation(value = "新增口味", notes = "新增口味", httpMethod = "POST")
  @ResponseBody
  @RequestMapping(value= "addNewDishFlavor",method = RequestMethod.POST)
  ApiRestResponse<Boolean> addNewDishFlavor(@RequestBody DishFlavor dishFlavor);

  @ApiOperation(value = "根据id删除口味", notes = "根据id删除口味", httpMethod = "DELETE")
  @ResponseBody
  @RequestMapping(value="/deleteDishFlavor/{id}",method = RequestMethod.DELETE)
  ApiRestResponse<Boolean> deleteDishFlavorByDishId(@PathVariable(value="id") String id);


}
