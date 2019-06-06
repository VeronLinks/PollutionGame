package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.go.Background;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.hud.HUD;

import java.util.ArrayList;

public class WorldRenderer {

    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;
    private SpriteBatch batch;

    private WorldController controller;
    private Background bg = new Background();
    private HUD hud;
    private GameManager GM =GameManager.getInstance();



    public WorldRenderer(WorldController wc){
        this.controller = wc;
        batch = new SpriteBatch();
        camera = controller.camera;
        hudCamera = controller.hudCamera;

        hud = GM.hud;

        init();
    }

    public void init(){


        camera.position.set(0,0,0);
        camera.update();

    }


    public void render(){

        ArrayList<GameObject> gameObjects = GM.cardsOnBoard;

        batch.setColor(Color.WHITE);
        batch.setProjectionMatrix(camera.combined);

        //render GOs
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.render(batch);
        int x = (int)-WorldController.camera.viewportWidth/5;
        int y = (int)-WorldController.camera.viewportHeight/2;
        int w = (int) (WorldController.camera.viewportWidth/1.75f);
        int h = (int) (WorldController.camera.viewportHeight/1.35f);
        batch.draw(Assets.getInstance().white, x, y, w, h);
        for (GameObject go : gameObjects) {
            go.render(batch);
        }
        batch.setColor(Color.WHITE);
        batch.end();

        //render HUD
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        hud.render(batch);
        batch.end();

    }

    public void resize(int width, int height){

        if((float)width/height < Constants.VIEWPORT_RATIO){
            camera.viewportWidth = Constants.VIEWPORT_WIDTH;
            camera.viewportHeight = (Constants.VIEWPORT_WIDTH/width)*height;
        }
        else{
            camera.viewportHeight = Constants.VIEWPORT_HEIGHT;
            camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
        }
        hudCamera.viewportHeight = camera.viewportHeight;
        hudCamera.viewportWidth = camera.viewportWidth;
        hudCamera.update();

        Gdx.app.debug("RESIZE", "" + camera.viewportWidth / camera.viewportHeight);
        camera.update();
    }

    public void dispose(){

        batch.dispose();
    }
}
