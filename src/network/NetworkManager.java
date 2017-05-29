package network;

import game.board.Point;
import mvc.Controller;
import mvc.NetworkController;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {
    private boolean isConnected = false;
    private int port;
    private String host;
    private boolean isServer;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;

    private NetworkController gameController;
    private Controller controller;

    public NetworkManager(int port, String host, boolean isServer) {
        this.port = port;
        this.host = host;
        this.isServer = isServer;
    }

    public void setGameController(NetworkController gameController) {
        this.gameController = gameController;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void connect() {
        new GameServer().start();
    }

    public void sendMessage(int x, int y) {
        try {
            messageSender.sendMessage(x, y);
        } catch (IOException ignored) {

        }
    }

    private class GameServer extends Thread {
        public void run() {
            try {
                if (isServer) {
                    controller.setInfo("Waiting for opponent");
                    ServerSocket serverSocket = new ServerSocket(port);
                    serverSocket.setSoTimeout(200000);
                    clientSocket = serverSocket.accept();
                    controller.setInfo("Black turn");
                } else {
                    clientSocket = new Socket(host, port);
                }

                messageSender = new MessageSender();
                messageReceiver = new MessageReceiver();
                messageReceiver.start();
            } catch (IOException ignored) {
                JOptionPane.showMessageDialog(null, "Connection failed", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class MessageSender extends Thread {
        private ObjectOutputStream socketOut;

        public MessageSender() throws IOException {
            socketOut = new ObjectOutputStream(clientSocket.getOutputStream());
        }

        public void sendMessage(int x, int y) throws IOException {
            int ints[] = {x, y};
            socketOut.writeObject(ints);
        }
    }

    private class MessageReceiver extends Thread {
        private ObjectInputStream socketIn;

        public MessageReceiver() throws IOException {
            socketIn = new ObjectInputStream(clientSocket.getInputStream());
        }

        public void run() {
            try {
                Object object;
                while (true) {
                    while ((object = socketIn.readObject()) != null) {

                        int position[] = (int[]) object;
                        gameController.enemyMove(new Point(position[0], position[1]));
                    }
                }
            } catch (IOException | ClassNotFoundException ignored) {

            }
        }
    }
}

