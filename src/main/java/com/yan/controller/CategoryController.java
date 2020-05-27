package com.yan.controller;

import com.yan.Service.CategoryService;
import com.yan.pojo.Category;
import com.yan.util.ImageUtil;
import com.yan.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/5/25 10:57
 */

@RestController //表示这是一个控制器，并且对每个方法的返回值都会直接转换为 json 数据格式。
public class CategoryController {

    //自动装配 CategoryService
    //spring负责对CategoryService对象进行生产，注入参数，运行构造器，省去我们创建对象的过程
    @Autowired
    CategoryService categoryService;

//    @GetMapping("/categories")
//    public List<Category> list() throws Exception {
//        return categoryService.list();
//    }

    //从服务器获取数据使用getmapping
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

    //因为使用的是restful风格的@RestController，服务器跳转时只看注解postmapping，使用的post即可
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");

        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        image.transferTo(file);

        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }


    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id,
                         HttpServletRequest request)  throws Exception {

        //根据id删除分类
        categoryService.delete(id);
        //删除后台的图片
        File  imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();

        return null;
    }

}
