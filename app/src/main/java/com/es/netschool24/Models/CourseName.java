package com.es.netschool24.Models;

public class CourseName {
    private String courseName, imgUrl;

    public CourseName(String courseName, String imgUrl) {
        this.courseName = courseName;
        this.imgUrl = imgUrl;
    }

    public CourseName() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
