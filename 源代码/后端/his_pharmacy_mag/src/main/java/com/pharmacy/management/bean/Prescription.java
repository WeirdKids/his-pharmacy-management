package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity // @Entity 表示这是一个实体类
@Table(name = "prescriptions") // @Table(name=“drugs”) 表示对应的表名是 drugs
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "prescriptioncode")
    int prescriptionCode;

    @Column(name = "doctorid")
    int doctorID;

    @Column(name = "chargetime")
    Date chargeTime;

    @Column(name = "charger")
    String charger;


    @Column(name="statue")
    String statue;

    @Column(name = "stage")
    String stage;

    @Column(name = "num")
    int num;

   @Column(name = "drugname")
    String drugName;

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public int getDoctorID(){return doctorID;}
    public void setDoctorID(int DoctorID){this.doctorID=DoctorID;}

    public int getPrescriptionCode(){return prescriptionCode;}
    public void setPrescriptionCode(int prescriptionCode){this.prescriptionCode=prescriptionCode;}

    public Date getChargeTime(){return chargeTime;}
    public void setChargeTime(Date chargeTime){this.chargeTime=chargeTime;}

    public String getCharger(){return charger;}
    public void setCharger(String charger){this.charger=charger;}


    public String getStatue(){return statue;}
    public void setStatue(String statue){this.statue=statue;}

    public String getStage(){return stage;}
    public void setStage(String stage){this.stage=stage;}

    public int getNum(){return num;}
    public void setNum(int num){this.num=num;}

    public String getDrug(){return drugName;}
    public void setDrug(String drugName){this.drugName=drugName;}
}
