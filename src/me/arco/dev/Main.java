package me.arco.dev;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        Snoipa game = new Snoipa();

        JFrame frame = new JFrame("StarRuler - Desolation of Wijns");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);

        game.setPreferredSize(new Dimension(735, 720));

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        game.init();
    }
}
