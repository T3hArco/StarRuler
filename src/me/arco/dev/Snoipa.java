package me.arco.dev;

import me.arco.dev.debug.DebugScreen;
import me.arco.dev.entities.Entity;
import me.arco.dev.entities.EntityHandler;
import me.arco.dev.entities.living.Humanoid;
import me.arco.dev.entities.ship.ShipElement;
import me.arco.dev.gamestate.GamestateHandler;
import me.arco.dev.items.Inventory;
import me.arco.dev.items.Item;
import me.arco.dev.sound.SoundHandler;
import me.arco.dev.ui.Button;
import me.arco.dev.ui.Hud;
import me.arco.dev.ui.UI;
import me.arco.dev.ui.screen.UpgradeScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Snoipa extends GameApplet {
    private final String author = "Arnaud Coel (code!), Kamiel Klumpers (music!)";
    private final String version = "2.1.0";
    private final String branch = "";
    private final String[] information = new String[6];
    private final Random random = new Random();
    private final List<UI> uiElements = new ArrayList<UI>();
    private String debugArgs = "items";
    private float shipHealth, shipShield;
    private boolean renderInventory, renderHud, debug, gameover, renderDebug, upgrades, elements, exit, doUpgrade, gamestarted, credits;
    private int mouseX, mouseY = mouseX = 0;
    private String mouseAction;
    private long now;
    private int framesCount = 0;
    private int framesCountAvg = 0;
    private long framesTimer = 0;
    private EntityHandler entityHandler = new EntityHandler();
    private UI ui;
    private Hud hud;
    private Inventory inventory;
    private DebugScreen debugScreen;
    private SoundHandler soundHandler = new SoundHandler();
    private UpgradeScreen upgradeScreen = new UpgradeScreen();
    private GamestateHandler gamestateHandler = new GamestateHandler();
    private Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png"));

    @Override
    protected void initialize() {
        debug = false;

        gamestateHandler.setGamestate(GamestateHandler.GameState.MAINSCREEN);

        if (debug) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Debug arguments separated by comma: ");
            debugArgs = scanner.nextLine();

            System.out.println("Resuming game");
        }

        System.out.println(this.getClass().getSimpleName() + " - " + version + ", " + branch + ". By: " + author);

        inventory = new Inventory();

        for (int i = 0; i < 75; i++)
            entityHandler.addEntity(random.nextInt(650), random.nextInt(650), 100, 100, "star", EntityHandler.Type.STAR);

        entityHandler.addEntity(350, 350, 100, 100, "ship", EntityHandler.Type.SHIP);

        ui = new Hud();
        uiElements.add(ui);

        if (debugArgs.contains("items")) {
            for (int i = 0; i < 6; i++) {
                inventory.addItem(i);
            }
        }

        hud = (Hud) ui;
        debugScreen = new DebugScreen();

        ui.addButton("Upgrades", 10, 555, 10, 30, "toggleupgrades");
        ui.addButton("New Element", 95, 555, 10, 30, "toggleelements");
        ui.addButton("Quit", 212, 555, 10, 30, "toggleexit");
        ui.addButton("Start Game", 320, 380, 10, 30, "game_togglestart", true);
        ui.addButton("Credits", 280, 380, 10, 30, "togglecredits");

        upgradeScreen.setShipElements(entityHandler.getShip().getShipElements());
        upgradeScreen.setUI(ui);

        soundHandler.makeSound("music/idk.wav");
        System.out.println("Java version is: " + System.getProperty("java.version"));
    }

    @Override
    protected void update(long delta) {
        List<Button> tempList = ui.getButtons();
        /*

            0. Upgrades
            1. New Element
            2. Quit
            3. Start Game
            4. Credits
            5. UPGRADE

         */

        upgrades = tempList.get(0).isToggled();
        elements = tempList.get(1).isToggled();
        tempList.get(1).disable();
        exit = tempList.get(2).isToggled(); // piss off
        tempList.get(2).disable();
        gamestarted = tempList.get(3).isToggled(); // gamestarted boolean
        credits = tempList.get(4).isToggled();
        tempList.get(4).disable();
        doUpgrade = tempList.get(5).isToggled(); // upgrades

        //for(int i = 0; i < tempList.size(); i++) System.out.println(i + ". " + tempList.get(i).getText());
        //System.out.println(tempList.get(4).isToggled());

        if (gamestarted) {
            gamestateHandler.setGamestate(GamestateHandler.GameState.INGAME);
            tempList.get(3).renderOff();
            tempList.get(3).makeUntogglable();
            tempList.get(4).renderOff();
            tempList.get(4).makeUntogglable();
        }

        if (gamestateHandler.getGamestate().equals(GamestateHandler.GameState.INGAME)) {
            for (Entity entity : entityHandler.getEntities()) {
                if (entity.getType().equals("star")) {
                    entity.update(1);
                } else if (entity.getType().equals("ship")) {
                    shipHealth = entityHandler.getShip().getHealth();
                    shipShield = entityHandler.getShip().getShield();
                }
            }

            if (debugArgs.contains("damage")) {
                if (random.nextInt(1000) == 250) {
                    entityHandler.getShip().hit();
                    hud.setConsoleMessage("DEBUG: hit!");
                }
            }

            if (entityHandler.getShip().checkIfDead()) gameover = true;

            if (random.nextInt(10000) == 500) {
                if (random.nextBoolean()) {
                    entityHandler.addEntity(random.nextInt(400), random.nextInt(300), 100, 100, "wijns", EntityHandler.Type.ENEMY);
                }
            }

            if (doUpgrade) {
                entityHandler.getShip().levelUp();
                for (ShipElement shipElement : entityHandler.getShip().getShipElements()) shipElement.levelUp();
                entityHandler.getShip().addNewRandomElement();

                tempList.get(5).click();
                doUpgrade = false;
            }

            if (credits) {
                gamestateHandler.setGamestate(GamestateHandler.GameState.CREDITS);
                tempList.get(3).renderOff();
                tempList.get(3).makeUntogglable();
                tempList.get(4).renderOff();
                tempList.get(4).makeUntogglable();
            }


            // start compiling debug information
            information[0] = framesCountAvg + "";
            information[1] = entityHandler.getShip().getTotalPopulation() + "";
            information[2] = "n/i";
            information[3] = mouseX + "";
            information[4] = mouseY + "";
            information[5] = mouseAction;

            if (!upgrades || !credits)
                for (Button button : ui.getButtons()) if (button.isCanBeClosed()) button.renderOff();
            if (upgrades || credits)
                for (Button button : ui.getButtons()) if (button.isCanBeClosed()) button.renderOn();

            if (random.nextInt(100) == 5)
                if (entityHandler.randomEnemyShoot()) soundHandler.makeSound("generic/pew.wav");
            entityHandler.cleanList();

            // if(exit) shutdown();
        } else if (gamestateHandler.getGamestate().equals(GamestateHandler.GameState.CREDITS)) {
            // LOOP FOR CREDITS
        }

    }

    @Override
    protected void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);

        // Render version and stuff
        g.setColor(Color.WHITE);
        g.drawString(version + " " + branch, 5, 15);
        g.drawString(hud.getConsoleMessage() + "", 745 - hud.getConsoleMessage().length() * 8, 15);

        g.drawString("FPS: " + framesCountAvg, 680, 15);

        switch (gamestateHandler.getGamestate()) {
            case MAINSCREEN:
                g.drawImage(logo, 117, 160, null);

                for (Button button : ui.getButtons()) {
                    if (button.getText().contains("Game") || button.getText().contains("Credits")) {
                        button.draw(g);
                    }
                }
                break;

            case INGAME:
                // Render all the entities
                for (Entity entity : entityHandler.getEntities()) entity.draw(g);

                // Rendering inventory if true
                if (!renderInventory) inventory.draw(g);

                // Rendering our debugging window
                if (renderDebug) debugScreen.draw(g, information);

                // Rendering the humanoids
                for (Humanoid humanoid : entityHandler.getShip().getAllHumanoids()) humanoid.draw(g);

                // Print our gameover if it's over ;)
                if (gameover) {
                    g.setColor(new Color(1f, 0f, 0f, .5f));
                    g.fillRect(0, 0, 735, 745);
                }

                if (upgrades) upgradeScreen.draw(g);

                //if(elements) { }

                // Rendering our UI
                if (!renderHud) {
                    for (UI ui : uiElements) ui.draw(g, shipShield, shipHealth);
                    for (Button button : ui.getButtons()) if (button.isRender()) button.draw(g);
                }
                break;

            case CREDITS:
                gamestateHandler.setGamestate(GamestateHandler.GameState.INGAME);
                break;

            case GAME_OVER:

                break;
        }


        now = System.currentTimeMillis();
        framesCount++;
        if (now - framesTimer > 1000) {
            framesTimer = now;
            framesCountAvg = framesCount;
            framesCount = 0;
        }
    }

    @Override
    protected void handleKeyboardEvent(KeyboardEvent event) {
        if (event.getType() == KeyboardEvent.DOWN) {
            switch (event.getKeyCode()) {
                case KeyboardEvent.KEY_I:
                    renderInventory ^= true; // fancy xor!
                    break;

                /*case KeyboardEvent.KEY_0:
                    hud.setConsoleMessage("[DEBUG] ATTACKED SELF");
                    entityHandler.getShip().hit();
                    break;*/

                case KeyboardEvent.KEY_H:
                    renderHud ^= true; // more fanciness!
                    break;

                case KeyboardEvent.KEY_D:
                    renderDebug ^= true;
                    break;

                case KeyboardEvent.KEY_7:
                    entityHandler.getShip().addNewRandomElement();
                    break;

                case KeyboardEvent.KEY_S:
                    entityHandler.addEntity(random.nextInt(400), random.nextInt(300), 100, 100, "wijns", EntityHandler.Type.ENEMY);
                    System.out.println("spawned");
                    break;

                /*case KeyboardEvent.KEY_E:
                    entityHandler.addEntity(100, 100, 100, 100, "generic", EntityHandler.Type.GENERIC);
                    System.out.println("Guess I got my polygon back!");
                    break;

                case KeyboardEvent.KEY_N:
                    entityHandler.addEntity(100, 100, 100, 100, "nicpisaur", EntityHandler.Type.ENEMY);
                    System.out.println("Got nicpisaur!");
                    break;*/

                case KeyboardEvent.KEY_LEFT:
                    if (upgrades) upgradeScreen.renderBack();
                    else if (elements) {
                    }
                    break;

                case KeyboardEvent.KEY_RIGHT:
                    if (upgrades) upgradeScreen.renderForward();
                    else if (elements) {
                    }
                    break;
            }
        }
    }

    @Override
    protected void handleMouseEvent(MouseEvent event) {
        if (debugArgs.contains("mouse")) System.out.println(event);

        switch (event.getType()) {
            case CLICKED:
                mouseX = event.getX();
                mouseY = event.getY();

                if (debugArgs.contains("ui")) System.out.println(ui.isButtonAtLocation(mouseX, mouseY));
                if (debugArgs.contains("items")) System.out.println(inventory.isItemAtLocation(mouseX, mouseY));
                if (debugArgs.contains("mouse"))
                    System.out.println(ui.getIdList()[mouseX][mouseY] + ", x-pos: " + mouseX + ", y-pos: " + mouseY);

                if (ui.isButtonAtLocation(mouseX, mouseY)) {
                    Button button = ui.getButtonById(ui.getIdList()[mouseX][mouseY]);
                    button.click();

                    if (debug) hud.setConsoleMessage("[DEBUG] We've clicked something! :O");
                } else if (inventory.isItemAtLocation(mouseX, mouseY) && gamestateHandler.getGamestate().equals(GamestateHandler.GameState.INGAME)) {
                    Item item = inventory.getItemById(inventory.getIdList()[mouseX][mouseY]);
                    System.out.println(item);
                    System.out.println(inventory.getIdList()[mouseX][mouseY]);

                    if (debug) hud.setConsoleMessage("[DEBUG] We've clicked an item! :O");
                    inventory.useItem(entityHandler.getShip(), inventory.getIdList()[mouseX][mouseY]);
                } else if (entityHandler.isEntityAtLocation(mouseX, mouseY) && gamestateHandler.getGamestate().equals(GamestateHandler.GameState.INGAME)) {
                    if (!entityHandler.getEnemyAtLocation(mouseX, mouseY).getType().equals("Nothing")) {
                        entityHandler.getShip().shoot(entityHandler.getEnemyAtLocation(mouseX, mouseY));
                        System.out.println("[ENEMY HIT] " + entityHandler.isEntityAtLocation(mouseX, mouseY));
                        soundHandler.makeSound("generic/pew.wav");
                    }
                }
                /*else if(entityHandler.getShip().shipElementAtPos(mouseX, mouseY))
                {
                    System.out.println("Element: " + entityHandler.getShip().shipElementAtPos(mouseX, mouseY));
                    System.out.println("[DEBUG] Ship element hit!");
                }
                break;*/
                break;

        }

        mouseAction = event.getType() + "";
    }

}
