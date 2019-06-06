package com.mygdx.game.Controllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Constants;
import com.mygdx.game.go.Card;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.hud.GameStats;
import com.mygdx.game.hud.HUD;

import java.util.ArrayList;

public class WorldController {


    public static OrthographicCamera camera;
    public static OrthographicCamera hudCamera;

    private static WorldController instance;

    Card toRemove;



    private WorldController(){

        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Gdx.input.setInputProcessor(this);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        init();
    }
    public static WorldController getInstance(){
        if(instance==null){
            instance = new WorldController();
        }
        return instance;
    }

    public static void init(){

    }

    public void update(float dt){

    }
}
