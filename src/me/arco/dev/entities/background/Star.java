package me.arco.dev.entities.background;

import me.arco.dev.entities.Entity;

import java.awt.*;

public class Star extends Entity
{
    private long timePassed = 0;

    public Star(float x, float y, float motionX, float motionY, String type)
    {
        super(x, y, motionX, motionY, type);
        this.motionX = 1;
    }


    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.WHITE);
        g.drawOval((int) x, (int) y, 5, 5);
    }

    @Override
    public void update(long delta)
    {
        timePassed += delta;
        if(timePassed > 10000) {
            timePassed = 0;
            motionX *= -1;
        }
        super.update(delta);
    }
}
