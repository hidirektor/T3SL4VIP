package me.t3sl4.vip;

import me.t3sl4.vip.Listener.InventoryClick;
import me.t3sl4.vip.Listener.PlayerJoinListener;
import me.t3sl4.vip.api.API;
import me.t3sl4.vip.api.Initializing;
import me.t3sl4.vip.command.CommandHandler;
import me.t3sl4.vip.command.general.ReloadCommand;
import me.t3sl4.vip.runnable.RemovingRunnable;
import me.t3sl4.vip.util.GMHook;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class T3SL4VIP extends JavaPlugin {

   public void onEnable() {
      Initializing.startPlugin(this);
   }

   public void onDisable() {
      Initializing.stopPlugin(this);
   }

   public static T3SL4VIP getPlugin() {
      return (T3SL4VIP)Bukkit.getPluginManager().getPlugin("T3SL4VIP");
   }
}
