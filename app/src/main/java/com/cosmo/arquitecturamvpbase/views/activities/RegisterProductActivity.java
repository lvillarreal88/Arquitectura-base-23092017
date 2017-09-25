package com.cosmo.arquitecturamvpbase.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.ValidateInternet;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.RegisterProductPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

public class RegisterProductActivity extends BaseActivity<RegisterProductPresenter> implements IRegisterProductView, TextWatcher {

    private EditText etxtvNombre;
    private EditText etxtvDescripcion;
    private EditText etxtvCantidad;
    private EditText etxtPrecio;
    private Button btnCrear;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        initRegisterProductActv();
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p=new Product();
                p.setName(etxtvNombre.getText().toString());
                p.setDescription(etxtvDescripcion.getText().toString());
                p.setQuantity(etxtvCantidad.getText().toString());
                p.setPrice(etxtPrecio.getText().toString());
                registerProduct(p);
            }
        });
    }

    public void initRegisterProductActv(){
        setPresenter(new RegisterProductPresenter());
        getPresenter().inject(this, getValidateInternet());
        etxtvNombre= (EditText) findViewById(R.id.register_etext_nombre);
        etxtvDescripcion=(EditText) findViewById(R.id.register_etext_description);
        etxtvCantidad=(EditText)  findViewById(R.id.register_etext_cantidad);
        etxtPrecio=(EditText) findViewById(R.id.register_etext_precio);
        btnCrear=(Button) findViewById(R.id.register_btn_crear);
        btnCrear.setEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String vacio="";
        if(!etxtvNombre.getText().equals(vacio)&&
                !etxtvDescripcion.getText().equals(vacio)&&
                !etxtvCantidad.getText().equals(vacio)&&
                !etxtPrecio.getText().equals(vacio)){
            btnCrear.setEnabled(true);
        }else{
            btnCrear.setEnabled(false);
        }
    }

    @Override
    public void registerProduct(Product product) {
        try{
            getPresenter().onAddProduct(product);
            Toast.makeText(this, "REGISTRO EL PRODUCTO!", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, "ERROR AL REGISTRAR PRODUCTO!", Toast.LENGTH_LONG).show();
        }

    }
}
