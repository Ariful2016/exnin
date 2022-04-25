package com.es.netschool24.Models;

public class Tutionfee {

   private String monthly,weekly,time,fee;

    public Tutionfee(String monthly, String weekly, String time, String fee) {
        this.monthly = monthly;
        this.weekly = weekly;
        this.time = time;
        this.fee = fee;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
