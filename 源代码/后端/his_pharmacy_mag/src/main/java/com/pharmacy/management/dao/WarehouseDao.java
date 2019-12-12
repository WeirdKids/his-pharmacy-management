package com.pharmacy.management.dao;

import com.pharmacy.management.bean.Warehouse;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WarehouseDao extends JpaRepository<Warehouse, Integer> {
    @Query(value = "select sum(num) from warehouses where warehouse = :warehouse", nativeQuery = true)
    int getNum(@Param("warehouse") String warehouse);
}
