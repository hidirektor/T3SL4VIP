package me.t3sl4.vip.runnable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.api.API;
import me.t3sl4.vip.api.Initializing;
import me.t3sl4.vip.util.GMHook;
import me.t3sl4.vip.util.PlayerUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RemovingRunnable extends BukkitRunnable {
   SettingsManager manager = SettingsManager.getInstance();
   GMHook groupManager = Initializing.getGroupManager();
   public static ArrayList<String> derank = new ArrayList();
   PlayerUtil putil = new PlayerUtil();

   public void run() {
      Date date = new Date();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      OfflinePlayer[] var3 = Bukkit.getOfflinePlayers();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         OfflinePlayer op = var3[var5];
         if (this.manager.getFile("data").contains(op.getName())) {
            String time_check = this.manager.getFile("data").getString(op.getName() + ".Bitis");
            String time = dateFormat.format(date);
            Player p = op.getPlayer();
            if (p != null && (time.equalsIgnoreCase(time_check) || this.putil.getRemainingTime(p) < 0)) {
               String name = op.getName();
               derank.add(name);
               this.manager.getFile("data").set(op.getName(), (Object)null);
               this.manager.saveAllFiles();
            }
         }
      }
   }
}
