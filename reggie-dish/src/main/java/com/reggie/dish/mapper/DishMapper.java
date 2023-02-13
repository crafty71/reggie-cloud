package com.reggie.dish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.dish.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
