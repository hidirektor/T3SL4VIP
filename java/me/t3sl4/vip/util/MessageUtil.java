package me.t3sl4.vip.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageUtil {
   static SettingsManager manager = SettingsManager.getInstance();
   public static String PREFIX;
   public static String TITLE;
   public static List<String> ITEMLORE;
   public static List<String> ITEMLORE2;
   public static String INFOPREFIX;
   public static String INFOCHECK;
   public static String INFOGIVE;
   public static String INFOREMOVE;
   public static String INFODISTRIBUTE;
   public static String INFOADD;
   public static String INFORELOAD;
   public static String DEFAULTRANK;
   public static List<String> RANKS;
   public static String NOPERM;
   public static String NOPLAYER;
   public static String NOVIPPLAYER;
   public static String NORANK;
   public static String ERRORUNDEFINED;
   public static String NOTVIP;
   public static String CONSOLE;
   public static String ALREADYVIP;
   public static String TIMETYPE;
   public static String VIPADD;
   public static String VIPGIVEN;
   public static String RELOAD;
   public static String REMOVED;
   public static String ADDEDHOUR;
   public static String ADDEDDAY;
   public static String USAGEADD;
   public static String USAGEGIVE;
   public static String USAGEDISTRIBUTE;
   public static String USAGEREMOVE;
   public static String NOTBOUGHT;
   public static String CACHE;
   public static String NOCACHED;
   public static String CMDMAIN;
   public static String CMDCHECK;
   public static String CMDGIVE;
   public static String CMDREMOVE;
   public static String CMDDISTRIBUTE;
   public static String CMDADD;
   public static String CMDRELOAD;

   public static void loadMessages() {
      PREFIX = colorize(manager.getConfig().getString("Prefix"));
      TITLE = colorize(manager.getConfig().getString("GUI.title"));
      ITEMLORE = colorizeList(manager.getConfig().getStringList("GUI.lore"));
      ITEMLORE2 = colorizeList(manager.getConfig().getStringList("GUI.lore2"));

      INFOPREFIX = colorize(manager.getConfig().getString("Info-Prefix"));
      INFOCHECK = colorize(manager.getConfig().getString("Info-Check"));
      INFOGIVE = colorize(manager.getConfig().getString("Info-Give"));
      INFOREMOVE = colorize(manager.getConfig().getString("Info-Remove"));
      INFODISTRIBUTE = colorize(manager.getConfig().getString("Info-Distribute"));
      INFOADD = colorize(manager.getConfig().getString("Info-Add"));
      INFORELOAD = colorize(manager.getConfig().getString("Info-Reload"));

      DEFAULTRANK = manager.getConfig().getString("Settings.DefaultRank");
      RANKS = manager.getConfig().getStringList("Settings.Ranks");
      NOPERM = PREFIX + colorize(manager.getConfig().getString("Messages.no-perm"));
      NOPLAYER = PREFIX + colorize(manager.getConfig().getString("Messages.no-player"));
      NOVIPPLAYER = PREFIX + colorize(manager.getConfig().getString("Messages.no-vip-player"));
      NORANK = PREFIX + colorize(manager.getConfig().getString("Messages.no-rank"));
      ERRORUNDEFINED = PREFIX + colorize(manager.getConfig().getString("Messages.error-undefined"));
      NOTVIP = PREFIX + colorize(manager.getConfig().getString("Messages.not-vip"));
      CONSOLE = PREFIX + colorize(manager.getConfig().getString("Messages.console-error"));
      ALREADYVIP = PREFIX + colorize(manager.getConfig().getString("Messages.already-vip"));
      TIMETYPE = PREFIX + colorize(manager.getConfig().getString("Messages.time-type"));
      VIPADD = PREFIX + colorize(manager.getConfig().getString("Messages.vip-add"));
      VIPGIVEN = PREFIX + colorize(manager.getConfig().getString("Messages.vip-given"));
      RELOAD = PREFIX + colorize(manager.getConfig().getString("Messages.reload"));
      REMOVED = PREFIX + colorize(manager.getConfig().getString("Messages.removed"));
      ADDEDHOUR = PREFIX + colorize(manager.getConfig().getString("Messages.vip-added-hour"));
      ADDEDDAY = PREFIX + colorize(manager.getConfig().getString("Messages.vip-added-day"));
      USAGEADD = PREFIX + colorize(manager.getConfig().getString("Usages.add"));
      USAGEGIVE = PREFIX + colorize(manager.getConfig().getString("Usages.give"));
      USAGEDISTRIBUTE = PREFIX + colorize(manager.getConfig().getString("Usages.distribute"));
      USAGEREMOVE = PREFIX + colorize(manager.getConfig().getString("Usages.remove"));
      NOTBOUGHT = PREFIX + colorize(manager.getConfig().getString("Messages.not-bought"));
      CACHE = PREFIX + colorize(manager.getConfig().getString("Messages.cached-player"));
      NOCACHED = PREFIX + colorize(manager.getConfig().getString("Messages.no-cached"));
      CMDMAIN = manager.getConfig().getString("Cmds.Ana_Komut");
      CMDCHECK = manager.getConfig().getString("Cmds.Check_Arg");
      CMDGIVE = manager.getConfig().getString("Cmds.Give_Arg");
      CMDREMOVE = manager.getConfig().getString("Cmds.Remove_Arg");
      CMDDISTRIBUTE = manager.getConfig().getString("Cmds.Distribute_Arg");
      CMDADD = manager.getConfig().getString("Cmds.Add_Arg");
      CMDRELOAD = manager.getConfig().getString("Cmds.Reload_Arg");
   }

   public static String colorize(String str) {
      return str.replace("&", "§");
   }

   public static List<String> colorizeList(List<String> str) {
      for(int x=0; x<str.size(); x++) {
         str.set(x, str.get(x).replace("&", "§"));
      }
      return str;
   }
}
