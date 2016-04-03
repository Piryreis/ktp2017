package fr.valentin.ktp2017.util;

/**
 * @author Val'entin.
 */
public class Cooldown extends Thread{

    private int defaultTime;
    private int time;

    private boolean isAlive = true;
    private boolean started = false;

    public Cooldown(int seconde){
        this.time = seconde;
        this.defaultTime = seconde;
    }

    @Override
    public void start(){
        if (getState().equals(State.NEW)){
            super.start();
        }
        started = true;
    }

    @Override
    public void run(){
        while (isAlive) {
            while (started) {
                if (time > 0){
                    time--;
                }
                else end();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    super.start();
                }
            }
        }
    }

    public void pause(){
        started = false;
    }

    public void end(){
        isAlive = false;
    }

    public int getTime(){
        return time;
    }

    public int getDefaultTime(){
        return defaultTime;
    }

    public void reset(){
        time = defaultTime;
    }

    public boolean isStarted(){
        return started;
    }
}