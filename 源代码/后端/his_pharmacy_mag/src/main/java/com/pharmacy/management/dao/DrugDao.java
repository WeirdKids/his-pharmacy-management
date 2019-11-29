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

    //通过 mnemonicCode 字段查询到对应的一行或几行，并返回给Drug列表
    List<Drug> findAllByMnemonicCodeLike(String mnemonicCode);
    List<Drug> findAllById(int id);
    List<Drug> findAllByDrugsNameLike(String drugsName);

    @Query(value = "select * from drugs limit 50", nativeQuery = true)
    List<Drug> returnAll();
}
