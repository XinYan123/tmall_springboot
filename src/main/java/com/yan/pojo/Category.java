package com.yan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/5/25 10:34
 */


@Entity  //表示这是一个实体类
@Table(name = "category") //对应的表名

/**
 * 因为是做前后端分离，而前后端数据交互用的是 json 格式。
 * 那么 Category 对象就会被转换为 json 数据。
 * 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate,
 * 在 jpa 工作过程中，就会创造代理类来继承 Category ，并添加 handler 和 hibernateLazyInitializer
 * 这两个无须 json 化的属性，所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
 */
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })


@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String name;
}
