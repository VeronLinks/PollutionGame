package com.mygdx.game.Controllers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

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

        if(Controllers.getControllers().indexOf(controller,true) == 0) {
            if(buttonCode == 0)
                inputSelect = true;

            if(buttonCode == 6)
                inputPause = true;

            return true;
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

        //Y axis
        if(axisCode == 0){
            if(value < -0.9f){
                System.out.println("Up");
                //Go down
            }else if(value > 0.9f){
                System.out.println("Down");
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
