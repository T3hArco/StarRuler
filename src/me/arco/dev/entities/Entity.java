package me.arco.dev.entities;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:44
 */

public abstract class Entity
{
    protected float x, y, width, height;
    protected float motionX;
    private final float motionY;
    private final String type;

    protected Entity(float x, float y, float width, float height, float motionX, float motionY, String type)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.motionX = motionX;
        this.motionY = motionY;
        this.type = type;
    }

    public abstract void draw(Graphics2D g);

    public void update(long delta)
    {
        Random random = new Random();

        if (random.nextBoolean())
        {
            x += motionX * delta * 0.0001f;
            y += motionY * delta * 0.0001f;
        } else
        {
            x -= motionX * delta * 0.0001f;
            y -= motionY * delta * 0.0001f;
        }
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public String getType()
    {
        return type;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public abstract boolean checkIfDead();

    public abstract boolean shipElementAtPos(int x, int y);
}
