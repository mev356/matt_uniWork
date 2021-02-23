class Main {
    public static void main(String[] args) {

        int i;
        int game_width;
        int game_length;
        int num_human_players;
        int num_comp_players;
        int n_in_a_row;

        //i = 2; // For Req 2
        //i = 4; // Req 4
        i = 6; // Bot Battle Royal
        switch (i) {
            // For requirement 2, the case of N=4 and 2 players
            case 2:
                new MyConnectN();
                break;
            // For Requirement 5, the case of 2<N<7 and 1 human and 2 comp players
            case 4:
                 game_width = 7;
                 game_length = 6;
                 num_human_players = 1;
                 num_comp_players = 2;
                 n_in_a_row = 3;
                //Throw the arguments into a new game
                new MyConnectN(game_width, game_length, num_human_players, num_comp_players, n_in_a_row);
                break;
            // For additional fun, letting the computers battle it out in a royal rumble
            // (On a slightly bigger board)
            case 6:
                game_width = 16;
                game_length = 14;
                num_human_players = 0;
                num_comp_players = 10;
                n_in_a_row = 3;
                new MyConnectN(game_width, game_length, num_human_players, num_comp_players, n_in_a_row);
                break;
            //Else (i know this is the same as case 2)
            default:
                new MyConnectN();
                break;
        }


    }
}