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

    //调用DAO层之后进行分页处理，带参数的list方法，引入局部变量start，也就是调用这个方法的必须输入start的局部变量
    public Page4Navigator<Category> list(int start, int size, int navigatePages) {

        //先将ID排序
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        //将pageable封装好
        Pageable pageable = new PageRequest(start, size,sort);

        //上面封装好的对像放进去，用categoryDAO调用findAll方法
        Page pageFromJPA =categoryDAO.findAll(pageable);

        //返回最后用Page4Navigator封装的对象
        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

    //这个是无参方法list，上面的是有参list
    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    //添加分类
    public void add(Category bean) {
        categoryDAO.save(bean);
    }




}
