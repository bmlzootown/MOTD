package ml.bmlzootown.listeners;

import ml.bmlzootown.MOTD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPing implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPing(ServerListPingEvent event) {
        event.setMotd(MOTD.motd.replaceAll("&([0-9a-fk-o,r])", "§$1"));
    }

}
