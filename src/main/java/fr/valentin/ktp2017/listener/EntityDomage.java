package fr.valentin.ktp2017.listener;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.game.Game;
import fr.valentin.ktp2017.game.GameManager;
import fr.valentin.ktp2017.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.EnumMap;

/**
 * @author Val'entin.
 */
public class EntityDomage implements Listener {

    private static EnumMap<EntityDamageEvent.DamageCause, String> causeMessage = new EnumMap<EntityDamageEvent.DamageCause, String>(EntityDamageEvent.DamageCause.class);
    static {
        causeMessage.put(EntityDamageEvent.DamageCause.FALL, "une chute");
        causeMessage.put(EntityDamageEvent.DamageCause.DROWNING, "une noyade");
        causeMessage.put(EntityDamageEvent.DamageCause.FIRE, "brulure");
    }

    @EventHandler
    public void onEntityDomage(EntityDamageEvent event){
        Game game = GameManager.getGame();
        if (event.getEntity() instanceof Player){
            if (!GameManager.gameIsEmpty() && game.getGameStat().equals(GameManager.GameStat.STARTED)){
                Player player = (Player) event.getEntity();
                if (event.getDamage() > player.getHealth()){
                    event.setCancelled(true);
                    game.getPlayers().killPlayer(player);
                    broadcastDiedMessageWithCause(event.getCause(), player);
                }
            } else event.setCancelled(true);
        }
    }

    private void broadcastDiedMessageWithCause(EntityDamageEvent.DamageCause cause, Player player){
        String damageCause = causeMessage.get(cause);
        if (damageCause != null) {
            Ktp2017.broadcastMessage(MessageUtil.createDeathMessage(player.getDisplayName(), damageCause));
        }
        else {
            if (cause.equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) && player.getLastDamageCause() != null) {
            Ktp2017.broadcastMessage(MessageUtil.createDeathMessage(player.getDisplayName(), player.getLastDamageCause().getEntity().getName()));
            }
            else {
                Ktp2017.broadcastMessage(MessageUtil.createDeathMessage(player.getDisplayName(), cause.name()));
            }
        }
    }

}
