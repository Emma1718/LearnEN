package com.example.presenter;

import com.example.view.AbstractView;

public abstract class AbstractPresenter<V extends AbstractView> {
    protected V view;
    public void setView(V view) {
        this.view = view;
    }
  
}
