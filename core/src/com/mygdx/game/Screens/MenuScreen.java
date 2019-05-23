package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.hud.HUDElement;
import com.mygdx.game.hud.TextButton;

import java.util.ArrayList;

// WE WILL HAVE TO CHANGE THE INPUT PROCESSOR FOR THE MENU

public class MenuScreen implements Screen {

    Game game;
    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    int playersNumber;
    ArrayList<TextButton> elements;

    public MenuScreen(Game game) {
        this.game = game;
        font = Assets.getInstance().bigFont;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //

        elements = new ArrayList<TextButton>();

    }

    @Override
    public void show() {

        TextButton b1 = new TextButton("2",10,10,120,40) {
            @Override
            public void click() {
                System.out.println("E1");
                playersNumber = 2;
                game.setScreen(((MyGdxGame) game).gameScreen);
            }
        };
        TextButton b2 = new TextButton("3",10,80,120,40){
            @Override
            public void click()
            {
                System.out.println("E2");
                playersNumber = 3;
                game.setScreen(((MyGdxGame) game).gameScreen);
            }
        };
        TextButton b3 = new TextButton("4",10,150,120,40) {
            @Override
            public void click() {
                System.out.println("E3");
                playersNumber = 4;
                game.setScreen(((MyGdxGame) game).gameScreen);
            }
        };
        elements.add(b1);
        elements.add(b2);
        elements.add(b3);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ((MyGdxGame)game).input.update(Gdx.graphics.getDeltaTime());

        /*//update
        if (Gdx.input.isTouched())
        {
            if (Gdx.input.getX() < Gdx.graphics.getWidth() / 3)
            {
                playersNumber = 2;
            }
            else if (Gdx.input.getX() < Gdx.graphics.getWidth() * 2 / 3)
            {
                playersNumber = 3;
            }
            else
            {
                playersNumber = 4;
            }
        }*/

        //render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.draw(batch,"Choose how many players will play", -camera.viewportWidth/2,camera.viewportHeight/3,camera.viewportWidth, Align.center,true);

        for(HUDElement he : elements){
            he.render(batch);
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
