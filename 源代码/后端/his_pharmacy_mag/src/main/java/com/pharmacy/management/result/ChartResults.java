package com.pharmacy.management.result;

import java.util.List;

public class ChartResults {
    private List<ChartResult> result1;
    private List<ChartResult> result2;

    public ChartResults (List<ChartResult> result1, List<ChartResult> result2) {
        this.result1 = result1;
        this.result2 = result2;
    }

    public List<ChartResult> getResult1() {
        return result1;
    }

    public void setResult1(List<ChartResult> result1) {
        this.result1 = result1;
    }

    public List<ChartResult> getResult2() {
        return result2;
    }

    public void setResult2(List<ChartResult> result2) {
        this.result2 = result2;
    }
}
