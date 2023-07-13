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

        // On commence par vérifier si ce qui entre la commande est bien un joueur
        if (sender instanceof Player) {
            // Si c'est le cas, on transforme celui qui a envoyé la commande en joueur
            Player p = (Player) sender;
            // Variable utilisée pour savoir combien de fois envoyer le message dans la commande /crier
            int nbRepetitions = 1;
            // Variable pour si broadcast ou chuchoter le message dans la commande /crier
            boolean broadcast = true;
            // Permet de concaténer les différents Strings dans la commande /crier
            StringBuilder bt = new StringBuilder();

            // On regarde si la commande est le /helloworld
            if (cmd.getName().equalsIgnoreCase("helloworld")) {
                // Si c'est bien la commande, on envoie un petit message au joueur
                p.sendMessage("Salut " + p.getName() + " !");

                return true;
            }

            // On regarde si la commmande est le /crier
            if (cmd.getName().equalsIgnoreCase("crier")) {
                // On parcours le tableau d'arguments passé à la commande
                for (int i = 0; i < args.length; i++) {
                    // On regarde quel est l'argument actuel
                    switch (args[i]) {
                        // Option qui permet d'indiquer le nombre de répétitions du message
                        case "-r":
                            // On vérifie qu'il y a bien quelque chose après le -r
                            if (i+1== args.length) {
                                p.sendMessage("[Erreur] : Il manque un paramètre à l'option -r !");
                                return false;
                            }
                            // Si il y a bien quelque chose, on met à jour la variable nbRepetitions
                            nbRepetitions = Integer.valueOf(args[i+1]);
                            // On passe à l'argument suivant
                            i++;
                            break;
                        // Option qui permet de spécifier si on envoie en broadcast ou non. Par défaut, c'est du broadcast
                        case "-c":
                            broadcast = false;
                            break;
                        // Option (obligatoire) qui permet de rentrer le message à envoyer
                        case "-m":
                            // Tant que l'on arrive pas à la fin du tableau, on parcours les arguments
                            while(i+1 < args.length){
                                // Si on a l'option -r ou -c, il ne s'agit plus du message à envoyer alors on quitte le while
                                if (args[i+1].equals("-r") || args[i+1].equals("-c")){
                                    break;
                                }
                                // On ajoute l'argument actuel au message à envoyer
                                bt.append(args[i+1] + " ");
                                // On passe à l'argument suivant
                                i++;
                            }
                            break;

                        default:
                            p.sendMessage("[Erreur] : Options manquantes ou invalides");
                            return false;
                    }
                }
                // On transforme le message à envoyer en un String unique
                String texte = bt.toString();
                // On regarde si c'est du broadcast
                if (broadcast == true) {
                    // On envoie le message selon le nombre de répétitions spécifiées
                    for (int e = 0; e < nbRepetitions; e++) {
                        Bukkit.broadcastMessage(p.getName() + " crie " + (e+1) + " fois : \"" + texte + "\"");
                    }
                } else { // Si c'est pas du broadcast, on chuchote
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