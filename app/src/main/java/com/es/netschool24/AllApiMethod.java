package com.es.netschool24;

import com.es.netschool24.Models.CourseName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllApiMethod {

    @GET("")
    Call<List<CourseName>> getAllCourse();

}
