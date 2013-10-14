package me.arco.dev;

import me.arco.dev.entities.Entity;
import me.arco.dev.entities.background.Star;
import me.arco.dev.entities.ship.Ship;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarRuler extends GameWindow
{
    private Random random = new Random();
    private List<Entity> entities = new ArrayList<Entity>();
    private Entity entity;
    private float shipHealth, shipShield;

    public StarRuler(String title, int width, int height)
    {
        super(title, width, height);
    }

    @Override
    protected void initialize()
    {
        for(int i = 0; i < 50; i++)
        {
            entity = new Star(random.nextInt(650), random.nextInt(650), 100, 100, "Star");
            entities.add(entity);
        }

        entity = new Ship(250, 250, 100, 100, "Ship");
        entities.add(entity);
    }

    @Override
    protected void update(long delta)
    {
        for(Entity entity : entities)
        {
            if(entity.getType().equals("Star"))
            {
                entity.update(1);
            }
            else if(entity.getType().equals("Ship"))
            {
                Ship ship = (Ship) entity;
                shipHealth = ship.getHealth();
                shipShield = ship.getShield();
            }
        }
    }

    @Override
    protected void render(Graphics2D renderHandle)
    {
        for(Entity entity : entities)
        {
            entity.draw(renderHandle);
        }

        // Rendering our UI

        // Rending our shield and stuff
        renderHandle.setColor(Color.RED);
        renderHandle.fillRect(50, 660, 80, 20);
        renderHandle.setColor(Color.GRAY);
        renderHandle.fillRect(50, 660, (int) shipShield, 20);
        renderHandle.setColor(Color.WHITE);
        renderHandle.drawString("Shield: ", 5, 675);
        renderHandle.drawString(shipShield + "", 80, 675);

        // Rendering our health and stuff
        renderHandle.setColor(Color.RED);
        renderHandle.fillRect(50, 690, 80, 20);
        renderHandle.setColor(new Color(0, 100, 0));
        renderHandle.fillRect(50, 690, (int) shipShield, 20);
        renderHandle.setColor(Color.WHITE);
        renderHandle.drawString("Health: ", 5, 705);
        renderHandle.drawString(shipShield + "", 80, 705);
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

    @Override
    protected void handleMouseEvent(MouseEvent event)
    {
        System.out.println(event.getX());
    }

}
