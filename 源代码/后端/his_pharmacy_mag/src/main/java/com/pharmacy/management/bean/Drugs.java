package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity

@Table(name="drugs")

@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})

public class Drugs {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
//药品编号
    @Column(name = "drugscode")
    String drugsCode;
    //药品名
    @Column(name = "drugsname")
    String drugsName;
    //药品规格
    @Column(name = "drugsformat")
    String drugsFormat;
    //药品单位
    @Column(name = "drugsunit")
    String durgsUnit;
    //生产厂家
    @Column(name = "manufacturer")
    String manufacturer;

    @Column(name = "drugsdosageid")
    int drugsDosageID;

    @Column(name = "drugstypeid")
    int durgsTypeID;
//单价
    @Column(name = "drugsprice")
    float drugsPrice;
//助记码
    @Column(name = "mnemoniccode")
    String mnemonicCode;
//创建日期
    @Column(name = "creationdate")
    date creationDate;
//数量
    @Column(name = "num")
    int num;
//储存要求
    @Column(name = "saverequire")
    String saveRequire;
//所属目录
    @Column(name = "category")
    String category;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void getDrugsCode() {
        return drugsCode;
    }
    public void setDrugsCode(String drugsName) {
        this.drugsCode = drugsCode;
    }

    public void getDrugsName() {
        return drugsName;
    }
    public void setDrugsName(String drugsName) { this.drugsName = drugsName; }

    public void getDrugsFormat() { return drugsFormat; }
    public void setDrugsFormat(String drugsFormat) {
        this.drugsFormat = drugsFormat;
    }

    public void getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public void getDrugsPrice() { return drugsPrice; }
    public void setDrugsPrice(float drugsPrice) {
        this.drugsPrice = drugsPrice;
    }

    public void getMnemonicCode() {
        return mnemonicCode;
    }
    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public void getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(date creationDate) {
        this.creationDate = creationDate;
    }

    public void getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public void getSaveRequire() {
        return saveRequire;
    }
    public void setSaveRequire(String saveRequire) {
        this.saveRequire = saveRequire;
    }

    public void getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void getDrugsDosageID() {
        return drugsDosageID;
    }
    public void setDrugsDosageID(int drugsDosageID) {
        this.drugsDosageID = drugsDosageID;
    }

    public void getDrugsTypeID() {
        return drugsTypeID;
    }
    public void setDrugsTypeID(int drugsTypeID) {
        this.drugsTypeID = drugsTypeID;
    }

}