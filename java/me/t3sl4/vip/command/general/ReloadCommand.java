package me.t3sl4.vip.command.general;

import me.t3sl4.vip.command.CommandInterface;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandInterface {
    private SettingsManager manager = SettingsManager.getInstance();

    public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        manager.reloadConfig();
        manager.reloadData();
        MessageUtil.loadMessages();
        sender.sendMessage(MessageUtil.RELOAD);
    }
}
