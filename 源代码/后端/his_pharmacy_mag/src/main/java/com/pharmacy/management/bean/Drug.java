package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author 徐奥飞
 * @date 2019-11-8 7:50
 */
@Entity // @Entity 表示这是一个实体类
@Table(name = "drugs") // @Table(name=“drugs”) 表示对应的表名是 drugs
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Drug {

    @Id
    // @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
    // IDENTITY：采用数据库ID自增长的方式来自增主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "drugscode")
    String drugsCode;

    @Column(name = "drugsname")
    String drugsName;

    @Column(name = "drugsformat")
    String drugsFormat;

    @Column(name = "drugsunit")
    String drugsUnit;

    @Column(name = "drugsdosageid")
    int drugsDosageID;

    @Column(name = "drugstypeid")
    int drugsTypeID;

    @Column(name = "drugsprice")
    double drugsPrice;

    @Column(name = "mnemoniccode")
    String mnemonicCode;

    int num;

    @Column(name = "saverequire")
    String saveRequire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugsCode() {
        return drugsCode;
    }

    public void setDrugsCode(String drugsCode) {
        this.drugsCode = drugsCode;
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName;
    }

    public String getDrugsFormat() {
        return drugsFormat;
    }

    public void setDrugsFormat(String drugsFormat) {
        this.drugsFormat = drugsFormat;
    }

    public String getDrugsUnit() {
        return drugsUnit;
    }

    public void setDrugsUnit(String drugsUnit) {
        this.drugsUnit = drugsUnit;
    }

    public int getDrugsDosageID() {
        return drugsDosageID;
    }

    public void setDrugsDosageID(int drugsDosageID) {
        this.drugsDosageID = drugsDosageID;
    }

    public int getDrugsTypeID() {
        return drugsTypeID;
    }

    public void setDrugsTypeID(int drugsTypeID) {
        this.drugsTypeID = drugsTypeID;
    }

    public double getDrugsPrice() {
        return drugsPrice;
    }

    public void setDrugsPrice(double drugsPrice) {
        this.drugsPrice = drugsPrice;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSaveRequire() {
        return saveRequire;
    }

    public void setSaveRequire(String saveRequire) {
        this.saveRequire = saveRequire;
    }
}
