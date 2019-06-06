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
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SoundManager;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.TextButton;

import java.util.ArrayList;

// WE WILL HAVE TO CHANGE THE INPUT PROCESSOR FOR THE MENU

public class MenuScreen implements Screen {

    MyGdxGame game;
    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    int playersNumber;
    HUD hud;
    float buttonWidth = 120, buttonHeight = 40;

    public MenuScreen(Game game) {
        this.game = (MyGdxGame)game;
        font = Assets.getInstance().bigFont;
        batch = new SpriteBatch();
        camera = WorldController.hudCamera;
        hud = GameManager.getInstance().hud;

        init();
    }

    public void init()
    {
        GameManager.players = playersNumber = 1;
    }

    @Override
    public void show() {

        TextButton b1 = new TextButton("2",-buttonWidth,buttonHeight * 3.5f, buttonWidth*2, buttonHeight*2) {
            @Override
            public void click() {
                SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                System.out.println("E1");
                playersNumber = 2;
                hud.clear();
                GameManager.getInstance().gameInit(playersNumber);
                game.setScreen(((MyGdxGame) game).gameScreen);
            }
        };
        TextButton b2 = new TextButton("3",-buttonWidth,buttonHeight, buttonWidth*2, buttonHeight*2){
            @Override
            public void click()
            {
                SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                System.out.println("E2");
                playersNumber = 3;
                hud.clear();
                GameManager.getInstance().gameInit(playersNumber);
                game.setScreen(((MyGdxGame) game).gameScreen);
            }
        };
        TextButton b3 = new TextButton("4",-buttonWidth,-buttonHeight * 1.5f, buttonWidth*2, buttonHeight*2) {
            @Override
            public void click() {
                SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                System.out.println("E3");
                playersNumber = 4;
                hud.clear();
                GameManager.getInstance().gameInit(playersNumber);
                game.setScreen(((MyGdxGame) game).gameScreen);
            }
        };
        hud.add(b1);
        hud.add(b2);
        hud.add(b3);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ((MyGdxGame)game).input.update(Gdx.graphics.getDeltaTime());

        //render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.draw(batch,"Choose how many players will play", -camera.viewportWidth/2,camera.viewportHeight/3,camera.viewportWidth, Align.center,true);

        hud.render(batch);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        game.renderer.resize(width, height);
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
