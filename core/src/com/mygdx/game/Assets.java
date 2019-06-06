package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

    public Texture box;
    public Texture coin;
    public Texture button;
    public BitmapFont HUDfont;
    public BitmapFont bigFont;
    public BitmapFont mediumFont;
    public BitmapFont littleFont;
    public Texture card;
    public Texture card_ONG;
    public Texture card_factory;
    public Texture basicAction1;
    public Texture basicAction2;
    public Texture black;
    public Texture white;
    public Texture green;
    public Texture red;
    public Texture cloud;
    public Texture backgroundGame;
    public Texture factoryIcon;

    public BitmapFont GameFont;

    private static Assets instance;


    private Assets() {

        box = new Texture(Gdx.files.internal("box.png"));
        coin = new Texture(Gdx.files.internal("coin.png"));
        card = new Texture(Gdx.files.internal("card.png"));
        card_ONG = new Texture(Gdx.files.internal("CARD_ONG.png"));
        card_factory = new Texture(Gdx.files.internal("CARD_Factory.png"));
        basicAction1 = new Texture(Gdx.files.internal("BasicAction1.png"));
        basicAction2 = new Texture(Gdx.files.internal("BasicAction2.png"));
        factoryIcon = new Texture(Gdx.files.internal("ICON_Factory.png"));
        black = new Texture(Gdx.files.internal("black.png"));
        white = createRect(Color.WHITE);
        green = createRect(new Color(0x01/255f, 0xb0/255f, 0x50/255f, 1));
        red = createRect(new Color(0xc0/255f, 0x50/255f, 0x4d/255f, 1));
        cloud = new Texture(Gdx.files.internal("cloud.png"));
        backgroundGame = new Texture(Gdx.files.internal("Tablero.jpg"));
        button = createButtonTexture();
        HUDfont = new BitmapFont();
        HUDfont.getData().setScale(2);


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Medium.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        bigFont = generator.generateFont(parameter);

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        mediumFont = generator.generateFont(parameter);

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        littleFont = generator.generateFont(parameter);

        GameFont = littleFont;

        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public Texture getTexture(String texName) {
        if (texName.equals("cloudTex")) {
            return cloud;
        } else if (texName.equals("moneyTex")) {
            return coin;
        } else
            return box;
    }


    private Texture createButtonTexture() {
        Pixmap pm = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pm.setColor(0, 1, 0, 1);
        pm.drawRectangle(0, 0, 10, 10);
        pm.setColor(0, 0.5f, 0, 1);
        pm.fillRectangle(1, 1, 8, 8);
        return new Texture(pm);
    }


    public static Assets getInstance() {

        if (instance == null)
            instance = new Assets();

        return instance;

    }


    private Texture createRect(Color color){

        int width = 100;

        Pixmap pm = new Pixmap(width, width, Pixmap.Format.RGBA8888);
        pm.setColor(color);

        pm.fillRectangle(0, 0, width, width);

        Texture t = new Texture(pm);
        return t;
    }
}
