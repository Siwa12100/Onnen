package dev.siwa.onnen.affichage;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Afficheur implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            int nbRepetitions = 1;
            boolean broadcast = true;
            StringBuilder bt = new StringBuilder();

            if (cmd.getName().equalsIgnoreCase("helloworld")) {
                System.out.println("Passage dans Hellword....(temp)");
                p.sendMessage("Salut " + p.getName() + " !");

                return true;
            }

            // Ne fonctionne pas encore tout à fait mais on y est presque...
            if (cmd.getName().equalsIgnoreCase("crier")) {
                System.out.println("Passage dans Crier....(temp)");

                for (int i = 0; i < args.length; i++) {
                    switch (args[i]) {
                        case "-r":
                            if (i+1== args.length) {
                                p.sendMessage("[Erreur] : Il manque un paramètre à l'option -r !");
                                return false;
                            }
                            nbRepetitions = Integer.valueOf(args[i+1]);
                            i++;
                            break;

                        case "-c":
                            broadcast = false;
                            break;

                        case "-m":
                            while(i+1 < args.length){
                                if (args[i+1].equals("-r") || args[i+1].equals("-c")){
                                    break;
                                }
                                bt.append(args[i+1] + " ");
                                i++;
                            }
                            break;

                        default:
                            p.sendMessage("[Erreur] : Options manquantes ou invalides");
                            return false;
                    }
                }

                String texte = bt.toString();

                if (broadcast == true) {
                    for (int e = 0; e < nbRepetitions; e++) {
                        Bukkit.broadcastMessage(p.getName() + "crie " + (e+1) + "fois : \"" + texte + "\"");
                    }
                } else {
                    for (int e = 0; e < nbRepetitions; e++) {
                        p.sendMessage("Vous chuchotez " + (e+1) + " fois : \"" + texte + "\"");
                    }
                }
                return true;
            }
        }

        return false;
    }
}