package me.arco.dev.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 18/11/13
 * Time: 18:22
 */
public class SoundHandler
{
    public static synchronized void makeSound(final String url)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((getClass().getResource(url)));
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception e)
                {
                    System.err.println("[ERROR] Failed to open audio file @ " + getClass().getResource(url));
                    System.out.println(e);
                }
            }
        }).start();
    }
}
