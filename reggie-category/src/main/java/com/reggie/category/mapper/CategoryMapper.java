package com.reggie.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.category.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
