package com.pharmacy.management.service;

import com.pharmacy.management.bean.Drug;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 徐奥飞
 * date 2019-11-12 8:10
 */

public interface DrugService {
    public List<Drug> getByMnemonicCode(String mnemonicCode);
}
