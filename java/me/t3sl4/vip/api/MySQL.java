package me.t3sl4.vip.api;

import me.t3sl4.vip.T3SL4VIP;
import me.t3sl4.vip.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    private String host;
    private String database;
    private String user;
    private String password;
    private String port;
    private Connection con;
    private String queryString = "CREATE TABLE IF NOT EXISTS T3SL4VIPData (" +
            "playername VARCHAR(16), " +
            "vipbuycount INT(10), " +
            "viptype VARCHAR(16), " +
            "vipendtime VARCHAR(16), " +
            "oldrank VARCHAR(16))";

    public MySQL(String host, String database, String user, String password, String port) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void close() {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)T3SL4VIP.getPlugin(), () -> {
           try {
               if(this.con != null) {
                   this.con.close();
               }
           }catch (SQLException e) {
               e.printStackTrace();
           }
        });
    }

    public void closeInstantly() {
        if(this.con != null) {
            try {
                this.con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect() {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)T3SL4VIP.getPlugin(), () -> {
            if(this.con != null) {
                this.close();
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                this.con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.user, this.password);
            } catch(SQLException e) {
                e.printStackTrace();
            }

            new BukkitRunnable() {
                public void run() {
                    if(MySQL.this.isConnected()) {
                        System.out.println(MessageUtil.PREFIX + " Connected to MySQL database.");
                        MySQL.this.createTable();
                    }
                    if(!MySQL.this.isConnected()) {
                        System.out.println(MessageUtil.PREFIX + " could not connect to MySQL database :((((");
                    }
                }
            }.runTaskLater((Plugin)T3SL4VIP.getPlugin(), 60L);
        });
    }

    public void createTable() {
        try {
            this.getConnection().createStatement().executeUpdate(this.queryString);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.con;
    }

    public boolean isConnected() {
        try {
            return !this.getConnection().isClosed();
        } catch(Exception e) {
            return false;
        }
    }

    public ResultSet querySet(String query) {
        ResultSet executeQuery = null;
        try {
            executeQuery = this.con.createStatement().executeQuery(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return executeQuery;
    }

    public void update(String query) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)T3SL4VIP.getPlugin(), () -> {
           try {
               this.con.createStatement().executeUpdate(query);
           } catch(SQLException e) {
               e.printStackTrace();
           }
        });
    }
}
