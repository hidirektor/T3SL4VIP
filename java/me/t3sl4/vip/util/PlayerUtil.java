package me.t3sl4.vip.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.api.API;
import me.t3sl4.vip.api.Initializing;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtil {
   SettingsManager manager = SettingsManager.getInstance();
   GMHook groupManager = Initializing.getGroupManager();

   public boolean isVIP(Player p, String name) {
      if(p != null) {
         return this.manager.getFile("data").isConfigurationSection(p.getName());
      }
      return this.manager.getFile("data").isConfigurationSection(name) && this.manager.getFile("cache").isConfigurationSection(name);
   }

   public boolean isCached(String name) {
      return this.manager.getFile("cache").isConfigurationSection(name);
   }

   public Integer getRemainingTime(Player p) {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      String endTime = this.manager.getFile("data").getString(p.getName() + ".Bitis");
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(2, 1);

      Date endDate;
      try {
         endDate = dateFormat.parse(endTime);
      } catch (Exception var13) {
         return 0;
      }

      long diff = endDate.getTime() - date.getTime();
      Calendar calA = Calendar.getInstance();

      try {
         calA.setTime(dateFormat.parse(dateFormat.format(diff)));
      } catch (ParseException var12) {
         var12.printStackTrace();
         return 0;
      }

      long day = diff / 86400000L + 1L;
      return Math.toIntExact(day);
   }

   public void setGroup(Player p, String type, int time) {
      Date date = new Date();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(5, time);
      String output_time = dateFormat.format(cal.getTime());
      this.manager.getFile("data").createSection(p.getName() + "");
      this.manager.getFile("data").set(p.getName() + ".OldRank", getOldRank(p));
      this.manager.getFile("data").set(p.getName() + ".NextRank", type);
      this.manager.getFile("data").set(p.getName() + ".Bitis", output_time);
      this.manager.saveAllFiles();
      String command = this.manager.getFile("config").getString("Settings.GlobalCommands.give");
      command = command.replace("%player%", p.getName()).replace("%VIP_Turu%", type);
      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
   }

   public void setGroupNull(String pName, String type, int time) {
      Date date = new Date();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(5, time);
      String output_time = dateFormat.format(cal.getTime());
      this.manager.getFile("cache").createSection(pName + "");
      this.manager.getFile("cache").set(pName + ".MevcutRank", type);
      this.manager.getFile("cache").set(pName + ".Bitis", output_time);
      this.manager.getFile("cache").set(pName + ".DayCount", time);
      this.manager.saveAllFiles();

   }

   public void deleteVIP(Player player) {
      String command = this.manager.getFile("config").getString("Settings.GlobalCommands.take-back").replace("%player%", player.getName());
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
      this.manager.getFile("data").set(player.getName(), (Object)null);
      this.manager.saveAllFiles();
   }

   public void addTime(Player player, int time, String type) {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      String bitis = this.manager.getFile("data").getString(player.getName() + ".Bitis");
      Calendar cal = Calendar.getInstance();

      Date dateA;
      try {
         dateA = dateFormat.parse(bitis);
      } catch (ParseException e) {
         e.printStackTrace();
         return;
      }

      cal.setTime(dateA);

      if(type.equalsIgnoreCase("day")) {
         cal.add(5, time);
         player.sendMessage(MessageUtil.ADDEDDAY.replaceAll("%vip_ekle_gun%", String.valueOf(time)));
      } else if(type.equalsIgnoreCase("hour")) {
         cal.add(11, time);
         player.sendMessage(MessageUtil.ADDEDHOUR.replaceAll("%vip_ekle_saat%", String.valueOf(time)));
      }

      this.manager.getFile("data").set(player.getName() + ".Bitis", dateFormat.format(cal.getTime()));
      this.manager.saveAllFiles();
   }

   public String getRank(Player p) {
      if(GMHook.getGroupManager() == null) {
         if(isVIP(p, p.getName())) {
            return this.manager.getFile("data").getString(p.getName() + ".MevcutRank");
         } else {
            return this.manager.getFile("config").getString("Settings.DefaultRank");
         }
      }
      return this.groupManager.getGroup(p);
   }

   public String getOldRank(Player p) {
      //TODO
      //OldRank system
      if(MessageUtil.OLDRANKSYSTEM) {
         if(MessageUtil.GROUPMANAGERPLUGIN) {
            if(GMHook.getGroupManager() == null) {
               if(isVIP(p, p.getName())) {
                  return this.manager.getFile("data").getString(p.getName() + ".MevcutRank");
               } else {
                  return this.manager.getFile("data").getString("Settings.DefaultRank");
               }
            }
         } else if(MessageUtil.LUCKPERMSPLUGIN) {

         } else if(MessageUtil.ULTRAPERMISSIONSPLUGIN) {

         } else if(MessageUtil.LIGHTPERMPLUGIN) {

         } else if(MessageUtil.PERMISSIONSPLUSPLUGIN) {

         } else if(MessageUtil.GROUPMANAGERPLUGIN) {
            //TODO
            //Çoklu true için hata mesajı
         }
      }
      return MessageUtil.DEFAULTRANK;
   }

   public String getBitis(Player p) {
      return this.manager.getFile("data").getString(p.getName() + ".Bitis");
   }
}
