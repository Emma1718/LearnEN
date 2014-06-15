package com.example.presenter;

import java.util.HashMap;
import java.util.LinkedList;

import com.example.view.LearningView;

public class LearningPresenter extends AbstractPresenter<LearningView> {

	private HashMap<String, String>  words = new HashMap<String, String>();
	private LinkedList<String> wordList = new LinkedList<String>();
	
	private int actual=0; // dobre odpowiedzi
	private int answers=0;
	private MainPresenter parentPresenter = new MainPresenter();
	
	public void setParentPresenter(MainPresenter p) {
        this.parentPresenter = p;
    }
	
	public void next(String pl, String eng) {
		
		if(words.get(pl).equals(eng)){
			actual++;
		}
		answers++;
	}
	
	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}
	
	public int getAnswers() {
		return answers;
	}

	public void setAnswers(int answers) {
		this.answers = answers;
	}
	
	public void setWords(HashMap<String,String> words, LinkedList<String> wordList){
		this.words=words;
		this.wordList=wordList;
	}
	
}
