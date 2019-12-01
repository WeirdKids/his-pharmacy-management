package com.pharmacy.management.service;


import com.pharmacy.management.bean.Prescription;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:10
 */

public interface PresService {
    public List<Prescription> getByPresCode(int PresCode);
    public List<Prescription> getAll();
    public List<Prescription> getSent();
    public Prescription getByPresId(int pres_id);
    public void Update(Prescription prescription);
}
