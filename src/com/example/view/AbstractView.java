package com.example.view;

import com.example.presenter.GeneralPresenter;
import com.vaadin.ui.CustomComponent;

public abstract class AbstractView extends CustomComponent{
    protected GeneralPresenter presenter;
    
    public void setPresenter(GeneralPresenter gp) {
        presenter = gp;
    }
    
    public AbstractView() {
        initFields();
        initView();
    }
    protected abstract void initFields();
    protected abstract void initView();

}
