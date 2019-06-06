package com.mygdx.game.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.go.Background;
import com.mygdx.game.go.BasicAction;
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

    private Color[] colors = new Color[4];

    public WorldRenderer(WorldController wc){
        this.controller = wc;
        batch = new SpriteBatch();
        camera = controller.camera;
        hudCamera = controller.hudCamera;

        hud = GM.hud;

        colors[0] = new Color(1, 1, 1, 1);
        colors[1] = new Color(1, 0, 0, 1);
        colors[2] = new Color(0, 1, 0, 1);
        colors[3] = new Color(0, 0, 1, 1);

        init();
    }

    public void init(){


        camera.position.set(0,0,0);
        camera.update();

    }


    public void render(){

        ArrayList<GameObject> gameObjects = GM.cardsOnBoard;
        for(GameObject b : GM.basicActions){
            gameObjects.add(b);
        }

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
        renderPollutionBar(batch);
        renderPlayers(batch);
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


    private void renderPollutionBar(SpriteBatch batch){
        int totalBars = 16;
        int greenBars = 12;
        int x = (int)(WorldController.camera.viewportWidth/2.55f);
        int y = (int) (-WorldController.camera.viewportHeight/2.3f);
        int w = (int) WorldController.camera.viewportHeight/10;
        int h = (int) WorldController.camera.viewportHeight/35;
        int offset = (int) WorldController.camera.viewportHeight/100;
        batch.draw(Assets.getInstance().black, x-offset, y-offset, w+offset*2, h*totalBars+offset*(totalBars+1));
        for(int i=0;i<totalBars;i++){
            if(i<12){
                batch.draw(Assets.getInstance().green, x, y+h*i+offset*i, w, h);
            }else{
                batch.draw(Assets.getInstance().red, x, y+h*i+offset*i, w, h);
            }
        }
    }

    private void renderPlayers(SpriteBatch batch){

        int x = (int)(WorldController.camera.viewportWidth/2.55f);
        int y = (int) (-WorldController.camera.viewportHeight/2.3f);
        int w = (int) WorldController.camera.viewportHeight/50;
        int offsetX = w+(int) WorldController.camera.viewportHeight/150;
        int offsetY = (int) WorldController.camera.viewportHeight/35+(int) WorldController.camera.viewportHeight/100;
        for(int i=0;i<GM.players; i++){
            batch.setColor(colors[i]);
            batch.draw(Assets.getInstance().box, x+offsetX*i, y+offsetY*GM.playerList.get(i).pollution, w, w);
        }
        batch.setColor(Color.WHITE);
    }
}
