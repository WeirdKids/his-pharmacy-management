package com.pharmacy.management.dao;

import com.pharmacy.management.bean.Drug;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8：01
 */
@Transactional // 执行修改方法时一定要添加这个注解和 @Modifying 注解
public interface DrugDao extends JpaRepository<Drug, Integer> {
    // 根据药品助记码查询对应的药品信息
    List<Drug> findAllByMnemonicCode(String MnemonicCode);

    // 查询所有的药品信息
    @Query(value = "select * from drugs limit 50", nativeQuery = true)
    List<Drug> returnAll();
}
