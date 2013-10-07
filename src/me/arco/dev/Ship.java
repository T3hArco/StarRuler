package me.arco.dev;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:50
 */
public class Ship extends Entity
{
    private String image;
    private float health;
    private float shield;
    private boolean dead;
    private List<ShipElement> shipElementList = new ArrayList<ShipElement>();

    public Ship(String type, int listID, int xpos, int ypos, int xrender, int yrender, String image, int health, int shield)
    {
        super(type, listID, xpos, ypos, xrender, yrender);
        this.image = image;
        this.health = health;
        this.shield = shield;
    }

    public float getHealth()
    {
        return health;
    }

    public float getShield()
    {
        return shield;
    }

    public void decreaseHealth(float health)
    {
        this.health -= health;
    }

    public void decreaseShield(float shield)
    {
        this.shield -= shield;
    }

    public void setShield(float shield)
    {
        this.shield = shield;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public boolean isDead()
    {
        return dead;
    }

    public void setDead(boolean dead)
    {
        this.dead = dead;
    }
}
