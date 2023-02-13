package com.reggie.setMealDish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.setMeal.SetMealDish;
import com.reggie.setMealDish.mapper.SetmealDishMapper;
import com.reggie.setMealDish.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetMealDish> implements
    SetmealDishService {
}
