package com.pharmacy.management.bean;

public class PresInfo {
    private int [] presId;
    private int [] num;
    private int [] drugId;

    public int[] getDrugId() {
        return drugId;
    }

    public void setDrugId(int[] drugId) {
        this.drugId = drugId;
    }

    public int[] getPresId(){
        return presId;
    }

    public void setPresId(int[] presId) {
        this.presId = presId;
    }

    public void setNum(int[] num) {
        this.num = num;
    }

    public int[] getNum() {
        return num;
    }
}
