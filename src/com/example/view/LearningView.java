package com.example.view;

import java.util.HashMap;
import java.util.LinkedList;

import com.example.presenter.LearningPresenter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.example.ExampleUI;
import com.vaadin.ui.Notification;

public class LearningView extends AbstractView<LearningPresenter> {
	
	private VerticalLayout vl;
	private HorizontalLayout hl;
	private Label list;
	private ComboBox listCB;
	private Button submit;
	private Label pl;
	private TextField eng;
	private Button nextBtn;
	private int i=1;
	private int listID;
	private Label result;
	private boolean ifAll;
	
	LearningPresenter learningPresenter = new LearningPresenter();
	
	private HashMap<String, String>  words;
	private LinkedList<String> wordList = new LinkedList<String>();
	private HashMap<String, Integer>  lists = new HashMap<String, Integer>();
	private LinkedList<String> listsList = new LinkedList<String>();
	
    Connection connection;

    public LearningView() {
    	try {
            connection = DriverManager.getConnection(ExampleUI.DB_URL);
        } catch (SQLException e) {
            Notification.show("Problem with connection to database!");
            e.printStackTrace();
            System.exit(-1);
        }
		loadLists();
    }
    
    public boolean loadTest() {
    	words= new HashMap<String, String>();
        try {
			getWords();
			if(wordList.size()>0) {
				pl.setValue(wordList.get(0));
			} else {
				Notification.show("Brak słów na liście");
				return false;
			}
			return true;
		} catch (SQLException e) {
			Notification.show("Błąd wczytania słów z listy");
		}
        return true;
    }
    
    public void getWords() throws SQLException {
    	PreparedStatement pstmt;
    	if (ifAll) {
    		pstmt = connection
	                .prepareStatement("select ang,pol  from TAB_WORDS");
    	} else { 
	        pstmt = connection
	                .prepareStatement("select ang,pol  from TAB_WORDS where LIST_ID = ? ");
	        pstmt.setInt(1, listID);
    	}
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            words.put(rs.getString("pol"),rs.getString("ang"));
	        wordList.add(rs.getString("pol"));
        }

        learningPresenter.setWords(words, wordList);
        
	
    }

	@Override
	protected void initFields() {
		pl =  new Label("pl");
		eng = new TextField();
		eng.setValue("tumaczenie...");
		nextBtn = new Button("Dalej");
		result = new Label("Wynik: ");
		list = new Label("Lista: ");
		listCB = new ComboBox();
		submit = new Button("Potwierdź");

        pl.setVisible(false);
        eng.setVisible(false);
        nextBtn.setVisible(false);
        result.setVisible(false);
	}

	
	private void loadLists() {
		int x=(Integer) VaadinSession.getCurrent().getAttribute(ExampleUI.LOGGED_USER);
        listCB.addItem("Wszystko");
        listCB.setNullSelectionAllowed(false);
		PreparedStatement pstmt;
		try {
			pstmt = connection
			        .prepareStatement("select ID, NAME from TAB_LISTS where private = \"F\" or USER_ID = ? ");
			pstmt.setInt(1, x);
			//pstmt.setFloat(1, (VaadinSession.getCurrent().getAttribute(ExampleUI.LOGGED_USER).toString()));
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	lists.put(rs.getString("name"), rs.getInt("id"));
	        	listsList.add(rs.getString("name"));
	        	listCB.addItem(rs.getString("name"));
	        }
		} catch (Exception e) {
			Notification.show("Błąd wczytania list");
		}
	}
	
	@Override
	protected void initView() {
		hl = new HorizontalLayout();
		hl.setMargin(true);
		hl.setSpacing(true);
		hl.addComponent(list);
		hl.addComponent(listCB);
		hl.addComponent(submit);
		
		vl = new VerticalLayout();
		vl.setMargin(true);
		vl.setSpacing(true);
		
		submit.addClickListener(new ClickListener() {
			
            @Override
            public void buttonClick(ClickEvent event) {
            	result.setValue("Wynik: ");
            	try {
	            	if (listCB.getValue().toString().equals("Wszystko")){
	                	ifAll=true;
	            		if (loadTest()) {
			                pl.setVisible(true);
			                eng.setVisible(true);
			                nextBtn.setVisible(true);
			                result.setVisible(true);
			                learningPresenter.setActual(0);
		                }
	            	} else if (listsList.size()>0) {
	                	ifAll=false;
	            		listID = lists.get(listCB.getValue().toString());
		                if (loadTest()) {
			                pl.setVisible(true);
			                eng.setVisible(true);
			                nextBtn.setVisible(true);
			                result.setVisible(true);
			                learningPresenter.setActual(0);
		                }
	            	}
            	} catch (Exception e) {
            		Notification.show("Nie wybrano");
            	}
            }
        });
		
		nextBtn.addClickListener(new ClickListener() {
			
            @Override
            public void buttonClick(ClickEvent event) {
            	if (i < wordList.size()) {
            		learningPresenter.next(pl.getValue(), eng.getValue());
	            	pl.setValue(wordList.get(i));
	            	i++;
	        		result.setValue("Wynik: "+learningPresenter.getActual()+"/"+
    						learningPresenter.getAnswers()+"/"+wordList.size());
            	} else {
            		pl.setVisible(false);
                    eng.setVisible(false);
            		nextBtn.setVisible(false);
            		result.setValue("KONIEC. Twój wynik to: "+learningPresenter.getActual()+"/"+
            						learningPresenter.getAnswers()+"/"+wordList.size());
            	}

            }
        });
		
		setCompositionRoot(vl);
		vl.addComponent(hl);
		vl.addComponent(pl);
		vl.addComponent(eng);
		vl.addComponent(nextBtn);
		vl.addComponent(result);
	}
	
	public String getEngLbl() {
		return pl.getValue();
	}

	public void setEngLbl(String eng) {
		this.pl.setValue(eng);
	}
	
	public String getPlTF() {
		return eng.getValue();
	}

	public void setPlTxt(String pl) {
		this.pl.setValue(pl);
	}

}
