package com.example.view;

import java.util.ArrayList;
import java.util.List;

import com.example.presenter.MainPresenter;
import com.example.presenter.RegisterFormPresenter;
import com.vaadin.data.Validatable;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

public class RegisterFormView extends AbstractView<RegisterFormPresenter> {
    private VerticalLayout vl;

    private TextField nameTF;
    private TextField surnameTF;
    private TextField emailTF;
    private PasswordField passwordTF;
    private PasswordField confirmpassTF;
    private Button submit;
    private List<Validatable> fieldList;

    @Override
    protected void initFields() {
        nameTF = new TextField("Imię");
        surnameTF = new TextField("Nazwisko");
        emailTF = new TextField("E-mail");
        passwordTF = new PasswordField("Hasło");
        confirmpassTF = new PasswordField("Potwierdź Hasło");
        submit = new Button("Potwierdź");
        submit.setClickShortcut(KeyCode.ENTER);
    }

    @Override
    public void initView() {
        vl = new VerticalLayout();
        vl.setMargin(true);
        vl.setSpacing(true);

        submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                presenter.confirmRegisterForm();
            }
        });

        setCompositionRoot(vl);
        vl.addComponent(nameTF);
        vl.addComponent(surnameTF);
        vl.addComponent(emailTF);
        vl.addComponent(passwordTF);
        vl.addComponent(confirmpassTF);
        vl.addComponent(submit);

        nameTF.setRequired(true);
        surnameTF.setRequired(true);
        emailTF.setRequired(true);
        passwordTF.setRequired(true);
        confirmpassTF.setRequired(true);
        nameTF.addValidator(new RegexpValidator(
                "[a-zA-ZŻŹĄĘŚĆÓŃŁęółąćźżń]{3,30}", "Nieprawidłowe imię"));
        surnameTF.addValidator(new RegexpValidator(
                "[a-zA-ZŻŹĄĘŚĆÓŃŁęółąćźżń]{3,30}",
                "Nieprawidłowe nazwisko"));
        emailTF.addValidator(new EmailValidator("Niepoprawny e-mail"));

        fieldList = new ArrayList<Validatable>();
        fieldList.add(nameTF);
        fieldList.add(surnameTF);
        fieldList.add(emailTF);
    }

    
    public boolean isValid() {
        for (Validatable c : fieldList) {
            if (!c.isValid()) {
                return false;
            }
        }
        return true;
    }

    
    public String getName() {
        // TODO Auto-generated method stub
        return nameTF.getValue();
    }

    
    public String getSurname() {
        // TODO Auto-generated method stub
        return surnameTF.getValue();
    }

    
    public String getEmail() {
        return emailTF.getValue();
    }

    
    public String getPassword() {
        return passwordTF.getValue();
    }

    
    public String getConfirmPassword() {
        return confirmpassTF.getValue();
    }

    private void onSubmitRegisterForm() {

    }

}
