package com.es.netschool24.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AllCourseParent {

    @SerializedName("data")
    @Expose
    private List<AllCourse> data = null;

    public List<AllCourse> getCourse() {
        return data;
    }

    public void setData(List<AllCourse> data) {
        this.data = data;
    }
}
