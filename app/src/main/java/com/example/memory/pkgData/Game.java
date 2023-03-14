package com.example.memory.pkgData;

import java.util.TreeMap;

public class Game {
    private static int _ID = 1;
    private int id;
    public TreeMap<Integer, Integer> cards;

    public Game () {
        this.id = _ID++;
        this.cards = new TreeMap<Integer, Integer>();
        for(int i = 1; i <= 16; i++) {
            this.cards.put(i, 0);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TreeMap<Integer, Integer> getCards() {
        return cards;
    }

    public void setCards(TreeMap<Integer, Integer> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return this.id + ". Game: " + this.cards.toString();
    }
}
