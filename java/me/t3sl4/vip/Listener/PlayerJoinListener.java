package me.t3sl4.vip.Listener;

import me.t3sl4.vip.runnable.RemovingRunnable;
import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.util.GMHook;
import me.t3sl4.vip.util.PlayerUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
   GMHook groupManager = T3SL4VIP.getGroupManager();
   SettingsManager manager = SettingsManager.getInstance();
   PlayerUtil putil = new PlayerUtil();

   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
      Player p = e.getPlayer();
      if (RemovingRunnable.derank.contains(p.getName())) {
         this.putil.deleteVIP(p);
         RemovingRunnable.derank.remove(p.getName());
      }

   }
}
