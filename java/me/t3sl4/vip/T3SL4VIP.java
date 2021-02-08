package me.t3sl4.vip;

import me.t3sl4.vip.Listener.InventoryClick;
import me.t3sl4.vip.Listener.PlayerJoinListener;
import me.t3sl4.vip.command.Commands;
import me.t3sl4.vip.runnable.RemovingRunnable;
import me.t3sl4.vip.util.GMHook;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T3SL4VIP extends JavaPlugin {
   public static String PREFIX;
   static GMHook groupManager;
   static Plugin plugin;
   SettingsManager manager = SettingsManager.getInstance();

   public void onEnable() {
      this.start();
      groupManager = new GMHook(this);
   }

   public static Plugin getPlugin() {
      return plugin;
   }

   public void start() {
      plugin = this;
      this.registerCommands();
      this.registerListeners();
      Bukkit.getConsoleSender().sendMessage("   ");
      Bukkit.getConsoleSender().sendMessage("  ____   __   __  _   _   _____   _____   ____    _       _  _   ");
      Bukkit.getConsoleSender().sendMessage(" / ___|  \\ \\ / / | \\ | | |_   _| |___ /  / ___|  | |     | || |  ");
      Bukkit.getConsoleSender().sendMessage(" \\___ \\   \\ V /  |  \\| |   | |     |_ \\  \\___ \\  | |     | || |_ ");
      Bukkit.getConsoleSender().sendMessage("  ___) |   | |   | |\\  |   | |    ___) |  ___) | | |___  |__   _|");
      Bukkit.getConsoleSender().sendMessage(" |____/    |_|   |_| \\_|   |_|   |____/  |____/  |_____|    |_|  ");
      Bukkit.getConsoleSender().sendMessage("    ");
	  Bukkit.getConsoleSender().sendMessage("T3SL4 Series: T3SL4VIP");
      manager.setup(this);
      MessageUtil.loadMessages();
      scheduleSyncRepeatingTask(this, new RemovingRunnable(), 20L, 400L);
   }

   public int scheduleSyncRepeatingTask(final Plugin plugin, final Runnable runnable, long delay, long period) {
      return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period).getTaskId();
   }

   public void registerCommands() {
      Bukkit.getPluginCommand("tvip").setExecutor(new Commands());
   }

   public void registerListeners() {
      Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
      Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
   }

   public static GMHook getGroupManager() {
      return groupManager;
   }
}
