package com.pharmacy.management.service.Impl;

import com.pharmacy.management.bean.Warehouse;
import com.pharmacy.management.dao.WarehouseDao;
import com.pharmacy.management.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:15
 */

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseDao warehouseDao;

    @Override
    public List<Warehouse> getAll() {
        return warehouseDao.returnAll();
    }
}
