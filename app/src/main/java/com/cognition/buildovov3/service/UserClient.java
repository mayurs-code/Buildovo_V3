package com.cognition.buildovov3.service;

import com.cognition.buildovov3.api.model.productEntity.construction.products.Image;
import com.cognition.buildovov3.api.model.productEntity.construction.products.Product;
import com.cognition.buildovov3.api.model.userEntity.UserRequest;
import com.cognition.buildovov3.api.model.userEntity.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {

    @POST("user/login")
    Call<UserResponse> accountLogin(@Body UserRequest request);

    @GET("products")
    Call<ArrayList<Product>> getAllConstructionMaterials();

    @GET("products/id/{id}/images")
    Call<ArrayList<Image>> getAllConstructionMaterialImage(@Path("id") String id);


}
