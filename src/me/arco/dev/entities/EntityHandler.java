package me.arco.dev.entities;

import me.arco.dev.entities.enemy.Enemy;
import me.arco.dev.entities.living.Human;
import me.arco.dev.entities.ship.Ship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

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
        ENEMY, HUMANOID, LIVING, SHIP, STAR, GENERIC
    }

    private Type types;
    private int[][] idList = new int[735][745];
    private List<Entity> entities = new ArrayList<Entity>();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private Ship ship;
    private int shipID;
    private Random random = new Random();

    public EntityHandler()
    {
        for (int i = 0; i < idList.length; i++) for (int j = 0; j < idList[0].length; j++) idList[i][j] = -1;
    }

    public void addEntity(float x, float y, float motionX, float motionY, String type, Type types)
    {
        switch (types)
        {
            case ENEMY:
                Enemy tempE;

                if (type == "wijns")
                {
                    tempE = new Enemy(x, y, 74, 99, motionX, motionY, type);
                    tempE.setType(Enemy.Type.WIJNSINATOR);
                } else if (type == "nicpisaur")
                {
                    tempE = new Enemy(x, y, 74, 99, motionX, motionY, type);
                    tempE.setType(Enemy.Type.NICPISAURUS);
                } else
                {
                    tempE = new Enemy(x, y, 74, 99, motionX, motionY, type);
                    tempE.setType(Enemy.Type.GENERIC);
                }

                entities.add(tempE);
                enemies.add(tempE); // make it easier for us to get the enemies in one line ;)
                break;

            case HUMANOID:
                entities.add(new Human(x, y, motionY, 100, 100, 1));
                break;

            case SHIP:
                try
                {
                    entities.add(ship = new Ship(x, y, motionX, type));
                    shipID = entities.size();
                } catch (IOException e)
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

    public boolean randomEnemyShoot()
    {
        if (enemies.size() == 0) return false;
        if (ship.getHealth() <= 0) return false;
        int enemyId = random.nextInt(enemies.size());

        if (enemies.get(enemyId).checkIfDead()) return false;
        enemies.get(enemyId).shoot(ship);

        if (random.nextBoolean()) randomEnemyShoot();

        return true;
    }

    public void cleanList()
    {
        if (entities.size() == 0) return;

        try
        {
            for (Entity entity : entities)
            {
                if (entity.checkIfDead()) entities.remove(entity);
                formArray();
            }
        } catch (ConcurrentModificationException e)
        {
            System.err.println("[ERROR] Error at runtime!");
        }

    }

    public int getIdAtLocation(int x, int y)
    {
        return idList[x][y];
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

        for (Entity entity : entities)
        {
            xFor:
            for (int i = (int) entity.getY(); i < entity.getY() + entity.getHeight(); i++)
            {
                for (int j = (int) entity.getX(); j < entity.getX() + entity.getWidth(); j++)
                {
                    try
                    {
                        idList[j][i] = count;
                    } catch (ArrayIndexOutOfBoundsException e)
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
        if (idList[x][y] != -1) return true;

        return false;
    }

    public Enemy getEnemyAtLocation(int x, int y)
    {
        try
        {
            return (Enemy) entities.get(idList[x][y]);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.println("[ERROR] Tried shooting unexistent enemy");
            return new Enemy(x, y, 0, 0, 0, 0, "Nothing");
        }
        catch (ClassCastException e)
        {
            System.err.println("[ERROR] Entity is not an enemy..");
            return new Enemy(x, y, 0, 0, 0, 0, "Nothing");
        }
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
