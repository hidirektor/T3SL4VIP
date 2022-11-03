package me.t3sl4.vip.Listener;

import me.t3sl4.vip.api.API;
import me.t3sl4.vip.api.Initializing;
import me.t3sl4.vip.runnable.RemovingRunnable;
import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.util.GMHook;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.PlayerUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class PlayerJoinListener implements Listener {
   GMHook groupManager = Initializing.getGroupManager();
   SettingsManager manager = SettingsManager.getInstance();
   PlayerUtil putil = new PlayerUtil();

   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
      Player p = e.getPlayer();
      if (RemovingRunnable.derank.contains(p.getName())) {
         this.putil.deleteVIP(p);
         RemovingRunnable.derank.remove(p.getName());
      }

      if(this.manager.getCache().isConfigurationSection(p.getName())) {
         String type = this.manager.getCache().getString(p.getName() + ".MevcutRank");
         String command = this.manager.getConfig().getString("Settings.GlobalCommands.give");
         command = command.replace("%player%", p.getName()).replace("%VIP_Turu%", type);
         Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
         final ArrayList<String> commands = (ArrayList<String>)this.manager.getConfig().getStringList("Settings.Commands." + type);

         for (String commandSub : commands) {
            commandSub = commandSub.replace("/", "").replace("%p%", p.getName()).replace("%player%", p.getName());
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), commandSub);
         }
         this.manager.getData().createSection(p.getName() + "");
         this.manager.getData().set(p.getName() + ".MevcutRank", this.manager.getCache().getString(p.getName() + ".MevcutRank"));
         this.manager.getData().set(p.getName() + ".Bitis", this.manager.getCache().getString(p.getName() + ".Bitis"));
         this.manager.saveData();

         p.sendMessage(MessageUtil.VIPGIVEN.replace("%sure%", this.manager.getCache().getString(p.getName() + ".DayCount")).replace("%rutbe%", type));
         this.manager.getCache().set(p.getName(), (Object)null);
         this.manager.saveCache();
      }
   }
}
