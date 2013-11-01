package me.arco.dev;

/*
 * TODO LIST
 * Add boss called Cameltron
 * Add boss called Nicpisaur
 * Make code more efficient
 */

import me.arco.dev.entities.Entity;
import me.arco.dev.entities.Star;
import me.arco.dev.entities.ship.Ship;
import me.arco.dev.items.Inventory;
import me.arco.dev.ui.*;
import me.arco.dev.ui.Button;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Snoipa extends GameWindow
{
    private String author = "Arnaud Coel";
    private String version = "0.7.0";
    private String branch = "Indev";
    private String debugArgs;

    private Random random = new Random();
    private List<Entity> entities = new ArrayList<Entity>();
    private List<UI> uiElements = new ArrayList<UI>();
    private Entity entity;
    private UI ui, button;
    private Hud hud;
    private float shipHealth, shipShield;
    private Inventory inventory;
    private boolean renderInventory, renderHud, debug, gameover;

    public Snoipa(String title, int width, int height)
    {
        super(title, width, height);
    }

    @Override
    protected void initialize()
    {
        debug = true;

        if(debug)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Debug arguments separated by comma: ");
            debugArgs = scanner.nextLine();

            System.out.println("Resuming game");
        }

        System.out.println(this.getClass().getSimpleName() + " - " + version + ", " + branch + ". By: " + author);

        inventory = new Inventory();

        for(int i = 0; i < 75; i++)
        {
            entity = new Star(random.nextInt(650), random.nextInt(650), 100, 100, "Star");
            entities.add(entity);
        }

        try
        {
            entity = new Ship(350, 350, 100, 100, "Ship");
        }
        catch (IOException e)
        {
            System.err.println("ERR: Drawing ship went wrong! (missing image?)");
        }

        entities.add(entity);

        ui = new Hud();
        //button = new Button("Buttontest");
        uiElements.add(ui);
        //uiElements.add(button);

        if(debugArgs.contains("items"))
        {
            for(int i = 0; i < 6; i++)
            {
                inventory.addItem(i);
            }
        }

        hud = (Hud) ui;
    }

    @Override
    protected void update(long delta)
    {
        Ship ship = (Ship) entity;

        for(Entity entity : entities)
        {
            if(entity.getType().equals("Star"))
            {
                entity.update(1);
            }
            else if(entity.getType().equals("Ship"))
            {
                shipHealth = ship.getHealth();
                shipShield = ship.getShield();
            }
        }

        if(debugArgs.contains("damage"))
        {
            if(random.nextInt(1000) == 250)
            {
                ship.hit(2);
                hud.setConsoleMessage("DEBUG: hit!");
            }
        }

        if(ship.checkIfDead()) gameover = true;
    }

    @Override
    protected void render(Graphics2D renderHandle)
    {
        // Render version and stuff
        renderHandle.setColor(Color.WHITE);
        renderHandle.drawString(version + " " + branch, 5, 15);
        renderHandle.drawString(hud.getConsoleMessage() + "", 745 - hud.getConsoleMessage().length() * 8, 15);

        // Render all the entities
        for(Entity entity : entities) entity.draw(renderHandle);

        // Rendering our UI
        if(!renderHud) for(UI ui : uiElements) ui.draw(renderHandle, shipShield, shipHealth, null);

        // Rendering inventory if true
        if(!renderInventory) inventory.draw(renderHandle);

        // Print our gameover if it's over ;)
        if(gameover) {
            renderHandle.setColor(new Color(1f, 0f, 0f, .5f));
            renderHandle.fillRect(0, 0, 735, 745);
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

                case KeyboardEvent.KEY_I:
                    renderInventory ^= true; // fancy xor!
                    break;

                case KeyboardEvent.KEY_0:
                    hud.setConsoleMessage("[DEBUG] ATTACKED SELF");
                    Ship ship = (Ship) entity;
                    ship.hit(2);
                    break;

                case KeyboardEvent.KEY_H:
                    renderHud ^= true; // more fanciness!
                    break;
            }
        }
    }

    @Override
    protected void handleMouseEvent(MouseEvent event)
    {
        if(debugArgs.contains("mouse")) System.out.println(event);

        if(event.getType() == MouseEvent.Type.CLICKED)
        {
            System.out.println("Mouse clicked @ x: " + event.getX() + " y: " + event.getY() + ".");
        }
    }

}
