package com.yan.Service;

import com.yan.dao.CategoryDAO;
import com.yan.pojo.Category;
import com.yan.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    //调用DAO层之后进行分页处理，带参数的list方法
    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }


    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }
}
