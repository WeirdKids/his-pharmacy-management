package com.pharmacy.management.dao;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Warehouse;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8：01
 */
@Transactional // 执行修改方法时一定要添加这个注解和 @Modifying 注解
public interface DrugDao extends JpaRepository<Drug, Integer> {
    List<Drug> findAllByMnemonicCode(String MnemonicCode);

    Drug findById(int id);

    Drug findAllById(int id);
    Drug findAllByDrugsName(String drugsName);

    @Query(value = "select * from drugs limit 50", nativeQuery = true)
    List<Drug> returnAll();
    // 根据药品 id 实现单个删除
    void deleteById(int id);

    @Modifying
    @Query(value = "update drugs set drugsPrice = :drugsPrice, totalNum = :totalNum where id = :id ", nativeQuery = true)
    void updateRepertory(@Param("id") int id, @Param("drugsPrice") double drugsPrice, @Param("totalNum") int totalNum);
}
