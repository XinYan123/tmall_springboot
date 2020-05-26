package com.yan.dao;

import com.yan.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//使用jpa技术，直接操作实体类，SQL语言都省掉了
public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
