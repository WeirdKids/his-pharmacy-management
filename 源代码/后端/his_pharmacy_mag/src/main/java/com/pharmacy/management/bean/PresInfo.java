package com.pharmacy.management.bean;

import java.util.Date;
import java.util.List;
import java.lang.String;

public class PresInfo {
    private int [] presIds;
    private int [] prescriptionCodes;
    private int [] doctorIDs;
    private List<String> chargers;
    private List<Date> chargeTimes;
    private List<String> statues;
    private int [] totalStages;
    private int [] currentStages;
    private int [] nums;
    private int [] sentNums;
    private List<String> drugNames;
    private int [] drugIds;

    public int[] getPresIds(){
        return presIds;
    }
    public void setPresIds(int[] presIds) {
        this.presIds = presIds;
    }

    public int[] getPrescriptionCodes() {
        return prescriptionCodes;
    }
    public void setPrescriptionCodes(int[] prescriptionCodes) {
        this.prescriptionCodes = prescriptionCodes;
    }

    public int[] getDoctorIDs() {
        return doctorIDs;
    }
    public void setDoctorIDs(int[] doctorIDs) {
        this.doctorIDs = doctorIDs;
    }

    public List<String> getChargers(){return chargers;}
    public void setChargers(List<String> chargers){this.chargers=chargers;}

    public List<Date> getChargeTimes(){return chargeTimes;}
    public void setChargeTimes(List<Date> chargeTimes){this.chargeTimes=chargeTimes;}

    public List<String> getStatues(){return statues;}
    public void setStatues(List<String> statues){this.statues=statues;}

    public int[] getTotalStages() {
        return totalStages;
    }
    public void setTotalStages(int[] totalStages) {
        this.totalStages = totalStages;
    }

    public int[] getCurrentStages() {
        return currentStages;
    }
    public void setCurrentStages(int[] currentStages) {
        this.currentStages = currentStages;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }
    public int[] getNums() {
        return nums;
    }

    public int[] getSentNums() {
        return sentNums;
    }
    public void setSentNums(int[] sentNums) {
        this.sentNums = sentNums;
    }

    public List<String> getDrugNames(){return drugNames;}
    public void setDrugNames(List<String> drugNames){this.drugNames=drugNames;}

    public int[] getDrugIds() {
        return drugIds;
    }
    public void setDrugIds(int[] drugIds) {
        this.drugIds = drugIds;
    }
}
