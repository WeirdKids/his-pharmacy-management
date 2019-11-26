package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author 徐奥飞
 * date 2019-11-12 7:50
 */
@Entity // @Entity 表示这是一个实体类
@Table(name = "warehouse") // @Table(name=“warehouse”) 表示对应的表名是 warehouse
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Warehouse {
    @Id
    // @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
    // IDENTITY：采用数据库ID自增长的方式来自增主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    int num;

    String warehouse;

    @Column(name = "mnemoniccode")
    String mnemonicCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }
}
