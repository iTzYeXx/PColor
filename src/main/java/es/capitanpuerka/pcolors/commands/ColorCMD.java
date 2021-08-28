package es.capitanpuerka.pcolors.commands;

import org.bukkit.ChatColor;
import es.capitanpuerka.pcolors.inventory.MainInventory;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class ColorCMD implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command arg1, final String arg2, final String[] arg3) {
        if (!(sender instanceof Player)) {
            return true;
        }
        final Player p = (Player)sender;
        if (p.hasPermission("puerkascolors.open.command")) {
            MainInventory.get().open(p);
        }
        else {
            p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
        }
        return false;
    }
}
