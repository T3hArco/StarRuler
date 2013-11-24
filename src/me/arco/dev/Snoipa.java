package me.arco.dev;

/*
 * TODO LIST
 * Add boss called Cameltron
 * Add boss called Nicpisaur
 * Make code more efficient
 */

import me.arco.dev.debug.DebugScreen;
import me.arco.dev.entities.Entity;
import me.arco.dev.entities.EntityHandler;
import me.arco.dev.entities.Star;
import me.arco.dev.entities.enemy.Enemy;
import me.arco.dev.entities.living.Humanoid;
import me.arco.dev.entities.ship.Ship;
import me.arco.dev.items.Inventory;
import me.arco.dev.items.Item;
import me.arco.dev.ui.Button;
import me.arco.dev.ui.Hud;
import me.arco.dev.ui.UI;
import me.arco.dev.sound.SoundHandler;

import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Snoipa extends GameWindow
{
    private final String author = "Arnaud Coel (code!), Kamiel Klumpers (music!)";
    private final String version = "1.0.0";
    private final String branch = "Alpha";
    private String debugArgs = "items";
    private float shipHealth, shipShield;
    private boolean renderInventory, renderHud, debug, gameover, renderDebug;
    private final String[] information = new String[6];
    private int mouseX, mouseY = mouseX = 0;
    private String mouseAction;
    private long now;
    private int framesCount = 0;
    private int framesCountAvg = 0;
    private long framesTimer = 0;


    private final Random random = new Random();
    private final List<Entity> entities = new ArrayList<Entity>();
    private final List<UI> uiElements = new ArrayList<UI>();
    private EntityHandler entityHandler = new EntityHandler();
    private Entity entity;
    private UI ui;
    private Hud hud;
    private Inventory inventory;
    private DebugScreen debugScreen;
    private SoundHandler soundHandler;
    private Enemy tempEnemy;

    public Snoipa(String title, int height)
    {
        super("Snoipa", 735, 745);
    }

    @Override
    protected void initialize()
    {
        debug = false;

        if(debug)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Debug arguments separated by comma: ");
            debugArgs = scanner.nextLine();

            System.out.println("Resuming game");
        }

        System.out.println(this.getClass().getSimpleName() + " - " + version + ", " + branch + ". By: " + author);

        inventory = new Inventory();

        for(int i = 0; i < 75; i++) entityHandler.addEntity(random.nextInt(650), random.nextInt(650), 100, 100, "star", EntityHandler.Type.STAR);

        entityHandler.addEntity(350, 350, 100, 100, "ship", EntityHandler.Type.SHIP);

        ui = new Hud();
        uiElements.add(ui);

        ui.addButton("test", 100, 100, 10, 30);

        if(debugArgs.contains("items"))
        {
            for(int i = 0; i < 6; i++)
            {
                inventory.addItem(i);
            }
        }

        hud = (Hud) ui;

        debugScreen = new DebugScreen();

        soundHandler = new SoundHandler();
        soundHandler.makeSound("music/idk.wav");
    }

    @Override
    protected void update(long delta)
    {
        for(Entity entity : entityHandler.getEntities())
        {
            if(entity.getType().equals("star"))
            {
                entity.update(1);
            }
            else if(entity.getType().equals("ship"))
            {
                shipHealth = entityHandler.getShip().getHealth();
                shipShield = entityHandler.getShip().getShield();
            }
        }

        if(debugArgs.contains("damage"))
        {
            if(random.nextInt(1000) == 250)
            {
                entityHandler.getShip().hit();
                hud.setConsoleMessage("DEBUG: hit!");
            }
        }

        if(entityHandler.getShip().checkIfDead()) gameover = true;

        if(random.nextInt(10000) == 500)
        {
            if(random.nextBoolean())
            {
                System.out.println("[SYSTEM] An enemy has been spawned randomly!");

                entityHandler.addEntity(367, 100, 100, 100, "Wijns", EntityHandler.Type.ENEMY);
            }
        }

        // start compiling debug information
        information[0] = framesCountAvg + "";
        information[1] = entityHandler.getShip().getTotalPopulation() + "";
        information[2] = "n/i";
        information[3] = mouseX + "";
        information[4] = mouseY + "";
        information[5] = mouseAction;

    }

    @Override
    protected void render(Graphics2D renderHandle)
    {
        Ship ship = (Ship) entity; // for our ship needs

        // Render version and stuff
        renderHandle.setColor(Color.WHITE);
        renderHandle.drawString(version + " " + branch, 5, 15);
        renderHandle.drawString(hud.getConsoleMessage() + "", 745 - hud.getConsoleMessage().length() * 8, 15);

        // Render all the entities
        for(Entity entity : entityHandler.getEntities()) entity.draw(renderHandle);

        // Rendering our UI
        if(!renderHud)
        {
            for(UI ui : uiElements) ui.draw(renderHandle, shipShield, shipHealth);
            for(Button button : ui.getButtons()) button.draw(renderHandle);
        }

        // Rendering inventory if true
        if(!renderInventory) inventory.draw(renderHandle);

        // Rendering our debugging window
        if(renderDebug) debugScreen.draw(renderHandle, information);

        // Rendering the humanoids
        for(Humanoid humanoid : entityHandler.getShip().getAllHumanoids()) humanoid.draw(renderHandle);

        // Print our gameover if it's over ;)
        if(gameover)
        {
            renderHandle.setColor(new Color(1f, 0f, 0f, .5f));
            renderHandle.fillRect(0, 0, 735, 745);
        }

        now=System.currentTimeMillis();
        framesCount++;
        if(now-framesTimer > 1000)
        {
            framesTimer=now;
            framesCountAvg=framesCount;
            framesCount=0;
        }
    }

    @Override
    protected void handleKeyboardEvent(KeyboardEvent event)
    {
        Ship ship = (Ship) entity;

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
                    ship.hit();
                    break;

                case KeyboardEvent.KEY_H:
                    renderHud ^= true; // more fanciness!
                    break;

                case KeyboardEvent.KEY_D:
                    renderDebug ^= true;
                    break;

                case KeyboardEvent.KEY_9:
                    SecureRandom secureRandom = new SecureRandom();
                    ui.addButton(ui.getCurrentButtonListLength() + ": " + new BigInteger(130, secureRandom).toString(32) + "", random.nextInt(600), random.nextInt(600), 0, 0);
                    break;

                case KeyboardEvent.KEY_8:
                    ui.clearButtons();
                    break;

                case KeyboardEvent.KEY_7:
                    entityHandler.getShip().addHuman();
                    break;

                case KeyboardEvent.KEY_S:
                    entityHandler.addEntity(367, 100, 100, 100, "wijns", EntityHandler.Type.ENEMY);
                    System.out.println("spawned");
                    break;
            }
        }
    }

    @Override
    protected void handleMouseEvent(MouseEvent event)
    {
        Ship ship = (Ship) entity;

        if(debugArgs.contains("mouse")) System.out.println(event);

        switch(event.getType())
        {
            case CLICKED:
                mouseX = event.getX();
                mouseY = event.getY();

                if(debugArgs.contains("ui")) System.out.println(ui.isButtonAtLocation(mouseX, mouseY));
                if(debugArgs.contains("items")) System.out.println(inventory.isItemAtLocation(mouseX, mouseY));
                if(debugArgs.contains("mouse")) System.out.println(ui.getIdList()[mouseX][mouseY] + ", x-pos: " + mouseX + ", y-pos: " + mouseY);

                if(ui.isButtonAtLocation(mouseX, mouseY))
                {
                    Button button = ui.getButtonById(ui.getIdList()[mouseX][mouseY - 25]);
                    //button.click();
                    System.out.println(button.getText());

                    if(debug) hud.setConsoleMessage("[DEBUG] We've clicked something! :O");
                }
                else if(inventory.isItemAtLocation(mouseX, mouseY))
                {
                    Item item = inventory.getItemById(inventory.getIdList()[mouseX][mouseY - 25]);
                    System.out.println(item);
                    System.out.println(inventory.getIdList()[mouseX][mouseY - 25]);

                    if(debug) hud.setConsoleMessage("[DEBUG] We've clicked an item! :O");
                    //item.use(ship);
                    inventory.useItem(ship, inventory.getIdList()[mouseX][mouseY - 25]);
                }
                else if(entityHandler.isEntityAtLocation(mouseX, mouseY))
                {
                    //entityHandler.getShip().shoot(tempEnemy);
                    System.out.println("[ENEMY HIT] " + entityHandler.isEntityAtLocation(mouseX, mouseY));
                }
                break;

        }

        mouseAction = event.getType() + "";
    }

}
