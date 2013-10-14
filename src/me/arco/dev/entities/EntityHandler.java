package me.arco.dev.entities;

import me.arco.dev.entities.background.Star;
import me.arco.dev.entities.ship.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:42
 */

public class EntityHandler
{

}

/*public class EntityHandler
{
    private List<Entity> entityList = new ArrayList<Entity>();
    private int[][] entityIdArr = new int[18][18];
    private Random random = new Random();

    private int shipXpos, shipYpos = -1;

    public EntityHandler()
    {
        for(int i = 0; i < 34; i++)
        {
            addEntity("Star", entityList.size(), random.nextInt(18), random.nextInt(18), random.nextInt(750), random.nextInt(750), "", -1, -1);
        }

        addEntity("Ship", entityList.size(), random.nextInt(18), random.nextInt(18), 500, 500, "", 1000, 1000);
    }


    public void addEntity(String type, int listID, int xpos, int ypos, int xrender, int yrender, String image, int health, int shield)
    {
        Entity entity = null;

        if(type == "Star")
        {
            entity = new Star(type, listID, xpos, ypos, xrender, yrender, image);
        }
        else if(type == "Ship")
        {
            shipXpos = xpos;
            shipYpos = ypos;

            entity = new Ship(type, listID, xpos, ypos, xrender, yrender, image, health, shield);
        }
        else
        {
            //entity = new LivingObject(health, dead, type, accessible, listpos);
        }

        entityList.add(entity);
        entityIdArr[xpos][ypos] = listID;
    }

    public int[][] getEntityIdArr()
    {
        return entityIdArr;
    }

    public String getType(int xpos, int ypos)
    {
        return entityList.get(entityIdArr[xpos][ypos]).getType();
    }

    public Entity getEntity(int xpos, int ypos)
    {
        return entityList.get(entityIdArr[xpos][ypos]);
    }

    public int getShipXpos()
    {
        return shipXpos;
    }

    public int getShipYpos()
    {
        return shipYpos;
    }

    public float getShipHealth()
    {
        Ship ship = (Ship) entityList.get(entityIdArr[shipXpos][shipYpos]);
        return ship.getHealth();
    }

    public float getShipShield()
    {
        Ship ship = (Ship) entityList.get(entityIdArr[shipXpos][shipYpos]);
        return ship.getShield();
    }

    public void hitShip(int amt)
    {
        Ship ship = (Ship) entityList.get(entityIdArr[shipXpos][shipYpos]);
        float calculation;

        if(ship.getShield() != 0)
        {
            calculation = amt / (ship.getShield() / 2);
            ship.decreaseHealth(calculation);
            ship.decreaseShield(random.nextInt(10));
        }
        else
        {
            ship.decreaseHealth(amt);
        }

        if(ship.getShield() < 0)
        {
            ship.setShield(0);
        }

        if(ship.getHealth() < 0)
        {
            ship.setHealth(0);
            ship.setDead(true);
        }
    }
}          */
