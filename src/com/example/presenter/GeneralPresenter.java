package com.example.presenter;

import com.example.example.ExampleUI;
import com.example.model.RegisterFormModel;
import com.example.view.IRegisterFormView;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public class GeneralPresenter {

    private IRegisterFormView view;
    private RegisterFormModel model;
    private ExampleUI ui;

    public void confirmRegisterForm() {
        if (view.isValid()) {
            if (view.getPassword().equals(view.getConfirmPassword())) {

            } else {
                Notification.show("Podane hasła nie są identyczne",
                        Type.ERROR_MESSAGE);
            }
        } else {
            Notification.show("Niepoprawne dane", Type.ERROR_MESSAGE);
        }
    }

    public void setView(IRegisterFormView view) {
        this.view = view;

    }

    public void loginClickEvent() {

    }

    public void registerClickEvent() {

    }

    public void learnClickEvent() {

    }

    public void manageClickEvent() {

    }

    public void logoutClickEvent() {
        VaadinSession.getCurrent().setAttribute("userID", null);
    }
}
