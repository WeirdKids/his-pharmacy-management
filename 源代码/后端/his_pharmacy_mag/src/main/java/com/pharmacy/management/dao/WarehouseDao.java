package com.pharmacy.management.dao;

import com.pharmacy.management.bean.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8：01
 */
@Transactional // 执行修改方法时一定要添加这个注解和 @Modifying 注解
public interface WarehouseDao extends JpaRepository<Warehouse, Integer> {

    @Query(value = "select warehouse.id, warehouse.num, warehouse.warehouse " +
            "from drugs inner join warehouse " +
            "on drugs.drugsName = warehouse.drugsName " +
            "and drugs.drugsPrice = warehouse.drugsPrice " +
            "and drugs.mnemonicCode = warehouse.mnemonicCode " +
            "limit 50", nativeQuery = true)
    List<Warehouse> returnAll();
}
