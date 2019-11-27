package com.pharmacy.management.bean;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity // @Entity 表示这是一个实体类
@Table(name = "drugs_prescription")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Pres_Drug {
    @Id
    @ManyToMany
    @JoinColumn(name= "id")
    Drug drug1;

    @Id
    @ManyToMany
    @JoinColumn(name = "id")
    Prescription pres1;

    public Pres_Drug(Drug drug,Prescription pres){this.drug1=drug;this.pres1=pres;}

    public Drug getDrug(){return drug1;}
    public void setDrug(Drug drug){this.drug1=drug1;}

    public Prescription getPres(){return pres1;}
    public void setPres(Prescription pres){this.pres1=pres;}
}
