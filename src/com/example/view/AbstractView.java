package com.example.view;

import com.example.presenter.AbstractPresenter;
import com.example.presenter.MainPresenter;
import com.vaadin.ui.CustomComponent;

public abstract class AbstractView<P extends AbstractPresenter> extends CustomComponent{
    protected P presenter;
    
    public void setPresenter(P p) {
       presenter = p;
    }
    
    public AbstractView() {
        initFields();
        initView();
    }
    protected abstract void initFields();
    protected abstract void initView();

}
