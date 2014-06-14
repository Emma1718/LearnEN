package com.example.view;

import com.vaadin.ui.*;

public class LoginView extends AbstractView {

    private TextField emailTF;
    private PasswordField passwordTF;
    private VerticalLayout vl;
    private Button submit;

    public String getPassword() {
        return passwordTF.getValue();
    }

    public String getLogin() {
        return emailTF.getValue();
    }

    @Override
    protected void initFields() {
        vl = new VerticalLayout();
        emailTF = new TextField("Login");
        passwordTF = new PasswordField("Has�o");
        submit = new Button("Zaloguj");
    }

    @Override
    protected void initView() {

        setCompositionRoot(vl);
        vl.addComponent(emailTF);
        vl.addComponent(passwordTF);
        vl.addComponent(submit);

    }

}
