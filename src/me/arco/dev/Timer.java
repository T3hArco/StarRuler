package me.arco.dev;

class Timer
{

    private long startTime;

    public Timer()
    {
        startTime = 0l;
    }

    public void set()
    {
        startTime = System.currentTimeMillis();
    }

    public long check()
    {
        return System.currentTimeMillis() - startTime;
    }

    public long stop()
    {
        long delta = System.currentTimeMillis() - startTime;
        startTime = 0;
        return delta;
    }


}
