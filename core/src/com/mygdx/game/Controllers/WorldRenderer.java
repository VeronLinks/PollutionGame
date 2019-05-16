package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants;
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



    public WorldRenderer(WorldController wc){
        this.controller = wc;
        batch = new SpriteBatch();
        camera = controller.camera;
        hudCamera = controller.hudCamera;

        hud = controller.hud;

        init();
    }

    public void init(){


        camera.position.set(0,0,0);
        camera.update();

    }


    public void render(){

        ArrayList<GameObject> gameObjects = controller.cardsOnBoard;

        batch.setColor(Color.WHITE);
        batch.setProjectionMatrix(camera.combined);

        //render GOs
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.render(batch);
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

        hudCamera.viewportWidth = width;
        hudCamera.viewportHeight = height;

        //0,0 in the lower left corner
        hudCamera.position.x = width / 2;
        hudCamera.position.y = height / 2;
        hudCamera.update();

        if((float)width/height < Constants.VIEWPORT_RATIO){
            camera.viewportWidth = Constants.VIEWPORT_WIDTH;
            camera.viewportHeight = (Constants.VIEWPORT_WIDTH/width)*height;
        }
        else{
            camera.viewportHeight = Constants.VIEWPORT_HEIGHT;
            camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
        }

        Gdx.app.debug("RESIZE", "" + camera.viewportWidth / camera.viewportHeight);
        camera.update();
    }

    public void dispose(){

        batch.dispose();
    }
}
