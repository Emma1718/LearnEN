package com.example.presenter;

import com.example.view.WordsManagementView;

import com.example.model.WordsManagementModel;

public class WordsManagementPresenter extends AbstractPresenter<WordsManagementView>{

    
    private WordsManagementModel model;
    private WordsManagementView view;
   
    public WordsManagementPresenter(WordsManagementModel wmmodel, WordsManagementView wmview){
            this.model = wmmodel;
            this.view = wmview;
            
      //      view.addListener(this);
            
            System.out.println(model.getColList()[0]);
            System.out.println(model.getColList()[1]);
            System.out.println(model.getColList()[2]);
            
            System.out.println(model.getWordsContainer().size());
            
            view.initTable(model.getWordsContainer(),model.getColList());
            view.initEditor(model.getColList(), model.getListsNames());
            view.setAllWords(model.getWordsContainer());
    }

    
    public void deleteButtonClick(Object i) {
            model.deleteRecord(i);
            view.updateTabele(model.getWordsContainer(), model.getColList());
            view.setAllWords(model.getWordsContainer());
            view.selectableTable(true);
            
    }

    public int newButtonClick() {
            int inserts;
            inserts = model.insertRecord();
            System.out.println("Adding new item");
            view.updateTabele(model.getWordsContainer(), model.getColList());
            view.setAllWords(model.getWordsContainer());
            return inserts;
    }

    
    public void updateButtonClick(Object i, String nat, String tans, String l_name) {
            model.updateRecord(i, nat, tans, l_name);
            view.setAllWords(model.getWordsContainer());
            view.editVisibility(false);
            
    }
    
    
}
