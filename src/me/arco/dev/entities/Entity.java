package me.arco.dev.entities;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:44
 */

public abstract class Entity
{
    protected float x, y;
    protected float motionX, motionY;
    protected String type;

    protected Entity(float x, float y, float motionX, float motionY, String type)
    {
        this.x = x;
        this.y = y;
        this.motionX = motionX;
        this.motionY = motionY;
        this.type = type;
    }

    public abstract void draw(Graphics2D g);

    public void update(long delta) {
        x += motionX * delta * 0.0001f;
        y += motionY * delta * 0.0001f;
    }

    public String getType()
    {
        return type;
    }
}
