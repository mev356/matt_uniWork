import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.logging.SocketHandler;

public class ChatClient {
    private String address;
    private int port;
    private int option_index = 0;
    private String[] option_1_choices = new String[] {"I ", " Just", "love ", "talking", "about", "numbers"};
    private String[] option_2_choices = new String[] {"negative ", " negative","negative ", "negative","negative","negative"};
    private String response;
    private Socket socket;

    public ChatClient(String address,int port){
        this.address = address;
        this.port  = port;
    }

    public void run()  {

        System.out.println("Connecting to " + address + " on port " + port);

        int option = 0;
        try {
            socket = new Socket(address, port);
        } catch (IOException E) {
            E.printStackTrace();
            System.exit(-1);
        }

        // System.out.println("Client connected to: " + address + " port: " + port);

        Thread inputThread = new Thread(){
            public void run(){
                try {
                    PrintWriter sendTo = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader cmd = new BufferedReader(new InputStreamReader(System.in));
                    boolean loop_flag = true;
                    String userInput;
                    while (loop_flag) {
                        if(option_index > (option_1_choices.length - 1)){
                            userInput = cmd.readLine();
                        } else if (option==1){
                            userInput = option_1_choices[option_index];
                            option_index++;
                            System.out.println(userInput);
                        }else if(option==2){
                            userInput = option_2_choices[option_index];
                            option_index++;
                            System.out.println(userInput);
                        }else{
                            userInput = cmd.readLine();
                        }
                        sendTo.println(userInput);
                        if (userInput.equals("EXIT")) {
                            break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    System.exit(-1);
                }
            }


        };
        inputThread.start();
        Thread messageThread = new Thread(){
            public void run(){
                try {
                    boolean ifExit = false;;
                    BufferedReader readFrom = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (!ifExit){
                        response = readFrom.readLine();
                        //if (!response.equals(null)) {
                        if (response.equals("EXIT")) {
                            System.exit(-1);
                        }
                        System.out.println(response);

                        //}
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        };
        messageThread.start();

    }
    //}

    public static void main(String[] args){
        //int option;
        String argFlag;
        String arg;
        String inpAddress = "localhost";
        int inpPort = 14001;
        int i = 0;

        if (args.length>0){
            while (i < args.length && args[i].startsWith("-")){
                argFlag = args[i];
                arg = args[i+1];
                System.out.println("using " + argFlag + "   " + arg);
                switch (argFlag){
                    case "-cca":
                        inpAddress = arg;
                        break;
                    case "-ccp":
                        inpPort = Integer.parseInt(arg);
                        break;
                }
                i = i + 2;
            }
        }

        ChatClient client = new ChatClient(inpAddress,inpPort);
        client.run();
    }
}
