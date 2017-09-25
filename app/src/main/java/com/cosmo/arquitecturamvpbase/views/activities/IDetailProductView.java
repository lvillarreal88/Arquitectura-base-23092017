package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by leonardo on 23/09/2017.
 */

public interface IDetailProductView extends IBaseView{

    void showAlertDialog(int message);

    void showToast(int id);
}
