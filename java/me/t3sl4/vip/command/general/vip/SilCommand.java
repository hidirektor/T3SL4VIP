package me.t3sl4.vip.command.general.vip;

import me.t3sl4.vip.api.API;
import me.t3sl4.vip.command.CommandInterface;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SilCommand implements CommandInterface {
    private SettingsManager manager = SettingsManager.getInstance();

    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("t3sl4vip.remove")) {
            sender.sendMessage(MessageUtil.NOPERM);
        }
        if (args.length != 2) {
            sender.sendMessage(MessageUtil.USAGEREMOVE);
        }
        final String name = args[1];
        final Player player = Bukkit.getServer().getPlayer(name);
        if (player == null) {
            sender.sendMessage(MessageUtil.NOPLAYER.replace("%player%", name));
        }
        if (!API.putil.isVIP(player, name) || !this.manager.getFile("data").isConfigurationSection(player.getName())) {
            sender.sendMessage(MessageUtil.NOTVIP);
        }
        API.putil.deleteVIP(player);
        sender.sendMessage(MessageUtil.REMOVED.replace("%player%", player.getName()));
    }
}