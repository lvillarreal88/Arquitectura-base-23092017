package com.cosmo.arquitecturamvpbase.services;

import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IServices {


    @GET("/products")
    ArrayList<Product> getProductList();

    @POST("/products")
    Product onRegisterProduct(@Body Product p);
    @DELETE("/products/{id}")
    DeleteResponse onDelete(@Path("id") String id);
}
