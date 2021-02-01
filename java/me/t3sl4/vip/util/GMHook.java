package me.t3sl4.vip.util;

import java.util.Arrays;
import java.util.List;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class GMHook implements Listener {
   private static GroupManager groupManager;
   private Plugin plugin;

   public GMHook(Plugin plugin) {
      this.plugin = plugin;
      Bukkit.getPluginManager().registerEvents(this, plugin);
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onPluginEnable(PluginEnableEvent event) {
      PluginManager pluginManager = this.plugin.getServer().getPluginManager();
      Plugin GMplugin = pluginManager.getPlugin("GroupManager");
      if (GMplugin != null && GMplugin.isEnabled()) {
         groupManager = (GroupManager)GMplugin;
      }

   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onPluginDisable(PluginDisableEvent event) {
      if (groupManager != null && event.getPlugin().getDescription().getName().equals("GroupManager")) {
         Bukkit.getLogger().info("Hadi görüşürüz group manager kaçar !");
         groupManager = null;
      }

   }

   public String getGroup(Player base) {
      AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
      return handler == null ? null : handler.getGroup(base.getName());
   }

   public boolean setGroup(Player base, String group) {
      OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
      if (handler == null) {
         return false;
      } else {
         handler.getUser(base.getName()).setGroup(handler.getGroup(group));
         return true;
      }
   }

   public List<String> getGroups(Player base) {
      AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
      return handler == null ? null : Arrays.asList(handler.getGroups(base.getName()));
   }

   public String getPrefix(Player base) {
      AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
      return handler == null ? null : handler.getUserPrefix(base.getName());
   }

   public String getSuffix(Player base) {
      AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
      return handler == null ? null : handler.getUserSuffix(base.getName());
   }

   public boolean hasPermission(Player base, String node) {
      AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
      return handler == null ? false : handler.has(base, node);
   }

   public static GroupManager getGroupManager() {
      return groupManager;
   }
}
