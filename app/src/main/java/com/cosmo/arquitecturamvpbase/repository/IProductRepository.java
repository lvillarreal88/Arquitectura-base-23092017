package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

/**
 * Created by leonardo on 23/09/2017.
 */

public interface IProductRepository {

    ArrayList<Product> getProductList();

    Product onRegisterProduct(Product p);

    DeleteResponse deleteProduct(String id);
}
