package es.capitanpuerka.pcolors.placeholders;

import es.capitanpuerka.pcolors.player.ColoredPlayer;
import org.bukkit.entity.Player;
import es.capitanpuerka.pcolors.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholders extends PlaceholderExpansion {

    public void load() {
        this.register();
    }
    
    public boolean persist() {
        return true;
    }
    
    public boolean canRegister() {
        return true;
    }
    
    public String getIdentifier() {
        return "puerkascolor";
    }
    
    public String getAuthor() {
        return Main.get().getDescription().getAuthors().toString();
    }
    
    public String getVersion() {
        return Main.get().getDescription().getVersion();
    }
    
    public String onPlaceholderRequest(final Player player, final String identifier) {
        if (player == null) {
            return "";
        }
        if (identifier.equalsIgnoreCase("color")) {
            return String.valueOf(ColoredPlayer.getPlayer(player.getName()).getColor());
        }
        return null;
    }
}
