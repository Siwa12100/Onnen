package dev.siwa.onnen;
import dev.siwa.onnen.affichage.*; 

import org.bukkit.plugin.java.JavaPlugin;

public class Onnen extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Lancement du plugin Onnen ! ");
        getCommand("helloworld").setExecutor(new Afficheur());
        getCommand("crier").setExecutor(new Afficheur());
    }

    @Override
    public void onDisable() {
        System.out.println("Fermeture du plugin Onnen ! ");
    }
}
