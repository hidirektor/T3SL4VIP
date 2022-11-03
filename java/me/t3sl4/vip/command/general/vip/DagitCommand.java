package me.t3sl4.vip.command.general.vip;

import me.t3sl4.vip.api.API;
import me.t3sl4.vip.command.CommandInterface;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DagitCommand implements CommandInterface {
    private SettingsManager manager = SettingsManager.getInstance();

    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("t3sl4vip.distribute")) {
            sender.sendMessage(MessageUtil.NOPERM);
        }
        if (args.length != 3) {
            sender.sendMessage(MessageUtil.USAGEDISTRIBUTE);
        }
        final String type2 = args[1];
        if (!MessageUtil.RANKS.contains(type2)) {
            sender.sendMessage(MessageUtil.NORANK.replaceAll("%rank%", type2));
        }
        int sure = 0;
        try {
            sure = Integer.parseInt(args[2]);
        }
        catch (Exception e3) {
            sender.sendMessage(MessageUtil.ERRORUNDEFINED);
        }
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (API.putil.isVIP(p, p.getName())) {
            }
            API.putil.setGroup(p, type2, sure);
            sender.sendMessage(MessageUtil.USAGEDISTRIBUTE);
        }
    }
}
