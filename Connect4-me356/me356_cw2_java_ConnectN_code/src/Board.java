import java.util.ArrayList;
import java.util.List;

/*This class does the bulk of the work.

    It handles:
        the board itself,
        checking for a win
        Checking for a valid move (including checking all columns)
        Placing a counter
        Checking if you can play
        Printing the board
        Getting the length and the width of the board itself.
        */
public class Board{

    //Initialise the board
    private char[][] board;
    private int win_n_in_row_condition;
    private List<Integer> i_co_ords = new ArrayList<>(); //For showing the winning counters - optional
    private List<Integer> j_co_ords = new ArrayList<>(); //For showing the winning counters - optional

    public Board(){
        //The default size board, connect4 to be played on
        board = new char[6][7];
        win_n_in_row_condition = 4;
    }

    //can give it an i and j if you would like, making whatever sized board you like.
    public Board(int i, int j,int n_in_a_row){
        board = new char[i][j];
        win_n_in_row_condition = n_in_a_row;
    }

    /*
     Checks to see if the users move is valid. This method is mainly used by the ConnectN Class.
    */
    public boolean check_valid_move(int position){
        //System.out.println("length: " + this.getLength() + " width: " + this.getWidth(1));
        //If within the bounds of the board
        if ((position > 0)&&(position<=this.getWidth(1))){
            //System.out.println(check_counters_in_column(position));
            // And the column isnt full
            if (check_counters_in_column(position) == this.getLength()){
                    return false;
            }
            return true;
        }else{
            return false;
        }
    }

    /*Checks the counters in the column specified, if its full, then we return false.*/
    private int check_counters_in_column(int position){
        int count = 0;
        // Loop round, checking for the empty character.
        for (int i=(this.getLength()-1);i>=0;i--){
            //System.out.println("i: " + i + " and thing here: " + board[i][position-1]);
            if (board[i][position-1] != '\0'){
                count++;
            }else{
                //If we find it, we leave the loop
                break;
            }
        }
        return count;
    };

    /*Place the players token in the place on the board provided.*/
    public void placeCounter(Player player, int position){
        boolean placed = false;
        //Start from the bottom of the board, working our way up
        for(int i=board.length-1; i>=0; i--){
            //System.out.println(i + " i & position: " + position);
            if(!placed){
                // If the current thing on the board is empty, we can replace it with the players token.
                if(board[i][position-1] == '\0'){
                    board[i][position-1] = player.getToken();
                    //System.out.println(board[i][position-1] + " i =  " + i + " position = " + (position-1));
                    placed = true;
                }
            }

        }
    }

    //Check to see if we can play (if we can't, its a draw), from the obliteration game win condition)
    public boolean canPlay(){
        for(int x=0; x<board.length; x++){
            for(int y=0; y<board[x].length; y++){
                if(board[x][y] == '\0'){
                    return true;
                }
            }
        }
        return false;
    }

