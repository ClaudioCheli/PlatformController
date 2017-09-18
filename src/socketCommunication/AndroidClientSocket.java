package socketCommunication;

import model.AppSystemInfo;
import model.Connection;
import model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AndroidClientSocket implements Runnable {

    private Connection connection;
    private static AndroidClientSocket clientSocket = null;

    private Socket client = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    private Thread clientSocketThread;

    private boolean send = false;


    private AndroidClientSocket() {
        connection = Connection.getConnection();
    }

    public static AndroidClientSocket getClient() {
        if (clientSocket == null) {
            clientSocket = new AndroidClientSocket();
        }
        return clientSocket;
    }

    public boolean connect() {
        try {
            client = new Socket(connection.getIp(), connection.getPort());
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            connection.setConnected(false);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            connection.setConnected(false);
            return false;
        }
        clientSocketThread = new Thread(clientSocket);
        clientSocketThread.start();
        connection.setConnected(true);
        return true;
    }

    public boolean disconnect() {
        clientSocketThread.interrupt();
        try {
            output.close();
            input.close();
            client.close();
            connection.setConnected(false);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (connection.isConnected()) {
                try {
                    read();
                } catch (IOException e) {
                    e.printStackTrace();
                    connection.setConnected(false);
                }
                if(send)
                    write();
            }
        }
        disconnect();
    }

    private void read() throws IOException{
        byte[] buffer = new byte[20];
        if (input.read(buffer, 0, buffer.length) != -1) {
            float xPos = ByteBuffer.wrap(buffer, 0, 4).order(ByteOrder.BIG_ENDIAN).getFloat();
            float yPos = ByteBuffer.wrap(buffer, 4, 4).order(ByteOrder.BIG_ENDIAN).getFloat();
            float speed = ByteBuffer.wrap(buffer, 8, 4).order(ByteOrder.BIG_ENDIAN).getFloat();
            float jumpSpeed = ByteBuffer.wrap(buffer, 12, 4).order(ByteOrder.BIG_ENDIAN).getFloat();
            int fps = ByteBuffer.wrap(buffer, 16, 4).order(ByteOrder.BIG_ENDIAN).getInt();
            Player.setxPos(xPos);
            Player.setyPos(yPos);
            Player.setSpeed(speed);
            Player.setJumpSpeed(jumpSpeed);
            Player.setFPS(fps);
            AppSystemInfo.setFPS(fps);
        }
    }

    private void write() {

    }
}
