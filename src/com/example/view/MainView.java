package com.example.view;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MainView extends AbstractView {

    private LeftPanel leftPanel;
    private HorizontalLayout mainLayout;
    @Override
    protected void initFields() {
        leftPanel = new LeftPanel();
        mainLayout = new HorizontalLayout();
    }

    @Override
    protected void initView() {
        setCompositionRoot(mainLayout);
        mainLayout.setWidth("100%");
        mainLayout.setWidth("100%");
        leftPanel.setWidth("20%");
        leftPanel.setHeight("100%");
        mainLayout.addComponent(leftPanel);
    }

}
