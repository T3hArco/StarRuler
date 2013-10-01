package me.arco.dev;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:50
 */
public class Ship extends Entity
{
    private String image;
    private int health;

    public Ship(String type, int listID, int xpos, int ypos, int xrender, int yrender, String image, int health)
    {
        super(type, listID, xpos, ypos, xrender, yrender);
        this.image = image;
        this.health = health;
    }
}
