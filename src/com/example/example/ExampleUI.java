package com.example.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.example.presenter.MainPresenter;
import com.example.view.LeftPanel;
import com.example.view.LoginView;
import com.example.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("example")
public class ExampleUI extends UI {
    private HorizontalLayout title = new HorizontalLayout();
    private VerticalLayout root = new VerticalLayout();
    public static String DB_URL = "jdbc:sqlite:C:\\Users\\Paulina\\workspace\\Example2\\test.sqlite";
    public static String LOGGED_USER = "logged_user";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ExampleUI.class)
    public static class Servlet extends VaadinServlet {

    }

    @Override
    protected void init(VaadinRequest request) {
        MainView mainView = new MainView();
        MainPresenter mainPresenter = new MainPresenter();
        mainView.setPresenter(mainPresenter);
        mainPresenter.setView(mainView);
        root.setSizeFull();
        Label titleLabel = new Label(
                "<font color = 'white'>Learn</font><font color = 'red'>EN</font><font color = 'white'>™</font>",
                ContentMode.HTML);
        
        titleLabel.setStyleName("title");
        titleLabel.setHeight("50px");

        title.addComponent(titleLabel);

        title.setWidth("100%");
        title.setHeight("50px");
  
        root.addComponent(title);

        mainView.setSizeFull();
        root.addComponent(mainView);
        root.setExpandRatio(mainView, 1);
        HorizontalLayout foot = new HorizontalLayout();
        foot.setStyleName("topbar");

        foot.setWidth("100%");
         foot.setHeight("50px");
        root.addComponent(foot);
        setContent(root);

        

    }
}