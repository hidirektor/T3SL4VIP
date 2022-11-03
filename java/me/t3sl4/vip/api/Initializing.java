package me.t3sl4.vip.api;

import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.runnable.RemovingRunnable;
import me.t3sl4.vip.util.GMHook;
import me.t3sl4.vip.util.MessageUtil;
import me.t3sl4.vip.util.SettingsManager;
import org.bukkit.Bukkit;

public class Initializing {
    private static T3SL4VIP plugin;
    private static GMHook groupManager;
    static SettingsManager manager = SettingsManager.getInstance();
    public static void startPlugin(T3SL4VIP instance) {
        plugin = instance;
        manager.setup(plugin);
        MessageUtil.loadMessages();
        groupManager = new GMHook(plugin);
        API.registerCommands();
        API.registerListener();
        API.scheduleSyncRepeatingTask(plugin, new RemovingRunnable(), 20L, 400L);
        Bukkit.getConsoleSender().sendMessage("   ");
        Bukkit.getConsoleSender().sendMessage("  ____   __   __  _   _   _____   _____   ____    _       _  _   ");
        Bukkit.getConsoleSender().sendMessage(" / ___|  \\ \\ / / | \\ | | |_   _| |___ /  / ___|  | |     | || |  ");
        Bukkit.getConsoleSender().sendMessage(" \\___ \\   \\ V /  |  \\| |   | |     |_ \\  \\___ \\  | |     | || |_ ");
        Bukkit.getConsoleSender().sendMessage("  ___) |   | |   | |\\  |   | |    ___) |  ___) | | |___  |__   _|");
        Bukkit.getConsoleSender().sendMessage(" |____/    |_|   |_| \\_|   |_|   |____/  |____/  |_____|    |_|  ");
        Bukkit.getConsoleSender().sendMessage("    ");
        Bukkit.getConsoleSender().sendMessage("T3SL4 Series: T3SL4VIP");
    }

    public static void stopPlugin(T3SL4VIP instance) {
        plugin = instance;
    }

    public static GMHook getGroupManager() {
        return groupManager;
    }
}
