package me.arco.dev.entities.ship;

import me.arco.dev.entities.Entity;
import me.arco.dev.entities.enemy.Enemy;
import me.arco.dev.entities.living.Humanoid;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:50
 */
public class Ship extends Entity
{
    private long timePassed = 0;
    private final Random random = new Random();
    private float health, shield = health = 1000;
    private final List<ShipElement> shipElements = new ArrayList<ShipElement>();
    //private List<Humanoid> humanoids = new ArrayList<Humanoid>();
    private boolean dead = false;
    private final int elementPx = 45;
    private final Image hangarImage, voidImage, hospitalImage, bridgeImage, engineImage;
    private boolean shooting;
    private int shootCount = 0;
    private Entity tempEnemy;
    private Image tempImage;
    private float tempx, tempy;
    private int level = 1;

    public Ship(float x, float y, float motionX, String type) throws IOException
    {
        super(x, y, 45, 45, (float) 100, (float) 100, type);
        this.motionX = 1;
        AtomicReference<List<ShipElement.Type>> types = new AtomicReference<List<ShipElement.Type>>(new ShipElement().getTypes());
        List<ShipElement.Offset> offsetList = new ShipElement().getOffsetList();

        /*for(int i = 0; i < 20; i++)
        {
            shipElements.add(new ShipElement(types.get().get(random.nextInt(types.get().size())), offsetList.get(random.nextInt(offsetList.size()))));
        }*/

        shipElements.add(new ShipElement(ShipElement.Type.BRIDGE, ShipElement.Offset.TOP));

        hangarImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/hangar.png"));
        voidImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/void.png"));
        hospitalImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/hospital.png"));
        bridgeImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/bridge.png"));
        engineImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/engines.png"));
    }

    public float getHealth()
    {
        return (health / 1000) * 187;
    }

    public float getShield()
    {
        return (shield / 1000) * 187;
    }

    public void addToHealth()
    {
        if (this.health + (double) 250 >= 1000)
        {
            this.health = 1000;
        } else
        {
            this.health += (double) 250;
        }
    }

    public void removeFromHealth()
    {
        if (this.health - (double) 250 <= 0)
        {
            this.health = 0;
        } else
        {
            this.health -= (double) 250;
        }
    }

    public void addToShield(double amount)
    {
        if (this.shield + amount >= 1000)
        {
            this.shield = 1000;
        } else
        {
            this.shield += amount;
        }
    }

    public List<ShipElement> getShipElements()
    {
        return shipElements;
    }

    public void removeFromShield()
    {
        if (this.shield - (double) 100 <= 0)
        {
            this.shield = 0;
        } else
        {
            this.shield -= (double) 100;
        }
    }

    public void hit()
    {
        if (shield <= 0)
        {
            health -= random.nextInt(15) * ((double) 2 / 3);
            shield = 0;
        } else
        {
            shield -= random.nextInt(15) / ((double) 2 / 2);
            if (random.nextBoolean())
            {
                health -= random.nextInt(15) / ((double) 2 / 4);
            }
        }

        if (health <= 0)
        {
            dead = true;
            health = 0;
        }
    }

    public boolean checkIfDead()
    {
        return dead;
    }

    public void addHuman()
    {
        shipElements.get(random.nextInt(shipElements.size())).addHumanoid();
    }

    public List<Humanoid> getAllHumanoids()
    {
        List<Humanoid> humanoids = new ArrayList<Humanoid>();

        for (ShipElement shipElement : shipElements)
        {
            humanoids.addAll(shipElement.getHumanoids());
        }

        return humanoids;
    }

    public boolean shipElementAtPos(int x, int y)
    {
        int[] offsets = new int[8];

        for (ShipElement shipElement : shipElements)
        {
            // Default values
            int shipX = 350;
            int shipY = 350;

            switch (shipElement.getOffset())
            {
                case TOP:
                    shipX = shipX + (offsets[0] * 45);
                    if (x >= shipX && x <= shipX) return true;

                    offsets[0]++;
                    break;

                case BOTTOM:
                    shipX = shipX - (offsets[1] * 45);
                    if (x >= shipX && x <= shipX) return true;

                    offsets[1]++;
                    break;

                case LEFT:
                    shipX = shipX - (offsets[2] * 45);
                    if (x >= shipX && x <= shipX) return true;

                    offsets[2]++;
                    break;

                case RIGHT:
                    offsets[3]++;
                    break;

                case TOP_RIGHT:
                    offsets[4]++;
                    break;

                case TOP_LEFT:
                    offsets[5]++;
                    break;

                case BOTTOM_RIGHT:
                    offsets[6]++;
                    break;

                case BOTTOM_LEFT:
                    offsets[7]++;
                    break;
            }
        }

        return false;
    }

