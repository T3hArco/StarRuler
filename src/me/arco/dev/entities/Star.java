package me.arco.dev.entities;

import java.awt.*;

public class Star extends Entity
{
    private long timePassed = 0;

    public Star(float x, float y, float motionX, String type)
    {
        super(x, y, 5, 5, (float) 100, (float) 100, "Star");
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
        if (timePassed > 10000)
        {
            timePassed = 0;
            motionX *= -1;
        }
        super.update(delta);
    }

    @Override
    public boolean checkIfDead()
    {
        return false;
    }

    @Override
    public boolean shipElementAtPos(int x, int y)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
