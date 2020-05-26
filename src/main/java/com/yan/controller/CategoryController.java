package com.yan.controller;

import com.yan.Service.CategoryService;
import com.yan.pojo.Category;
import com.yan.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//    @GetMapping("/categories")
//    public List<Category> list() throws Exception {
//        return categoryService.list();
//    }

    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;

        //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        Page4Navigator<Category> page =categoryService.list(start, size, 5);

        //上面封装的对象最后直接扔给前端，
        // page =categoryService.list(start, size, 5);
        // 给前端的page封装好了start，size这些数据
        return page;
    }
}
