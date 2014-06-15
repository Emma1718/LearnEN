package com.example.presenter;

import java.sql.SQLException;

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

public class RegisterFormPresenter extends AbstractPresenter<RegisterFormView>{

    private RegisterFormView view;
    private RegisterFormModel model = new RegisterFormModel();;
    private MainPresenter parentPresenter = new MainPresenter();

    public void setParentPresenter(MainPresenter p) {
        this.parentPresenter = p;
    }
    public void confirmRegisterForm() {

        try {
            model.registerUser(view.getName(), view.getSurname(), view.getEmail(),
                    view.getPassword());
            parentPresenter.onRegister();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
