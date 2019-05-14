package com.mygdx.game.go;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class CardFactory {

    public ArrayList<Card> cards;

    public Card gimmeRandomCard() {

        Card copy = new Card();

        Card original = cards.get(MathUtils.random(0, cards.size() - 1));

        //this copy can be done in better ways, like a copy constructor!
        copy.money = original.money;
        copy.name = original.name;
        copy.tex = original.tex;
        copy.text = original.text;
        copy.pollution = original.pollution;
        copy.cardR = original.cardR;
        copy.cardG = original.cardG;
        copy.cardB = original.cardB;

        copy.init();

        return copy;
    }

}
