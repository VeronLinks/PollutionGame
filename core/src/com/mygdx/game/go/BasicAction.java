package com.mygdx.game.go;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.GameLogic.EvilFactory;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.SoundManager;
import com.mygdx.game.go.AuxiliarClasses.Cost;
import com.mygdx.game.go.AuxiliarClasses.QuantityTarget;

public class BasicAction extends GameObject{

    int id;
    Cost cost;
    QuantityTarget money;
    QuantityTarget volunteers;
    private Rectangle imageBounds;
    private Rectangle textBounds;

    private GameManager GM;

    /**
     * will be called by the JSON populator
     */
    public BasicAction() {
        super();
        dimension.x = (int)(WorldController.camera.viewportWidth/4);
        dimension.y = (int)(WorldController.camera.viewportHeight/6);
        position.x =  (int)(-WorldController.camera.viewportWidth/2.2f);
        position.y =  (int)(WorldController.camera.viewportHeight/13) - dimension.y*id*1.5f;
        System.out.println(id);
        //System.out.println("Card constructor called");
    }

    public void init() {
        imageBounds = new Rectangle(position.x + dimension.x * 0.1f, position.y + dimension.y * 0.45f, dimension.x * 0.8f, dimension.y * 0.50f);
        textBounds = new Rectangle(position.x + dimension.x * 0.1f, position.y + dimension.y * 0.05f, dimension.x * 0.8f, dimension.y * 0.35f);
        GM = GameManager.getInstance();
    }

    @Override
    public void render(SpriteBatch batch) {
        position.y =  (int)(WorldController.camera.viewportHeight/13) - dimension.y*id*1.1f;
        if(id == 0){
            batch.draw(Assets.getInstance().basicAction1, position.x, position.y, dimension.x, dimension.y);
        }else{
            batch.draw(Assets.getInstance().basicAction2, position.x, position.y, dimension.x, dimension.y);
        }
    }

    @Override
    public void update(float elapsedTime) {

    }


    public void use() {

        SoundManager.getInstance().click.play();

        targetedUse(money);
        targetedUse(volunteers);

        GameManager.getInstance().nextTurn();
    }

    private void targetedUse(QuantityTarget qT)
    {
        System.out.println(qT.target);
        if (qT.target == Constants.SELF)
        {
            GameManager.playerList.get(GameManager.turn).useCard
                    (0, money.quantity, volunteers.quantity);
        }
    }
}
