package com.mygdx.game.go;

import com.badlogic.gdx.math.MathUtils;
import sun.security.smartcardio.SunPCSC;

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

    public ArrayList <SelfCard> self_cards;
    public SelfCard gimmeRandomSelfCard() {
        SelfCard copy = new SelfCard();
        SelfCard original = self_cards.get(MathUtils.random(0, self_cards.size() - 1));

        copy.id = original.id;
        copy.volunteers = original.volunteers;
        copy.money = original.money;

        copy.init();

        return copy;
    }

    public ArrayList <SelectedCard> selected_cards;
    public SelectedCard gimmeRandomSelectedCard(){
        SelectedCard copy = new SelectedCard();
        SelectedCard original = selected_cards.get(MathUtils.random(0, selected_cards.size() -1));

        copy.id = original.id;
        copy.name = original.name;
        copy.description = original.description;
        copy.cost = original.cost;
        copy.cost.money = original.cost.money;
        copy.cost.volunteers = original.cost.volunteers;
        copy.money = original.money;
        copy.money.quantity = original.money.quantity;
        copy.money.target = original.money.target;
        copy.volunteers = original.volunteers;
        copy.volunteers.quantity = original.volunteers.quantity;
        copy.volunteers.target = original.volunteers.target;
        copy.pollution = original.pollution;
        copy.pollution.quantity = original.pollution.quantity;
        copy.pollution.target = original.pollution.target;
        copy.affinity = original.affinity;
        copy.affinity.quantity = original.affinity.quantity;
        copy.affinity.target = original.affinity.target;
        copy.bankrupty = original.bankrupty;
        copy.bankrupty.quantity = original.bankrupty.quantity;
        copy.bankrupty.target = original.bankrupty.target;

        copy.init();

        return copy;
    }

    public ArrayList <FactoryCard> factory_cards;
    public FactoryCard gimmeRandomFactoryCard(){
        FactoryCard copy = new FactoryCard();
        FactoryCard original = factory_cards.get(MathUtils.random(0, factory_cards.size() -1));

        copy.id = original.id;
        copy.name = original.name;
        copy.description = original.description;
        copy.money = original.money;
        copy.money.quantity = original.money.quantity;
        copy.money.target = original.money.target;
        copy.volunteers = original.volunteers;
        copy.volunteers.quantity = original.volunteers.quantity;
        copy.volunteers.target = original.volunteers.target;
        copy.pollution = original.pollution;
        copy.pollution.quantity = original.pollution.quantity;
        copy.pollution.target = original.pollution.target;
        copy.affinity = original.affinity;
        copy.affinity.quantity = original.affinity.quantity;
        copy.affinity.target = original.affinity.target;
        copy.bankrupty = original.bankrupty;
        copy.bankrupty.quantity = original.bankrupty.quantity;
        copy.bankrupty.target = original.bankrupty.target;

        copy.init();

        return copy;
    }

}
