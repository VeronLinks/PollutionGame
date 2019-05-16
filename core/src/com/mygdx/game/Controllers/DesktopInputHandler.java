package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.go.Card;
import com.mygdx.game.go.GameObject;

public class DesktopInputHandler implements InputController {

    private boolean inputLeft, inputRight;
    private boolean inputPause;

    private Vector3 pointHUD, pointGame;

    private WorldController controller;


    public DesktopInputHandler(WorldController c){
        controller = c;
    }

    @Override
    public void update(float dt) {


        //DEBUG FEATURES
        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            WorldController.camera.zoom -= 1f * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.O)){
            WorldController.camera.zoom += 1f * dt;
        }

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
        System.out.println("Click");
        //screen touched, corresponds to a HUD button?
        pointHUD = new Vector3(screenX, screenY, 0);

        //check if the click is for the HUD
        controller.hudCamera.unproject(pointHUD);
        if (!controller.hud.click(pointHUD.x, pointHUD.y)) {

            //the click is not for the HUD, check if it is for the cards!
            pointGame = new Vector3(screenX, screenY, 0);
            controller.camera.unproject(pointGame);

            for (GameObject card : controller.cardsOnBoard) {

                if (card instanceof Card && card.getBounds().contains(pointGame.x, pointGame.y)) {
                    //card clicked, "use" it and remove it afterwards
                    controller.toRemove = (Card) card;
                    controller.toRemove.use(controller.gameStats);
                }
            }
        }
        if (controller.toRemove != null) {
            controller.cardsOnBoard.remove(controller.toRemove);
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
