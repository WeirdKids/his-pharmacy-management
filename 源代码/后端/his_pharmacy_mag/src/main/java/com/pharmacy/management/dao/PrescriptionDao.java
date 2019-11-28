package com.pharmacy.management.dao;

import com.pharmacy.management.bean.Drug;
import com.pharmacy.management.bean.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8：01
 */
@Transactional // 执行修改方法时一定要添加这个注解和 @Modifying 注解
public interface PrescriptionDao extends JpaRepository<Prescription, Integer> {
    List<Prescription> findAllByPrescriptionCodeLike(String presCode);

    @Query(value="select * from drugs limit 50", nativeQuery = true)
    List<Prescription> returnAll();
}
