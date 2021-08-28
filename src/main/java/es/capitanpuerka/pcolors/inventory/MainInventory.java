
package es.capitanpuerka.pcolors.inventory;

import es.capitanpuerka.pcolors.Main;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import es.capitanpuerka.pcolors.mysql.Database;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import es.capitanpuerka.pcolors.player.ColoredPlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.lang.reflect.Field;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class MainInventory implements Listener
{
    private static MainInventory instance;
    HashMap<String, String> colors;
    
    public MainInventory() {
        (this.colors = new HashMap<String, String>()).put("§9Azul Oscuro§7 ", "7dd3ede0ad53768abdce493fbf3c2359dc87ec55d2fceeb17754ed590e41a");
        this.colors.put("§2Verde Oscuro§7 ", "78d58a7651fedae4c03efebc226c03fd791eb74a132babb974e8d838ac6882");
        this.colors.put("§bAqua§7 ", "83d1c463756ca33c9d6cebd2423795d8a4d87e7194b503579e8e11225166fe");
        this.colors.put("§cRed§7 ", "d2932b66decaeff6ebdc7c5be6b2467aa6f14b746388a06a2e1e1a8463e9e122");
        this.colors.put("§5Morado§7 ", "565e24eb655d507763254b4364e9fe3c36b7dba366c6347376f97bc97e5c0");
        this.colors.put("§6Naranja§7 ", "e79add3e5936a382a8f7fdc37fd6fa96653d5104ebcadb0d4f7e9d4a6efc454");
        this.colors.put("§9Azul Oscuro§7", "");
        this.colors.put("§9Azul Oscuro§7", "");
    }
    
    public void open(final Player p) {
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 27, "§aSeleccionar un color");
        inv.setItem(0, this.getSkull("§9Azul Oscuro", "7dd3ede0ad53768abdce493fbf3c2359dc87ec55d2fceeb17754ed590e41a", "§7Selecciona el azul oscuro", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(1, this.getSkull("§2Verde Oscuro", "78d58a7651fedae4c03efebc226c03fd791eb74a132babb974e8d838ac6882", "§7Selecciona el verde oscuro", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(2, this.getSkull("§bAqua", "83d1c463756ca33c9d6cebd2423795d8a4d87e7194b503579e8e11225166fe", "§7Selecciona el aqua", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(3, this.getSkull("§cRojo", "d2932b66decaeff6ebdc7c5be6b2467aa6f14b746388a06a2e1e1a8463e9e122", "§7Selecciona el rojo", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(4, this.getSkull("§5Morado", "565e24eb655d507763254b4364e9fe3c36b7dba366c6347376f97bc97e5c0", "§7Selecciona el morado", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(5, this.getSkull("§6Naranja", "e79add3e5936a382a8f7fdc37fd6fa96653d5104ebcadb0d4f7e9d4a6efc454", "§7Selecciona el naranja", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(6, this.getSkull("§7Gris", "2a17e97037ce353f85f5c65df435d29449a88da4442e4361cf99abbe1f892fb", "§7Selecciona el gris", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(7, this.getSkull("§8Negro", "967a2f218a6e6e38f2b545f6c17733f4ef9bbb288e75402949c052189ee", "§7Selecciona el negro", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(8, this.getSkull("§aVerde", "22d145c93e5eac48a661c6f27fdaff5922cf433dd627bf23eec378b9956197", "§7Selecciona el verde", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(9, this.getSkull("§dRosa", "73b0af83d0c728aeeca470f08a1d75f41cee253a3573ba4157ca2433e6c36", "§7Selecciona el rosa", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(10, this.getSkull("§eAmarillo", "143c79cd9c2d3187ea03245fe2128e0d2abbe7945214bc5834dfa403c134e27", "§7Selecciona el amarillo", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(11, this.getSkull("§fBlanco", "ad93117b9e180e0dc39e5e8a0508482cf1f60e446e022978fe0651a562a597f", "§7Selecciona el blanco", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(12, this.getSkull("§4Rojo Oscuro", "c47237437eef639441b92b217efdc8a72514a9567c6b6b81b553f4ef4ad1cae", "§7Selecciona el rojo oscuro", "§7para tener en tu nombre.", "§7", "§6Click para seleccionar!"));
        inv.setItem(22, this.getItem(Material.BOOK, 0, "§9Cerrar menu", "§7Click para cerrar", "§7el menu."));
        p.openInventory(inv);
    }
    
    public ItemStack getItem(final Material m, final int data, final String name, final String... lores) {
        final ItemStack item = new ItemStack(m, 1, (short)(byte)data);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        final List<String> colorlores = new ArrayList<String>();
        for (final String l : lores) {
            colorlores.add(ChatColor.translateAlternateColorCodes('&', l));
        }
        meta.setLore((List)colorlores);
        item.setItemMeta(meta);
        return item;
    }
    
    public ItemStack getSkull(final String name, final String url, final String... lore) {
        final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta meta = (SkullMeta)item.getItemMeta();
        final GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        final byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" + url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex1) {
            ex1.printStackTrace();
        }
        meta.setDisplayName(name);
        final ArrayList<String> color = new ArrayList<String>();
        for (final String b : lore) {
            color.add(ChatColor.translateAlternateColorCodes('&', b));
            meta.setLore((List)color);
        }
        item.setItemMeta((ItemMeta)meta);
        return item;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInvClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        final ColoredPlayer cp = ColoredPlayer.getPlayer(p.getName());
        if (p.isOp() && e.getInventory() == p.getInventory()) {
            return;
        }
        if (e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
            return;
        }
        e.setCancelled(true);
        final int slot = e.getRawSlot();
        final String title = e.getInventory().getTitle();
        if (title.equalsIgnoreCase("§aSeleccionar un color")) {
            if (slot == 22) {
                p.closeInventory();
            }
            else if (slot == 0) {
                if (!p.hasPermission("puerkascolor.darkblue")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §9Dark Blue§e!");
                cp.setColor("9");
                p.closeInventory();
            }
            else if (slot == 1) {
                if (!p.hasPermission("puerkascolor.darkgreen")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §2Dark Green§e!");
                cp.setColor("2");
                p.closeInventory();
            }
            else if (slot == 2) {
                if (!p.hasPermission("puerkascolor.aqua")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §bAquan§e!");
                cp.setColor("b");
                p.closeInventory();
            }
            else if (slot == 3) {
                if (!p.hasPermission("puerkascolor.red")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §cRojo§e!");
                cp.setColor("c");
                p.closeInventory();
            }
            else if (slot == 4) {
                if (!p.hasPermission("puerkascolor.morado")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §5Morado§e!");
                cp.setColor("5");
                p.closeInventory();
            }
            else if (slot == 5) {
                if (!p.hasPermission("puerkascolor.orange")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §6Naranja§e!");
                cp.setColor("6");
                p.closeInventory();
            }
            else if (slot == 6) {
                if (!p.hasPermission("puerkascolor.gris")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §7Gris§e!");
                cp.setColor("7");
                p.closeInventory();
            }
            else if (slot == 7) {
                if (!p.hasPermission("puerkascolor.negro")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §8Negro§e!");
                cp.setColor("8");
                p.closeInventory();
            }
            else if (slot == 8) {
                if (!p.hasPermission("puerkascolor.verde")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §aVerde§e!");
                cp.setColor("a");
                p.closeInventory();
            }
            else if (slot == 9) {
                if (!p.hasPermission("puerkascolor.rosa")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §dRosa§e!");
                cp.setColor("d");
                p.closeInventory();
            }
            else if (slot == 10) {
                if (!p.hasPermission("puerkascolor.amarillo")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §eAmarillo§e!");
                cp.setColor("e");
                p.closeInventory();
            }
            else if (slot == 11) {
                if (!p.hasPermission("puerkascolor.blanco")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §fBlanco§e!");
                cp.setColor("f");
                p.closeInventory();
            }
            else if (slot == 12) {
                if (!p.hasPermission("puerkascolor.darkred")) {
                    p.sendMessage(ChatColor.RED + "No tienes permisos para hacer esto.");
                    return;
                }
                p.sendMessage("§eHas seleccionado el color §4Rojo Oscuro§e!");
                cp.setColor("4");
                p.closeInventory();
            }
        }
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if(!Database.get().userExists(p.getUniqueId())) Database.get().insertValues(p.getUniqueId());
        final ColoredPlayer gp = ColoredPlayer.getPlayer(p.getName());
        Bukkit.getScheduler().runTaskAsynchronously(Main.get(), new Runnable() {
            @Override
            public void run() {
                gp.setColor(Database.get().getColor(p.getUniqueId()));
            }
        });
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        final ColoredPlayer gp = ColoredPlayer.getPlayer(p.getName());
        Bukkit.getScheduler().runTaskAsynchronously(Main.get(), new Runnable() {
            @Override
            public void run() {
                Database.get().setColor(p.getUniqueId(), gp.getSimpleColor());
            }
        });
    }
    
    @EventHandler
    public void onKick(final PlayerKickEvent e) {
        final Player p = e.getPlayer();
        final ColoredPlayer gp = ColoredPlayer.getPlayer(p.getName());
        Bukkit.getScheduler().runTaskAsynchronously(Main.get(), new Runnable() {
            @Override
            public void run() {
                Database.get().setColor(p.getUniqueId(), gp.getSimpleColor());
            }
        });
    }
    
    public static MainInventory get() {
        if (MainInventory.instance == null) {
            MainInventory.instance = new MainInventory();
        }
        return MainInventory.instance;
    }
}
