package com.cosmo.arquitecturamvpbase;

import com.cosmo.arquitecturamvpbase.helper.IValidateInternet;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.presenter.DetailProductPresenter;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.IDetailProductView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by leonardo on 23/09/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Mock
    IValidateInternet validateInternet;

    @Mock
    IProductRepository productRepository;

    @Mock
    IDetailProductView detailProductView;

    DetailProductPresenter detailProductPresenter;

    @Before
    public void setUp() throws Exception{
        detailProductPresenter = Mockito.spy(new DetailProductPresenter(productRepository));
        detailProductPresenter.inject(detailProductView,validateInternet);
    }

    @Test
    public void methodDeleteProductWithConnectionShouldCallMethodCreateThreadDeleteProduct(){
        String id="452678ie";
        when(validateInternet.isConnected()).thenReturn(true);//garantizar conectividad
        detailProductPresenter.deleteProduct(id);
        verify(detailProductPresenter).createThreadDeleteProduct(id);
        verify(detailProductView, never()).showAlertDialog(R.string.validate_internet);
    }

    @Test
    public void methodDeleteProductWithoutConnectionShouldShowAlertDialog(){
        String id="qw98qiwe";
        when(validateInternet.isConnected()).thenReturn(false);
        detailProductPresenter.deleteProduct(id);
        verify(detailProductView).showAlertDialog(R.string.validate_internet);
        verify(detailProductPresenter, never()).createThreadDeleteProduct(id);
    }

    @Test
    public void methodDeleteProductShouldCallMethodDeleteProductInRepositoryTrue(){
        DeleteResponse drsp= new DeleteResponse();
        drsp.setStatus(true);
        String id = "617238asd";
        when(productRepository.deleteProduct(id)).thenReturn(drsp);
        detailProductPresenter.deleteProductRepository(id);
        Assert.assertTrue(drsp.isStatus());
        verify(detailProductView).showToast(R.string.correct);
        verify(detailProductView, never()).showAlertDialog(R.string.incorrect);
    }

    @Test
    public void methodDeleteProductShouldCallMethodDeleteProductInRepositoryFalse(){
        DeleteResponse drsp= new DeleteResponse();
        drsp.setStatus(false);
        String id = "617238asd";
        when(productRepository.deleteProduct(id)).thenReturn(drsp);
        detailProductPresenter.deleteProductRepository(id);
        Assert.assertFalse(drsp.isStatus());
        verify(detailProductView).showAlertDialog(R.string.incorrect);
        verify(detailProductView, never()).showToast(R.string.correct);
    }


}
