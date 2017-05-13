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

    public NetworkManager(int port, String host, boolean isServer)
    {
        this.port = port;
        this.host = host;
        this.isServer = isServer;
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
        catch (IOException e)
        {
            ;
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
            catch (IOException e)
            {
                ;
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
                Object object = null;
                while (true)
                {
                    while ((object = socketIn.readObject()) != null)
                    {
                        int position[] = (int[]) object;
                        System.out.println(position[0]+" "+position[1]);
                    }
                }
            }
            catch (IOException e)
            {
                ;
            }
            catch (ClassNotFoundException e)
            {
                ;
            }
        }
    }
}

