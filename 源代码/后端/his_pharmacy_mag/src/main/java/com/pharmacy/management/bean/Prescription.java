package com.pharmacy.management.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity // @Entity 表示这是一个实体类
@Table(name = "prescriptions")
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

    @Column(name = "totalstage")
    int totalStage;

    @Column(name = "currentstage")
    int currentStage;

    @Column(name = "num")
    int num;

    @Column(name = "sentnum")
    int sentNum;

   @Column(name = "drugname")
    String drugName;

   @Column(name="drugid")
   int drugId;

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

    public int getTotalStage(){return totalStage;}
    public void setTotalStage(int totalStage){this.totalStage=totalStage;}

    public int getCurrentStage(){return currentStage;}
    public void setCurrentStage(int currentStage){this.currentStage=currentStage;}

    public int getNum(){return num;}
    public void setNum(int num){this.num=num;}

    public int getSentNum(){return sentNum;}
    public void setSentNum(int sentNum){this.sentNum=sentNum;}

    public String getDrugName(){return drugName;}
    public void setDrugName(String drugName){this.drugName=drugName;}

    public int getDrugId(){return drugId;}
    public void setDrugId(int drugId){this.drugId=drugId;}
}
