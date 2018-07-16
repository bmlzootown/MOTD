package ml.bmlzootown;

import ml.bmlzootown.listeners.ServerPing;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MOTD extends JavaPlugin {
    public static Plugin pl;
    public static String motd;
    private boolean autosave;
    private Logger log;

    @Override
    public void onEnable () {
        pl = this;
        log = getLogger();
        loadcfg();
        getServer().getPluginManager().registerEvents(new ServerPing(), this);
    }

    @Override
    public void onDisable () {
        savecfg();
    }

    private void loadcfg () {
        FileConfiguration cfg = getConfig();
        cfg.options().copyDefaults(true);
        motd = cfg.getString("motd");
        this.autosave = cfg.getBoolean("autosave");
    }

    private void savecfg () {
        FileConfiguration cfg = getConfig();
        cfg.set("motd", motd);
        saveConfig();
    }


    private String join (String joiner, String[] args) {
        String joined = "";
        for (int i = 0; i < args.length; i++) {
            boolean last = (i == (args.length-1));
            if (last) {
                joined += args[i];
            } else {
                joined += (args[i] + joiner);
            }
        }
        return joined;
    }

    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            sender.sendMessage(motd);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("setmotd") && args.length >= 1) {
            motd = join(" ", args);
            if (this.autosave) savecfg();
            return true;
        } else if (cmd.getName().equalsIgnoreCase("reloadmotd")) {
            reloadConfig();
            loadcfg();
            return true;
        } else {
            return false;
        }
    }

}


