package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.go.*;

public class DesktopInputHandler implements InputController {

    private boolean inputLeft, inputRight, inputUp, inputDown;
    private boolean inputPause;
    private boolean inputSelect;

    private Vector3 pointHUD, pointGame;

    private WorldController controller;
    private GameManager GM =GameManager.getInstance();


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
        WorldController.camera.update();

    }

    @Override
    public boolean keyDown(int keycode)
    {
        if(keycode == Input.Keys.ESCAPE)
        {
            GameManager.isPaused=true;
        }

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
        //screen touched, corresponds to a HUD button?
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