    public void shoot(Enemy enemy)
    {
        tempx = enemy.getX();
        tempy = enemy.getY();

        shooting = true;
        shootCount = 0;

        enemy.hit(level);
        tempEnemy = enemy;
    }

    public void levelUp()
    {
        level++;
    }

    public void addNewRandomElement()
    {
        AtomicReference<List<ShipElement.Type>> types = new AtomicReference<List<ShipElement.Type>>(new ShipElement().getTypes());
        List<ShipElement.Offset> offsetList = new ShipElement().getOffsetList();

        shipElements.add(new ShipElement(types.get().get(random.nextInt(types.get().size())), offsetList.get(random.nextInt(offsetList.size()))));
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.fillRect((int) x, (int) y, elementPx, elementPx);

        int topOffset = 0, bottomOffset = 1, leftOffset = 1, rightOffset = 1, topLeftOffset = 1, topRightOffset = 1, bottomLeftOffset = 1, bottomRightOffset = 1;

        for (ShipElement shipElement : shipElements)
        {
            switch (shipElement.getType())
            {
                case VOID:
                    tempImage = voidImage;
                    break;

                case HANGAR:
                    tempImage = hangarImage;
                    break;

                case HOSPITAL:
                    tempImage = hospitalImage;
                    break;

                case BRIDGE:
                    tempImage = bridgeImage;
                    break;

                case ENGINE:
                    tempImage = engineImage;
                    break;

                default:
                    tempImage = voidImage;
                    break;
            }


            switch (shipElement.getOffset())
            {
                case BOTTOM:
                    g.drawImage(tempImage, (int) x, (int) y - (bottomOffset * elementPx), null);
                    bottomOffset++;
                    break;

                case TOP:
                    g.drawImage(tempImage, (int) x, (int) y + (topOffset * elementPx), null);
                    topOffset++;
                    break;

                case LEFT:
                    g.drawImage(tempImage, (int) x - (leftOffset * elementPx), (int) y, null);
                    leftOffset++;
                    break;

                case RIGHT:
                    g.drawImage(tempImage, (int) x + (rightOffset * elementPx), (int) y, null);
                    rightOffset++;
                    break;

                case TOP_LEFT:
                    g.drawImage(tempImage, (int) x - (topLeftOffset * elementPx), (int) y + (topLeftOffset * elementPx), null);
                    topLeftOffset++;
                    break;

                case TOP_RIGHT:
                    g.drawImage(tempImage, (int) x + (topRightOffset * elementPx), (int) y + (topRightOffset * elementPx), null);
                    topRightOffset++;
                    break;

                case BOTTOM_LEFT:
                    g.drawImage(tempImage, (int) x - (bottomLeftOffset * elementPx), (int) y - (bottomLeftOffset * elementPx), null);
                    bottomLeftOffset++;
                    break;

                case BOTTOM_RIGHT:
                    g.drawImage(tempImage, (int) x + (bottomRightOffset * elementPx), (int) y - (bottomRightOffset * elementPx), null);
                    bottomRightOffset++;
                    break;
            }
        }

        if (shooting)
        {
            while (shootCount <= 4500)
            {
                g.setColor(new Color(49, 128, 76));
                g.drawLine(350, 350, (int) tempx + random.nextInt(20), (int) tempy + random.nextInt(20));
                shootCount++;
            }

            shooting = false;
        }
    }

    public int getTotalPopulation()
    {
        int total = 0;

        for (ShipElement shipElement : shipElements)
        {
            total += shipElement.getHumanoidCount();
        }

        return total;
    }

    @Override
    public void update(long delta)
    {
        timePassed += delta;
        if (timePassed > 10000)
        {
            timePassed = 0;
            motionX *= -1;
        }
        super.update(delta);
    }
}

