
import java.io.*;
/*Extends player, a child of player. Uses it's constructor, its getToken and its definition
* Different to the comp player, as it needs to get input from the player. */
public class HumanPlayer extends Player{

    // The reader and the token
    private BufferedReader in;
    private char token;

    //Its constructor
    public HumanPlayer(char t){
        super(t);
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    // Get the user input, make sure its a number, or tell em to do it again.
    @Override
    public String getInput(){
        //Board board
        boolean check_flag = false;
        String toReturn = null;
        while (!check_flag) {
            try {
                toReturn = in.readLine();
                if (is_numeric(toReturn)) {
                    check_flag = true;
                    return toReturn;
                } else {
                    System.out.println("Please enter a Valid move");
                }
            } catch (IOException e) {

            }
        }
        return toReturn;
    }

    // Say the player type is a human
    @Override
    public char getPlayerType(){
        return 'h';
    }

}