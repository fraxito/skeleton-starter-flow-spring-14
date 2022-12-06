package org.vaadin.example;

import java.util.ArrayList;

public class CharacterList {
    private int count;
    private String next;
    private String prev;
    private ArrayList<Character> results;

    public CharacterList(int count, String next, String prev, ArrayList<Character> results) {
        this.count = count;
        this.next = next;
        this.prev = prev;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }
}
