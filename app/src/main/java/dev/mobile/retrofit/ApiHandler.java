package dev.mobile.retrofit;

import java.util.List;
import retrofit.Call;
import retrofit.http. Field;
import retrofit.http. FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
public interface ApiHandler
{
    @GET("WebServices/list.php")
    Call<List<User>> getAllUsers();

    @FormUrlEncoded
    @POST("webServices/insert.php")
    Call<User> insertUser(@Field("name") String name,
                          @Field("username") String username,
                          @Field("password") String password,
                          @Field("email") String email);

    @FormUrlEncoded
    @POST("webServices/update.php")
    Call<User> updatetUser(@Field("id") int id,
                           @Field("name") String name,
                           @Field("username") String username,
                           @Field("password") String password,
                           @Field("email") String email);
    @FormUrlEncoded
    @POST("webServices/delete.php")
    Call <User> deleteUser(@Field("id") int id);
}