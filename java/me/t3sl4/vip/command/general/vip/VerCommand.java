package me.t3sl4.vip.command.general.vip;

import me.t3sl4.vip.api.API;
import me.t3sl4.vip.command.CommandInterface;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.PlayerUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VerCommand implements CommandInterface {
    private SettingsManager manager = SettingsManager.getInstance();

    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("t3sl4vip.give")) {
            sender.sendMessage(MessageUtil.NOPERM);
        }
        if (args.length != 4) {
            sender.sendMessage(MessageUtil.USAGEGIVE);
        }
        final String type = args[2];
        if (!MessageUtil.RANKS.contains(type)) {
            sender.sendMessage(MessageUtil.NORANK.replace("%rank%", type));
        }
        int time = 0;
        try {
            time = Integer.parseInt(args[3]);
        }
        catch (Exception e) {
            sender.sendMessage(MessageUtil.ERRORUNDEFINED);
        }
        final String name = args[1];
        final Player player = Bukkit.getServer().getPlayer(name);
        if (API.putil.isVIP(player, name)) {
            sender.sendMessage(MessageUtil.ALREADYVIP.replace("%player%", player.getName()));
        }
        if (player == null) {
            if(API.putil.isCached(name)) {
                sender.sendMessage(MessageUtil.NOCACHED.replace("%player%", name));
            } else {
                API.putil.setGroupNull(name, type, time);
                sender.sendMessage(MessageUtil.CACHE.replace("%player%", name));
            }
        } else {
            API.putil.setGroup(player, type, time);
            final ArrayList<String> commands = (ArrayList<String>)this.manager.getConfig().getStringList("Settings.Commands." + type);
            for (String command : commands) {
                command = command.replace("/", "").replace("%p%", player.getName()).replace("%player%", player.getName());
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
            sender.sendMessage(MessageUtil.VIPADD.replace("%player%", name).replace("%sure%", String.valueOf(time)).replace("%rutbe%", type));
            player.sendMessage(MessageUtil.VIPGIVEN.replace("%sure%", String.valueOf(time)).replace("%rutbe%", type));
        }
    }
}