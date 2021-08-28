package es.capitanpuerka.pcolors;

import java.sql.Statement;
import es.capitanpuerka.pcolors.mysql.MySQL;
import es.capitanpuerka.pcolors.placeholders.Placeholders;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import es.capitanpuerka.pcolors.inventory.MainInventory;
import org.bukkit.command.CommandExecutor;
import es.capitanpuerka.pcolors.commands.ColorCMD;
import java.sql.Connection;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main instance;
    public static Connection conn;
    
    public void onEnable() {
        (Main.instance = this).saveConfig();
        this.getCommand("color").setExecutor(new ColorCMD());
        this.getServer().getPluginManager().registerEvents(new MainInventory(), this);
        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholders().register();
            this.setupSQL();
        }
    }
    
    public static Main get() {
        return Main.instance;
    }
    
    public void setupSQL() {
        try {
            Main.conn = new MySQL().getConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
