package com.yan.controller;

import com.yan.Service.CategoryService;
import com.yan.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/5/25 10:57
 */

@RestController //表示这是一个控制器，并且对每个方法的返回值都会直接转换为 json 数据格式。
public class CategoryController {

    @Autowired//自动装配 CategoryService
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() throws Exception {
        return categoryService.list();
    }
}
