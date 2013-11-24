package me.arco.dev.entities.living;

import me.arco.dev.entities.Entity;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 7/10/13
 * Time: 21:18
 */
public class Humanoid extends Entity
{
    private final int health;
    private final int air;
    private final int level;
    private long timePassed = 0;

    Humanoid(float x, float y, float width, float height, float motionX, float motionY, String type, int health, int air, int level)
    {
        super(x, y, 9, 16, motionX, motionY, type);
        this.health = health;
        this.air = air;
        this.level = level;
    }

    @Override
    public void draw(Graphics2D g)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(long delta)
    {
        timePassed += delta;
        if (timePassed > 10000)
        {
            timePassed = 0;
            motionX *= -1;
        }
        super.update(delta);
    }
}
