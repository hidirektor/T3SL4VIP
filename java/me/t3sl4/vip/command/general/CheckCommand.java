package me.t3sl4.vip.command.general;

import me.t3sl4.vip.command.CommandInterface;
import me.t3sl4.vip.util.GuiManager;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckCommand implements CommandInterface {
    private SettingsManager manager = SettingsManager.getInstance();

    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.CONSOLE);
        }
        final Player player2 = (Player)sender;
        if(sender.hasPermission("t3sl4vip.check")) {
            new GuiManager(player2);
        } else {
            sender.sendMessage(MessageUtil.NOPERM);
        }
    }
}


