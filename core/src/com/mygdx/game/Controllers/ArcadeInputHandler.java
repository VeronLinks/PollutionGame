package com.mygdx.game.Controllers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SoundManager;
import com.mygdx.game.go.BasicAction;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.go.SelectedCard;

import static com.mygdx.game.GameLogic.GameManager.game;

public class ArcadeInputHandler implements ControllerListener, InputController {

    private boolean inputLeft, inputRight, inputUp, inputDown;
    private boolean inputPause;
    private boolean inputSelect;

    private Vector3 pointHUD, pointGame;

    private WorldController controller;


    public ArcadeInputHandler(WorldController c){
        controller = c;
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        if (GameManager.players > 1) {
            switch (buttonCode) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    ((SelectedCard) (GameManager.cardsOnBoard.get(buttonCode))).use();
                    return true;
            }
        }
        else
        {
            switch (buttonCode) {
                case 0:
                    SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                    game.menuScreen.playersNumber = 2;
                    GameManager.hud.clear();
                    GameManager.getInstance().gameInit(2);
                    game.setScreen(((MyGdxGame) game).gameScreen);
                case 1:
                    SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                    game.menuScreen.playersNumber = 3;
                    GameManager.hud.clear();
                    GameManager.getInstance().gameInit(3);
                    game.setScreen(((MyGdxGame) game).gameScreen);
                case 2:
                    SoundManager.getInstance().menuClick.play(SoundManager.sfxVolume);
                    game.menuScreen.playersNumber = 4;
                    GameManager.hud.clear();
                    GameManager.getInstance().gameInit(4);
                    game.setScreen(((MyGdxGame) game).gameScreen);
            }
        }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        if(Controllers.getControllers().indexOf(controller,true) == 0) {
            if(buttonCode == 0)
                inputSelect = false;

            return true;
        }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        if (GameManager.players > 1)
        {
            //Y axis
            if(axisCode == 0){
                if(value < -0.9f){
                    ((BasicAction)(GameManager.basicActions.get(0))).use();
                    return true;
                    //Go down
                }else if(value > 0.9f){
                    ((BasicAction)(GameManager.basicActions.get(1))).use();
                    return true;
                    //Go up
                }
            }
            //X axis
            if(axisCode == 1){
                if(value < -0.9f){
                    System.out.println("Left");
                    //Go left
                }else if(value > 0.9f){
                    System.out.println("Right");
                    //Go right
                }
            }
        }

        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
