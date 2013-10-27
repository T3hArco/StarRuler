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
    private boolean dead = false;

    public Ship(float x, float y, float motionX, float motionY, String type)
    {
        super(x, y, motionX, motionY, type);
        this.motionX = 1;
    }

    public float getHealth()
    {
        return (health / 1000) * 187;
    }

    public float getShield()
    {
        return (shield / 1000) * 187;
    }

    public void hit(double force)
    {
        if(shield <= 0)
        {
            health -= random.nextInt(15) * (force / 3);
            shield = 0;
        }
        else
        {
            shield -= random.nextInt(15) / (force / 2);
            if(random.nextBoolean())
            {
                health -= random.nextInt(15) / (force / 4);
            }
        }

        if(health <= 0)
        {
            dead = true;
            health = 0;
        }
    }

    public boolean checkIfDead()
    {
        return dead;
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
        if (timePassed > 10000)
        {
            timePassed = 0;
            motionX *= -1;
        }
        super.update(delta);
    }
}

