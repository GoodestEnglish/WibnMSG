package rip.diamond.wibnmsg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "指令用法: /" + label + " <玩家名稱> <msg>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "指定玩家處於離線狀態");
            return false;
        }

        if (sender == target) {
            sender.sendMessage(ChatColor.RED + "你不能私訊給自己");
            return false;
        }

        String message = buildMessage(args);

        sender.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "你偷偷發送給 " + target.getName() + " » " + message);
        target.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + sender.getName() + "偷偷告訴你 » " + message);

        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission(WibnMSG.PERMISSION)) {
                staff.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "S" + ChatColor.GRAY + "] " + sender.getName() + " 偷偷發送 " + target.getName() + " » "  + message);
            }
        }

        return true;
    }

    protected String buildMessage(String[] args) {
        if (1 >= args.length) {
            return "";
        }
        return ChatColor.stripColor(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
    }
}
