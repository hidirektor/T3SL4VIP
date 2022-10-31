package me.t3sl4.vip.util;

import java.io.File;
import java.io.IOException;

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

   private Plugin p;

   private FileConfiguration config;
   private File configFile;

   private FileConfiguration data;
   private File dFile;

   private FileConfiguration cache;
   private File cacheFile;

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

   public FileConfiguration getData() {
      return data;
   }

   public void saveData() {
      try {
         data.save(dFile);
      }
      catch (IOException e) {
         Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
      }
   }

   public void reloadData() {
      data = YamlConfiguration.loadConfiguration(dFile);
   }

   public FileConfiguration getCache() {
      return cache;
   }

   public void saveCache() {
      try {
         cache.save(cacheFile);
      }
      catch (IOException e) {
         Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save cache.yml!");
      }
   }

   public void reloadCache() {
      cache = YamlConfiguration.loadConfiguration(cacheFile);
   }

   public FileConfiguration getConfig() {
      return config;
   }

   public void saveConfig() {
      try {
         config.save(configFile);
      }
      catch (IOException e) {
         Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml ve gui.yml!");
      }
   }

   public void reloadConfig() {
      config = YamlConfiguration.loadConfiguration(configFile);
   }

   public PluginDescriptionFile getDesc() {
      return p.getDescription();
   }
}