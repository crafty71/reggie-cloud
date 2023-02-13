package com.reggie.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.reggie.category.Category;
import com.reggie.category.mapper.CategoryMapper;
import com.reggie.category.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>  implements
    CategoryService {
}
