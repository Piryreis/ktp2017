package fr.valentin.ktp2017.command;

import fr.valentin.ktp2017.Ktp2017;
import fr.valentin.ktp2017.arena.ArenaManager;
import fr.valentin.ktp2017.game.GameManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * @author Val'entin.
 */
public class ktp2017Commands implements CommandExecutor {

    private Ktp2017 ktp2017 = Ktp2017.getInstance();

    public ktp2017Commands(){
        Command command = ktp2017.getCommand("ktp2017");

        command.setPermissionMessage(Ktp2017.getTag() + "Plugin par " + StringUtils.join(ktp2017.getDescription().getAuthors(), ", ")
                + " version " + ktp2017.getDescription().getVersion());

        command.setUsage(Ktp2017.getTag() + command.getUsage());
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(Ktp2017.getTag() + ChatColor.RED + "Erreur: Vous devez être un joueur pour effectuer cette commande.");
            return true;
        }

        Player player = (Player)commandSender;

        // Si le premier argument est mauvais, on envoie le help
        if (args.length == 0){
            for (String help : getHelp.values()) {
                player.sendMessage(Ktp2017.getTag() + help);
            }
            return true;
        }

        // /ktp2017 newGame ...
        if (args[0].equalsIgnoreCase("newGame")){
            if (ArenaManager.getArena() == null){
                player.sendMessage(Ktp2017.getTag() + ChatColor.RED + "Erreur: Veuillez configurer une arène.");
                player.sendMessage(Ktp2017.getTag() + getHelp.get("newArena"));
            } else {
                GameManager.loadGame();
                player.sendMessage(Ktp2017.getTag() + ChatColor.GREEN + "Nouvelle partie démarré, les joueurs peuvent rejoindre la partie.");
                player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Veullez vous reconnectez pour pouvoir rentrer dans la partie.");
            }
            return true;
        }

        // /ktp2017 newArena ...
        if (args[0].equalsIgnoreCase("newArena")){
            try {
                String name = args[1];
                int size = Integer.parseInt(args[2]);
                boolean worldborder = Boolean.parseBoolean(args[3]);
                Location center = player.getLocation();

                ArenaManager.newArena(name, center, size, worldborder);
                player.sendMessage(Ktp2017.getTag() + ChatColor.GREEN + "L'arène est bien été crée.");

            } catch (Exception e){
                player.sendMessage(Ktp2017.getTag() + ChatColor.RED + "Erreur: Veuillez bien remplir les arguments.");
                player.sendMessage(Ktp2017.getTag() + getHelp.get("newArena"));
            }
            return true;
        }

        return false;
    }

    private static HashMap<String, String> getHelp =  new HashMap<String, String>();
    static {
        getHelp.put("newGame", "/ktp2017 newGame");
        getHelp.put("newArena", "/ktp2017 newArena <name> <size> <worldborder(true|false)>");
    }

}
