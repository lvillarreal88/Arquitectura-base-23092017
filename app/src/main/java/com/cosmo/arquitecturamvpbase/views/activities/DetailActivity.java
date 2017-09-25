package com.cosmo.arquitecturamvpbase.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.DetailProductPresenter;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class DetailActivity extends BaseActivity<DetailProductPresenter> implements IDetailProductView {

    private TextView nameValue;
    private TextView descriptionValue;
    private TextView quantityValue;
    private TextView priceValue;
    private Product product;
    private FloatingActionButton fabtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.activity_product_detail);
        setPresenter(new DetailProductPresenter(new ProductRepository()));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        loadView();
        product = (Product) getIntent().getSerializableExtra(Constants.ITEM_PRODUCT);
        setDataItem();

        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().deleteProduct(product.getId());
            }
        });

    }

    private void setDataItem() {
        nameValue.setText(product.getName());
        descriptionValue.setText(product.getDescription());
        quantityValue.setText(product.getQuantity());
        priceValue.setText(product.getPrice());
    }

    private void loadView() {

        nameValue = (TextView) findViewById(R.id.product_detail_name_value);
        descriptionValue = (TextView) findViewById(R.id.product_detail_description_value);
        quantityValue = (TextView) findViewById(R.id.product_detail_quantity_value);
        priceValue = (TextView) findViewById(R.id.product_detail_price_value);
        fabtn = (FloatingActionButton) findViewById(R.id.activityProductDetail_removebtn);

    }

    @Override
    public void showProgress(int message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAlertDialog(int message) {

    }

    @Override
    public void showToast(final int id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DetailActivity.this,R.string.eliminadaEmpanada, Toast.LENGTH_LONG).show();
            }
        });

    }

}
