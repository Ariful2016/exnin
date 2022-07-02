package com.es.netschool24;

import com.es.netschool24.Models.AboutUs;
import com.es.netschool24.Models.AllCourseParent;
import com.es.netschool24.Models.Banners;
import com.es.netschool24.Models.CartCourseMain;
import com.es.netschool24.Models.CourseDay;
import com.es.netschool24.Models.LoginResponse;
import com.es.netschool24.Models.Options;
import com.es.netschool24.Models.PrivacyPolicy;
import com.es.netschool24.Models.SocialLink;
import com.es.netschool24.Models.TeachersEducation;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyApi {

    @GET ("course")
    Call<AllCourseParent> getAllCourse();

    @GET ("banners")
    Call<List<Banners>> getBanners();

    @GET ("about-us")
    Call<List<AboutUs>> getAboutUs();

    @GET ("teacher-education")
    Call<List<TeachersEducation>> getTeachersEducation();

    @GET ("privacy-policy")
    Call<List<PrivacyPolicy>> getPrivacyPolicy();

    @GET ("options")
    Call<List<Options>> getOptions();

    @GET ("social-icon")
    Call<List<SocialLink>> getSocialLink();

    @GET ("course-day")
    Call<List<CourseDay>> getCourseDay();

    @GET ("carts")
    Call<CartCourseMain> getCart();

   // @Header ("Authorization") String token,
    @FormUrlEncoded
    @POST ("enroll")
    Call<ResponseBody> courseEnroll(
            @Header ("Authorization") String token,
            @Field ("studentDay") String studentDay,
            @Field ("studenttime") String studenttime,
            @Field ("user_id") String user_id,
            @Field ("course_id") String course_id
    );

    @FormUrlEncoded
    @POST ("student/registration")
    Call<ResponseBody> studentRegister(
            @Field ("name") String name,
            @Field ("fathername") String fathername,
            @Field ("email") String email,
            @Field ("password") String password,
            @Field ("birthday") String birthday,
            @Field ("mobile") String mobile,
            @Field ("nationality") String nationality,
            @Field ("gender") String gender,
            @Field ("address") String address,
            @Field ("guardianname") String guardianname,
            @Field ("gnumber") String gnumber

    );


    @FormUrlEncoded
    @POST ("teacher/registration")
    Call<ResponseBody> tRegister(
            @Field ("name") String name,
            @Field ("email") String email,
            @Field ("password") String password,
            @Field ("birthday") String birthday,
            @Field ("mobile") String mobile,
            @Field ("address") String address,
            @Field ("national") String national,
            @Field ("education") String education,
            @Field ("father_name") String father_name,
            @Field ("gender") String gender,
            @Field ("parmanet_address") String parmanet_address,
            @Field ("courses") String courses,
            @Field ("university") String university,
            @Field ("nid") String nid,
            @Field ("photo") String photo,
            @Field ("certificate") String certificate

    );


    @FormUrlEncoded
    @POST ("login")
    Call<LoginResponse> userLogin(
            @Field ("email") String email,
            @Field ("password") String password
    );


    @FormUrlEncoded
    @POST ("contact-us")
    Call<ResponseBody> sendMessage(
            @Field ("name") String name,
            @Field ("mobile") String mobile,
            @Field ("email") String email,
            @Field ("message") String message
    );

    @FormUrlEncoded
    @POST ("free-learning")
    Call<ResponseBody> getJoinFree(
            @Field ("name") String name,
            @Field ("email") String email,
            @Field ("mobile") String mobile,
            @Field ("address") String address,
            @Field ("course_id") int course_id
    );


}
