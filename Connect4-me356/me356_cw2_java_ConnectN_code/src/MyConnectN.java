import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*This is the connectN class for the Coursework. This is  the extended version and  can be played
 * with more than 2 players & play connect n
 *
 * This is the main game class, this creates new players and a new board. The main game class interacts with the others
 * to play the game.
 *
 * The main game class calls board methods to place counters, check the win conditions and print the board
 * This class also calls the player class to get its move, either for the computer or the human player
 * For a comp player, it gets a random possible move and plays it (it loops round till finding a correct move)
 * For a Human player, it asks for your input
 *
 * The game is in a while loop, it finishes when there is no possible moves, or when a player has won
 *
 * Happy playing!*/

public class MyConnectN {

    //The property of the board
    private Board board;
    private List<Player> players = new ArrayList<>();
    private Token_list token_list = new Token_list();
    private int num_comp_players;
    private int num_human_players;
    private int n_in_a_row;

    // Create a new board with these set dimensions (the print method can handle any size <10 without spacing issues) however)
    public MyConnectN(){
        int game_width = 7;
        int game_length = 6;
        num_human_players = 1;
        num_comp_players = 1;
        n_in_a_row = 4;
        board = new Board(game_length,game_width,n_in_a_row);
        playGame();
    }

    // For different arguments to make the game
    public MyConnectN(int in_game_width,int in_game_length,int in_num_human_players,
                         int in_num_comp_players, int in_n_in_a_row){
        num_human_players = in_num_human_players;
        num_comp_players = in_num_comp_players;
        n_in_a_row = in_n_in_a_row;
        board = new Board(in_game_length,in_game_width,n_in_a_row);
        playGame();
    }

    /*the MAin subroutine for the game. Loops round till one player wins or a draw*/
    private void playGame(){
        // Create the players to play the game
        for (int k=0;k<num_human_players;k++){
            players.add(new HumanPlayer(token_list.get_next_token()));
        }
        for (int k=0;k<num_comp_players;k++){
            players.add(new ComputerPlayer(token_list.get_next_token()));
        }
        int player_list_index = 0;
        Player current_player = players.get(player_list_index); //Set the current player

        System.out.println("Amount of players: " + players.size());
        System.out.println("Welcome to ConnectN, 'N' in this case is: '" + n_in_a_row + "'");
        System.out.println("There are " + players.size() + " players");
        System.out.println("Human Players: '" + num_human_players + "'. 'Bot' players: '" + num_comp_players + "'.");
        System.out.println("Players:");
        print_players();
        System.out.println("");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting '" + n_in_a_row + "' counters in a row - vertically, horizontally or diagonally");
        System.out.println("");
        board.printBoard(); //Print it
        boolean win = false; //Set the (If player has won flag) to false
        boolean valid_move_flag; // Check if the input is a valid move
        int move = -1; //Default never used value

        /* Loop round until a win (or draw) */
        while(!win){
            // IF you can play (if you cant, it's a draw)
            if (!board.canPlay()){
                System.out.println("Drawn Game");
                print_players();
                break;
            }
            valid_move_flag = false;

            //Get the user input (works for both human and comp players)
            while (!valid_move_flag){
                //Keep looping round until a valid move is entered (if a column is full, we cant place there)
                String userInput = current_player.getInput(); //This will always be numeric (decided from the player method)
                move = Integer.parseInt(userInput);
                //If its valid, set to true and move on
                if (board.check_valid_move(move)){
                    valid_move_flag = true;
                }else{
                    // Only print that its wrong when its a human
                    if (current_player.getPlayerType() == 'h'){
                        System.out.println("Please enter a valid move");
                    }
                }
            }

            //Place the current players counter where they decided to move
            board.placeCounter(current_player,move);
            board.printBoard();
            boolean hasWon = false;
            // check to see if this current player has won
            if (board.check_win(current_player)){
                //And print the board again (useful for when im checking for an obvious win)
                board.printBoard();
                hasWon = true;
            }

            if(hasWon){
                //Message the winner
                System.out.println("Player '" + current_player.getToken() + "' Has Won!!!");
                //print_players();
                win = true;
            }

            //If no one has won, we go to the next player.
            player_list_index++;
            if (player_list_index==players.size()) {
                player_list_index = 0;
            }
            current_player = players.get(player_list_index);

        }

    }

    private void print_players(){
        players.forEach( player_this -> {
                System.out.println("   player: '" + player_this.getToken() + "' Type: " + player_this.getPlayerType());
        });
    }




}
