package me.t3sl4.vip.util;

import java.io.File;
import java.io.IOException;

import me.t3sl4.vip.T3SL4VIP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {

   private SettingsManager() { }

   static SettingsManager instance = new SettingsManager();

   public static SettingsManager getInstance() {
      return instance;
   }

   private FileConfiguration config, data, cache;
   private File configFile, dFile, cacheFile;

   public void setup(Plugin p) {
      configFile = new File(p.getDataFolder(), "config.yml");

      if (!p.getDataFolder().exists()) {
         p.getDataFolder().mkdir();
      }

      if(!configFile.exists()){
         p.saveDefaultConfig();
      }
      config = p.getConfig();


      dFile = new File(p.getDataFolder(), "data.yml");

      if (!dFile.exists()) {
         try {
            dFile.createNewFile();
         }
         catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
         }
      }
      data = YamlConfiguration.loadConfiguration(dFile);

      cacheFile = new File(p.getDataFolder(), "cache.yml");

      if (!cacheFile.exists()) {
         try {
            cacheFile.createNewFile();
         }
         catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create cache.yml!");
         }
      }
      cache = YamlConfiguration.loadConfiguration(cacheFile);
   }

   public FileConfiguration getFile(String fileName) {
      if(fileName.equalsIgnoreCase("data")) {
         return data;
      } else if(fileName.equalsIgnoreCase("cache")) {
         return cache;
      } else {
         return config;
      }
   }

   public void saveAllFiles() {
      try {
         data.save(dFile);
         cache.save(cacheFile);
         config.save(configFile);
      } catch(IOException e) {
         Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save files !");
      }
   }

   public void reloadAllFiles() {
      data = YamlConfiguration.loadConfiguration(dFile);
      cache = YamlConfiguration.loadConfiguration(cacheFile);
      config = YamlConfiguration.loadConfiguration(configFile);
   }
}