package com.mygdx.game.Unit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Utils.AddedUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Weapon;

public abstract class Tank {
    MyGdxGame game;
    Weapon weapon;
    Texture texture;
    Vector2 position;

    int hp;
    int hpMax;

    float speed;
    float angle;

    float turretAngle;
    float fireTimer;

    int width;
    int height;

    public Tank(MyGdxGame game) {
        this.game = game;
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angle, 0, 0, width, height, false, false);
        batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, turretAngle, 0, 0, width, height, false, false);
    }

    public abstract void update(float dt);

    public void rotateTurretToPoint(float pointX, float pointY, float dt) {
        float angleTo = AddedUtils.getAngle(position.x, position.y, pointX, pointY);
        turretAngle = AddedUtils.makeRotation(turretAngle, angleTo, 270.0f, dt);
        turretAngle = AddedUtils.angleToFromNegPiToPosPi(turretAngle);
    }



    public void fire(float dt) {
        fireTimer += dt;
        if (fireTimer >= weapon.getFirePeriod()) {
            fireTimer = 0.0f;
            float angleRad = (float) Math.toRadians(turretAngle);
            game.getBulletEmitter().activate(position.x, position.y, 300.0f * (float) Math.cos(angleRad), 300.0f * (float) Math.sin(angleRad), weapon.getDamage());
        }
    }

}
