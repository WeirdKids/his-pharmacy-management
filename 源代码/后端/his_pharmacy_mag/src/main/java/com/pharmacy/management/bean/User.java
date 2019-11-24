package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author 徐奥飞
 * @date 2019-11-5 7:50
 */

@Entity // @Entity 表示这是一个实体类
@Table(name = "users") // @Table(name=“users”) 表示对应的表名是 users
// 因为是做前后端分离，而前后端数据交互用的是 json 格式
// 那么 User 对象就会被转换为 json 数据
// 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate
// 在 jpa 工作过程中，就会创造代理类来继承 User
// 并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性
// 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class User {
    // @Id 标注用于声明一个实体类的属性映射为数据库的主键列
    @Id
    // @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
    // IDENTITY：采用数据库ID自增长的方式来自增主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String username;

    String password;
    // @Column(name = "realName")这样写访问数据库时会出错！(数据库的字段，数据库不区分大小写，这个要注意)
    // hibernate会按照驼峰命名规范将 realName 转成 real_name，相当于数据库中 realName 等于实体类中的 real_name (hibernate自动帮我们转换)
    // 所以，要按照下面这样写，访问数据库才能顺利进行
    @Column(name = "realname")
    String realName;

    // 账户当前状态（未登录 || 已登录）
    String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
