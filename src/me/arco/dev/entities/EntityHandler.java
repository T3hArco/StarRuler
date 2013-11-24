package me.arco.dev.entities;

import me.arco.dev.entities.enemy.Enemy;
import me.arco.dev.entities.living.Human;
import me.arco.dev.entities.living.Humanoid;
import me.arco.dev.entities.ship.Ship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: arco
 * Date: 11/23/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityHandler
{
    public enum Type
    {
        ENEMY, HUMANOID, LIVING, SHIP, STAR
    }

    private Type types;
    private int[][] idList = new int[735][745];
    private List<Entity> entities = new ArrayList<Entity>();
    private Entity ship;
    private int shipID;

    public EntityHandler()
    {
        for(int i = 0; i < idList.length; i++) for(int j = 0; j < idList[0].length; j++) idList[i][j] = -1;
    }

    public void addEntity(float x, float y, float motionX, float motionY, String type, Type types)
    {
        switch (types)
        {
            case ENEMY:
                if(type == "wijns")
                {
                    Enemy tempE = new Enemy(x, y, 74, 99, motionX, motionY, type);
                    tempE.setType(Enemy.Type.WIJNSINATOR);

                    entities.add(tempE);
                }
                else
                {
                    // insert random polygon here
                }
                break;

            case HUMANOID:
                entities.add(new Human(x, y, motionY, 100, 100, 1));
                break;

            case SHIP:
                try
                {
                    entities.add(ship = new Ship(x, y, motionX, type));
                    shipID = entities.size();
                }
                catch (IOException e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                break;

            case STAR:
                entities.add(new Star(x, y, 100, "star"));
                break;
        }

        formArray();
    }

    public void addX(float xAmt, int ID)
    {
        entities.get(ID).setX(entities.get(ID).getX() + xAmt);
        formArray();
    }

    public void addY(float yAmt, int ID)
    {
        entities.get(ID).setY(entities.get(ID).getY() + yAmt);
        formArray();
    }

    public void formArray()
    {
        int count = 0;

        for(Entity entity : entities)
        {
            xFor:
            for(int i = (int) entity.getY(); i < entity.getY() + entity.getHeight(); i++)
            {
                for(int j = (int) entity.getX(); j < entity.getX() + entity.getWidth(); j++)
                {
                    try
                    {
                        idList[j][i] = count;
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {
                        System.err.println("[ERROR] Went out of bounds");
                        break xFor;
                    }
                }
            }

            count++;
        }
    }

    public boolean isEntityAtLocation(int x, int y)
    {
        if(idList[x][y] != -1) return true;

        return false;
    }

    public Ship getShip()
    {
        return (Ship) ship;
    }

    public List<Entity> getEntities()
    {
        return entities;
    }
}
