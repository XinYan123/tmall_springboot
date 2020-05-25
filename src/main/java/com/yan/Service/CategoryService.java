package com.yan.Service;

import com.yan.dao.CategoryDAO;
import com.yan.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/5/25 10:48
 */

@Service
public class CategoryService {

    @Autowired//自动进行对象构建，注入依赖
    CategoryDAO categoryDAO;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }
}
