package model;

import java.util.Observable;

public class Connection extends Observable{

    private int port;
    private String ip;
    private boolean connected = false;

    private static Connection connection = null;

    private Connection(){
        port = 6000;
        ip = "192.168.1.100";
    }

    public static Connection getConnection() {
        if(connection == null){
            connection = new Connection();
        }
        return connection;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        setChanged();
        notifyObservers();
    }
}
