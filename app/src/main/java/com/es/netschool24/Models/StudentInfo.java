package com.es.netschool24.Models;

import android.widget.ImageView;

public class StudentInfo {

  private String fullName,fatherName,motherName,dateOfBirth,whatsApp,email,nationality,address,nameOfCourse,day,time,guardianName,guardianPhone,
          relationWithGuardian,password,studentUserId;

    public StudentInfo(String fullName, String fatherName, String motherName, String dateOfBirth, String whatsApp, String email, String nationality, String address, String nameOfCourse, String day, String time, String guardianName, String guardianPhone, String relationWithGuardian, String password, String studentUserId) {
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.dateOfBirth = dateOfBirth;
        this.whatsApp = whatsApp;
        this.email = email;
        this.nationality = nationality;
        this.address = address;
        this.nameOfCourse = nameOfCourse;
        this.day = day;
        this.time = time;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.relationWithGuardian = relationWithGuardian;
        this.password = password;
        this.studentUserId = studentUserId;
    }

    public StudentInfo() {
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

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getRelationWithGuardian() {
        return relationWithGuardian;
    }

    public void setRelationWithGuardian(String relationWithGuardian) {
        this.relationWithGuardian = relationWithGuardian;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }
}
