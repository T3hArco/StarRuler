package me.arco.dev.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 25/11/13
 * Time: 19:18
 */
public class Shapes
{
    /**
     * Genereert een willekeurige polygoon voor onze enemy schepen
     *
     * @param sides
     * @param outsideRadius
     * @param insideRadius
     * @return random poly
     */
    public static Shape generatePolygon(int sides, int outsideRadius, int insideRadius, int x, int y)
    {
        if (sides < 3) return new Ellipse2D.Float(x, y, 10, 10);

        AffineTransform affineTransform = new AffineTransform();
        Polygon polygon = new Polygon();

        for (int i = 0; i < sides; i++)
        {
            affineTransform.rotate(Math.PI * 2 / (float) sides / 2);
            Point2D out = affineTransform.transform(new Point2D.Float(x, outsideRadius), null);
            polygon.addPoint((int) out.getX(), (int) out.getY());
            affineTransform.rotate(Math.PI * 2 / (float) sides / 2);

            if (insideRadius > 0)
            {
                Point2D in = affineTransform.transform(new Point2D.Float(y, insideRadius), null);
                polygon.addPoint((int) in.getX(), (int) in.getY());
            }
        }

        return polygon;
    }
}
