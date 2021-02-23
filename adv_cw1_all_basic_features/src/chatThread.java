import javax.lang.model.type.NullType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class chatThread extends Thread{

    private Socket sock;
    private String clientName;
    public static ArrayList<String> message_list = new ArrayList<String>();
    public static ArrayList<String> message_list_client = new ArrayList<String>();
    private int lastest_message_printed_index = -1;
    private boolean isExit = false;
    private String latest_message = "";
    public static ArrayList<String> activeClientList = new ArrayList<String>();


    public chatThread(Socket sock,String clientName){
        this.sock = sock;
        this.clientName = clientName;
        activeClientList.add(clientName);
    }
    public synchronized int message_list_size (){
        return(message_list.size());
    }

    public synchronized void addMessageToList(String message){
        message_list.add(message);
    }

    public synchronized String getMessage(int index){
        return message_list.get(index);
    }



    public synchronized void addClientIdToList(String clientID){
        message_list_client.add(clientID);
    }

    public synchronized String getClientID(int index){
        return(message_list_client.get(index));
    }

    public synchronized int getLastMessageIndexFromClientID(String ClientID){
        for (int i = (message_list_size()-2) ;  i >= 0 ; i--){
            if (getClientID(i).equals(ClientID)){
                return(i);
            }
        }
        return -1;
    }

    public synchronized String get_list_of_messages(int index_from, int index_to){

        String messages = "";

        for (int i = index_from; i < index_to; i++){
            //messages = messages + getMessage(i) + "\r\n ";
            //String clientName = getClientID(i);
            messages = messages +  getClientID(i) + ": " + getMessage(i);
            if (i < (index_to - 1)){
                messages = messages + System.getProperty("line.separator");
            }
        }

        return messages;
    }

    public synchronized String build_echo_string(String clientName){
        int index_to = message_list_size()-1;
        int index_from = getLastMessageIndexFromClientID(clientName) + 1;

        if ((index_from == -1)|(index_to-index_from<=0)){
            return "";
        }

        String message_to_output = "";
        //message_to_output = message_to_output + "i: " + index_from + " j: " + index_to + " ";
        message_to_output = message_to_output + get_list_of_messages(index_from,index_to);
        return (message_to_output);
    }

    public synchronized String build_broadcast_message(String clientName){

        String current_last_message = getMessage(message_list_size()-1);
        int last_message_index_from_me = getLastMessageIndexFromClientID(clientName);

        if (last_message_index_from_me > 0) {
            String last_message_from_me = getMessage(getLastMessageIndexFromClientID(clientName));
            if (!current_last_message.equals(last_message_from_me)) {
                return current_last_message;
            }
        }
        return "";
    }

    public synchronized String simpleBroadcast(String clientName){
        String output = "";
        output = (message_list_size()-1) + ". [" + getClientID(message_list_size()-1) + "]: " + getMessage(message_list_size()-1);
        return output;
    }

    public synchronized String Broadcast(String ClientName){

        String current_message = getMessage(message_list_size()-1);
        if (current_message.startsWith("-private")){
            String[] splitString = current_message.split(" ");
            System.out.println("Split string length: " + splitString.length);
            String privateMessageReceiver = splitString[1];

            if (privateMessageReceiver.equals(ClientName)){
                //StringBuilder builder = new StringBuilder();
                StringBuilder rest_of_string = new StringBuilder();
                rest_of_string.append("~PRIVATE~").append(" ");
                for (int i = 2; i < splitString.length; i++){
                   rest_of_string.append(splitString[i]).append(" ");
                }
                return (rest_of_string.toString());
            }else{
             return("-1=doNotPrint");
            }
        }else{
            return(simpleBroadcast(ClientName));
        }
    }

    public void run(){


        Thread broadcastThread = new Thread() {

            public void run() {
                try (
                        PrintWriter serverResponse = new PrintWriter(sock.getOutputStream(), true);
                ) {
                    serverResponse.println("Welcome " + "[" + clientName + "]");
                    int message_printed_index = 0;
                    String broadcastMessage;
                    while (!isExit) {
                        if (message_list_size() > 0) {
                            if (message_printed_index < message_list_size()){
                                broadcastMessage = Broadcast(clientName);
                                if (!broadcastMessage.equals("-1=doNotPrint")){
                                    serverResponse.println(broadcastMessage);
                                }
                                message_printed_index++;
                            }
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        };
        broadcastThread.start();

        Thread clientHandler = new Thread(){

            public void run(){
                try(
                        BufferedReader userCLInput = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                ){
                    String client_message = userCLInput.readLine();
                    while (!isExit) {
                        if (client_message.equals("exit")){
                            break;
                        }
                        addMessageToList(client_message);
                        addClientIdToList(clientName);
                        System.out.println(clientName + " has received " + client_message + " list length: " + message_list_size());
                        client_message = userCLInput.readLine();
                    }
                }catch (IOException E){
                    E.printStackTrace();
                }
            }
        };
        clientHandler.start();
    }




}