    /* Checks the player and this board to see if they've won.
     *Does 4 main loops to achieve this, going horizontal, vertical, and each of the diagonals, \
     * checking for a win.
     *
     * Has the added option of highlighting the win on the board with 'O's if you want it too.
     * Just set:
     *  boolean make_win_obvious_flag = true;
     * */
    public boolean check_win(Player player){

        boolean make_win_obvious_flag = false;
        //System.out.println("Need in a row: " + win_n_in_row_condition);
        int count = 0;
        for(int i=0; i<board.length; i++){
            count=0;
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == player.getToken()){
                    count = count + 1;
                    if(count >= this.win_n_in_row_condition){
                        //System.out.println("horizontal"); //Print that its a horizontal win
                        if (make_win_obvious_flag) {
                            for (int l = 0; l < win_n_in_row_condition; l++) {
                                i_co_ords.add(i);
                                j_co_ords.add(j - l);
                            }
                            this.make_win_obvious();
                        }
                        return true;
                    }
                }
                else{
                    count = 0;
                }
            }

        }
        // check vertical (basically the i,j reverse of the above)
        count = 0;
        for(int i=0; i<board[0].length; i++){
            count=0;
            for(int j=0; j<board.length; j++){
                if(board[j][i] == player.getToken()){
                    count = count + 1;
                    if(count >= this.win_n_in_row_condition){
                        System.out.println("vertical: " + player.getToken());
                        if (make_win_obvious_flag) {
                            for (int l = 0; l < win_n_in_row_condition; l++) {
                                i_co_ords.add(j - l);
                                j_co_ords.add(i);
                            }
                            this.make_win_obvious();
                        }
                        return true;
                    }
                }else{
                    count = 0;
                }
            }
        }

        /*Check the ascending diagonals.
        * The slightly complex if statements are due to the fact that you only want to check
        * a certain amount of columns from the side (depending on what the win condition is)*/
        for (int i=(win_n_in_row_condition-1); i<(getLength()); i++){
            for (int j=0; j<(getWidth(1)-(win_n_in_row_condition-1)); j++){
                count = 1;
                for (int k=1;k<win_n_in_row_condition;k++){

                    if ((this.board[i][j] == player.getToken()) &&
                            (this.board[i-k][j+k] == player.getToken())){
                        count++;
                    }
                    if (count >= win_n_in_row_condition){
                        if (make_win_obvious_flag) {
                            for (int l = 0; l < win_n_in_row_condition; l++) {
                                i_co_ords.add(i - l);
                                j_co_ords.add(j + l);
                            }
                            this.make_win_obvious();
                        }
                        return true;
                    }
                }

            }
        }

        // descendingDiagonalCheck
        /*Check the descending Diagonals.
         * The slightly complex if statements are due to the fact that you only want to check
         * a certain amount of columns from the side (depending on what the win condition is)*/
        for (int i=(win_n_in_row_condition-1); i<(getLength()); i++){
            for (int j=(win_n_in_row_condition-1); j<getWidth(1); j++){
                //System.out.println("i: " + i + " j: " + j);
                count = 1;
                for (int k=1;k<win_n_in_row_condition;k++) {
//                    System.out.println("i: " + i + " j:" + j + " k: " + k + " width: " +
//                            getWidth(1) + " length: " + getLength());
                    if ((this.board[i][j] == player.getToken()) &&
                            (this.board[i-k][j-k] == player.getToken())){

                        count++;
                    }
                    if (count >= win_n_in_row_condition){
                        if (make_win_obvious_flag) {
                            for (int l = 0; l < win_n_in_row_condition; l++) {
                                i_co_ords.add(i - l);
                                j_co_ords.add(j - l);
                            }
                            this.make_win_obvious();
                        }
                        return true;
                    }
                }
                }
        }
        return false;
    }

    //Prints the board, can print any length board, not just the default size.
    public void printBoard(){
        //System.out.println("len i,j = " + (board.length-1) + " " + (board[2].length-1));
        //System.out.println("")
        for(int i=0; i<=board.length-1; i++){
            //System.out.print(i + " ");
            for(int j = 0; j<=board[i].length-1; j++){
                //If its not the empty char, then print it.
                if (board[i][j]!= '\0'){
                    System.out.print("| " + board[i][j] + " ");
                    // As we need to take into account the extra number in on the effective x axis
                    if (j > 8){System.out.print(" ");}
                }else{
                    System.out.print("|   ");
                    if (j > 8){System.out.print(" ");}
                }
            }
            System.out.println("|");
        }
        // Print the width of the board based on its width (rather than the default)
        //System.out.print("    ");
        System.out.print("  ");
        for (int j=1; j<=board[1].length; j++){
            System.out.print(j + "   ");
        }
        System.out.println();
    }

    // This is a testing function, to make the winners tokens all 'O', so i can see them easier.
    private void make_win_obvious(){
        //System.out.println(i1 + " " + j1);
        //System.out.println(i2 + " " + j2);
        //System.out.println(i3 + " " + j3);
        //System.out.println(i4 + " " + j4);
        for (int k=0;k<win_n_in_row_condition;k++){
            board[i_co_ords.get(k)][j_co_ords.get(k)] = 'O';
        }
/*        board[i1][j1] = 'O';
        board[i2][j2] = 'O';
        board[i3][j3] = 'O';
        board[i4][j4] = 'O';*/
    }

    // Get the length of the board
    public int getLength(){
        return board.length;
    }
    //Get the width of the board.
    public int getWidth(int i){
        return board[i].length;
    }

}