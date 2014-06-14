package com.example.view;

public interface IRegisterFormView {
    void initView();
    boolean isValid();
    
    String getName();
    String getSurname();
    String getEmail();
    String getPassword();
    String getConfirmPassword();
}
