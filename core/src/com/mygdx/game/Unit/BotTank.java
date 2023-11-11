package com.mygdx.game.Unit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Utils.Direction;
import com.mygdx.game.Weapon;

public class BotTank extends Tank{
    Direction preferredDirection;
    float aiTimer;
    float aiTimerTo;
    boolean active;

    public boolean isActive() {
        return active;
    }

    public BotTank(MyGdxGame game) {
        super(game);
        this.weapon = new Weapon();
        this.texture = new Texture("bot_tank_base.png");
        this.position = new Vector2(500.0f, 500.0f);
        this.speed = 100.0f;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.hpMax = 4;
        this.hp = this.hpMax;
        this.aiTimerTo = 3.0f;
        this.preferredDirection = Direction.UP;
    }


    public void activate(float x, float y) {
        hpMax = 4;
        hp = hpMax;
        preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        position.set(x, y);
        aiTimer = 0.0f;
        active = true;
    }
    public void update(float dt) {
        aiTimer += dt;
        if(aiTimer >= aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.5f, 4.0f);
            preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = preferredDirection.getAngle();
        }
        position.add(speed * preferredDirection.getVx() * dt, speed * preferredDirection.getVy() * dt);
    }
}
