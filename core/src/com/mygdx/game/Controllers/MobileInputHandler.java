package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.go.*;

public class MobileInputHandler implements InputController {

    private boolean finger1, finger2;
    private boolean inputLeft, inputRight, inputUp, inputDown;
    private boolean inputPause;
    private boolean inputSelect;

    private GameManager GM = GameManager.getInstance();
    private Vector3 pointHUD, pointGame;

    private WorldController controller;

    public MobileInputHandler(WorldController c){
        controller = c;
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
        pointHUD = new Vector3(screenX, screenY, 0);

        //check if the click is for the HUD
        controller.hudCamera.unproject(pointHUD);

        switch (GameManager.state) {
            case GameManager.STATE_NONE:
                if (!GM.hud.click(pointHUD.x, pointHUD.y)) {
                    //the click is not for the HUD, check if it is for the cards!
                    pointGame = new Vector3(screenX, screenY, 0);
                    controller.camera.unproject(pointGame);

                    for (GameObject card : GM.cardsOnBoard) {

                        if (card instanceof SelectedCard && card.getBounds().contains(pointGame.x, pointGame.y)) {
                            //card clicked, "use" it and remove it afterwards
                            ((SelectedCard) card).use();
                            return true;
                        }

                        if (card instanceof BasicAction && card.getBounds().contains(pointGame.x, pointGame.y)) {
                            //card clicked, "use" it and remove it afterwards
                            ((BasicAction) card).use();
                            return true;
                        }
                    }
                }
                break;
            case GameManager.STATE_GAME_ACTIONS:
            case GameManager.STATE_PLAYER_ACTIONS:
                for (GameObject card : GameManager.currentSpecialCard) {
                    if (card.active) {
                        if (card instanceof FactoryCard) {
                            ((FactoryCard) card).use();
                        }
                        else if (card instanceof SelfCard)
                        {
                            ((SelfCard) card).use();
                        }
                    }
                }
                break;
        }
        if (controller.toRemove != null) {
            GM.cardsOnBoard.remove(controller.toRemove);
            controller.toRemove = null;
        }
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
