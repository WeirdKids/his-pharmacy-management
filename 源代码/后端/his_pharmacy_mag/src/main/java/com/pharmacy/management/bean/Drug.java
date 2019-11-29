package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 徐奥飞
 * date 2019-11-8 7:50
 */
@Entity // @Entity 表示这是一个实体类
@Table(name = "drugs") // @Table(name=“drugs”) 表示对应的表名是 drugs
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Drug implements Serializable {

    @Id // 主键
    // @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
    // IDENTITY：采用数据库ID自增长的方式来自增主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotEmpty(message = "药品编码不能为空")
    @Column(name = "drugscode", nullable = false)
    private String drugsCode;

//    @Id
    @NotEmpty(message = "药品名称不能为空")
    @Column(name = "drugsname", nullable = false)
    private String drugsName;

    @NotEmpty(message = "药品规格不能为空")
    @Column(name = "drugsformat", nullable = false)
    private String drugsFormat;

    @NotEmpty(message = "药品单位不能为空")
    @Column(name = "drugsunit", nullable = false)
    private String drugsUnit;

    @NotEmpty(message = "药品剂型不能为空")
    @Column(name = "drugsdosageid", nullable = false, length = 4)
    private int drugsDosageID;

    @NotEmpty(message = "药品类型不能为空")
    @Column(name = "drugstypeid", nullable = false, length = 4)
    private int drugsTypeID;

    @NotEmpty(message = "药品单价不能为")
    @Column(name = "drugsprice", nullable = false, length = 5)
    private double drugsPrice;

    @NotEmpty(message = "药品助记码不能为空")
    @Column(name = "mnemoniccode", nullable = false)
    private String mnemonicCode;

    @NotEmpty(message = "药品总数量不能为空")
    @Column(name = "totalnum", nullable = false, length = 5)
    private int totalNum;

    @NotEmpty(message = "药品保存条件不能为空")
    @Column(name = "saverequire", nullable = false)
    private String saveRequire;

    // 级联保存、更新、删除、刷新；延迟加载。
    // 当删除药品，会级联删除该药品的所有存放地点信息
    // 拥有 mappedBy 注解的实体类为关系被维护端
    // mappedBy = "drug" 中的 drug 是 Warehouse 中的 drug 属性
    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Warehouse> warehouses = new ArrayList<>(); // 存放地点列表

    public Drug() {
        super();
    }

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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getSaveRequire() {
        return saveRequire;
    }

    public void setSaveRequire(String saveRequire) {
        this.saveRequire = saveRequire;
    }

    // @JsonManagedReference 标注的属性在序列化时，会被序列化
    // 但在反序列化时，如果没有该 @JsonManagedReference，则不会自动注入 @JsonBackReference 标注的属性
    @JsonManagedReference
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    @JsonManagedReference
    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
