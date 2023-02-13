package com.reggie.dish.client;

import com.reggie.ApiRestResponse;
import com.reggie.category.Category;
import com.reggie.dishFlavor.DishFlavor;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("category-service")
public interface CategoryFeignClient {

  @RequestMapping(value="/categoryInfo/categoryList/{id}",method = RequestMethod.GET)
  ApiRestResponse<Category> getCategoryById(@PathVariable String id);
}
