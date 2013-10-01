package me.arco.dev;

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
    private List<Entity> entityList = new ArrayList<Entity>();
    private int[][] entityIdArr = new int[18][18];
    private Random random = new Random();

    private int shipXpos, shipYpos = -1;

    public EntityHandler()
    {
        for(int i = 0; i < 34; i++)
        {
            addEntity("Star", entityList.size(), random.nextInt(18), random.nextInt(18), random.nextInt(750), random.nextInt(750), "", -1);
        }

        addEntity("Ship", entityList.size(), random.nextInt(18), random.nextInt(18), 500, 500, "", 1000);
    }


    public void addEntity(String type, int listID, int xpos, int ypos, int xrender, int yrender, String image, int health)
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

            entity = new Ship(type, listID, xpos, ypos, xrender, yrender, image, health);
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
}
