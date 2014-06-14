package com.example.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.example.view.LeftPanel;
import com.example.view.LoginView;
import com.example.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("example")
public class ExampleUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ExampleUI.class)
    public static class Servlet extends VaadinServlet {

    }

    @Override
    protected void init(VaadinRequest request) {
        MainView mainView = new MainView();
        setContent(mainView);
        // RegisterFormView view = new RegisterFormView();
        // RegisterFormModel model = new RegisterFormModel();
        // GeneralPresenter presenter = new GeneralPresenter();
        // presenter.setView(view);
        // view.setPresenter(presenter);
        // view.initView();
        // setContent(vl);
        // vl.addComponent(view);
        // vl.setComponentAlignment(view, Alignment.MIDDLE_CENTER);

        String driver = "org.sqlite.JDBC";
        String dbUrl = "jdbc:sqlite:C:\\Users\\Paulina\\Dropbox\\Projekt\\DATABASE\\DB_LEARNEN.db";
        Connection conn;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Notification.show("JDBC Driver Not Found");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            Notification.show("Problem with connection to database!");
            e.printStackTrace();
            System.exit(-1);
        }

    }
}