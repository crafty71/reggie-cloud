package com.reggie.setMeal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.setMeal.SetMeal;
import com.reggie.setMeal.mapper.SetMealMapper;
import com.reggie.setMeal.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetMealMapper, SetMeal> implements
    SetMealService {
}
