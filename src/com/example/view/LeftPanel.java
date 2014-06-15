package com.example.view;

import org.eclipse.jdt.internal.core.SetContainerOperation;

import com.example.presenter.MainPresenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;

public class LeftPanel extends AbstractView<MainPresenter> {

    private VerticalLayout mainView;
    private Button learnBtn;
    private Button managementBtn;
    private Button logoutBtn;
    private Button loginBtn;
    private Button registerBtn;

    private Label loggedAs;

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
        loggedAs = new Label("");
    }

    public void setLoggedAs(String userName) {
        loggedAs.setValue("Zalogowany jako: " + userName);
    }
    protected void initView() {
        initFields();
        VerticalLayout buttonsLayout = new VerticalLayout();
        setStyleName("main-view");

        setCompositionRoot(mainView);
      
        buttonsLayout.addComponent(learnBtn);
        buttonsLayout.addComponent(managementBtn);
        buttonsLayout.addComponent(registerBtn);
        buttonsLayout.addComponent(loginBtn);

        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);
        for (int i = 0; i < buttonsLayout.getComponentCount(); i++) {
            buttonsLayout.getComponent(i).setWidth("85%");
            buttonsLayout.setComponentAlignment(buttonsLayout.getComponent(i),
                    Alignment.MIDDLE_CENTER);
            
        }
        mainView.addComponent(loggedAs);
        mainView.addComponent(buttonsLayout);
        mainView.setExpandRatio(buttonsLayout, 1);
        

    }

    public void addListeners() {
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
