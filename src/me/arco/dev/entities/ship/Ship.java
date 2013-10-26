package me.arco.dev.entities.ship;

import me.arco.dev.entities.Entity;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:50
 */
public class Ship extends Entity
{
    private long timePassed = 0;
    private Random random = new Random();
    private float health, shield = health = 1000;
    private List<ShipElement> shipElements = new ArrayList<ShipElement>();

    public Ship(float x, float y, float motionX, float motionY, String type)
    {
        super(x, y, motionX, motionY, type);
        this.motionX = 1;
    }

    public String healthToString()
    {
        return health + "";
    }

    public String shieldToString()
    {
        return shield + "";
    }

    public float getHealth()
    {
        return (health / 1000) * 50;
    }

    public float getShield()
    {
        return (shield / 1000) * 50;
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        g.drawRect((int) x, (int) y, 25, 25);
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

