package com.example.presenter;

import com.example.view.AbstractView;
import com.example.view.LoginView;

public class LoginPresenter  extends AbstractPresenter<LoginView>{
    private MainPresenter parentPresenter = new MainPresenter();

    public void setParentPresenter(MainPresenter p) {
        this.parentPresenter = p;
    }
    
}
