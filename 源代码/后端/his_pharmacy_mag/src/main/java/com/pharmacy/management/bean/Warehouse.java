package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author 徐奥飞
 * date 2019-11-12 7:50
 */
@Entity // @Entity 表示这是一个实体类
@Table(name = "warehouse") // @Table(name=“warehouse”) 表示对应的表名是 warehouse
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Warehouse implements Serializable {
    @Id
    // @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
    // IDENTITY：采用数据库ID自增长的方式来自增主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotEmpty(message = "当前存放地点的药品数量不能为空")
    @Column(name = "num", nullable = false, length = 5)
    private int num;

    @NotEmpty(message = "药品存放地点不能为空")
    @Column(name = "warehouse", nullable = false)
    private String warehouse;

    // 可选属性 optional = false，表示 drug 不能为空。删除存放地点，不影响药品
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "drug_id") // 设置在 warehouse 表中的关联字段(外键)
    private Drug drug;  // 所属药品

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Warehouse() {
        super();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    // @JsonBackReference 标注的属性在序列化时，会被忽略（即结果中的json数据不包含该属性的内容）
    @JsonBackReference
    public Drug getDrug() {
        return drug;
    }

    @JsonBackReference
    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
