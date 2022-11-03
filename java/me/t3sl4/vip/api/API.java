package me.t3sl4.vip.api;

import me.t3sl4.vip.Listener.InventoryClick;
import me.t3sl4.vip.Listener.PlayerJoinListener;
import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.command.CommandHandler;
import me.t3sl4.vip.command.general.CheckCommand;
import me.t3sl4.vip.command.general.ReloadCommand;
import me.t3sl4.vip.command.general.vip.DagitCommand;
import me.t3sl4.vip.command.general.vip.EkleCommand;
import me.t3sl4.vip.command.general.vip.SilCommand;
import me.t3sl4.vip.command.general.vip.VerCommand;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class API {

    public static PlayerUtil putil = new PlayerUtil();

    public static int scheduleSyncRepeatingTask(Plugin plugin, Runnable runnable, long delay, long period) {
        return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period).getTaskId();
    }

    public static void registerCommands() {
        CommandHandler handler = new CommandHandler(MessageUtil.CMDMAIN);
        handler.addArgs(MessageUtil.CMDCHECK, new CheckCommand());
        handler.addArgs(MessageUtil.CMDGIVE, new VerCommand());
        handler.addArgs(MessageUtil.CMDREMOVE, new SilCommand());
        handler.addArgs(MessageUtil.CMDDISTRIBUTE, new DagitCommand());
        handler.addArgs(MessageUtil.CMDADD, new EkleCommand());
        handler.addArgs(MessageUtil.CMDRELOAD, new ReloadCommand());
        handler.build();
    }

    public static void registerListener() {
        registerListeners(new PlayerJoinListener(), new InventoryClick());
    }

    private static void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach((listener) -> {
            Bukkit.getPluginManager().registerEvents(listener, T3SL4VIP.getPlugin());
        });
    }
}
