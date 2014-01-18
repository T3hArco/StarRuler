package me.arco.dev;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static java.lang.System.currentTimeMillis;

public abstract class GameApplet extends Applet implements Runnable
{
    private Canvas canvas;
    private BufferStrategy buffer;

    private KeyboardEventManager keyboardEventManager;
    private MouseEventManager mouseEventManager;

    protected GameApplet() throws HeadlessException
    {
        this.keyboardEventManager = new KeyboardEventManager();
        this.mouseEventManager = new MouseEventManager();
    }

    @Override
    public void init()
    {
        canvas = new Canvas();
        canvas.addKeyListener(keyboardEventManager);

        canvas.addMouseListener(mouseEventManager);
        canvas.addMouseMotionListener(mouseEventManager);

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        initialize();

        new Thread(this).start();
    }

    @Override
    public void run()
    {
        long lastUpdate = currentTimeMillis(), millis = 0, delta = 0;
        long lastSleep = currentTimeMillis(), interval = 1000 / 60, sleep = 0;
        while (isDisplayable())
        {
            try
            {
                millis = currentTimeMillis();
                delta = millis - lastUpdate;
                lastUpdate = millis;

                while (keyboardEventManager.hasKeyboardEvents())
                {
                    KeyboardEvent keyboardEvent = keyboardEventManager.getNextKeyboardEvent();
                    handleKeyboardEvent(keyboardEvent);
                }

                while (mouseEventManager.hasMouseEvents())
                {
                    MouseEvent mouseEvent = mouseEventManager.getNextMouseEvent();
                    handleMouseEvent(mouseEvent);
                }

                update(delta);
                render((Graphics2D) buffer.getDrawGraphics());
                buffer.show();

                sleep = interval - (currentTimeMillis() - lastSleep);
                if (sleep > 0)
                {
                    Thread.sleep(sleep);
                } else
                {
                    System.out.println(-sleep + "ms lagg!");
                }

                lastSleep = currentTimeMillis();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    protected abstract void initialize();

    protected abstract void update(long delta);

    protected abstract void render(Graphics2D renderHandle);

    protected abstract void handleKeyboardEvent(KeyboardEvent event);

    protected abstract void handleMouseEvent(MouseEvent event);
}
