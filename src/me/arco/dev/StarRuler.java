package me.arco.dev;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class StarRuler extends GameWindow
{
    private boolean debug = true;
    private Random random = new Random();
    private long lastLoopTime = System.currentTimeMillis();

    private int starOffset = 0;

    private EntityHandler entityHandler = new EntityHandler();
    private int[][] entityIdArr = null;

    private float shipHealth, shipHealthBar, shipShield, shipShieldBar;
    private String shipHealthBarString, shipShieldBarString;

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

        shipHealth = entityHandler.getShipHealth();
        shipHealthBar = (shipHealth / 1000) * 80;

        shipHealthBarString = shipHealth + "";
        shipHealthBarString = shipHealthBarString.substring(0, shipHealthBarString.length() - 5);

        shipShield = entityHandler.getShipShield();
        shipShieldBar = (shipShield / 1000) * 80;

        shipShieldBarString = shipShield + "";
        shipShieldBarString = shipShieldBarString.substring(0, shipShieldBarString.length() - 2);

        // debugging
        if(debug)
        {
            int randomAction = random.nextInt(10);

            if(randomAction == 2)
            {
                entityHandler.hitShip(random.nextInt(10));
            }
        }
    }

    @Override
    protected void render(Graphics2D renderHandle)
    {
        // Rendering our elements
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

        // Rendering our UI

        // Rending our shield and stuff
        renderHandle.setColor(Color.RED);
        renderHandle.fillRect(50, 660, 80, 20);
        renderHandle.setColor(Color.GRAY);
        renderHandle.fillRect(50, 660, (int) shipShieldBar, 20);
        renderHandle.setColor(Color.WHITE);
        renderHandle.drawString("Shield: ", 5, 675);
        renderHandle.drawString(shipShieldBarString + "", 80, 675);

        // Rendering our health and stuff
        renderHandle.setColor(Color.RED);
        renderHandle.fillRect(50, 690, 80, 20);
        renderHandle.setColor(new Color(0, 100, 0));
        renderHandle.fillRect(50, 690, (int) shipHealthBar, 20);
        renderHandle.setColor(Color.WHITE);
        renderHandle.drawString("Health: ", 5, 705);
        renderHandle.drawString(shipHealthBarString + "", 80, 705);
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

    protected void handleClickEvent(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        System.out.println(x + ", " + y);
    }

}
