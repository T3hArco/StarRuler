package me.arco.dev;

import java.awt.*;
import java.util.Random;

public class StarRuler extends GameWindow
{
    private Random random = new Random();

    private int starOffset = 0;

    private EntityHandler entityHandler = new EntityHandler();
    private int[][] entityIdArr = null;

    public StarRuler(String title, int width, int height)
    {
        super(title, width, height);
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void update(long delta)
    {
        entityIdArr = entityHandler.getEntityIdArr();

        if(random.nextInt(100) == 5)
        {
            starOffset++;
        }
        else if(random.nextInt(100) == 3)
        {
            starOffset--;
        }
        else if(random.nextInt(100) == 10)
        {
            entityHandler.getEntity(entityHandler.getShipXpos(), entityHandler.getShipYpos()).setXrender(random.nextInt(10));
            entityHandler.getEntity(entityHandler.getShipXpos(), entityHandler.getShipYpos()).setYrender(random.nextInt(10));
        }
    }

    @Override
    protected void render(Graphics2D renderHandle)
    {
        for(int i = 0; i < entityIdArr.length; i++)
        {
            for(int j = 0; j < entityIdArr[0].length; j++)
            {
                if(entityHandler.getType(i, j).equals("Star"))
                {
                    renderHandle.setColor(Color.WHITE);
                    renderHandle.fillOval(entityHandler.getEntity(i, j).getXrender() + starOffset % 3, entityHandler.getEntity(i, j).getYrender() + starOffset % 3, 5, 5);
                }
                else if(entityHandler.getType(i, j).equals("Ship"))
                {
                    renderHandle.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    renderHandle.fillRect(entityHandler.getEntity(i, j).getXrender() * 40, (entityHandler.getEntity(i, j).getYrender()) * 40, 40, 40);
                }
            }
        }
    }

    @Override
    protected void handleKeyboardEvent(KeyboardEvent event)
    {
        if (event.getType() == KeyboardEvent.DOWN)
        {
            switch (event.getKeyCode())
            {
                case KeyboardEvent.KEY_ESCAPE:
                    shutdown();
                    break;
            }
        }
    }

}
