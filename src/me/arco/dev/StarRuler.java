package me.arco.dev;

import me.arco.dev.entities.Entity;
import me.arco.dev.entities.Star;
import me.arco.dev.entities.ship.Ship;
import me.arco.dev.items.Inventory;
import me.arco.dev.ui.*;
import me.arco.dev.ui.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarRuler extends GameWindow
{
    private Random random = new Random();
    private List<Entity> entities = new ArrayList<Entity>();
    private List<UI> uiElements = new ArrayList<UI>();
    private Entity entity;
    private UI ui, button;
    private float shipHealth, shipShield;
    private Inventory inventory;
    private boolean renderInventory = false;

    public StarRuler(String title, int width, int height)
    {
        super(title, width, height);
    }

    @Override
    protected void initialize()
    {
        inventory = new Inventory();

        for(int i = 0; i < 50; i++)
        {
            entity = new Star(random.nextInt(650), random.nextInt(650), 100, 100, "Star");
            entities.add(entity);
        }

        entity = new Ship(250, 250, 100, 100, "Ship");
        entities.add(entity);

        ui = new Hud();
        button = new Button("Buttontest");
        uiElements.add(ui);
        uiElements.add(button);

        inventory.addItem(0);
        inventory.addItem(1);
        inventory.addItem(3);
        inventory.addItem(4);
        inventory.addItem(5);
        inventory.addItem(6);
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
        // Render all the entities
        for(Entity entity : entities) entity.draw(renderHandle);

        // Rendering our UI
        for(UI ui : uiElements) ui.draw(renderHandle, shipShield, shipHealth, null);

        // Rendering inventory if true
        if(renderInventory) inventory.draw(renderHandle);
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

                case KeyboardEvent.KEY_I:
                    renderInventory ^= true; // fancy xor!
                    break;
            }
        }
    }

    @Override
    protected void handleMouseEvent(MouseEvent e)
    {
        System.out.println(e);
    }

}
