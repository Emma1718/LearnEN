package com.example.presenter;

import java.sql.SQLException;

import com.example.model.RegisterAndLoginModel;
import com.example.view.AbstractView;
import com.example.view.LoginView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class LoginPresenter  extends AbstractPresenter<LoginView>{
    private MainPresenter parentPresenter = new MainPresenter();
    private RegisterAndLoginModel model = new RegisterAndLoginModel();

    public void setParentPresenter(MainPresenter p) {
        this.parentPresenter = p;
    }
    
    public void onLoginClick() {
        try {
            String user = model.loginUser(view.getLogin(), view.getPassword());
            if(!user.equals("")) {
                parentPresenter.onLogin(user);
            } else {
                Notification.show("Niepoprawne hasło lub login!", Type.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
