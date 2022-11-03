package me.t3sl4.vip.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {
    void onCommand(CommandSender var1, Command var2, String var3, String[] var4);
}
