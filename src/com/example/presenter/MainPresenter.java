package com.example.presenter;

import com.example.example.ExampleUI;
import com.example.model.RegisterFormModel;
import com.example.view.IRegisterFormView;
import com.example.view.LoginView;
import com.example.view.MainView;
import com.example.view.RegisterFormView;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public class MainPresenter extends AbstractPresenter {

    private MainView view;

    public void setView(MainView view) {
        this.view = view;

    }

    public void onRegister() {

    }

    public void loginClickEvent() {
        LoginView loginView = new LoginView();
        view.setChangableLayout(loginView);
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.setView(loginView);
        loginPresenter.setParentPresenter(this);
        loginView.setPresenter(loginPresenter);
    }

    public void registerClickEvent() {
        RegisterFormView registerView = new RegisterFormView();
        view.setChangableLayout(registerView);
        RegisterFormPresenter presenter = new RegisterFormPresenter();
        registerView.setPresenter(presenter);
        presenter.setView(registerView);
        presenter.setParentPresenter(this);
    }

    public void learnClickEvent() {

    }

    public void manageClickEvent() {

    }

    public void logoutClickEvent() {
        VaadinSession.getCurrent().setAttribute("userID", null);
    }
}
