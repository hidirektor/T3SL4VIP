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
   public static boolean OLDRANKSYSTEM;
   public static boolean GROUPMANAGERPLUGIN;
   public static boolean LUCKPERMSPLUGIN;
   public static boolean ULTRAPERMISSIONSPLUGIN;
   public static boolean LIGHTPERMPLUGIN;
   public static boolean PERMISSIONSPLUSPLUGIN;

   public static void loadMessages() {
      PREFIX = colorize(manager.getFile("config").getString("Prefix"));
      TITLE = colorize(manager.getFile("config").getString("GUI.title"));
      ITEMLORE = colorizeList(manager.getFile("config").getStringList("GUI.lore"));
      ITEMLORE2 = colorizeList(manager.getFile("config").getStringList("GUI.lore2"));

      INFOPREFIX = colorize(manager.getFile("config").getString("Info-Prefix"));
      INFOCHECK = colorize(manager.getFile("config").getString("Info-Check"));
      INFOGIVE = colorize(manager.getFile("config").getString("Info-Give"));
      INFOREMOVE = colorize(manager.getFile("config").getString("Info-Remove"));
      INFODISTRIBUTE = colorize(manager.getFile("config").getString("Info-Distribute"));
      INFOADD = colorize(manager.getFile("config").getString("Info-Add"));
      INFORELOAD = colorize(manager.getFile("config").getString("Info-Reload"));

      DEFAULTRANK = manager.getFile("config").getString("Settings.DefaultRank");
      RANKS = manager.getFile("config").getStringList("Settings.Ranks");
      NOPERM = PREFIX + colorize(manager.getFile("config").getString("Messages.no-perm"));
      NOPLAYER = PREFIX + colorize(manager.getFile("config").getString("Messages.no-player"));
      NOVIPPLAYER = PREFIX + colorize(manager.getFile("config").getString("Messages.no-vip-player"));
      NORANK = PREFIX + colorize(manager.getFile("config").getString("Messages.no-rank"));
      ERRORUNDEFINED = PREFIX + colorize(manager.getFile("config").getString("Messages.error-undefined"));
      NOTVIP = PREFIX + colorize(manager.getFile("config").getString("Messages.not-vip"));
      CONSOLE = PREFIX + colorize(manager.getFile("config").getString("Messages.console-error"));
      ALREADYVIP = PREFIX + colorize(manager.getFile("config").getString("Messages.already-vip"));
      TIMETYPE = PREFIX + colorize(manager.getFile("config").getString("Messages.time-type"));
      VIPADD = PREFIX + colorize(manager.getFile("config").getString("Messages.vip-add"));
      VIPGIVEN = PREFIX + colorize(manager.getFile("config").getString("Messages.vip-given"));
      RELOAD = PREFIX + colorize(manager.getFile("config").getString("Messages.reload"));
      REMOVED = PREFIX + colorize(manager.getFile("config").getString("Messages.removed"));
      ADDEDHOUR = PREFIX + colorize(manager.getFile("config").getString("Messages.vip-added-hour"));
      ADDEDDAY = PREFIX + colorize(manager.getFile("config").getString("Messages.vip-added-day"));
      USAGEADD = PREFIX + colorize(manager.getFile("config").getString("Usages.add"));
      USAGEGIVE = PREFIX + colorize(manager.getFile("config").getString("Usages.give"));
      USAGEDISTRIBUTE = PREFIX + colorize(manager.getFile("config").getString("Usages.distribute"));
      USAGEREMOVE = PREFIX + colorize(manager.getFile("config").getString("Usages.remove"));
      NOTBOUGHT = PREFIX + colorize(manager.getFile("config").getString("Messages.not-bought"));
      CACHE = PREFIX + colorize(manager.getFile("config").getString("Messages.cached-player"));
      NOCACHED = PREFIX + colorize(manager.getFile("config").getString("Messages.no-cached"));
      CMDMAIN = manager.getFile("config").getString("Cmds.Ana_Komut");
      CMDCHECK = manager.getFile("config").getString("Cmds.Check_Arg");
      CMDGIVE = manager.getFile("config").getString("Cmds.Give_Arg");
      CMDREMOVE = manager.getFile("config").getString("Cmds.Remove_Arg");
      CMDDISTRIBUTE = manager.getFile("config").getString("Cmds.Distribute_Arg");
      CMDADD = manager.getFile("config").getString("Cmds.Add_Arg");
      CMDRELOAD = manager.getFile("config").getString("Cmds.Reload_Arg");
      OLDRANKSYSTEM = manager.getFile("config").getBoolean("OldRankSystem.OldRank");
      GROUPMANAGERPLUGIN = manager.getFile("config").getBoolean("OldRankSystem.PermPlugin.GroupManager");
      LUCKPERMSPLUGIN = manager.getFile("config").getBoolean("OldRankSystem.PermPlugin.LuckPerms");
      ULTRAPERMISSIONSPLUGIN = manager.getFile("config").getBoolean("OldRankSystem.PermPlugin.UltraPermissions");
      LIGHTPERMPLUGIN = manager.getFile("config").getBoolean("OldRankSystem.PermPlugin.LightPerms");
      PERMISSIONSPLUSPLUGIN = manager.getFile("config").getBoolean("OldRankSystem.PermPlugin.PermissionsPlus");
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
