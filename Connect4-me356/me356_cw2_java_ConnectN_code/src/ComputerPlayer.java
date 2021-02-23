
import java.io.*;
import java.util.Random;

/* A child of the abstract player class. It chooses a random move to do.
* The board checks if its a computer and doesnt message when it gets the input wrong
*
* Only asks it to input again.*/
public class ComputerPlayer extends Player{

    private char token;
    private Random rand = new Random(); //For the move

    public ComputerPlayer(char t){
        super(t);
    }

    @Override
    public String getInput(){
    //public String getInput(Board board){

        /*is there a better way, rather than getting a random int 1-100, then asking it do it again and
         again until it gets something thats possible? */
        //int y = 100; //
        int i = rand.nextInt(100);
        return "" + i;

    }

    @Override
    public char getPlayerType(){
        return 'c';
    }

    // //boolean check_move_flag = false;
    //        //while (!check_move_flag){
    //            //int i = (int)((double)(board.getWidth(1)+1)*Math.random());
    //            //System.out.println(board.getWidth(1));
    //if (board.check_valid_move(i,this.getPlayerType())){
    //    check_move_flag = true;
    //}
    //}
    //return "-1";
}