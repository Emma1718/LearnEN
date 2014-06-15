package com.example.view;

import java.util.ArrayList;

import com.example.presenter.WordsManagementPresenter;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class WordsManagementView extends AbstractView<WordsManagementPresenter> {

    private Table wordsTable;// = new Table();

    private Button newButton;// = new Button("New word");
    private Button deleteButton;// = new Button("Delete word");
    private Button okButton;// = new Button("Confirm");

    private FormLayout editorLayout;// = new FormLayout();
    private FieldGroup editorFields;// = new FieldGroup();
    private HorizontalLayout bottomLeftLayout;// = new HorizontalLayout();

    private IndexedContainer allWords;// = new IndexedContainer();

    private boolean newRecord;
    private ComboBox box;

    // private ArrayList<ViewInterfaceListener> listeners = new
    // ArrayList<ViewInterfaceListener>();


    public void initLayout() {
        HorizontalSplitPanel hsp = new HorizontalSplitPanel();
        VerticalLayout leftLayout = new VerticalLayout();
        hsp.addComponent(leftLayout);
        hsp.addComponent(editorLayout);
        leftLayout.addComponent(wordsTable);

        leftLayout.addComponent(bottomLeftLayout);
        bottomLeftLayout.addComponent(newButton);

        leftLayout.setSizeFull();
        leftLayout.setExpandRatio(wordsTable, 1);

        editorLayout.setMargin(true);
        editorLayout.setVisible(false);

        setCompositionRoot(hsp);

    }

    public void initEditor(Object[] fieldNames, Container listsNames) {

        for (Object fieldName : fieldNames) {
        	
            TextField field = new TextField(fieldName.toString());
            editorLayout.addComponent(field);
            field.setWidth("80%");
            editorFields.bind(field, fieldName);
        }
        
        box = new ComboBox("Nazwa listy");
        box.setInputPrompt("Nazwa listy");
        box.setTextInputAllowed(true);
        box.setNewItemsAllowed(true);
              
        for (Object itemId: listsNames.getItemIds()){
        	Item item = listsNames.getItem(itemId);
        	box.addItem(item);
        }
          
        editorLayout.addComponent(box);
   

        editorLayout.addComponent(deleteButton);
        editorLayout.addComponent(okButton);
        editorFields.setBuffered(false);
    }
    
  

    public void initButtons() {
        newButton.addClickListener(new ClickListener() {

            public void buttonClick(ClickEvent event) {
                newRecord = true;
                presenter.newButtonClick();
                newButton.setVisible(false);
                wordsTable.select(wordsTable.firstItemId());

            }
        });

        okButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                newButton.setVisible(true);
                System.out.println("new record should be false: " + newRecord);
                Object wordId = wordsTable.getValue();

                presenter.updateButtonClick(
                        wordId,
                        wordsTable.getContainerProperty(wordId, "POL")
                                .getValue().toString(),
                        wordsTable.getContainerProperty(wordId, "ANG")
                                .getValue().toString(),
                        wordsTable
                                .getContainerProperty(wordId, "LIST_NAME")
                                .getValue().toString());
                selectableTable(true);

            }
        });

        deleteButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                Object wordId = wordsTable.getValue();
                presenter.deleteButtonClick(wordId);
                newRecord = false;

            }
        });

    }

    public void updateTabele(IndexedContainer wordsCont, Object[] cols) {
        wordsTable.setContainerDataSource(wordsCont);
        wordsTable.setVisibleColumns(cols);
    }

    public void initTable(IndexedContainer wordsCont, Object[] cols) {
        wordsTable.setContainerDataSource(wordsCont);
        wordsTable.setVisibleColumns(cols);
        wordsTable.setSelectable(true);

        // the value shown in the viewer is updated immediately after editing
        // in the editor (once it loses the focus)

        wordsTable.setSizeFull();
        wordsTable.setImmediate(true);
        wordsTable.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                Object wordId = wordsTable.getValue();
                if (wordId != null) {
                    editorFields.setItemDataSource(wordsTable.getItem(wordId));
                    
                    
                    editorLayout.setVisible(true);
                }

            }
        });
    }


    public void selectableTable(boolean select) {
        wordsTable.setSelectable(select);
    }

    public void unselectTable(Object itemId) {
        wordsTable.unselect(itemId);
    }

    public void editVisibility(boolean visible) {
        editorLayout.setVisible(visible);
    }

    public void setAllWords(IndexedContainer allWords) {
        this.allWords = allWords;
    }

    @Override
    protected void initFields() {

        wordsTable = new Table();
        newButton = new Button("Nowe słowo");
        deleteButton = new Button("Usuń");
        okButton = new Button("Potwierdź");

        editorLayout = new FormLayout();
        editorFields = new FieldGroup();
        bottomLeftLayout = new HorizontalLayout();
        allWords = new IndexedContainer();

    }

    @Override
    protected void initView() {
        initLayout();
        initButtons();
    }

}
