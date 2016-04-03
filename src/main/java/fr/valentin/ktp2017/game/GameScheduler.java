package fr.valentin.ktp2017.game;

import fr.valentin.ktp2017.task.Ended;
import fr.valentin.ktp2017.task.StartCooldown;
import fr.valentin.ktp2017.task.Started;
import fr.valentin.ktp2017.task.WaitingPlayer;

/**
 * @author Val'entin.
 */
public class GameScheduler extends Thread {

    private WaitingPlayer waitingPlayer;
    private StartCooldown startCooldown;
    private Started started;
    private Ended ended;

    public GameScheduler(Game game){
        waitingPlayer = new WaitingPlayer(game);
        startCooldown = new StartCooldown(game);
        started = new Started(game);
        ended = new Ended(game);
    }

    @Override
    public void run() {
        waitingPlayer.run();
        startCooldown.run();
        started.run();
        ended.run();
    }

    public void cancel(){
        Thread.interrupted();
    }

}
