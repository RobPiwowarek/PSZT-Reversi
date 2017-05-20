package network;
import game.board.Point;
import mvc.GameController;
import mvc.NetworkController;

import java.io.*;
import java.net.*;

public class NetworkManager
{
    private boolean isConnected = false;
    private int port;
    private String host;
    private boolean isServer;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;

    private NetworkController gameController;

    public NetworkManager(int port, String host, boolean isServer, NetworkController gameController)
    {
        this.port = port;
        this.host = host;
        this.isServer = isServer;
        this.gameController = gameController;
    }

    public void connect()
    {
        new GameServer().run();
    }

    public void sendMessage(int x, int y)
    {
        try
        {
            messageSender.sendMessage(x, y);
        }
        catch (IOException ignored)
        {

        }
    }

    private class GameServer extends Thread
    {
        public void run()
        {
            try
            {
                if (isServer)
                {
                    ServerSocket serverSocket = new ServerSocket(port);
                    serverSocket.setSoTimeout(200000);
                    clientSocket = serverSocket.accept();
                } 
                else
                {
                    clientSocket = new Socket(host, port);
                }

                messageSender = new MessageSender();
                messageReceiver = new MessageReceiver();
                messageReceiver.start();
            }
            catch (IOException ignored)
            {

            }
        }
    }

    private class MessageSender extends Thread
    {
        private ObjectOutputStream socketOut;
        
        public MessageSender() throws IOException
        {
            socketOut = new ObjectOutputStream(clientSocket.getOutputStream());
        }

        public void sendMessage(int x, int y) throws IOException
        {
            int ints[] = {x,y};
            socketOut.writeObject(ints);
        }
    }

    private class MessageReceiver extends Thread
    {
        private ObjectInputStream socketIn;

        public MessageReceiver() throws IOException
        {
            socketIn = new ObjectInputStream(clientSocket.getInputStream());
        }

        public void run()
        {
            try
            {
                Object object;
                while (true)
                {
                    while ((object = socketIn.readObject()) != null)
                    {

                        int position[] = (int[]) object;
                        gameController.enemyMove(new Point(position[0], position[1]));
                    }
                }
            }
            catch (IOException | ClassNotFoundException ignored)
            {

            }
        }
    }
}

