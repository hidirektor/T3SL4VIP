package me.t3sl4.vip.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import me.t3sl4.vip.T3SL4VIP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtil {
   SettingsManager manager = SettingsManager.getInstance();
   GMHook groupManager = T3SL4VIP.getGroupManager();

   public boolean isVIP(Player p) {
      return this.manager.getData().isConfigurationSection(p.getName());
   }

   public Integer getRemainingTime(Player p) {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      String endTime = this.manager.getData().getString(p.getName() + ".Bitis");
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
      this.manager.getData().createSection(p.getName() + "");
      this.manager.getData().set(p.getName() + ".MevcutRank", type);
      this.manager.getData().set(p.getName() + ".Bitis", output_time);
      this.manager.saveData();
      String command = this.manager.getConfig().getString("Settings.GlobalCommands.give");
      command = command.replace("%player%", p.getName()).replace("%VIP_Turu%", type);
      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
   }

   public void deleteVIP(Player player) {
      String command = this.manager.getConfig().getString("Settings.GlobalCommands.take-back").replace("%player%", player.getName());
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
      this.manager.getData().set(player.getName(), (Object)null);
      this.manager.saveData();
   }

   public void addTime(Player player, int time, String type) {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      String bitis = this.manager.getData().getString(player.getName() + ".Bitis");
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

      this.manager.getData().set(player.getName() + ".Bitis", dateFormat.format(cal.getTime()));
      this.manager.saveData();
   }

   public String getRank(Player p) {
      return this.groupManager.getGroup(p);
   }

   public String getBitis(Player p) {
      return this.manager.getData().getString(p.getName() + ".Bitis");
   }
}
