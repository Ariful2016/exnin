package com.es.netschool24.Models;

public class TeacherInfo {

   private String fullName,fatherName,dateOfBirth,email,mobile,gender,nameOfCourse,PresentAddress,
            permanentAddress,education,versityName,result,password,nidImg,photo,
            certificate;

    public TeacherInfo(String fullName, String fatherName, String dateOfBirth, String email, String mobile, String gender, String nameOfCourse, String presentAddress, String permanentAddress, String education, String versityName, String result, String password, String nidImg, String photo, String certificate) {
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.nameOfCourse = nameOfCourse;
        PresentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.education = education;
        this.versityName = versityName;
        this.result = result;
        this.password = password;
        this.nidImg = nidImg;
        this.photo = photo;
        this.certificate = certificate;
    }

    public TeacherInfo() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public String getPresentAddress() {
        return PresentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        PresentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getVersityName() {
        return versityName;
    }

    public void setVersityName(String versityName) {
        this.versityName = versityName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNidImg() {
        return nidImg;
    }

    public void setNidImg(String nidImg) {
        this.nidImg = nidImg;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
