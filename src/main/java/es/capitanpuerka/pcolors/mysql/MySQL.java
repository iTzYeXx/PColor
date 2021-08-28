package es.capitanpuerka.pcolors.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import es.capitanpuerka.pcolors.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {

    private HikariDataSource hikari;
    int port = Integer.valueOf("3306");
    protected static MySQL DB;

    public static MySQL getDB(){
        return DB;
    }

    public MySQL() throws SQLException {
        try{
            String ip;
            String database;
            String username;
            String password;
            ip = Main.get().getConfig().getString("database.hostname");
            port = Main.get().getConfig().getInt("database.port");
            database = Main.get().getConfig().getString("database.database");
            username = Main.get().getConfig().getString("database.username");
            password = Main.get().getConfig().getString("database.password");


            boolean ssl = false;
            int maxpool = 10;
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:mysql://" + ip + ":" + port + "/" + database + "?autoReconnect=true");
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
            hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            hikariConfig.addDataSourceProperty("characterEncoding", "utf8");
            hikariConfig.addDataSourceProperty("encoding", "UTF-8");
            hikariConfig.addDataSourceProperty("useUnicode", "true");
            hikariConfig.addDataSourceProperty("useSSL", ssl);
            hikariConfig.setMinimumIdle(1);
            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.setMaxLifetime(180000L);
            hikariConfig.setIdleTimeout(60000L);
            this.hikari = new HikariDataSource(hikariConfig);
            System.out.println();
            System.out.println("MySQL Conectado");
            System.out.println();
            Logger.getGlobal().setLevel(Level.OFF);
            checkTables();
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
                @Override
                public void run() {
                    Database.get().userExists(UUID.randomUUID());
                }
            }, 20 * 60 * 60L,  20 * 60 * 60L);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void checkTables() throws SQLException {
        try(PreparedStatement statement = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS  `puerkas_colors` (`uuid` VARCHAR(120), `color` VARCHAR(2));")){
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return this.hikari.getConnection();
    }

    public void close() throws SQLException {
        if (hikari.getConnection() != null) {
            hikari.getConnection().close();
        }else{
            System.out.println(" ");
            System.out.println("Can't close MySQL connection");
            System.out.println(" ");
        }
        if(!hikari.isClosed()){
            hikari.close();
        }else{
            System.out.println(" ");
            System.out.println("Can't close MySQL connection (1)");
            System.out.println(" ");
        }
    }

    public boolean isClosed(){
        return hikari.isClosed();
    }

}
