package me.arco.dev;

public class Vector2D
{

    private double x;
    private double y;

    public Vector2D(double x, double y)
    {
        super();
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }


    public void add(Vector2D otherVector)
    {
        this.x += otherVector.x;
        this.y += otherVector.y;
    }

    public void subtract(Vector2D otherVector)
    {
        this.x -= otherVector.x;
        this.y -= otherVector.y;
    }

}
