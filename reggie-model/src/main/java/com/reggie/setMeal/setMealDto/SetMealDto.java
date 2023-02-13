package com.reggie.setMeal.setMealDto;


import com.reggie.setMeal.SetMeal;
import com.reggie.setMeal.SetMealDish;
import java.util.List;
import lombok.Data;

@Data
public class SetMealDto extends SetMeal {

  private List<SetMealDish> setmealDishes;

  private String name;
}

