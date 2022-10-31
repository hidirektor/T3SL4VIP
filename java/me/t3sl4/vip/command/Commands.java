package me.t3sl4.vip.command;

import me.t3sl4.vip.util.GuiManager;
import me.t3sl4.vip.util.PlayerUtil;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import me.t3sl4.vip.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.List;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor
{
   SettingsManager manager;

   public Commands() {
      this.manager = SettingsManager.getInstance();
   }

   public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
      final PlayerUtil putil = new PlayerUtil();
      if (cmd.getName().equalsIgnoreCase("tvip")) {
         if (args.length == 0) {
            if(sender.hasPermission("t3sl4vip.general")) {
               for ( String s : MessageUtil.INFO) {
                  sender.sendMessage(String.valueOf(s));
               }
            } else {
               sender.sendMessage(MessageUtil.INFO.get(0));
               sender.sendMessage(MessageUtil.INFO.get(1));
            }
            return true;
         }
         if (args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase("give")) {
            if (!sender.isOp() || !sender.hasPermission("t3sl4vip.give")) {
               sender.sendMessage(MessageUtil.NOPERM);
               return true;
            }
            if (args.length != 4) {
               sender.sendMessage(MessageUtil.USAGEGIVE);
               return true;
            }
            final String name = args[1];
            final Player player = Bukkit.getServer().getPlayer(name);
            if (player == null) {
               sender.sendMessage(MessageUtil.NOPLAYER.replace("%player%", name));
               return true;
            }
            final String type = args[2];
            if (!MessageUtil.RANKS.contains(type)) {
               sender.sendMessage(MessageUtil.NORANK.replace("%rank%", type));
               return true;
            }
            Integer time;
            try {
               time = Integer.parseInt(args[3]);
            }
            catch (Exception e) {
               sender.sendMessage(MessageUtil.ERRORUNDEFINED);
               return true;
            }
            if (putil.isVIP(player)) {
               sender.sendMessage(MessageUtil.ALREADYVIP.replace("%player%", player.getName()));
               return true;
            }
            putil.setGroup(player, type, time);
            final ArrayList<String> commands = (ArrayList<String>)this.manager.getConfig().getStringList("Settings.Commands." + type);
            for (String command : commands) {
               command = command.replace("/", "").replace("%p%", player.getName()).replace("%player%", player.getName());
               Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
            sender.sendMessage(MessageUtil.VIPADD.replace("%player%", name).replace("%sure%", String.valueOf(time)).replace("%rutbe%", type));
            player.sendMessage(MessageUtil.VIPGIVEN.replace("%sure%", String.valueOf(time)).replace("%rutbe%", type));
            return true;
         }
         else if (args[0].equalsIgnoreCase("ekle") || args[0].equalsIgnoreCase("add")) {
            if (!sender.isOp() || !sender.hasPermission("t3sl4vip.add")) {
               sender.sendMessage(MessageUtil.NOPERM);
               return true;
            }
            if (args.length != 4) {
               sender.sendMessage(MessageUtil.USAGEADD);
               return true;
            }
            final String name = args[1];
            final Player player = Bukkit.getServer().getPlayer(name);
            if (player == null) {
               sender.sendMessage(MessageUtil.NOVIPPLAYER.replaceAll("%player%", name));
               return true;
            }
            if (!putil.isVIP(player)) {
               sender.sendMessage(MessageUtil.NOTVIP);
               return true;
            }
            if (!args[3].equalsIgnoreCase("hour") && !args[3].equalsIgnoreCase("day")) {
               sender.sendMessage(MessageUtil.TIMETYPE);
               return true;
            }
            int time2;
            try {
               time2 = Integer.parseInt(args[2]);
            } catch(Exception e) {
               sender.sendMessage(MessageUtil.ERRORUNDEFINED);
               return true;
            }

            putil.addTime(player, time2, args[3]);
            return true;
         }
         else if (args[0].equalsIgnoreCase("dagit") || args[0].equalsIgnoreCase("distribute")) {
            if (!sender.isOp() || !sender.hasPermission("t3sl4vip.distribute")) {
               sender.sendMessage(MessageUtil.NOPERM);
               return true;
            }
            if (args.length != 3) {
               sender.sendMessage(MessageUtil.USAGEDISTRIBUTE);
               return true;
            }
            final String type2 = args[1];
            if (!MessageUtil.RANKS.contains(type2)) {
               sender.sendMessage(MessageUtil.NORANK.replaceAll("%rank%", type2));
               return true;
            }
            int sure;
            try {
               sure = Integer.parseInt(args[2]);
            }
            catch (Exception e3) {
               sender.sendMessage(MessageUtil.ERRORUNDEFINED);
               return true;
            }
            for (final Player p : Bukkit.getOnlinePlayers()) {
               if (putil.isVIP(p)) {
                  return true;
               }
               putil.setGroup(p, type2, sure);
               sender.sendMessage(MessageUtil.USAGEDISTRIBUTE);
            }
            return true;
         }
         else if (args[0].equalsIgnoreCase("sil") || args[0].equalsIgnoreCase("remove")) {
            if (!sender.isOp() || !sender.hasPermission("t3sl4vip.remove")) {
               sender.sendMessage(MessageUtil.NOPERM);
               return true;
            }
            if (args.length != 2) {
               sender.sendMessage(MessageUtil.USAGEREMOVE);
               return true;
            }
            final String name = args[1];
            final Player player = Bukkit.getServer().getPlayer(name);
            if (player == null) {
               sender.sendMessage(MessageUtil.NOPLAYER.replace("%player%", name));
               return true;
            }
            if (!putil.isVIP(player) || !this.manager.getData().isConfigurationSection(player.getName())) {
               sender.sendMessage(MessageUtil.NOTVIP);
               return true;
            }
            putil.deleteVIP(player);
            sender.sendMessage(MessageUtil.REMOVED.replace("%player%", player.getName()));
            return true;
         }
         else if (args[0].equalsIgnoreCase("sorgu") || args[0].equalsIgnoreCase("check")) {
            if (!(sender instanceof Player)) {
               sender.sendMessage(MessageUtil.CONSOLE);
               return true;
            }
            final Player player2 = (Player)sender;
            if(sender.hasPermission("t3sl4vip.check")) {
               if (!putil.isVIP(player2) || !this.manager.getData().isConfigurationSection(player2.getName())) {
                  player2.sendMessage(MessageUtil.NOTVIP.replace("%player%", player2.getName()));
                  return true;
               }
               new GuiManager(player2);
            } else {
               sender.sendMessage(MessageUtil.NOPERM);
            }
         }
         else {
            if (!args[0].equalsIgnoreCase("reload")) {
               sender.sendMessage(MessageUtil.ERRORUNDEFINED);
               return true;
            }
            if (!sender.isOp() || !sender.hasPermission("t3sl4vip.reload")) {
               sender.sendMessage(MessageUtil.NOPERM);
               return true;
            }
            manager.reloadConfig();
            manager.reloadData();
            MessageUtil.loadMessages();
            sender.sendMessage(MessageUtil.RELOAD);
            return true;
         }
      }
      return true;
   }
}
