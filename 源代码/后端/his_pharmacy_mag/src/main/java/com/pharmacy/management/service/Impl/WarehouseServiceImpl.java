package com.pharmacy.management.service.Impl;

import com.pharmacy.management.dao.WarehouseDao;
import com.pharmacy.management.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseDao warehouseDao;

    @Override
    public int getNum(String warehouse) {
        return warehouseDao.getNum(warehouse);
    }
}
