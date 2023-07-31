package rip.diamond.wibnmsg;

import org.bukkit.plugin.java.JavaPlugin;

public final class WibnMSG extends JavaPlugin {

    public static String PERMISSION = "wibnmsg.staff";

    @Override
    public void onEnable() {
        this.getCommand("message").setExecutor(new MessageCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
