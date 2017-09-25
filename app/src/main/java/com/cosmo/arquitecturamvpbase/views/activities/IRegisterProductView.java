package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by leonardo on 19/09/2017.
 */

public interface IRegisterProductView extends IBaseView{

    void registerProduct(Product product);

}
