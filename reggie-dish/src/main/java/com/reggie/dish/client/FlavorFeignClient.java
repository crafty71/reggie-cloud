package com.reggie.dish.client;

import com.reggie.ApiRestResponse;
import com.reggie.dishFlavor.DishFlavor;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("dishFlavor-service")
public interface FlavorFeignClient {

  @RequestMapping(value="/dishFlavorInfo/dishId/{id}",method = RequestMethod.GET)
  ApiRestResponse<List<DishFlavor>> getDishFlavorByDishId(@PathVariable(value="id") String id);

  @RequestMapping(value="/dishFlavor/dishId/{id}",method = RequestMethod.POST)
  ApiRestResponse<Boolean> addNewDishFlavor(@RequestBody DishFlavor dishFlavor);
}
