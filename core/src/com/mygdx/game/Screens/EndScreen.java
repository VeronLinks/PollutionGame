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

// WE WILL HAVE TO CHANGE THE INPUT PROCESSOR FOR THE MENU

public class EndScreen implements Screen {

    MyGdxGame game;
    HUD hud;
    boolean winner;
    float buttonWidth = 120, buttonHeight = 40;
    SpriteBatch batch;
    OrthographicCamera camera;
    BitmapFont font;

    public EndScreen(Game game, boolean winner) {
        this.game = (MyGdxGame) game;
        this.winner = winner;
        (hud = GameManager.getInstance().hud).clear();
        font = Assets.getInstance().bigFont;
        this.game.menuScreen.init();
        batch = ((MyGdxGame) game).menuScreen.batch;
        camera = WorldController.hudCamera;
    }

    @Override
    public void show() {

        TextButton b1 = new TextButton("Go to Menu", -buttonWidth, -buttonHeight * 1.5f, buttonWidth * 2, buttonHeight * 2) {
            @Override
            public void click() {
                SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                hud.clear();
                game.setScreen(((MyGdxGame) game).menuScreen);
            }
        };
        hud.add(b1);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ((MyGdxGame) game).input.update(Gdx.graphics.getDeltaTime());

        //render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (winner)
        {
            font.draw(batch, "YOU WON", -camera.viewportWidth / 2, camera.viewportHeight / 3, camera.viewportWidth, Align.center, true);
        }
        else
        {
            font.draw(batch, "GAME OVER", -camera.viewportWidth / 2, camera.viewportHeight / 3, camera.viewportWidth, Align.center, true);
            font.draw(batch, "You lost. You fucking loser.", -camera.viewportWidth / 2, -camera.viewportHeight / 3 + 60, camera.viewportWidth, Align.center, true);
            font.draw(batch, "Go kill yourself or play parchis.", -camera.viewportWidth / 2, -camera.viewportHeight / 3, camera.viewportWidth, Align.center, true);
            font.draw(batch, "git good or gtfo", -camera.viewportWidth / 2, -camera.viewportHeight / 3 - 80, camera.viewportWidth, Align.center, true);
        }

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