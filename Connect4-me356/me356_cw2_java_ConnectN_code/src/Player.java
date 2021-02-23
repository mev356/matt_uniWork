
import java.io.*;

/*The player class, an abstract superclass to both HumanPlayer and ComputerPlayer
 * This is abstract, as there's no players which could be this class, only human and comp ones*/
public abstract class Player {
    private char token;
    // Define its token
    public Player(char t){
        token = t;
    }

    // Get the input, abstract as this player will never play :'(
    public abstract String getInput();

    //Get the players token
    public char getToken(){
        return token;
    }

    // Get the player type, this will be overriden
    public abstract char getPlayerType();

    //Check to see if the input string is numeric, if its not, then its not a valid move (by definition)
    // The Human player uses this (its here as it could be useful elsewhere)
    public static boolean is_numeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            //System.out.println(str + " " + "F");
            return false;
        }
    }


}
