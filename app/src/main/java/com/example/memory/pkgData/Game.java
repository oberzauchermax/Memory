package com.example.memory.pkgData;

import java.util.Random;
import java.util.TreeMap;

public class Game {
    private static int _ID = 1;
    private int id;
    public TreeMap<Integer, Integer> cards;

    public Game () {
        this.id = _ID++;
        this.cards = new TreeMap<Integer, Integer>();
        Random rand = new Random();
        for(int i = 0; i <= 16; i++) {
            this.cards.put(i, rand.nextInt(8)+1);
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

    public int getNthCard(int id) {
        return this.cards.get(id);
    }

    @Override
    public String toString() {
        return this.id + ". Game: " + this.cards.toString();
    }
}
