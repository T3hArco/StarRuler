package me.arco.dev;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:50
 */
public class Star extends Entity
{
    private String image;

    public Star(String type, int listID, int xpos, int ypos, int xrender, int yrender, String image)
    {
        super(type, listID, xpos, ypos, xrender, yrender);
        this.image = image;
    }
}
