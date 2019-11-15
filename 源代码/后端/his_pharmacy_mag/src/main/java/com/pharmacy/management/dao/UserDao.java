package com.pharmacy.management.dao;
// Data Access Object（数据访问对象，DAO）即用来操作数据库的对象
// 这个对象可以是我们自己开发的，也可以是框架提供的。这里我们通过继承 JpaRepository 的方式构建 DAO

import com.pharmacy.management.bean.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 徐奥飞
 * @date 2019-11-5 8：01
 */
@Transactional // 执行修改方法时一定要添加这个注解和 @Modifying 注解
public interface UserDao extends JpaRepository<User, Integer> {
    // 通过username字段查询到对应的行，并返回给User类
    User findByUsername(String username);

    // 通过username和password字段查询
    User getByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "update users set state = :state where id = :id", nativeQuery = true)
    void updateStateById(@Param("id") int id, @Param("state") String state);
}
