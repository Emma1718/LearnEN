package com.example.presenter;

import com.example.example.ExampleUI;
import com.example.model.RegisterAndLoginModel;
import com.example.model.WordsManagementModel;
import com.example.view.IRegisterFormView;
import com.example.view.LoginView;
import com.example.view.MainView;
import com.example.view.RegisterFormView;
import com.example.view.WordsManagementView;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public class MainPresenter extends AbstractPresenter<MainView> {

    @Override
    public void setView(MainView view) {
        // TODO Auto-generated method stub
        super.setView(view);
        createLoginForm("Aby rozpocząć musisz się zalogować");
    }

    public void onRegister() {
        createLoginForm("Rejestracja przebiegła prawidłowo. Teraz możesz się zalogować");
    }

    public void onLogin(String user) {
        view.changeLoginLayout(false);
        view.setLoggedUser(user);
        view.setChangableLayout(null);
    }

    public void loginClickEvent() {
        createLoginForm("");
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
        if (VaadinSession.getCurrent().getAttribute(ExampleUI.LOGGED_USER) == null) {
            createLoginForm("Aby kontynuować musisz się zalogować");
        }

    }

    public void manageClickEvent() {
        if (VaadinSession.getCurrent().getAttribute(ExampleUI.LOGGED_USER) == null) {
            createLoginForm("Aby kontynuować musisz się zalogować");
        } else {
            WordsManagementView managementView = new WordsManagementView();
            WordsManagementPresenter managementPresenter = new WordsManagementPresenter(
                    new WordsManagementModel(), managementView);
            managementView.setPresenter(managementPresenter);
            view.setChangableLayout(managementView);
        }
    }

    public void logoutClickEvent() {
        VaadinSession.getCurrent().setAttribute(ExampleUI.LOGGED_USER, null);
        view.changeLoginLayout(true);
        view.setLoggedUser("");
        createLoginForm("Zostałeś wylogowany. Możesz zalogować się ponownie");
    }

    private void createLoginForm(String caption) {
        LoginView loginView = new LoginView();
        view.setChangableLayout(loginView);
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.setView(loginView);
        loginPresenter.setParentPresenter(this);
        loginView.setPresenter(loginPresenter);
        loginView.setRegLabel(caption);

    }

}
