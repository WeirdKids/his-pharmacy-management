package com.pharmacy.management.service;

import com.pharmacy.management.bean.Drug;

import java.util.List;


public interface DrugService {
    public List<Drug> getByMnemonicCodeLike(String mnemonicCode);
    public Drug getByDrugsName(String DrugsName);
    public List<Drug> getAll();
    public void deleteRepertory(int id);
    public void updateRepertory(int id, double drugsPrice, int totalNum);
    public Drug getById(int id);
    public void Update(Drug drug);
    public List<String> getDrugsTypeId();
    public List<String> getDrugsName(int drugsTypeID);
}
