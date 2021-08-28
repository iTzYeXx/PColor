package es.capitanpuerka.pcolors.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import es.capitanpuerka.pcolors.Main;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Database
{
    private static Database instance;
    
    public void insertValues(final UUID p) {
        try (PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO `puerkas_colors` (`uuid`, `color`) VALUES( '" + p.toString() + "', '7');")){
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setColor(final UUID p, final String color) {
        try (PreparedStatement statement = Main.conn.prepareStatement("UPDATE `puerkas_colors` SET `color` = '" + color + "' WHERE `uuid` = '" + p.toString() + "';")){
            statement.executeUpdate();
            Bukkit.getConsoleSender().sendMessage("PuerkasColor DEBUG Color actualizado en la DB:" + color);
        } catch (Exception e) {
        }
    }

    public boolean userExists(UUID uuid){
        try(PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM `puerkas_colors` WHERE `uuid`=?")){
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    
    public String getColor(final UUID p) {
        String i = "7";
        try(PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM `puerkas_colors` WHERE `uuid`=?")){
            statement.setString(1, p.toString());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                i = result.getString("color");
                Bukkit.getConsoleSender().sendMessage("PuerkasColor DEBUG Color cargado:" + i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return i;
        }
        return i;
    }
    
    public static Database get() {
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return Database.instance;
    }
}
