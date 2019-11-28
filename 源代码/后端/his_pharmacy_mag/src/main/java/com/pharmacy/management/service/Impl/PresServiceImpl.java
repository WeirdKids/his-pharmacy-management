package com.pharmacy.management.service.Impl;

import com.pharmacy.management.bean.Prescription;
import com.pharmacy.management.dao.PrescriptionDao;
import com.pharmacy.management.service.PresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public class PresServiceImpl implements PresService{
    @Autowired
    PrescriptionDao prescriptionDao;

    @Override
    public List<Prescription> getByPresCode(String PresCode){
        return prescriptionDao.findAllByPrescriptionCodeLike(PresCode);
    }

    @Override
    public List<Prescription> getAll(){return prescriptionDao.returnAll();}
}
