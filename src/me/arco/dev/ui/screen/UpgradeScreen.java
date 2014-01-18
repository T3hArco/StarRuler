package me.arco.dev.ui.screen;

import me.arco.dev.entities.ship.ShipElement;
import me.arco.dev.ui.UI;

import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 26/11/13
 * Time: 20:53
 */
public class UpgradeScreen
{
    private final Image hangarImage, voidImage, hospitalImage, bridgeImage, engineImage;
    private float xPos = 470;
    private float yPos = 440;

    private UI ui;

    public UpgradeScreen()
    {
        hangarImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/hangar.png"));
        voidImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/void.png"));
        hospitalImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/hospital.png"));
        bridgeImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/bridge.png"));
        engineImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/engines.png"));
    }

    private List<ShipElement> shipElements;
    private int position = 0;

    public void setShipElements(List<ShipElement> shipElements)
    {
        this.shipElements = shipElements;
    }

    public void renderBack()
    {
        System.out.println(position);
        if (position - 1 > 0) position--;
    }

    public void renderForward()
    {
        if (position + 1 <= shipElements.size() - 1) position++;
    }

    public void setUI(UI ui)
    {
        this.ui = ui;
        ui.addButton("UPGRADE", (int) xPos + 90, (int) yPos + 95, 10, 30, "toggleupgrades", false);
    }

    public void draw(Graphics2D g)
    {
        g.drawRect((int) xPos, (int) yPos, 250, 128);
        g.drawLine((int) xPos, (int) yPos + 55, (int) xPos + 250, (int) yPos + 55);
        g.setColor(new Color(0.56f, 1.0f, .5f, .5f));
        g.fillRect((int) xPos, (int) yPos, 251, 129);


        g.setColor(Color.WHITE);
        g.drawString("Upgrade your " + shipElements.get(position).getType().toString().toLowerCase().replace("_", " "), xPos + 0, yPos - 5);

        switch (shipElements.get(position).getType())
        {
            case VOID:
                g.drawImage(voidImage, (int) xPos + 105, (int) yPos + 5, null);
                break;

            case HANGAR:
                g.drawImage(hangarImage, (int) xPos + 105, (int) yPos + 5, null);
                break;

            case HOSPITAL:
                g.drawImage(hospitalImage, (int) xPos + 105, (int) yPos + 5, null);
                break;

            case BRIDGE:
                g.drawImage(bridgeImage, (int) xPos + 105, (int) yPos + 5, null);
                break;

            case ENGINE:
                g.drawImage(engineImage, (int) xPos + 105, (int) yPos + 5, null);
                break;

            default:
                System.err.println("[ERROR] UNKNOWN TYPE!"); // TODO: make error styrings
                break;

        }

        g.drawString("Element level: " + shipElements.get(position).getLevel(), (int) xPos + 5, (int) yPos + 80);

        /*g.drawString("Max health: ", (int) xPos + 5, (int) yPos + 101);
        g.drawString("Max population: ", (int) xPos + 5, (int) yPos + 120);*/
    }
}
