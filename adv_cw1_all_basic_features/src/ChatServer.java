import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ChatServer {
    private int port;
    private int clientNumber = 0;
    private ServerSocket socket;
    public static ArrayList<String> clientNameList = new ArrayList<String>();
    public static ArrayList<String> activeClientList = new ArrayList<String>();

    public ChatServer(){
        this.port = 14001;
    }

    public ChatServer(int port){
        this.port=port;
        addClientNames();
    }

    public synchronized void addClientNames(){
        clientNameList.add("Michael");
        clientNameList.add("Zack");
        clientNameList.add("Christina");
        clientNameList.add("Trezza");
        clientNameList.add("Shane-O");
        clientNameList.add("Ekp");
    }

    public synchronized String pickClientName(){
        Random rand = new Random();
        if (clientNameList.size() > 0){
            int randInt = (int) Math.floor(Math.random() * clientNameList.size());
            if (clientNameList.size() == 6){
                randInt = 3;
            }
            String picked_client = clientNameList.get(randInt);
            activeClientList.add(picked_client);
            clientNameList.remove(randInt);
            return picked_client;
        }else {
            return "Client_" + clientNumber;
        }
    }

    public void startServer(){

        boolean listeningFlag = true;

        Thread adminThread = new Thread() {
            public void run() {
                try (  BufferedReader cmd = new BufferedReader(new InputStreamReader(System.in))
                ) {
                    boolean isExited = false;
                    String adminMessage;
                    while (!isExited) {
                        adminMessage = cmd.readLine();
                        if (adminMessage.equals("EXIT")) {
                            System.out.println("Exiting the server, shutting down connections.");
                            isExited = true;
                            System.exit(-1);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        adminThread.start();

        try {
            // try {
            ServerSocket serverSock = new ServerSocket(port);
            System.out.println("Waiting for client on port " + serverSock.getLocalPort() + "...");

            while (listeningFlag) {
                Socket sock = serverSock.accept();
                String client_name = pickClientName();
                new chatThread(sock,pickClientName()).start();
                clientNumber++;
            }
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    //public static void Main(String[] args) {
    public static void main(String[] args){

        //int option;
        String argFlag;
        String arg;
        int inpPort = 14001;
        int i = 0;
        /*
        if (args.length != 1) {
            option = 0;
        }else{
            option = Integer.parseInt(args[0]);
        }
        */

        if (args.length>0){
            while (i < args.length && args[i].startsWith("-")){
                argFlag = args[i];
                arg = args[i+1];
                System.out.println("using " + argFlag + "   " + arg);
                switch (argFlag){
                    case "-csp":
                        inpPort = Integer.parseInt(arg);
                        break;
                }
                i = i + 2;
            }
        }


        //int portNumber = Integer.parseInt(args[0]);        // write your code here
        ChatServer server = new ChatServer(inpPort);
        server.startServer();
        /* System.out.println("t");
        EchoClient client = new EchoClient("localhost",chosen_port);
        client.run(); */
        // System.out.println("Hello World");
    }
}
