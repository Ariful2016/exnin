package com.es.netschool24.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutUs {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("section_title")
    @Expose
    private String sectionTitle;
    @SerializedName("section_description")
    @Expose
    private Object sectionDescription;
    @SerializedName("about_us")
    @Expose
    private String aboutUs;
    @SerializedName("about_banner")
    @Expose
    private String aboutBanner;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public Object getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(Object sectionDescription) {
        this.sectionDescription = sectionDescription;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getAboutBanner() {
        return aboutBanner;
    }

    public void setAboutBanner(String aboutBanner) {
        this.aboutBanner = aboutBanner;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
