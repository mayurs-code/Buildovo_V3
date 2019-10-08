package com.cognition.buildovov3.service;

import com.cognition.buildovov3.api.model.productEntity.construction.all.AllProducts;
import com.cognition.buildovov3.api.model.productEntity.construction.product.ProductResponse;
import com.cognition.buildovov3.api.model.productEntity.construction.search.SearchedProduct;
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

    @GET("products/products")
    Call<ArrayList<AllProducts>> getAllConstructionMaterials();


    @GET("products/products/name/{name}")
    Call<ArrayList<SearchedProduct>> getSearchedConstructionMaterials(@Path("name") String searchName);

    @GET("products/products/id/{id}")
    Call<ProductResponse> getProductConstructionMaterials(@Path("id") String id);
}
