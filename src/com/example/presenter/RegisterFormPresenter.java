package com.example.presenter;

import java.sql.SQLException;

import com.example.example.ExampleUI;
import com.example.model.RegisterAndLoginModel;
import com.example.view.LoginView;
import com.example.view.MainView;
import com.example.view.RegisterFormView;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public class RegisterFormPresenter extends AbstractPresenter<RegisterFormView> {

    private RegisterAndLoginModel model = new RegisterAndLoginModel();
    private MainPresenter parentPresenter;

    public void setParentPresenter(MainPresenter p) {
        this.parentPresenter = p;
    }
    
  
    public void confirmRegisterForm() {
        if (view.isValid()) {
            if (view.getPassword().equals(view.getConfirmPassword())) {
                try {
                    if(model.registerUser(view.getName(), view.getSurname(), view.getEmail(), view.getPassword())) {
                        parentPresenter.onRegister();
                    } else {
                        Notification.show("Użytkownik o podanym e-mailu istnieje!",
                                Type.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Notification.show("Podane hasła nie są identyczne",
                        Type.ERROR_MESSAGE);
            }
        } else {
            Notification.show("Niepoprawne dane", Type.ERROR_MESSAGE);
        }

    }
}
