package me.t3sl4.vip.command.general.vip;

import me.t3sl4.vip.api.API;
import me.t3sl4.vip.command.CommandInterface;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EkleCommand implements CommandInterface {
    private SettingsManager manager = SettingsManager.getInstance();

    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("t3sl4vip.add")) {
            sender.sendMessage(MessageUtil.NOPERM);
        }
        if (args.length != 4) {
            sender.sendMessage(MessageUtil.USAGEADD);
        }
        final String name = args[1];
        final Player player = Bukkit.getServer().getPlayer(name);
        if (player == null) {
            sender.sendMessage(MessageUtil.NOVIPPLAYER.replaceAll("%player%", name));
        }
        if (!API.putil.isVIP(player, name)) {
            sender.sendMessage(MessageUtil.NOTVIP);
        }
        if (!args[3].equalsIgnoreCase("hour") && !args[3].equalsIgnoreCase("day")) {
            sender.sendMessage(MessageUtil.TIMETYPE);
        }
        int time2 = 0;
        try {
            time2 = Integer.parseInt(args[2]);
        } catch(Exception e) {
            sender.sendMessage(MessageUtil.ERRORUNDEFINED);
        }

        API.putil.addTime(player, time2, args[3]);
    }
}
