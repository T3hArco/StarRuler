package me.arco.dev.entities.living;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 7/10/13
 * Time: 21:20
 */
public class Human extends Humanoid
{
    private final Image image;
    private long timePassed = 0;

    public Human(float x, float y, float motionY, int health, int air, int level)
    {
        super(x, y, (float) 100, (float) 100, "Humanoid", health, air, 1);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/living/images/humanoid.png"));
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.drawImage(image, (int) x, (int) y, null);
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
