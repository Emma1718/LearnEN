package com.example.view;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LeftPanel extends AbstractView {

    private VerticalLayout mainView;
    private Button learnBtn;
    private Button managementBtn;
    private Button logoutBtn;
    private Button loginBtn;
    private Button registerBtn;

    private Label title;

    public LeftPanel() {
        initView();
    }

    protected void initFields() {
        mainView = new VerticalLayout();
        learnBtn = new Button("Ucz się");
        managementBtn = new Button("Zarządzaj słówkami");
        loginBtn = new Button("Zaloguj się");
        registerBtn = new Button("Zarejestruj się");
        logoutBtn = new Button("Wyloguj");
        title = new Label("LearnEN");
    }

    protected void initView() {
        initFields();
        VerticalLayout buttonsLayout = new VerticalLayout();
        setStyleName("main-view");

        title.setStyleName("title");
        setCompositionRoot(mainView);
        mainView.addComponent(title);
        mainView.setComponentAlignment(title, Alignment.TOP_CENTER);
        title.setWidth("100%");
        buttonsLayout.addComponent(learnBtn);
        buttonsLayout.addComponent(managementBtn);
        // buttonsLayout.addComponent(logoutBtn);
        buttonsLayout.addComponent(registerBtn);
        buttonsLayout.addComponent(loginBtn);

        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);
        for (int i = 0; i < buttonsLayout.getComponentCount(); i++) {
            buttonsLayout.getComponent(i).setWidth("70%");
        }
        mainView.setHeight("100%");
        mainView.addComponent(buttonsLayout);
        mainView.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

    }

    private void addListeners() {
        registerBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.registerClickEvent();
            }

        });
        loginBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.loginClickEvent();
            }
        });

        logoutBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.logoutClickEvent();
            }
        });

        learnBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.learnClickEvent();
            }
        });

        managementBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.manageClickEvent();
            }
        });
    }

    public void hideLogin() {
        mainView.removeComponent(registerBtn);
        mainView.removeComponent(loginBtn);
    }

    public void showLogout() {
        mainView.addComponent(logoutBtn);
    }

}
