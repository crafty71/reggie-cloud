package com.reggie.dish.dto;



import com.reggie.dish.Dish;
import com.reggie.dishFlavor.DishFlavor;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
