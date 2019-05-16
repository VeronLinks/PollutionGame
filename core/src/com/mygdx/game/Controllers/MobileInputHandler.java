package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MobileInputHandler implements InputController {

    private boolean finger1, finger2;
    private boolean inputLeft, inputRight;
    private boolean inputPause;

    public MobileInputHandler(){

    }

    @Override
    public void update(float dt) {
        finger1 = Gdx.input.isTouched(0);
        finger2 = Gdx.input.isTouched(1);

    }

    private boolean getLeft(){
        boolean isFingerLeft = false;

        int finger1X = Gdx.input.getX();
        int finger2X = Gdx.input.getX(1);
        int middle = Gdx.graphics.getWidth()/2;

        if(finger1){
            if(finger1X < middle){
                isFingerLeft = true;
            }
        }
        if(finger2){
            if(finger2X < middle){
                isFingerLeft = true;
            }
        }
        return isFingerLeft;
    }

    private boolean getRight(){
        boolean isFingerRight = false;

        int finger1X = Gdx.input.getX();
        int finger2X = Gdx.input.getX(1);
        int middle = Gdx.graphics.getWidth()/2;

        if(finger1){
            if(finger1X > middle){
                isFingerRight = true;
            }
        }

        if(finger2){
            if(finger2X > middle){
                isFingerRight = true;
            }
        }

        return isFingerRight;
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
