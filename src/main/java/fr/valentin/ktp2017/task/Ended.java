package fr.valentin.ktp2017.task;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

/**
 * @author Val'entin.
 */
public class Ended implements Task {

    private Game game;

    private boolean launched = false;

    private static final Random random = new Random();

    public Ended(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getGameStat().equals(GameManager.GameStat.ENDED)) {
            if (!game.getPlayers().isEmpty()) {
                Location playerLocation = game.getPlayers().get(0).getLocation();
                spawnFirework(playerLocation);
            }

            if (!launched) {
                launched = true;
                Bukkit.getScheduler().runTaskLater(Ktp2017.getInstance(), new Runnable() {
                    @Override
                    public void run() {

                        if (Ktp2017.getInstance().getConfiguration().game_restart) {
                            GameManager.restartGame();
                        } else {
                            GameManager.removeGame();
                        }
                    }
                }, 15 * 20L);
            }
        }
    }

    private void spawnFirework(Location location){
        Firework fw = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        int rt = random.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
        if (rt == 3) type = FireworkEffect.Type.BURST;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;
        if (rt == 5) type = FireworkEffect.Type.STAR;

        int r1i = random.nextInt(4) + 1;
        int r2i = random.nextInt(4) + 1;
        Color c1 = Color.AQUA;
        Color c2 = Color.FUCHSIA;
        if (r1i == 2) c1 = Color.LIME;
        if (r2i == 2) c1 = Color.YELLOW;
        if (r1i == 3) c1 = Color.RED;
        if (r2i == 3) c1 = Color.PURPLE;
        if (r1i == 4) c1 = Color.WHITE;
        if (r2i == 4) c1 = Color.SILVER;
        if (r1i == 5) c1 = Color.ORANGE;
        if (r2i == 5) c1 = Color.MAROON;

        FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(random.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = random.nextInt(2) + 1;
        fwm.setPower(rp);

        fw.setFireworkMeta(fwm);
    }
}
