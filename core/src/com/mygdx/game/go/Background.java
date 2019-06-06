package com.mygdx.game.go;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Controllers.WorldController;

public class Background extends GameObject {

    public Background() {
        this.dimension = new Vector2(WorldController.camera.viewportWidth, WorldController.camera.viewportHeight);
        this.position = new Vector2(WorldController.camera.position.x-dimension.x/2, WorldController.camera.position.y-dimension.y/2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().backgroundGame, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elapsedTime) {

    }
}
