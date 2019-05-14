package com.mygdx.game.go;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class Background extends GameObject {

    public Background() {
        this.dimension = new Vector2(40, 40);
        this.position = new Vector2(0, 0);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().black, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elapsedTime) {

    }
}
