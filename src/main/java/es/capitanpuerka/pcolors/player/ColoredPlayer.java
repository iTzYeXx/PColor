package es.capitanpuerka.pcolors.player;

import java.util.HashMap;

public class ColoredPlayer
{
    private static HashMap<String, ColoredPlayer> players;
    private String name;
    private String color;
    
    static {
        ColoredPlayer.players = new HashMap<String, ColoredPlayer>();
    }
    
    public ColoredPlayer(final String player) {
        this.name = player;
        this.color = "§7";
        ColoredPlayer.players.put(this.name, this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(final String s) {
        this.color = "§" + s;
    }
    
    public String getSimpleColor() {
        return this.color.substring(1);
    }
    
    public static ColoredPlayer getPlayer(final String name) {
        ColoredPlayer cp = null;
        if (!ColoredPlayer.players.containsKey(name)) {
            cp = new ColoredPlayer(name);
        }
        return ColoredPlayer.players.get(name);
    }
}
