package me.t3sl4.vip.command;

import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {
    private HashMap<String, CommandInterface> args = new HashMap<>();
    private String commandLabel;
    private StringBuilder helpMessage = this.a();

    public CommandHandler(String commandLabel) {
        this.commandLabel = commandLabel;
    }

    public void addArgs(String args, CommandInterface arg) {
        this.args.put(args, arg);
    }

    private boolean exists(String args) {
        return this.args.containsKey(args);
    }

    private CommandInterface getExecutor(String args) {
        return (CommandInterface)this.args.get(args);
    }

    public void build() {
        T3SL4VIP.getPlugin().getCommand(this.commandLabel).setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length == 0) {
            if(sender.isOp()) {
                sender.sendMessage(this.helpMessage.toString());
            } else {
                sender.sendMessage(MessageUtil.INFOPREFIX);
                sender.sendMessage(MessageUtil.INFOCHECK);
            }
        }
        if(args.length <= 0) {
            return false;
        } else if(!sender.isOp()) {
            sender.sendMessage(MessageUtil.NOPERM);
            return false;
        } else if(this.exists(args[0])) {
            this.getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
            return true;
        } else {
            sender.sendMessage(this.helpMessage.toString());
            return false;
        }
    }

    private StringBuilder a() {
        this.helpMessage = new StringBuilder();
        this.helpMessage.append(MessageUtil.INFOPREFIX).append("\n");
        this.helpMessage.append(MessageUtil.INFOCHECK).append("\n");
        this.helpMessage.append(MessageUtil.INFOGIVE).append("\n");
        this.helpMessage.append(MessageUtil.INFOREMOVE).append("\n");
        this.helpMessage.append(MessageUtil.INFODISTRIBUTE).append("\n");
        this.helpMessage.append(MessageUtil.INFOADD).append("\n");
        this.helpMessage.append(MessageUtil.INFORELOAD);
        return this.helpMessage;
    }
}
