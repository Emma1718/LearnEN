package com.example.presenter;

import com.example.view.AbstractView;

public abstract class AbstractPresenter<V extends AbstractView> {
    protected V view;
    public void setView(V view) {
        System.out.println("Set view");
        this.view = view;
    }
  
}
