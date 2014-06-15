package com.example.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.presenter.MainPresenter;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class MainView extends AbstractView<MainPresenter> {

    private HorizontalSplitPanel splitPanel;// = new HorizontalSplitPanel();
    private VerticalLayout mainView;// = new VerticalLayout();
    private LeftPanel leftPanel;
    private VerticalLayout changableView;

    // private HorizontalSplitPanel splitPanel;
    @Override
    protected void initFields() {
        splitPanel = new HorizontalSplitPanel();
        mainView = new VerticalLayout();
        splitPanel = new HorizontalSplitPanel();
        leftPanel = new LeftPanel();
        changableView = new VerticalLayout();

    }
    
    public void setLoggedUser(String user) {
        leftPanel.setLoggedAs(user);
    }

    @Override
    protected void initView() {
        setCompositionRoot(mainView);
        mainView.setSizeFull();
        splitPanel.setSplitPosition(this.getWidth() / 5);
        splitPanel.setSizeFull();
        splitPanel.setLocked(true);

        changableView.setSizeFull();

        mainView.addComponent(splitPanel);
        splitPanel.setSizeFull();

        splitPanel.addComponent(leftPanel);
        splitPanel.addComponent(changableView);
        leftPanel.setSizeFull();

        mainView.setExpandRatio(splitPanel, 1);

    }

    @Override
    public void setPresenter(MainPresenter gp) {
        super.setPresenter(gp);

        leftPanel.setPresenter(gp);

        leftPanel.addListeners();
    }

    public void setChangableLayout(AbstractView view) {
        changableView.removeAllComponents();
        if(view != null) 
            changableView.addComponent(view);
    }
    
    public void changeLoginLayout(boolean b) {
        leftPanel.changeLoginLayout(b);
    }

}
