package me.arco.dev.entities.enemy;

import me.arco.dev.entities.Entity;
import me.arco.dev.entities.ship.Ship;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

import static me.arco.dev.util.Shapes.generatePolygon;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 18/11/13
 * Time: 19:49
 */
public class Enemy extends Entity
{
    private double health = 1000;
    private double shield = 1000;
    private int xOffset = 1000;
    private boolean dead = false;
    private boolean shooting = false;
    private float tempx, tempy;
    private Random random = new Random();
    private int yOffset = random.nextInt(1000);
    private int angle = 0;
    private int shootCount;
    private boolean moving = true;
    private String type;

    private int[] optVars = {random.nextInt(10), random.nextInt(500), random.nextInt(500)};

    public static enum Type
    {
        WIJNSINATOR, WIJNSINATOR_ANGRY, KAMIELINATOR, NICPISAURUS, GENERIC
    }

    private Image wijnsinator, wijnsinatorangry, nicpisaurus, kamieletron;
    private Type types;

    public Enemy(float x, float y, float width, float height, float motionX, float motionY, String type)
    {
        super(x, y, width, height, motionX, motionY, type);
        wijnsinator = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/Wijns.png"));
        wijnsinatorangry = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/WijnsULTIMATE.png"));
        nicpisaurus = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/NicpTri2.png"));
        this.type = type;
    }

    public void setType(Type type)
    {
        this.types = type;
    }

    public void hit(int multiplier)
    {
        if (random.nextBoolean())
        {
            if (shield <= 0)
            {
                health -= random.nextInt(20) * ((double) 2 / 3) * multiplier;
                shield = 0;
            } else
            {
                shield -= random.nextInt(20) / ((double) 2 / 2) * multiplier;
                if (random.nextBoolean())
                {
                    health -= random.nextInt(20) / ((double) 2 / 4);
                }
            }

            if (health <= 0)
            {
                dead = true;
                health = 0;
                System.out.println("died");
            }
        }
    }

    public boolean checkIfDead()
    {
        if (dead) return true;

        return false;
    }

    @Override
    public boolean shipElementAtPos(int x, int y)
    {
        return false;
    }

    public void shoot(Ship ship)
    {
        tempx = ship.getX();
        tempy = ship.getY();

        shooting = true;
        shootCount = 0;

        ship.hit();
    }

    public String getType()
    {
        return type;
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.RED);
        g.fillRect((int) x + 10 - xOffset, (int) y - 20, 50, 10);

        g.setColor(new Color(23, 128, 3));
        g.fillRect((int) x + 10 - xOffset, (int) y - 20, (int) (health / 1000 * 50), 10);

        g.setColor(Color.RED);
        g.fillRect((int) x + 10 - xOffset, (int) y - 40, 50, 10);

        g.setColor(Color.GRAY);
        g.fillRect((int) x + 10 - xOffset, (int) y - 40, (int) (shield / 1000 * 50), 10);

        switch (types)
        {
            case WIJNSINATOR:
                int yTemp = 0;

                if (x - xOffset <= x)
                {
                    xOffset--;
                    yOffset--;
                } else
                {
                    types = Type.WIJNSINATOR_ANGRY;
                }

                AffineTransform transform = g.getTransform();

                g.rotate(Math.toRadians(angle));
                g.drawImage(wijnsinator, (int) x - xOffset, (int) y - yTemp, null);
                g.rotate(Math.toRadians(-angle));

                g.setTransform(transform);

                yTemp++;
                angle += 5;

                break;

            case WIJNSINATOR_ANGRY:
                g.drawImage(wijnsinatorangry, (int) x, (int) y, null);
                break;

            case KAMIELINATOR:
                g.drawImage(nicpisaurus, (int) x, (int) y, null); // TODO: maak een kamiel derp
                break;

            case NICPISAURUS:
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.translate(x - xOffset, y);
                //while(moving) affineTransform.rotate(Math.PI/random.nextInt(10));

                angle++;
                if (angle < 2000 && moving == true)
                {
                    affineTransform.rotate(Math.toRadians(5));
                    xOffset++;
                } else
                {
                    moving = false;
                }
                //affineTransform.translate(-nicpisaurus.getWidth() / 2, -nicpisaurus.getHeight() / 2);

                g.drawImage(nicpisaurus, (int) x - xOffset, (int) y, null);
                //g.drawImage(nicpisaurus, affineTransform, null);
                break;

            default:
            case GENERIC:
                g.draw(generatePolygon(optVars[0], optVars[1], optVars[2], (int) x, (int) y));  // TODO fix this shnit
                break;
        }

        if (shooting)
        {
            while (shootCount <= 4500)
            {
                g.setColor(new Color(128, 0, 6));
                g.drawLine((int) x, (int) y, (int) tempx + random.nextInt(20), (int) tempy + random.nextInt(20));
                shootCount++;
            }

            shooting = false;
        }
        /*if (shooting)
        {
            g.setColor(new Color(128, 0, 6));

            Polygon p = new Polygon();
            p.addPoint((int) x, (int) y);
            p.addPoint((int) x, (int) (y + tempy));
            p.addPoint((int) (x + 20), (int) (y + tempy));
            g.fillPolygon(p);
            shootCount++;
            shooting = false;
        }*/
    }
}
