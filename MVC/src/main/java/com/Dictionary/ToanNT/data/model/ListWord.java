package com.Dictionary.ToanNT.data.model;

import java.util.ArrayList;
import java.util.List;

public class ListWord {
    private List<Word> myListWord = new ArrayList<>();

    public void addWord(Word word){
        myListWord.add(word);
    }

    public List<Word> getMyListWord() {
        return myListWord;
    }

    public void setMyListWord(List<Word> myListWord) {
        this.myListWord = myListWord;
    }
}
