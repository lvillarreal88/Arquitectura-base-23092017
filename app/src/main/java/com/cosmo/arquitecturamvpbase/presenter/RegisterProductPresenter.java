package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.IRegisterProductView;

/**
 * Created by leonardo on 19/09/2017.
 */

public class RegisterProductPresenter extends BasePresenter<IRegisterProductView> {

    private ProductRepository productRepository;

    public RegisterProductPresenter() {
        this.productRepository = new ProductRepository();
    }

    public void onAddProduct(Product p) {


        if (getValidateInternet().isConnected()){

            createThreadProduct(p);

        }else{
            //TODO: implementación alert
        }

    }

    private void createThreadProduct(final Product p) {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                onRegisterProduct(p);
            }
        });
        thread.start();
    }

    public boolean onRegisterProduct(Product p){
        try {

            getView().registerProduct(productRepository.onRegisterProduct(p));
            return true;
        } catch (Exception e){
            return false;
            //TODO: mostrar alert
        }

    }
}
