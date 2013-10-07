package me.arco.dev;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GameWindow
{

    private JFrame frame;
    private JPanel renderingPanel;
    private BufferedImage frameBuffer;
    private KeyboardEventManager keyboardEventManager;

    protected int width;
    protected int height;
    private boolean running;

    public GameWindow(String title, int width, int height)
    {
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        renderingPanel = new JPanel();
        frame.add(renderingPanel);

        keyboardEventManager = new KeyboardEventManager();
        frame.addKeyListener(keyboardEventManager);

        frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        frame.pack();
        frame.setVisible(true);
    }

    public void run()
    {
        initialize();
        running = true;

        Timer frameTimer = new Timer();

        while (running)
        {
            while (keyboardEventManager.hasKeyboardEvents())
            {
                KeyboardEvent keyboardEvent = keyboardEventManager.getNextKeyboardEvent();
                handleKeyboardEvent(keyboardEvent);
            }



            update(frameTimer.stop() % 1000);
            frameTimer.set();

			/* clear the framebuffer... (just draw a black rect over the entire window) */
            Graphics bufferRenderHandle = frameBuffer.getGraphics();
            bufferRenderHandle.setColor(Color.BLACK);
            bufferRenderHandle.fillRect(0, 0, 1280, 720);

            render((Graphics2D) bufferRenderHandle);

            swapBuffers();


            //Throttle for CPU usage... (optional)
            long timePassed = frameTimer.check();
            if (timePassed < 20)
            {
                try
                {
                    Thread.sleep(20 - timePassed);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        WindowEvent closeWindowEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindowEvent);
    }

    protected void swapBuffers()
    {
        Graphics g = renderingPanel.getGraphics();
        g.drawImage(frameBuffer, 0, 0, null);
    }

    public void shutdown()
    {
        running = false;
    }

    protected abstract void initialize();

    protected abstract void update(long delta);

    protected abstract void render(Graphics2D renderHandle);

    protected abstract void handleKeyboardEvent(KeyboardEvent event);

}
