package com.example.view;

import com.example.presenter.LoginPresenter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LoginView extends AbstractView<LoginPresenter> {

    private TextField emailTF;
    private PasswordField passwordTF;
    private VerticalLayout vl;
    private Button submit;
    private Label regLabel;

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
        passwordTF = new PasswordField("Hasło");
        submit = new Button("Zaloguj");
        regLabel = new Label();
        regLabel.setStyleName("register");
        submit.setClickShortcut(KeyCode.ENTER);
        submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.onLoginClick();
            }
        });
    }

    @Override
    protected void initView() {

        setCompositionRoot(vl);
        vl.setMargin(true);
        vl.setSpacing(true);
        vl.addComponent(regLabel);
        vl.addComponent(emailTF);
        vl.addComponent(passwordTF);
        vl.addComponent(submit);

    }

    public void setRegLabel(String val) {
        regLabel.setValue(val);
    }
}
