package com.es.netschool24.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Options {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("logo_icon")
    @Expose
    private String logoIcon;
    @SerializedName("header_contact")
    @Expose
    private String headerContact;
    @SerializedName("header_slogan")
    @Expose
    private String headerSlogan;
    @SerializedName("footer_google_store_link")
    @Expose
    private String footerGoogleStoreLink;
    @SerializedName("footer_number")
    @Expose
    private String footerNumber;
    @SerializedName("footer_email")
    @Expose
    private String footerEmail;
    @SerializedName("footer_site_link")
    @Expose
    private String footerSiteLink;
    @SerializedName("footer_copy")
    @Expose
    private String footerCopy;
    @SerializedName("home_app_title")
    @Expose
    private String homeAppTitle;
    @SerializedName("home_app_sub_title")
    @Expose
    private String homeAppSubTitle;
    @SerializedName("home_app_description")
    @Expose
    private Object homeAppDescription;
    @SerializedName("faq_title")
    @Expose
    private String faqTitle;
    @SerializedName("faq_subtitle")
    @Expose
    private String faqSubtitle;
    @SerializedName("course_title")
    @Expose
    private String courseTitle;
    @SerializedName("course_subtitle")
    @Expose
    private String courseSubtitle;
    @SerializedName("student_title")
    @Expose
    private String studentTitle;
    @SerializedName("student_subtitle")
    @Expose
    private String studentSubtitle;
    @SerializedName("teacher_title")
    @Expose
    private String teacherTitle;
    @SerializedName("teacher_subtitle")
    @Expose
    private String teacherSubtitle;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoIcon() {
        return logoIcon;
    }

    public void setLogoIcon(String logoIcon) {
        this.logoIcon = logoIcon;
    }

    public String getHeaderContact() {
        return headerContact;
    }

    public void setHeaderContact(String headerContact) {
        this.headerContact = headerContact;
    }

    public String getHeaderSlogan() {
        return headerSlogan;
    }

    public void setHeaderSlogan(String headerSlogan) {
        this.headerSlogan = headerSlogan;
    }

    public String getFooterGoogleStoreLink() {
        return footerGoogleStoreLink;
    }

    public void setFooterGoogleStoreLink(String footerGoogleStoreLink) {
        this.footerGoogleStoreLink = footerGoogleStoreLink;
    }

    public String getFooterNumber() {
        return footerNumber;
    }

    public void setFooterNumber(String footerNumber) {
        this.footerNumber = footerNumber;
    }

    public String getFooterEmail() {
        return footerEmail;
    }

    public void setFooterEmail(String footerEmail) {
        this.footerEmail = footerEmail;
    }

    public String getFooterSiteLink() {
        return footerSiteLink;
    }

    public void setFooterSiteLink(String footerSiteLink) {
        this.footerSiteLink = footerSiteLink;
    }

    public String getFooterCopy() {
        return footerCopy;
    }

    public void setFooterCopy(String footerCopy) {
        this.footerCopy = footerCopy;
    }

    public String getHomeAppTitle() {
        return homeAppTitle;
    }

    public void setHomeAppTitle(String homeAppTitle) {
        this.homeAppTitle = homeAppTitle;
    }

    public String getHomeAppSubTitle() {
        return homeAppSubTitle;
    }

    public void setHomeAppSubTitle(String homeAppSubTitle) {
        this.homeAppSubTitle = homeAppSubTitle;
    }

    public Object getHomeAppDescription() {
        return homeAppDescription;
    }

    public void setHomeAppDescription(Object homeAppDescription) {
        this.homeAppDescription = homeAppDescription;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqSubtitle() {
        return faqSubtitle;
    }

    public void setFaqSubtitle(String faqSubtitle) {
        this.faqSubtitle = faqSubtitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseSubtitle() {
        return courseSubtitle;
    }

    public void setCourseSubtitle(String courseSubtitle) {
        this.courseSubtitle = courseSubtitle;
    }

    public String getStudentTitle() {
        return studentTitle;
    }

    public void setStudentTitle(String studentTitle) {
        this.studentTitle = studentTitle;
    }

    public String getStudentSubtitle() {
        return studentSubtitle;
    }

    public void setStudentSubtitle(String studentSubtitle) {
        this.studentSubtitle = studentSubtitle;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherSubtitle() {
        return teacherSubtitle;
    }

    public void setTeacherSubtitle(String teacherSubtitle) {
        this.teacherSubtitle = teacherSubtitle;
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